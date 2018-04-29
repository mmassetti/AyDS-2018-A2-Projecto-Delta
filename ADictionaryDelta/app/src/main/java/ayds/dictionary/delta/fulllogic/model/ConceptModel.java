package ayds.dictionary.delta.fulllogic.model;

public interface ConceptModel {

    void searchTerm(String term);

    void setListenerConceptModel(ConceptModelListener listener);

    void setListenerConnection(CheckConnectionListener listener);
}
