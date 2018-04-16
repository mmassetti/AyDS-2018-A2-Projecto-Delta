package ayds.dictionary.delta.fulllogic.modelo;

public interface ConceptModel {

    void buscarTermino(String term);

    void setListener(ConceptModelListener listener);
}
