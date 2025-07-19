Garbage Collection (GC)

“Garbage Collection in JVM is the process of automatically reclaiming memory by removing unreachable objects from the heap.
JVM uses various garbage collectors like:

Serial GC (single-threaded, best for small apps),

Parallel GC (multiple threads, throughput-focused),

G1 GC (divides heap into regions, balances pause time and throughput),

ZGC and Shenandoah (low-pause collectors for large heaps).

In my projects, we monitored GC pauses using tools like jvisualvm or GC logs and tuned parameters like -Xms, -Xmx, and selected collectors via -XX:+UseG1GC based on application behavior."

You can also add:
In production, we proactively monitor GC pauses, Metaspace usage, and thread-level metrics using APM tools like Dynatrace or New Relic to avoid runtime issues.