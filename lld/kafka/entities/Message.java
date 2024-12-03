package lld.kafka.entities;

import java.time.LocalDate;

public class Message{
    private String key;
    private String message;
    private boolean isRead = false;
    private LocalDate readTime;
    private int readCount =0;
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
        readCount++;
    }
    public int getReadCount(){
        return this.readCount;
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