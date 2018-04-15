package ayds.dictionary.delta.fulllogic.vista;

import ayds.dictionary.delta.fulllogic.controlador.ControladorSignificado;
import ayds.dictionary.delta.fulllogic.controlador.ModuloControlador;

public class ModuloVista {
    private static ModuloVista instance = new ModuloVista();

    private ModuloVista(){
    }

    public static ModuloVista getInstance() {
        return instance;
    }


}

