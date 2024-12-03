package lld.kafka.producer;

import java.util.ArrayList;
import java.util.List;

import lld.kafka.TopicManger;
import lld.kafka.entities.Topic;

public class Producer  implements AbstractProducer{

    private List<Topic> topics;
    private TopicManger manager;
    private String producerName;
    public Producer(TopicManger manger,String name){
        this.manager = manger;
        this.topics = new ArrayList<>();
        this.producerName = name;
    }
    public String getProducerName(){
        return this.producerName;
    }
    @Override
    public void produceToTopic(Topic t){
        topics.add(t);
        t.addProducer(this);
    }
    @Override
    public void produceMessage(String message,String topicName) {
        // producer producing message to all the topics it is connected to
        System.out.println(getProducerName() + " writing message to topic "+ topicName);
        manager.publishMessage(topicName, message);
        
    }
    
}
