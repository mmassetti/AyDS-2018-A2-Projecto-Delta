package ayds.dictionary.delta.model.database.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ConceptDao {

    @Query("SELECT * FROM ConceptDB")
    List<ConceptDB> getAll();

    @Query("SELECT * FROM ConceptDB WHERE term LIKE :term AND source LIKE :source LIMIT 1")
    ConceptDB findByNameAndSource(String term, int source);

    @Insert(onConflict = REPLACE)
    void insert(ConceptDB concept);
}
