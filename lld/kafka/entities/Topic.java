package lld.kafka.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
    public Topic(String name){
        this.topicName =  name;
        queue = new LinkedList<>();
        read = new PriorityQueue<>((a,b)-> a.getReadTime().isBefore(b.getReadTime()) ? 1:0);
        consumers = new ArrayList<>();
        producers = new ArrayList<>();
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
    public Message read(){
        Message m = null;
        if(queue.isEmpty()) return null;
        queue.peek().incrementReadCount();

        if(queue.peek().getReadCount()==consumers.size()){
            m = queue.remove();
        }
        else{
            m = queue.peek();
        }
        m.setReadTime(LocalDate.now());
        read.add(m);// read and this can be removed once the persistent duration is reached
        return m;
    }

    public void addConsumer(AbstractConsumer c){
        this.consumers.add(c);
    }
    public void addProducer(AbstractProducer producer) {
        this.producers.add(producer);
    }

    public void add(String message){
        Message m = new Message(UniqueKeyGeneratorUtility.getUniqueKey(KafkaConfig.KEY_LEN), message);
        queue.add(m);
        //once new message is added consumers should be notified
        notifyConsumers();
    }
    public void notifyConsumers(){
        for(AbstractConsumer consumer : consumers){
            consumer.update(this.getTopicName());
        }
    }
}
