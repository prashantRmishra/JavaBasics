package lld.kafka.entities;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Message{
    private String key;
    private String message;
    private boolean isRead = false;
    private LocalDate readTime;
    private AtomicInteger readCount = new AtomicInteger(0);
    @Override
    public String toString() {
        return "Message [key=" + key + ", message=" + message + ", readTime=" + readTime + "]";
    }
    public Message(String k, String m){
        this.key  =k;
        this.message = m;
    }
    public String getMessage(){
        return this.message;
    }
    public String getKey(){
        return this.key;
    }
    public void incrementReadCount(){
        readCount.incrementAndGet();
    }
    public int getReadCount(){
        return readCount.get();
    }
    public void read(){
        this.isRead = true;
        this.readTime  = LocalDate.now();
    }
    public LocalDate getReadTime(){
        return this.readTime;
    }
    public void setReadTime(LocalDate now) {
        this.readTime = now;
    }
}