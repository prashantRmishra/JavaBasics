package lld.UrlShortener.utility;

import java.util.UUID;

public class UniqueIDGenerator {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateUniqueID(int length) {
        UUID uuid = UUID.randomUUID();
        
        long mostSignificantBits = uuid.getMostSignificantBits(); 
        long leastSignificantBits = uuid.getLeastSignificantBits();

        long combined = Math.abs(mostSignificantBits ^ leastSignificantBits);

        StringBuilder base62Encoded = new StringBuilder();
        while (combined > 0) {
            int remainder = (int) (combined % 62);
            base62Encoded.append(BASE62.charAt(remainder));
            combined /= 62;
        }
        base62Encoded.reverse();
        return base62Encoded.substring(0, Math.min(base62Encoded.length(), length));
    }
}
