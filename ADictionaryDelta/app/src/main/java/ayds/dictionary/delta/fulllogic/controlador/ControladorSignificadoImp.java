package ayds.dictionary.delta.fulllogic.controlador;

import ayds.dictionary.delta.fulllogic.modelo.ModeloConcepto;

public class ControladorSignificadoImp implements ControladorSignificado {
    private ModeloConcepto modeloConcepto;

    public ControladorSignificadoImp(ModeloConcepto modeloConcepto){
        this.modeloConcepto=modeloConcepto;

    }

    public void buscarSignificado(String term){
        modeloConcepto.buscarTermino(term);
    }
}
