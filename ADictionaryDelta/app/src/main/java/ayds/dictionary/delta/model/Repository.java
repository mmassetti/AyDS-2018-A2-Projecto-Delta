package ayds.dictionary.delta.model;

import java.util.List;

public interface Repository {
    List<FinalConceptResult> searchTerm(String term);
}
