package ayds.dictionary.delta.model.services;

public interface Service {
    void connect();
    String getMeaning(String term) throws Exception;
}
