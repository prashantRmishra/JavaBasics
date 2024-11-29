package lld.UrlShortener.utility;

import java.util.concurrent.atomic.AtomicLong;

import lld.UrlShortener.entities.UrlShortenerConfig;

public class ShortUrlCreatorUtility {
    private AtomicLong counter;
    public ShortUrlCreatorUtility(){
        this.counter = new AtomicLong(1);

    }
    public String createShortUrl(String alias){
        //using counter method for this
        if(alias!=null && !alias.equals("") && alias.length() ==UrlShortenerConfig.Short_Len.getSize()){
            return alias;
        }
       
        // long id = counter.getAndIncrement();
        // return encodeBase62(id);
        //or
         // we can also think of using uniqueUUID generator see the class UniqueIDgenerator
        return UniqueIDGenerator.generateUniqueID(UrlShortenerConfig.Short_Len.getSize());
        
    }

    public String encodeBase62(long value){
        StringBuilder sb = new StringBuilder();
        String base62 = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while(value!=0){
            sb.append(base62.charAt((int)(value%62)));
            value = value/62;
        }
        return sb.reverse().toString().substring(0,Math.min(sb.length(),UrlShortenerConfig.Short_Len.getSize()));
    }
}
// random function:
// let say 10M urls = 10000000  = 8 char, but if fixed length of the short url is 6 then it needs to be compacted using something like base62 encoding
//62^6 combination is what we are going to have 
//issues: collisions
// Using hashing
//logUrl> md5Hash(longUrl)> base62(hashvalue)> hashedAndEncodedString sliced to length 6 
//issues: same collisions
// counter:
//counter between 1  to 10M and base62(countervalue) > shortUrl
//issues; predictability
//solution: bijective function like squids.org which does one to one mapping of the given counter to unique string  
