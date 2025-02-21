## Key Features of Stream API:
- Declarative (Functional Style) – Allows writing cleaner and more readable code.
- Lazy Execution – Operations on streams are executed only when needed.
- Parallel Processing Support – Improves performance by using multi-core processors.
- Immutable and Stateless – Does not modify the original collection.
- Pipelining of Operations – Can chain multiple operations without creating intermediate results.

## Parallel Processing: Using Multiple cores 

### **Example in Code:**
Let’s say you have a list of numbers, and you want to **find the square of each number**.

#### **Normal Stream (Single Chef)**
```java
numbers.stream()
    .map(n -> n * n)
    .forEach(System.out::println);
```
🔴 Here, the numbers are processed **one at a time**, which is slower for large lists.  

#### **Parallel Stream (Multiple Chefs)**
```java
numbers.parallelStream()
    .map(n -> n * n)
    .forEach(System.out::println);
```
✅ Here, Java **divides the numbers** among multiple CPU cores, making the processing **faster**.  

### **When Should You Use Parallel Streams?**
✔ When you have **a huge amount of data** (e.g., millions of records)  
✔ When the task is **independent** (e.g., calculating squares, filtering, etc.)  
✔ When your **CPU has multiple cores** (which most modern computers do!)  

### **When NOT to Use Parallel Streams?**
❌ When working with **small datasets** (the overhead of splitting the work might slow things down)  
❌ When tasks are **dependent on each other** (e.g., reading/writing to the same file)  
❌ When order matters (Parallel processing can mix up results)  

In short, **parallel streams make your program faster** by dividing work among multiple CPU cores, just like hiring multiple chefs in a restaurant! 🚀