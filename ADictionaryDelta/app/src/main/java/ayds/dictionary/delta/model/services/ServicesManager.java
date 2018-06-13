package ayds.dictionary.delta.model.services;

import java.util.Set;
import ayds.dictionary.delta.model.Source;

public interface ServicesManager {

    Set<Source> getAllServices();
    String getMeaning(String term, Source source) throws Exception;
}
