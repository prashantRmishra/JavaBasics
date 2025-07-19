## Metaspace


Metaspace is the memory area where class metadata is stored. Before Java 8, this was called PermGen (Permanent Generation).
Starting Java 8, PermGen was removed, and Metaspace was introduced. Unlike PermGen, Metaspace resides in native memory, not in heap, and its size grows dynamically unless capped using flags like:

``-XX:MaxMetaspaceSize``

``-XX:MetaspaceSize``

In one situation, a high rate of dynamic class loading led to Metaspace exhaustion, which we diagnosed via OutOfMemoryError: Metaspace and resolved by setting appropriate max size and reviewing class loaders.

