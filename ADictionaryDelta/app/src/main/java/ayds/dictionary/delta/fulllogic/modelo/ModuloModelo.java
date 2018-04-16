package ayds.dictionary.delta.fulllogic.modelo;

import android.content.Context;
import android.util.Log;

import ayds.dictionary.delta.fulllogic.modelo.bdd.room.DataBase;
import ayds.dictionary.delta.fulllogic.modelo.servicios.ServicioApi;
import ayds.dictionary.delta.fulllogic.modelo.servicios.ServicioApiImp;

public class ModuloModelo {
    private static ModuloModelo instance;
    private ModeloConcepto modeloConcepto;

    private ModuloModelo(Context context){
        iniciarBDD(context);
        ServicioApi servicioApi = new ServicioApiImp();
        Repositorio repositorio = new RepositorioImp(servicioApi);
        modeloConcepto = new ModeloConceptoImp(repositorio);
    }

    public static ModuloModelo getInstance(Context context){
        if(instance==null)
            instance = new ModuloModelo(context);
        return instance;
    }

    public ModeloConcepto getModeloConcepto(){
        return modeloConcepto;
    }

    /* Es necesario usar el Thread?*/
    private void iniciarBDD(final Context context){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DataBase.createNewDatabase(context);
                    DataBase.saveTerm("test", "sarasa");

                    Log.e("**", "" + DataBase.getMeaning("test"));
                    Log.e("**", "" + DataBase.getMeaning("nada"));
                }
            }).start();
    }
}
