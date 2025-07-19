Memory leaks happens when the garbage collector is not able to remove the object because there are still references present even though the object is not being used.

One of the causes of Memory leak could be static collections holding references to unused objects
**static collections could lead to memory leak** because the static variables are tied to the class and are independent of the object lifecycle, So the collection will remain in the memory as long as the application is running(and the class is in the memory). If those static collections like HashMap, List, Set are holding references to the objects even if they are not being used, the Garbage collector won't be able to delete those objects as they would still be references by the static collections.
Solution:
We can use **WeakHashMap**, explicity **remove the objects** from the collection when they are no longer being used or we can think of using good cache (collections) **eviction policy** with fixed size, this will insure that we delete the objects when the size is full and new objects are to be added.
