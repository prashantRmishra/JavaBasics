package lld.UrlShortener;

import lld.UrlShortener.entities.Url;
import lld.UrlShortener.service.Service;

public class Controller {
    private Service service;
    public Controller(Service service){
        this.service = service;
    }

    //create
    public String createShortUrl(Url url, String alias){
        return service.createShortUrl(url, alias);
    }
    //get
    public String getLongUrl(String shortUrl){
        return service.getLongUrl(shortUrl);
    }
    //delete
    public void deleteShortUrl(String shortUrl){
        boolean status = service.removeShortUrl(shortUrl);
        if(status){
            System.out.println("short url"+ shortUrl +" remove successfully");
        }
        else{
            System.out.println("short url does not exists!!");
        }
    }
    //update
    public void updateUrl(Url url, String shortUrl){
        Url newUrl  = service.updateShortUrl(url, shortUrl);
        if(newUrl ==null) System.out.println("short Url "+ shortUrl+" does not exists");
        else{
            System.out.println("short url updated with " + newUrl.toString());
        }
    }
}
