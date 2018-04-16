package ayds.dictionary.delta.fulllogic.modelo.database;

import android.content.Context;

import ayds.dictionary.delta.fulllogic.modelo.database.room.DataBase;

public class DataBaseHelperImp implements DataBaseHelper {

    public DataBaseHelperImp(Context context) {
        iniciarBDD(context);
    }

    private void iniciarBDD(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataBase.createNewDatabase(context);
            }
        }).start();
    }

    public String getMeaning(String term) {
        return DataBase.getMeaning(term);
    }

    public void saveTerm(String meaning, String term) {
        DataBase.saveTerm(meaning, term);
    }
}
