package lld.UrlShortener.entities;

public enum UrlShortenerConfig {
    Short_Len(6);
    private int size;

    UrlShortenerConfig(int size){
            this.size  = size;
    }
    public int getSize(){
        return this.size;
    }
}