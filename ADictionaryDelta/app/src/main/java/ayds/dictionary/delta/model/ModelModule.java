package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.database.ModuleDataBase;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;
import ayds.dictionary.delta.model.exceptions.ModuleExceptions;
import services.Service;
import services.ServiceModule;

public class ModelModule {
    private static ModelModule instance;
    private ConceptModel conceptModel;

    private ModelModule() {
        DataBaseHelper dataBaseHelper = ModuleDataBase.getInstance().getDataBaseHelper();
        ConversorHelper conversorHelper = new ConversorHelperImp();
        Service service = getService();
        ExceptionHandler handler = ModuleExceptions.getInstance().getHandler();
        Repository repository = new RepositoryImp(service, dataBaseHelper, conversorHelper, handler);
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

    private Service getService(){
        return ServiceModule.getInstance().getService();
    }
}
