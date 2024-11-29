package lld.UrlShortener.service;

import lld.UrlShortener.entities.Url;

public interface Service {
    public String createShortUrl(Url url, String alias);
    public boolean removeShortUrl(String shortUrl);
    public String getLongUrl(String url);
    public Url updateShortUrl(Url url,String shortUrl);
}
