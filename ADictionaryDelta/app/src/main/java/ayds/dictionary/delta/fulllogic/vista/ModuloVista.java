package ayds.dictionary.delta.fulllogic.vista;

import android.content.Context;

public class ModuloVista {
    private static ModuloVista instance;
    Context context;

    private ModuloVista( ){
    }

    public static ModuloVista getInstance() {
        if(instance==null)
            instance = new ModuloVista();
        return instance;
    }

    public Context getContext(){
        return context;
    }

    public void setContext(Context context){
        this.context=context;
    }


}

