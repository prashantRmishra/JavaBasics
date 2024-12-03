package lld.kafka;

import lld.kafka.consumer.AbstractConsumer;
import lld.kafka.consumer.Consumer;
import lld.kafka.entities.Topic;
import lld.kafka.producer.AbstractProducer;
import lld.kafka.producer.Producer;

public class Driver {
    public static void main(String[] args) {
        Topic topic1 = new Topic("topic1");
        Topic topic2 = new Topic("topic2");

        TopicManger manger = new TopicManger();
        manger.addNewTopic(topic1);
        manger.addNewTopic(topic2);

        AbstractProducer producer1 = new Producer(manger,"producer 1");
        producer1.produceToTopic(topic1);
        producer1.produceToTopic(topic2);

        AbstractProducer producer2 = new Producer(manger,"producer 2");
        producer2.produceToTopic(topic1);
        producer2.produceToTopic(topic2);

        AbstractConsumer consumer1 = new Consumer(manger,"consumer 1");
        consumer1.consumerFromTopic(topic1);
        consumer1.consumerFromTopic(topic2);
        AbstractConsumer consumer2 = new Consumer(manger,"consumer 2");
        consumer2.consumerFromTopic(topic1);
        AbstractConsumer consumer3 = new Consumer(manger,"consumer 3");
        consumer3.consumerFromTopic(topic1);
        consumer3.consumerFromTopic(topic2);
        AbstractConsumer consumer4 = new Consumer(manger,"consumer 4");
        consumer4.consumerFromTopic(topic1);
        consumer4.consumerFromTopic(topic2);
        AbstractConsumer consumer5 = new Consumer(manger,"consumer 5");
        consumer5.consumerFromTopic(topic1);

        //publishing and consuming messages
        producer1.produceMessage("Message 1 ", topic1.getTopicName());
        producer1.produceMessage("Message 2 ", topic1.getTopicName());
        producer2.produceMessage("Message 3", topic1.getTopicName());
        producer1.produceMessage("Message 4 ", topic2.getTopicName());
        producer2.produceMessage("Message 5", topic2.getTopicName());


    }
}


/*
 * output:
 * 
producer 1 writing message to topic topic1
consumer 1 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
consumer 2 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
consumer 3 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
consumer 4 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
consumer 5 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
producer 1 writing message to topic topic1
consumer 1 received Message [key=NjSK, message=Message 2 , readTime=2024-12-03]
consumer 2 received Message [key=NjSK, message=Message 2 , readTime=2024-12-03]
consumer 3 received Message [key=NjSK, message=Message 2 , readTime=2024-12-03]
consumer 4 received Message [key=NjSK, message=Message 2 , readTime=2024-12-03]
consumer 5 received Message [key=NjSK, message=Message 2 , readTime=2024-12-03]
producer 2 writing message to topic topic1
consumer 1 received Message [key=rDmk, message=Message 3, readTime=2024-12-03]
consumer 2 received Message [key=rDmk, message=Message 3, readTime=2024-12-03]
consumer 3 received Message [key=rDmk, message=Message 3, readTime=2024-12-03]
consumer 4 received Message [key=rDmk, message=Message 3, readTime=2024-12-03]
consumer 5 received Message [key=rDmk, message=Message 3, readTime=2024-12-03]
producer 1 writing message to topic topic2
consumer 1 received Message [key=Hbae, message=Message 4 , readTime=2024-12-03]
consumer 3 received Message [key=Hbae, message=Message 4 , readTime=2024-12-03]
consumer 4 received Message [key=Hbae, message=Message 4 , readTime=2024-12-03]
producer 2 writing message to topic topic2
consumer 1 received Message [key=vaAU, message=Message 5, readTime=2024-12-03]
consumer 3 received Message [key=vaAU, message=Message 5, readTime=2024-12-03]
consumer 4 received Message [key=vaAU, message=Message 5, readTime=2024-12-03]

*/
