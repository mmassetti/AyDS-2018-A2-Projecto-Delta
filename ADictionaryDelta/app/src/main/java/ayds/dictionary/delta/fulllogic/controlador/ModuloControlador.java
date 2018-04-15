package ayds.dictionary.delta.fulllogic.controlador;

import android.content.Context;

import ayds.dictionary.delta.fulllogic.modelo.ModuloModelo;

public class ModuloControlador {

    private static ModuloControlador instance;
    ControladorSignificado controladorSignificado;

    private ModuloControlador(Context context){
        controladorSignificado = new ControladorSignificadoImp(ModuloModelo.getInstance(context).getModeloConcepto());
    }

    public static ModuloControlador getInstance(Context context){
        if(instance==null)
            instance = new ModuloControlador(context);
        return instance;
    }

    public ControladorSignificado getControlador(){
        return controladorSignificado;
    }


}
