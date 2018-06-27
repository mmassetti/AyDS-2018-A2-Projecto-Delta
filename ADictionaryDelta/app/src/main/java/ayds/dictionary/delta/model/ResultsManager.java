package ayds.dictionary.delta.model;

import java.util.List;
import java.util.Map;

public interface ResultsManager {
    List<FinalConceptResult> buildList(String term, List<Concept> meaningsList, Map<Source,Exception> sourceExceptionMap);
}
