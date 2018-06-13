package ayds.dictionary.delta.model;

import java.util.List;

public interface Repository {
    List<Concept> searchTerm(String term);
}
