package ayds.dictionary.delta.model.database;

import android.content.Context;

import ayds.dictionary.delta.model.Concept;
import ayds.dictionary.delta.model.database.room.DataBase;

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

    public String getConceptMeaning(Concept concept) {
        return DataBase.getMeaning(concept.getTerm(), concept.getSource().ordinal());
    }

    public void saveConcept(Concept concept) {
        String meaning = concept.getMeaning();
        String term = concept.getTerm();
        DataBase.saveTerm(term, meaning, concept.getSource().ordinal());
    }
}
