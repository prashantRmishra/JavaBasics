package lld.UrlShortener;

import java.time.LocalDate;

import lld.UrlShortener.entities.Url;
import lld.UrlShortener.entities.User;
import lld.UrlShortener.repo.Data;
import lld.UrlShortener.service.UrlCrudService;
import lld.UrlShortener.utility.ShortUrlCreatorUtility;

public class Client {
    public static void main(String[] args) {
        User user1 = new User("prashant", "prashant's address", "1234566");
        Controller controller = new Controller(new UrlCrudService(new ShortUrlCreatorUtility(), new Data()));

        Url url = new Url("https://google.com", LocalDate.of(2028, 12, 4), user1);
        Url url2 = new Url("https://flipkart/catalog", LocalDate.of(2028, 12, 4), user1);

        System.out.println("short:"+ controller.createShortUrl(url, "abcddd"));
        System.out.println("long:"+ controller.getLongUrl(url.getShortUrl()));

        System.out.println("short:"+ controller.createShortUrl(url2, ""));
        System.out.println("long:"+ controller.getLongUrl(url2.getShortUrl()));

        System.out.println("short:"+ controller.createShortUrl(url2, ""));
        System.out.println("long:"+ controller.getLongUrl(url2.getShortUrl()));


    }
}
