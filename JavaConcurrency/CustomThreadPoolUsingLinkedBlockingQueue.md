A Custom Thread Pool is useful when you want to control thread creation, task execution, and resource usage in a multi-threaded environment without relying on Java’s built-in Executors.

Below is a simple but functional custom thread pool implementation, demonstrating core concepts like:

Worker threads.

A task queue.

Graceful shutdown.


```java
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Main {
  public static void main(String[] args) throws InterruptedException {
 
    CustomThreadPool pool = new CustomThreadPool(3);
    
    for(int i =  0;i<10;i++){
      Runnable runnable = ()->{
        System.out.println(Thread.currentThread().getName() +" is getting executed");
      };
      pool.submit(runnable);
      try{
        Thread.sleep(200);// simulate work
      }
      
      catch(InterruptedException e){
        
      }
    }
    Thread.sleep(2000);
    pool.shutdown();
  }
}
class CustomThreadPool{
  BlockingQueue<Runnable> q = new LinkedBlockingQueue<>();
  int capacity;
  volatile boolean isShutdown = false;//intially thread pool is not shutdown
  Worker[] workers;
  
  public CustomThreadPool(int capacity){
    this.capacity = capacity;
    workers = new Worker[capacity];// thread pool will have fixed no. of threads
    for(int i =0;i< this.capacity;i++){
      workers[i] = new Worker("worker-thread-"+i,this);// create threads 
      workers[i].start();//keep on running the thread, thread will be kept blocked if there are no task to run, as soon as a task is submitted the blocked thread will start running the task
    }
  }
  
  public void submit(Runnable runnable){
    if(!isShutdown){
      q.offer(runnable);// or q.add(runnable);
    }
    else{
      throw new IllegalStateException("Illegal State");
    }
  }
  
  
  /*shutdown the thread pool, this will interrupt all the threads in the thread pool*/
  public void shutdown(){
    isShutdown = true;
    for(Worker w : workers){
      w.interrupt();// this will interrupt all the threads
    }
  }
}
class Worker extends Thread{
  private CustomThreadPool pool;
  public Worker(String name, CustomThreadPool pool){
    super(name);
    this.pool = pool;
  }
  
  @Override
  public void run(){
    while(true){
      try{
        Runnable runnable = pool.q.take();// this will not just dequeue the queue,it will block the current thread if the queue is empty ? or not task is available for execution
        runnable.run();//execute the task, once the task is executed this thread will again take() next thread in the queue and execute
      }
      catch(InterruptedException e){
        if(pool.isShutdown){
          //if shutdown is requested
          break;
        }
      }
      catch(Exception e ){
        System.out.println(e.getLocalizedMessage());
      }
    }
  }
}
/*
Output:

worker-thread-0 is getting executed
worker-thread-1 is getting executed
worker-thread-2 is getting executed
worker-thread-0 is getting executed
worker-thread-1 is getting executed
worker-thread-2 is getting executed
worker-thread-0 is getting executed
worker-thread-1 is getting executed
worker-thread-2 is getting executed
worker-thread-0 is getting executed
*/
```
