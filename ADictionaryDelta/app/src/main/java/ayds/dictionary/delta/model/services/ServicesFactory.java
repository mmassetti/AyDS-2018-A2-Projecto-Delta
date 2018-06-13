package ayds.dictionary.delta.model.services;

import java.util.Map;
import ayds.dictionary.delta.model.Source;

public interface ServicesFactory {
    Map<Source, ServiceDef> getServices();
}
