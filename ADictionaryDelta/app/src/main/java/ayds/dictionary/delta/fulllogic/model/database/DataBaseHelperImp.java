package ayds.dictionary.delta.fulllogic.model.database;

import android.content.Context;

import ayds.dictionary.delta.fulllogic.model.database.room.DataBase;

class DataBaseHelperImp implements DataBaseHelper {

    DataBaseHelperImp(Context context) {
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
