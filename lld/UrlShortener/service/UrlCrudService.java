package lld.UrlShortener.service;

import lld.UrlShortener.entities.Url;
import lld.UrlShortener.repo.Data;
import lld.UrlShortener.utility.ShortUrlCreatorUtility;

public class UrlCrudService implements Service {
    private static int RETRY_COUNT = 5;
    private ShortUrlCreatorUtility utility;
    private Data data;
    public UrlCrudService(ShortUrlCreatorUtility urlCreatorUtility,Data data){
        this.utility = urlCreatorUtility;
        this.data = data;
    }

    @Override
    public String createShortUrl(Url url, String alias) {
        if(url ==null || url.getLongUrl().equals("")){
            System.out.println("Invalid url or alias provided!!");
            return null;
        }
        String shortUrl  = utility.createShortUrl(alias);
        url.setShortUrl(shortUrl);
        //in case of collision retry for at least 5 times
        int retry  = RETRY_COUNT;
        while(retry-->0){
            if(data.add(url)) break;
        }
        if(retry==0) {
            System.out.println("multiple collisions, shortUrl can not be created");
            return null;
        }
        return shortUrl;
    }

    @Override
    public boolean removeShortUrl(String shortUrl) {
        return data.delete(shortUrl)!=null;
    }

    @Override
    public String getLongUrl(String url) {
        return data.get(url);
    }
    @Override
    public Url updateShortUrl(Url url, String shortUrl){
        if(shortUrl ==null || url ==null || url.getLongUrl().equals("") || shortUrl.equals("")){
            System.out.println("Invalid url or alias provided!!");
            return null;
        }
        return data.update(shortUrl, url);
    
    }
    
}
