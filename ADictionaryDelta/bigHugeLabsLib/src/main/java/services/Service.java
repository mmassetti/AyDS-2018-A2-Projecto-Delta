package services;

public interface Service {
    void connect();
    String getMeaning(String term) throws Exception;
}
