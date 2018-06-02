package ayds.dictionary.delta.fulllogic.model;

import ayds.dictionary.delta.fulllogic.model.listeners.ConceptModelListener;
import ayds.dictionary.delta.fulllogic.model.listeners.ErrorListener;

public interface ConceptModel {
    void searchTerm(String term);
    void addConceptListener(ConceptModelListener listener);
    void addErrorListener(ErrorListener listener);
}
