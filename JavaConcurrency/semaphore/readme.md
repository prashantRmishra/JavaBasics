In this question, we will design a `Barrier` class.

When running tasks by multiple threads concurrently,  sometimes we would like to coordinate the work to guarantee that some portion of the work is done by all threads before the rest of the work is performed.

The following task is performed by multiple threads concurrently:

```java
private void task() throws InterruptedException {

  // Performing Part 1
  System.out.println(Thread.currentThread().getName() 
    + " part 1 of the work is finished");
 
  barrier.waitForOthers();
 
  //Performing Part2
  System.out.println(Thread.currentThread().getName() 
    + " part 2 of the work is finished");
}
```

If we have 3 threads executing this task concurrently, we would like the output to look like this:

```
thread1 part 1 of the work is finished
thread2 part 1 of the work is finished
thread3 part 1 of the work is finished
 
thread2 part 2 of the work is finished
thread1 part 2 of the work is finished
thread3 part 2 of the work is finished
```
The order of the execution of each part is not important. But we want to make sure that all threads finish part1 before any thread can go ahead and perform part2



Here is a proposed solution with some blank spots we need to fill in

```java
public static class Barrier {
    private final int numberOfWorkers;
    private final Semaphore semaphore = new Semaphore( //** blank 1 **/);
    private int counter = //** blank 2 **/;
    private final Lock lock = new ReentrantLock();
 
    public Barrier(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }
 
    public void waitForOthers() throws InterruptedException {
        lock.lock();
        boolean isLastWorker = false;
        try {
            counter++;
 
            if (counter == numberOfWorkers) {
                isLastWorker = true;
            }
        } finally {
            lock.unlock();
        }
 
        if (isLastWorker) {
            semaphore.release(//** blank 3 **/);
        } else {
            semaphore.acquire();          
        }
    }
}

public static class CoordinatedWorkRunner implements Runnable {
    private final Barrier barrier;
 
    public CoordinatedWorkRunner(Barrier barrier) {
        this.barrier = barrier;
    }
 
    @Override
    public void run() {
        try {
            task();
        } catch (InterruptedException e) {
        }
    }
 
    private void task() throws InterruptedException {
        // Performing Part 1
        System.out.println(Thread.currentThread().getName() 
                + " part 1 of the work is finished");
 
        barrier.waitForOthers();
 
        // Performing Part2
        System.out.println(Thread.currentThread().getName() 
                + " part 2 of the work is finished");
    }
}
```
To make this solution correct, how should we fill in the /** blank ? **/  sections?



To test your code, you can use this main method:

```java
public static void main(String [] args) throws InterruptedException {
    int numberOfThreads = 200; //or any number you'd like 
 
    List<Thread> threads = new ArrayList<>();
 
    Barrier barrier = new Barrier(numberOfThreads);
    for (int i = 0; i < numberOfThreads; i++) {
        threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
    }
 
    for(Thread thread: threads) {
        thread.start();
    }
}
```
Answer

```
blank1: 0
blank2: 0
blank3:numberOfThreads-1
```