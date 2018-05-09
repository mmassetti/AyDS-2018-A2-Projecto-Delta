package ayds.dictionary.delta.model.listeners;

public interface ConceptModelListener {
    void didUpdateTerm(String meaning, String term);
}
