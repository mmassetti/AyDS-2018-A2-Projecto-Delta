package ayds.dictionary.delta.fulllogic.model.database;

public interface DataBaseHelper {

    String getMeaning(String term);

    void saveTerm(String meaning, String term);
}
