package ayds.dictionary.delta.model.database.room;


import android.arch.persistence.room.Room;
import android.content.Context;
import java.util.List;

public class DataBase {
    private static ConceptDataBase database;

    public static void createNewDatabase(Context context) {
        database = Room.databaseBuilder(context, ConceptDataBase.class, "dictionary.db").build();
    }

    public static void saveTerm(String term, String meaning, int source) {
        ConceptDB concept = new ConceptDB();
        concept.setTerm(term);
        concept.setMeaning(meaning);
        concept.setSource(source);
        insertConcept(concept);
    }

    public static String getMeaning(String term, int source) {
        ConceptDB concept = findConceptByNameAndSource(term, source);
        if (concept != null) {
            return concept.getMeaning();
        }
        return null;
    }


    private static ConceptDB findConceptByNameAndSource(String term, int source) {
        return database.termDao().findByNameAndSource(term, source);
    }

    private static void insertConcept(ConceptDB concept) {
        database.termDao().insert(concept);
    }
}
