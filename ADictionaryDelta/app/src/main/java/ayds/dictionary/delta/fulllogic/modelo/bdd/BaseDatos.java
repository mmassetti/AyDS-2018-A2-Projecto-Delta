package ayds.dictionary.delta.fulllogic.modelo.bdd;

public interface BaseDatos {

    String getMeaning(String term);
    void saveTerm(String meaning, String term);
}
