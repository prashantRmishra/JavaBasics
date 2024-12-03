package lld.kafka.consumer;

import java.util.ArrayList;
import java.util.List;

import lld.kafka.TopicManger;
import lld.kafka.entities.Message;
import lld.kafka.entities.Topic;

public class Consumer implements AbstractConsumer {
    
    private TopicManger manger;
    private List<Topic> consumerListeningToTopics;
    private String consumerName;
    public Consumer(TopicManger manger,String consumerName){
        this.manger = manger;
        this.consumerListeningToTopics = new ArrayList<>();
        this.consumerName = consumerName;
    }
    public String getConsumerName(){
        return this.consumerName;
    }

    @Override
    public void consumeMessage(String topicName){
        Message m = manger.consumeFromTopic(topicName);
        System.out.println(consumerName +" received "+ m);
    }

    @Override
    public void update(String topicName) {
        consumeMessage(topicName);
    }

    @Override
    public void consumerFromTopic(Topic t) {
        consumerListeningToTopics.add(t);
        t.addConsumer(this);
    }
}
