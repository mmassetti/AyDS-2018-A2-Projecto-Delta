package ayds.dictionary.delta.fulllogic;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import java.util.List;

import ayds.dictionary.delta.fulllogic.room.*;

public class DataBase {

  private static ConceptDataBase db;

  public static void createNewDatabase(Context context) {
    db = Room.databaseBuilder(context,
                              ConceptDataBase.class, "dictionary.db").build();
  }

  public static void testDB() {

    List<Concept> concepts = getConcepts();

    for (Concept concept :
        concepts) {
      Log.e("**", "id =" + concept.getId());
      Log.e("**", "term =" + concept.getTerm());
      Log.e("**", "meaning =" + concept.getMeaning());
      Log.e("**", "source =" + concept.getSource());

    }
  }

  public static void saveTerm(String term, String meaning) {
    Concept concept =  new Concept();
    concept.setTerm(term);
    concept.setMeaning(meaning);
    concept.setSource(1); //Crear enumerado setSource(Wikipedia)
    db.termDao().insert(concept);
  }

  public static String getMeaning(String term) {

    Concept concept = findConceptByName(String term);

    if (concept != null) {
      return concept.getMeaning();
    }
    return null;
  }
    
 private List<Concept> getConcepts(){
     return db.termDao().getAll();
 }

 private Concept findConceptByName(String term){
     return db.termDao().findByName(term);
     
 }

}
