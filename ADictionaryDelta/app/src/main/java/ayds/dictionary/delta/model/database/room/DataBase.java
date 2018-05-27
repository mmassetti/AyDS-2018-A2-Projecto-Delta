package ayds.dictionary.delta.model.database.room;


import android.arch.persistence.room.Room;
import android.content.Context;
import java.util.List;

public class DataBase {
    private static ConceptDataBase database;

    public static void createNewDatabase(Context context) {
        database = Room.databaseBuilder(context, ConceptDataBase.class, "dictionary.db").build();
    }

    public static void saveTerm(String term, String meaning) {
        ConceptDB concept = new ConceptDB();
        concept.setTerm(term);
        concept.setMeaning(meaning);
        concept.setSource(1);
        insertConcept(concept);
    }

    public static String getMeaning(String term) {
        ConceptDB concept = findConceptByName(term);
        if (concept != null) {
            return concept.getMeaning();
        }
        return null;
    }

    private static List<ConceptDB> getConcepts() {
        return database.termDao().getAll();
    }

    private static ConceptDB findConceptByName(String term) {
        return database.termDao().findByName(term);
    }

    private static void insertConcept(ConceptDB concept) {
        database.termDao().insert(concept);
    }
}
