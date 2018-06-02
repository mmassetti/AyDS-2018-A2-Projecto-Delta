package ayds.dictionary.delta.fulllogic.model.database;

import android.content.Context;

import ayds.dictionary.delta.fulllogic.model.database.room.DataBase;

class DataBaseHelperImp implements DataBaseHelper {

    DataBaseHelperImp(Context context) {
        initDataBase(context);
    }

    private void initDataBase(final Context context) {
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
        DataBase.saveTerm(term,meaning);
    }
}
