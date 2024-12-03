
# Kafka-like Low-Level Design

## Overview
This repository implements a simplified version of a Kafka-like messaging system. The design includes producers, consumers, topics, and messages, and demonstrates how these components interact in a typical publish-subscribe model. The system allows multiple producers to write messages to topics and multiple consumers to read from those topics.

## Structure
The system consists of several key components:
- **Producer**: A producer writes messages to topics.
- **Consumer**: A consumer reads messages from topics.
- **Topic**: A topic holds messages and manages the consumers and producers associated with it.
- **Message**: A message contains the actual content being passed between producers and consumers.

### Directory Structure
```
src/
│
├── lld.kafka
│   ├── configs/
│   │   └── KafkaConfig.java
│   ├── consumer/
│   │   ├── AbstractConsumer.java
│   │   └── Consumer.java
│   ├── entities/
│   │   ├── Message.java
│   │   └── Topic.java
│   ├── producer/
│   │   ├── AbstractProducer.java
│   │   └── Producer.java
│   ├── utilities/
│   │   └── UniqueKeyGeneratorUtility.java
│   └── Driver.java
└── README.md
```

## Components

### KafkaConfig.java
This file contains the basic configuration settings used across the system, including constants for key lengths and Base62 encoding.

### AbstractConsumer.java and Consumer.java
- **AbstractConsumer.java** defines the interface for consumers.
- **Consumer.java** implements the interface and is responsible for consuming messages from topics.

### AbstractProducer.java and Producer.java
- **AbstractProducer.java** defines the interface for producers.
- **Producer.java** implements the interface and is responsible for producing messages to topics.

### Message.java
Represents a message with a key and content. The message also tracks how many times it has been read and when it was last read.

### Topic.java
Represents a topic in the messaging system. Topics hold queues of messages and manage their consumers and producers. Consumers can subscribe to topics, and producers can publish messages to them.

### UniqueKeyGeneratorUtility.java
This utility generates unique keys for messages using a Base62 encoding strategy.

### Driver.java
The entry point of the application, which demonstrates how producers and consumers interact with topics by publishing and consuming messages.

## Features
- **Producers** can publish messages to multiple topics.
- **Consumers** can subscribe to multiple topics and receive messages when they are published.
- **Messages** are read once by all consumers. Once all consumers have read a message, it is removed from the queue.
- **Message retention** can be managed (though not fully implemented) to simulate message persistence.

## Installation

To run this project, you need to have the following:
- Java 8 or higher installed.

### Steps to Run the Project
1. Clone the repository.
2. Open the project in your preferred IDE (IntelliJ IDEA, Visual Studio Code, etc.).
3. Ensure that the project is set up to use a Java 8+ JDK.
4. Build the project.
5. Run the `Driver.java` file to see the Kafka-like behavior in action.

## Usage

### Producing and Consuming Messages
1. **Producer**: 
   - Create an instance of `Producer` and associate it with one or more topics.
   - Use the `produceMessage()` method to send messages to the topics.
   
2. **Consumer**:
   - Create an instance of `Consumer` and associate it with one or more topics using the `consumerFromTopic()` method.
   - Use the `consumeMessage()` method to consume messages from the topics.

### Example
```java
Topic topic1 = new Topic("topic1");
Topic topic2 = new Topic("topic2");

TopicManger manger = new TopicManger();
manger.addNewTopic(topic1);
manger.addNewTopic(topic2);

AbstractProducer producer1 = new Producer(manger, "producer 1");
producer1.produceToTopic(topic1);
producer1.produceToTopic(topic2);

AbstractConsumer consumer1 = new Consumer(manger, "consumer 1");
consumer1.consumerFromTopic(topic1);
consumer1.consumerFromTopic(topic2);

// Producing messages
producer1.produceMessage("Message 1", topic1.getTopicName());
producer1.produceMessage("Message 2", topic2.getTopicName());
```

### Output
The output of the program will show which consumer receives which messages:
```
producer 1 writing message to topic topic1
consumer 1 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
consumer 2 received Message [key=vxDd, message=Message 1 , readTime=2024-12-03]
...
```

## Future Improvements
- **Message Retention**: Implement persistence and retention policies for messages, such as setting expiry times for messages.
- **Message Partitioning**: Introduce partitioning for topics to handle larger loads and distribute messages across multiple consumers.
- **Error Handling**: Improve error handling and manage failures in message consumption or production.
- **Thread Safety**: Add synchronization mechanisms to ensure thread safety when accessing shared resources.

---
[Kafka deep dive](https://github.com/prashantRmishra/System-design/blob/main/kafka/Readme.md)