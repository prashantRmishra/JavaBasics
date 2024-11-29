package lld.UrlShortener.entities;

import java.time.LocalDate;

public class Url {
    private String longUrl;
    private String shortUrl;
    private LocalDate dateOfCreation;
    private LocalDate expirationDate;
    private User createdBy;
    @Override
    public String toString() {
        return "Url [logUrl=" + longUrl + ", shortUrl=" + shortUrl + ", dateOfCreation=" + dateOfCreation
                + ", expirationDate=" + expirationDate + ", createdBy=" + createdBy + "]";
    }
    public Url(String logUrl, LocalDate dateOfExpiration, User createdBy){
        this.longUrl = logUrl;
        this.dateOfCreation = LocalDate.now();
        this.createdBy = createdBy;
        this.expirationDate = dateOfExpiration;
    }
    public String getShortUrl(){
        return this.shortUrl;
    }
    public void setShortUrl(String shortUrl){
        this.shortUrl = shortUrl;
    }
    public String getLongUrl(){
        return this.longUrl;
    }
    public void updateLongUrl(String longUrl){
        this.longUrl = longUrl;
    }
    public LocalDate getExpirationDate(){
        return this.expirationDate;
    }
}
