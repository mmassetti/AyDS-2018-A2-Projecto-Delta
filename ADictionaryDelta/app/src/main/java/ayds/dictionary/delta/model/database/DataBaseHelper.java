package ayds.dictionary.delta.model.database;

public interface DataBaseHelper {
    String getMeaning(String term);
    void saveTerm(String meaning, String term);
}
