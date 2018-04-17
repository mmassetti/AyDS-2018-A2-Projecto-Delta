package ayds.dictionary.delta.fulllogic.model;

public interface ConceptModelListener {

    void didUpdateTerm(String meaning, String term);
}
