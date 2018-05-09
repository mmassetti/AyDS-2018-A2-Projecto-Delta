package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.listeners.ConceptModelListener;
import ayds.dictionary.delta.model.listeners.ErrorListener;

public interface ConceptModel {
    void searchTerm(String term);
    void addConceptListener(ConceptModelListener listener);
    void addErrorListener(ErrorListener listener);
}
