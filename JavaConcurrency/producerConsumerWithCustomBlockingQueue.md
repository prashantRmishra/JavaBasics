A custom blocking queue in Java is useful when you want to control the producer-consumer interaction with thread-safety, using wait-notify or Condition variables for blocking behavior.

Here’s a simple, efficient example of a custom blocking queue using ReentrantLock and Condition from java.util.concurrent.locks.

```java
import java.util.*;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Main {
  public static void main(String[] args) {
    System.out.println("Custom blocking queue");
    CustomBlockingQueue<String> customBlockingQueue = new CustomBlockingQueue<>(5);
    Runnable producer = new Runnable(){
      public void run(){
        for(int i =1;i<=10;i++){
          customBlockingQueue.put("data-"+i);
        }
      }
    };
    Runnable consumer = new Runnable(){
      public void run(){
        for(int i =1;i<=10;i++){
          customBlockingQueue.consume();
        }
      }
    };
    
    new Thread(producer).start();
    new Thread(consumer).start();
  }
}
class CustomBlockingQueue<T> {
  Queue<T> q = new LinkedList<>();
  int capacity;
  public CustomBlockingQueue(int capacity){
    this.capacity  = capacity;
  }
  ReentrantLock lock = new ReentrantLock();
  Condition notFull = lock.newCondition();
  Condition notEmpty = lock.newCondition();
  
  public void put(T t){
    
    lock.lock();
    try{
      
      while(q.size() == capacity){
        //we can not put more elements we will have to wait for the consumer to consumer first
        notFull.await();
      }
      q.add(t);
      System.out.println("produced "+ t);
      notEmpty.signal();
    }
    catch(InterruptedException e){
      Thread.currentThread().interrupt();
    }
    finally{
      lock.unlock();
    }
  }
  public void consume(){
    lock.lock();
    try{
      while(q.isEmpty()){
        //if q is empty it can not be consumed
        notEmpty.await();
      }
      System.out.println("consumed "+ q.remove());
      notFull.signal();
    }
    catch(InterruptedException e){
      Thread.currentThread().interrupt();
    }
    finally{
      lock.unlock();
    }
  }
}

/*
Output:

Custom blocking queue
produced data-1
produced data-2
produced data-3
produced data-4
produced data-5
consumed data-1
produced data-6
consumed data-2
produced data-7
consumed data-3
produced data-8
consumed data-4
produced data-9
consumed data-5
produced data-10
consumed data-6
consumed data-7
consumed data-8
consumed data-9
consumed data-10
*/
```