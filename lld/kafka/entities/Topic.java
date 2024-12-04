package lld.kafka.entities;


import java.time.LocalDate;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import lld.kafka.configs.KafkaConfig;
import lld.kafka.consumer.AbstractConsumer;
import lld.kafka.producer.AbstractProducer;
import lld.kafka.utilities.UniqueKeyGeneratorUtility;

public class Topic {
    private Queue<Message> queue;
    private PriorityQueue<Message> read;
    private String topicName;
    private List<AbstractConsumer> consumers;
    private List<AbstractProducer> producers;
    private final ReentrantLock consumerLock = new ReentrantLock();
    private final ReentrantLock producerLock = new ReentrantLock();
    public Topic(String name){
        this.topicName =  name;
        queue = new ConcurrentLinkedQueue<>();
        read = new PriorityQueue<>((a,b)-> a.getReadTime().isBefore(b.getReadTime()) ? 1:0);
        consumers = new CopyOnWriteArrayList<>();
        producers = new CopyOnWriteArrayList<>();
    }
    public String getTopicName(){
        return this.topicName;
    }

    //for the sake of simplicity we are going to assume that once the message are consumed they are deleted 
    //form the queue  i.e the persistent time is 0s
    public void removePersistedMessage(int threshold){
        //logic to remove entry after the threshold is reached
    }
    // once the message are read they are moved to persisted queue where they can be removed after some time of persistence
    public synchronized Message read(){
        Message m = null;
        if(queue.isEmpty()) return null;
        queue.peek().incrementReadCount();

        if(queue.peek().getReadCount()==consumers.size()){
            m = queue.remove();// Removes the message only when all consumers have read it
        }
        else{
            m = queue.peek();
        }
        m.setReadTime(LocalDate.now());
        read.add(m);// read and this can be removed once the persistent duration is reached
        return m;
    }

    /*FINE GRAINED CONTROL 
     * Locking for Fine-Grained Control If certain operations require stricter control 
     * (e.g., adding/removing consumers or producers), use ReentrantLock for fine-grained synchronization 
     * instead of synchronizing the entire method.
    */

    public void addConsumer(AbstractConsumer c){
        consumerLock.lock();
        try{
            this.consumers.add(c);
        }
        finally{
            consumerLock.unlock();
        }
    }
    public void addProducer(AbstractProducer producer) {
        producerLock.lock();
        try{
            this.producers.add(producer);
        }
        finally{
            producerLock.unlock();
        }
    }

    public synchronized void add(String message){
        Message m = new Message(UniqueKeyGeneratorUtility.getUniqueKey(KafkaConfig.KEY_LEN), message);
        queue.add(m);
        //once new message is added consumers should be notified
        notifyConsumers();
    }
    /*THREAD SAFE NOTIFICATION
     * notifyConsumers() might trigger multiple threads to consume messages simultaneously.
     *  Make sure this method does not cause deadlocks or race conditions
    */
    public void notifyConsumers(){
        for(AbstractConsumer consumer : consumers){
            new Thread(()-> consumer.update(this.getTopicName())).start();;
        }
    }
}
