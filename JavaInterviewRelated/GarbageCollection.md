## Garbage Collection (GC)

“Garbage Collection in JVM is the process of automatically reclaiming memory by removing unreachable objects from the heap.
JVM uses various garbage collectors like:

Serial GC (single-threaded, best for small apps),

Parallel GC (multiple threads, throughput-focused),

G1 GC (divides heap into regions, balances pause time and throughput),

ZGC and Shenandoah (low-pause collectors for large heaps).

In my projects, we monitored GC pauses using tools like jvisualvm or GC logs and tuned parameters like -Xms, -Xmx, and selected collectors via -XX:+UseG1GC based on application behavior."

You can also add:
In production, we proactively monitor GC pauses, Metaspace usage, and thread-level metrics using APM tools like Dynatrace or New Relic to avoid runtime issues.

## How garbage collection works

Garbage Collection (GC) in Java is the automatic process of reclaiming memory by identifying and removing objects that are no longer referenced by the application.
JVM uses a process called reachability analysis—starting from GC Roots (like static references, active thread stacks, class loaders)—to trace which objects are still accessible. Any object not reachable from these roots is considered garbage and eligible for collection.
Modern JVMs use generational garbage collection, dividing memory into:

Young Generation (Eden + Survivor spaces) where most objects are created and collected quickly.

Old Generation (Tenured) where long-lived objects reside.

Metaspace for class metadata (since Java 8).

Common Garbage Collectors include:

Parallel GC (focuses on throughput),

G1 GC (splits heap into regions, aims for low pause times),

ZGC or Shenandoah (for very large heaps with minimal pause times).

In production, we monitor GC performance using GC logs and APM tools to tune JVM parameters like -Xms, -Xmx, and GC-specific flags.

[How to analyze heap dump for memory leaks or OutoFMemoryError ?](https://github.com/prashantRmishra/JavaBasics/blob/main/JavaInterviewRelated/AnalyzeHeapDump.md)