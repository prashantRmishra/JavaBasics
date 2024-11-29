package lld.UrlShortener.repo;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lld.UrlShortener.entities.Url;

public class Data {
    private  Map<String,Url> urls = new ConcurrentHashMap<>();

    public boolean add(Url url){
        try{
            if(urls.containsKey(url.getShortUrl())){
                return false;
            }
            urls.put(url.getShortUrl(), url);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String get(String shortUrl){
        Url url  = urls.get(shortUrl);
        if(url ==null) return null;
        else if(isExpired(LocalDate.now(), url.getExpirationDate())){
            System.out.println("url is expired");
            urls.remove(shortUrl);// cleanup
            return null;
        }
        return url.getLongUrl();
    }

    public Url delete(String shortUrl){
        return urls.remove(shortUrl);
    }

    public Url update(String shortUrl, Url newUrl){
        Url url = urls.get(shortUrl);
        if(url==null) return null;
        urls.put(shortUrl, newUrl);
        return newUrl;
    }

    private boolean isExpired(LocalDate a, LocalDate b){
        return a.isAfter(b);
    }
    
}
