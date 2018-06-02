package ayds.dictionary.delta.fulllogic.model.services;

public interface Service {
    void connect();
    String getMeaning(String term) throws Exception;
}
