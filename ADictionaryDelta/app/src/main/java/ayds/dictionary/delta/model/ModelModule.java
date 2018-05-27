package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.database.ModuleDataBase;
import services.Service;
import services.ServiceImp;

public class ModelModule {
    private static ModelModule instance;
    private ConceptModel conceptModel;

    private ModelModule() {
        DataBaseHelper dataBaseHelper = ModuleDataBase.getInstance().getDataBaseHelper();
        ConversorHelper conversorHelper = new ConversorHelperImp();
        Service service = new ServiceImp();
        Repository repository = new RepositoryImp(service, dataBaseHelper, conversorHelper);
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
