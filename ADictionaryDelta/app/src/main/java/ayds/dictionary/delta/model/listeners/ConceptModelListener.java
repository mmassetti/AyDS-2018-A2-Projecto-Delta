package ayds.dictionary.delta.model.listeners;

import ayds.dictionary.delta.model.Concept;

public interface ConceptModelListener {
    void didUpdateTerm(Concept concept);
}
