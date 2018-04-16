package ayds.dictionary.delta.fulllogic.controlador;

import ayds.dictionary.delta.fulllogic.modelo.ModuloModelo;

public class ModuloControlador {

    private static ModuloControlador instance;
    ControladorSignificado controladorSignificado;

    private ModuloControlador(){
        controladorSignificado = new ControladorSignificadoImp(ModuloModelo.getInstance().getModeloConcepto());
    }

    public static ModuloControlador getInstance(){
        if(instance==null) {
            instance = new ModuloControlador();
        }
        return instance;
    }

    public ControladorSignificado getControlador(){
        return controladorSignificado;
    }


}
