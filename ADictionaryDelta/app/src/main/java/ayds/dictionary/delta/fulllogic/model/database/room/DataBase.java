package ayds.dictionary.delta.fulllogic.model.database.room;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

public class DataBase {
    private static ConceptDataBase database;

    public static void createNewDatabase(Context context) {
        database = Room.databaseBuilder(context, ConceptDataBase.class, "dictionary.db").build();
    }

    public static void saveTerm(String term, String meaning) {
        Concept concept = new Concept();
        concept.setTerm(term);
        concept.setMeaning(meaning);
        concept.setSource(1);
        insertConcept(concept);
    }

    public static String getMeaning(String term) {
        Concept concept = findConceptByName(term);
        if (concept != null) {
            return concept.getMeaning();
        }
        return null;
    }

    private static List<Concept> getConcepts() {
        return database.termDao().getAll();
    }

    private static Concept findConceptByName(String term) {
        return database.termDao().findByName(term);

    }

    private static void insertConcept(Concept concept) {
        database.termDao().insert(concept);
    }
}
