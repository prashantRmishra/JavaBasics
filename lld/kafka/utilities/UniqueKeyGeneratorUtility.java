package lld.kafka.utilities;

import java.util.UUID;

import lld.kafka.configs.KafkaConfig;
public class UniqueKeyGeneratorUtility {
   
    public static String getUniqueKey(int maxSize){

        UUID uuid = UUID.randomUUID();

        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long combined = Math.abs(mostSignificantBits ^ leastSignificantBits);
        StringBuilder base62Encoded = new StringBuilder();
        while(combined > 0){
            int remainder = (int) (combined % 62);
            base62Encoded.append(KafkaConfig.BASE_62.charAt(remainder));
            combined = combined/62;
        }
        return base62Encoded.substring(0,Integer.min(maxSize,base62Encoded.length()));
    }
}
