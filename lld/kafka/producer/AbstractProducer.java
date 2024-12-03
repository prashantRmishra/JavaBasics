package lld.kafka.producer;

import lld.kafka.entities.Topic;

public interface AbstractProducer {
    public void produceMessage(String m,String topicName);

    public void produceToTopic(Topic topic1);
}
