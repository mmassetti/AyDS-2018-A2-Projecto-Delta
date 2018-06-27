package ayds.dictionary.delta.model.listeners;

import java.util.List;

import ayds.dictionary.delta.model.FinalConceptResult;

public interface ConceptModelListener {
    void didUpdateTerm(List<FinalConceptResult> concept);
}
