package ayds.dictionary.delta.model.database;

import ayds.dictionary.delta.model.Concept;

public interface DataBaseHelper {
    String getConceptMeaning(Concept concept);
    void saveConcept(Concept concept);
}
