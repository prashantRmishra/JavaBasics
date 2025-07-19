Reader-Writer Locks are a concurrency control mechanism used when you have:

Multiple reader threads (that only read data)

Fewer writer threads (that modify data)

The key benefit:

Multiple readers can read concurrently, as long as no writer is writing.

Writers need exclusive access (block both readers and writers).

🔒 Why Reader-Writer Locks?
Traditional locks (like synchronized or ReentrantLock) block all threads (readers and writers) once acquired. This can lead to unnecessary blocking when multiple readers only need shared access without modification.

Reader-Writer locks optimize this by distinguishing:

Read Lock – allows concurrent access to multiple readers.

Write Lock – exclusive access to a single writer.



```java
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

public class Main {
  public static void main(String[] args) {
    System.out.println("Reader-Writer Lock");
    SharedResource resource = new SharedResource(5);
    Runnable reader = ()->{
      try{
        while(true){
          resource.readValue();
          Thread.sleep(200);
        }
      }
      catch(InterruptedException ignore){
        
      }
    };
    Runnable writer = ()->{
      int i = 1;
      try{
        while(true){
          resource.updateValue(i++);
          Thread.sleep(100);
        }
      }
      catch(InterruptedException ignore){
        
      }
    };
    new Thread(reader,"reader-1").start();
    new Thread(reader, "reader-2").start();
    new Thread(writer, "writer-1").start();
    
  }
}
class SharedResource{
  ReentrantReadWriteLock  reentrantLock = new ReentrantReadWriteLock();
  Lock readLock=  reentrantLock.readLock();
  Lock writeLock = reentrantLock.writeLock();
  
  int data;
  public SharedResource(int data){
    this.data = data;
  }
  
  public void readValue(){
    readLock.lock();
    try{
      System.out.println(Thread.currentThread().getName() +": reading data =  "+ this.data);
      Thread.sleep(2);
    }
    catch(InterruptedException ignore){
      
    }
    finally{
      readLock.unlock();
    }
  }
  
  public void updateValue(int value){
    writeLock.lock();
    try{
      this.data = value;
      System.out.println(Thread.currentThread().getName() +": updated the data = " + this.data);
      Thread.sleep(3);
    }
    catch(InterruptedException ignore){
      
    }
    finally{
      writeLock.unlock();
    }
  }
}

/*
Output:

Reader-Writer Lock
reader-1: reading data =  5
reader-2: reading data =  5
writer-1: updated the data = 1
writer-1: updated the data = 2
reader-1: reading data =  2
reader-2: reading data =  2
writer-1: updated the data = 3
writer-1: updated the data = 4
reader-1: reading data =  4
reader-2: reading data =  4
writer-1: updated the data = 5
writer-1: updated the data = 6
reader-1: reading data =  6
reader-2: reading data =  6
writer-1: updated the data = 7
writer-1: updated the data = 8
reader-1: reading data =  8
reader-2: reading data =  8
writer-1: updated the data = 9
writer-1: updated the data = 10
reader-1: reading data =  10
reader-2: reading data =  10
writer-1: updated the data = 11
writer-1: updated the data = 12
reader-1: reading data =  12
reader-2: reading data =  12
writer-1: updated the data = 13
writer-1: updated the data = 14
reader-1: reading data =  14
reader-2: reading data =  14
writer-1: updated the data = 15
writer-1: updated the data = 16
reader-1: reading data =  16
reader-2: reading data =  16
writer-1: updated the data = 17
writer-1: updated the data = 18
reader-1: reading data =  18
reader-2: reading data =  18
writer-1: updated the data = 19
writer-1: updated the data = 20
reader-2: reading data =  20
reader-1: reading data =  20
writer-1: updated the data = 21
writer-1: updated the data = 22
reader-2: reading data =  22
reader-1: reading data =  22
writer-1: updated the data = 23
writer-1: updated the data = 24
reader-2: reading data =  24
reader-1: reading data =  24
writer-1: updated the data = 25
writer-1: updated the data = 26
reader-2: reading data =  26
reader-1: reading data =  26
writer-1: updated the data = 27
writer-1: updated the data = 28
reader-2: reading data =  28
reader-1: reading data =  28
writer-1: updated the data = 29
writer-1: updated the data = 30
reader-2: reading data =  30
reader-1: reading data =  30
writer-1: updated the data = 31
writer-1: updated the data = 32
reader-1: reading data =  32
reader-2: reading data =  32
writer-1: updated the data = 33
writer-1: updated the data = 34
reader-1: reading data =  34
reader-2: reading data =  34
writer-1: updated the data = 35
writer-1: updated the data = 36
reader-1: reading data =  36
reader-2: reading data =  36
writer-1: updated the data = 37
writer-1: updated the data = 38
reader-1: reading data =  38
reader-2: reading data =  38
writer-1: updated the data = 39
writer-1: updated the data = 40
reader-1: reading data =  40
reader-2: reading data =  40
writer-1: updated the data = 41

Error: Command failed: timeout 7 java Main.java

*/
```