package lld.kafka;


import java.util.HashMap;
import java.util.Map;

import lld.kafka.entities.Message;
import lld.kafka.entities.Topic;

public class TopicManger {
    private Map<String,Topic> topics;

    public TopicManger(){
        topics = new HashMap<>();
    }

    public void addNewTopic(Topic t){
        this.topics.put(t.getTopicName(), t);
    }

    //producer part
    public void publishMessage(String topicName,String message){
        Topic topic = this.topics.get(topicName);
        if(topic!=null){
            topic.add(message);
        }
        
    }

    //consumer part
    public Message consumeFromTopic(String topicName) {
        return this.topics.get(topicName).read();
    }
}
