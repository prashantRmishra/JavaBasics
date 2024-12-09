## Inter-Thread Producer and consumer

```java
ReentrantLock lock = new ReentrantLock();
Condition condition = lock.newCondition();


//thread 1
try{
    lock.lock();
    while(username ==null || password==null){
    condition.await();// by this this thead will be put to sleep but the await() release the lock atomically before the thread is put
    //to sleep
    }
}
finally{
    lock.unlock();
}
doStuff();

//thread 2 

try{
    lock.lock();
    username = userTextBox.getText();
    password = passwordTextBox.getText();
    condition.signal();// signals the thread 1 to wake up and check the status of username and password again
}
finally{
    lock.unlock();//thread 1 will wait for the lock to be freed by thread 2 before it can acquire the lock again and proceed 
}

```

```
void await()//unlocked wait untill signalled
long awaitNanos(long nonosTimeout)//wait no more than the nono time mentioned in the parameter
boolean await(long time, TimeUnit unit)//wait no longer than the time in the given time unit
boolean awaitUntil(Date deadline)//wake up before the deadline 
```

```
void signal()//wakes up a single thread waiting on the condition variable
//Thread that wakes up need to recquire the lock associated with the condition variable
//If currently no threads are waiting on the condition variable then the singnal method does not do anything
void singnalAll()//will broadcast signal to all the threads waiting on the condition variable, and all the threads will wake up.
```

wait(),notify() and notifyAll() are very similar to await(), signal() and signalAll();

Here the lock is applied on an Object instead of creation a condition object we can simple lock on the object of the class that is under use i.e this


For example:


```java
//thread 1
private boolean isTaksCompleted = false;
public void waitUntilCompleted(){
    synchronized(this){
        while(isTaskCompleted ==false){
            this.wait();
        }
    } 
    .... 
}

//thread 2
public void completed(){
    sychronized(this){
        isTaskCompleted = true;
        this.notify();
    }
    ....
}


//when the object is the current object, we can clear the code by adding sychronized keyword on the method itself

//thread 1
private boolean isTaksCompleted = false;
public synchronized void waitUntilCompleted(){
        while(isTaskCompleted ==false){
            wait();
        }
    } 
    .... 

//thread 2
public synchronized void completed(){
    isTaskCompleted = true;
    notify();

}
```