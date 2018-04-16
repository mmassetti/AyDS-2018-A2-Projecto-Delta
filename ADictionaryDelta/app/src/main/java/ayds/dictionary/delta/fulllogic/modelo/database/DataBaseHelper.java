package ayds.dictionary.delta.fulllogic.modelo.database;

public interface DataBaseHelper {

    String getMeaning(String term);

    void saveTerm(String meaning, String term);
}
