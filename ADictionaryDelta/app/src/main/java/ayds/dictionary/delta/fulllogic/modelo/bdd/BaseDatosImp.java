package ayds.dictionary.delta.fulllogic.modelo.bdd;

import android.content.Context;

import ayds.dictionary.delta.fulllogic.modelo.bdd.room.DataBase;

public class BaseDatosImp implements BaseDatos {

    public BaseDatosImp(Context context){
        iniciarBDD(context);
    }

    private void iniciarBDD(final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataBase.createNewDatabase(context);
            }
        }).start();
    }

    public String getMeaning(String term){
        return DataBase.getMeaning(term);
    }

    public void saveTerm(String meaning, String term){
        DataBase.saveTerm(meaning, term);
    }
}
