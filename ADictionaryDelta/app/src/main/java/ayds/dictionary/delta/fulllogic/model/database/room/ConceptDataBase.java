package ayds.dictionary.delta.fulllogic.model.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Concept.class}, version = 1)
public abstract class ConceptDataBase extends RoomDatabase {
    public abstract ConceptDao termDao();
}
