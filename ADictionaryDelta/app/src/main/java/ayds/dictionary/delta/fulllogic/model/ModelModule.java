package ayds.dictionary.delta.fulllogic.model;

import ayds.dictionary.delta.fulllogic.model.database.DataBaseHelper;
import ayds.dictionary.delta.fulllogic.model.database.DataBaseHelperImp;
import ayds.dictionary.delta.fulllogic.model.services.Service;
import ayds.dictionary.delta.fulllogic.model.services.ServiceImp;
import ayds.dictionary.delta.fulllogic.view.ViewModule;

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
