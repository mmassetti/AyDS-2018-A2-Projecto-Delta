package ayds.dictionary.delta.model.listeners;

import java.util.List;

import ayds.dictionary.delta.model.Concept;

public interface ConceptModelListener {
    void didUpdateTerm(List<Concept> concept);
}
