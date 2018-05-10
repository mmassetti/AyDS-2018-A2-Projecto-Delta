package ayds.dictionary.delta.model.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ConceptDB.class}, version = 1)
public abstract class ConceptDataBase extends RoomDatabase {
    public abstract ConceptDao termDao();
}
