**Contention**, **race condition**, and **deadlock** are related to multi-threaded programming but are different concepts:

---

### **1. Contention**
- **Definition**: Occurs when multiple threads try to access a shared resource, and synchronization mechanisms (like locks) force some threads to wait, causing delays.
- **Outcome**: Performance issues due to waiting, but the program behaves correctly (assuming proper synchronization).
- **Example**:
    ```java
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
    ```
    Multiple threads contend for the `lock`, causing some threads to wait.

---

### **2. Race Condition**
- **Definition**: Happens when two or more threads access shared data simultaneously, and the result of the program depends on the timing or order of execution.
- **Outcome**: Incorrect behavior or unpredictable results because synchronization is missing or inadequate.
- **Example**:
    ```java
    private int counter = 0;

    public void increment() {
        counter++; // Not thread-safe
    }
    ```
    If two threads increment `counter` at the same time, they may overwrite each other's updates, leading to incorrect results.

---

### **3. Deadlock**
- **Definition**: Occurs when two or more threads are waiting indefinitely for each other to release locks, creating a circular dependency.
- **Outcome**: Program hangs because none of the threads can proceed.
- **Example**:
    ```java
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                // Do something
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            synchronized (lock1) {
                // Do something
            }
        }
    }
    ```
    If `thread1` acquires `lock1` and `thread2` acquires `lock2`, both threads will wait indefinitely for the other to release its lock.

---

### **Key Differences**

| **Aspect**             | **Contention**                         | **Race Condition**                     | **Deadlock**                        |
|-------------------------|-----------------------------------------|-----------------------------------------|-------------------------------------|
| **Cause**              | Multiple threads competing for a resource. | Missing or improper synchronization.   | Circular wait for locks.           |
| **Outcome**            | Slower performance.                     | Incorrect or unpredictable results.    | Program hangs (no progress).       |
| **Involves Synchronization?** | Yes, but threads are forced to wait.     | No or insufficient synchronization.   | Yes, with circular dependencies.   |
| **Example Scenario**   | Threads waiting for a lock to be released. | Two threads updating a variable without a lock. | Two threads each holding one lock and waiting for the other. |

---

### **Relationship**
- **Contention** happens when locks are used to prevent **race conditions**.
- Poorly implemented locking can lead to **deadlocks**.

Each issue requires a different strategy to address, depending on your application's needs.