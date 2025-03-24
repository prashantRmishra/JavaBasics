[Garbage collection in heap in java( young and old gen heaps)](https://www.youtube.com/watch?v=Mlbyft_MFYM&t=317s)

finalize() method is executed before the object is garbage collected, 
System.gc() or Runtime.gc() when called invokes Garbage Collector daemon thread that is responsible for garbage collecting java objects.
Note: Before the object is garbage collected, finalize() method is executed for activities like closing db connection etc