package lld.kafka.consumer;

import lld.kafka.entities.Topic;

public interface AbstractConsumer {
    public void consumeMessage(String topicName);

    public void update(String topic);

    public void consumerFromTopic(Topic topic1);
}
