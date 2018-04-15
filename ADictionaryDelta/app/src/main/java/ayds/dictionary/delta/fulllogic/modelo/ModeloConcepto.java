package ayds.dictionary.delta.fulllogic.modelo;

public interface ModeloConcepto {

    void buscarTermino(String term);
    void setListener(ModeloConceptoListener listener);
}
