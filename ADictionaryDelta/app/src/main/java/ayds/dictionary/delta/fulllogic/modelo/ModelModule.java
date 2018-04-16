package ayds.dictionary.delta.fulllogic.modelo;

import ayds.dictionary.delta.fulllogic.modelo.database.DataBaseHelper;
import ayds.dictionary.delta.fulllogic.modelo.database.DataBaseHelperImp;
import ayds.dictionary.delta.fulllogic.modelo.services.Service;
import ayds.dictionary.delta.fulllogic.modelo.services.ServiceImp;
import ayds.dictionary.delta.fulllogic.vista.ViewModule;

public class ModelModule {
    private static ModelModule instance;
    private ConceptModel conceptModel;

    private ModelModule() {
        DataBaseHelper dataBaseHelper = new DataBaseHelperImp(ViewModule.getInstance().getContext());
        ConversorHelper conversorHelper = new ConversorHelperImp();
        Service service = new ServiceImp(conversorHelper);
        Repository repository = new RepositoryImp(service, dataBaseHelper);
        conceptModel = new ConceptModelImp(repository);
    }

    public static ModelModule getInstance() {
        if (instance == null)
            instance = new ModelModule();
        return instance;
    }

    public ConceptModel getConceptModel() {
        return conceptModel;
    }


}
