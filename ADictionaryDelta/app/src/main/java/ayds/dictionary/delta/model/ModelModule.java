package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.database.DataBaseModule;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;
import ayds.dictionary.delta.model.exceptions.ModuleExceptions;
import ayds.dictionary.delta.model.services.ServicesManager;
import ayds.dictionary.delta.model.services.ServicesModule;
import ayds.dictionary.delta.services.BigHugeLabsService;
import ayds.dictionary.delta.services.BigHugeLabsModule;

public class ModelModule {
    private static ModelModule instance;
    private ConceptModel conceptModel;

    private ModelModule() {
        DataBaseHelper dataBaseHelper = getDBHelper();
        ServicesManager servicesManager = getServicesManager();
        ExceptionHandler handler = getExceptionHandler();
        FormatChecker formatChecker = new FormatCheckerImp();
        Repository repository = new RepositoryImp(servicesManager, dataBaseHelper, handler, formatChecker);
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

    private DataBaseHelper getDBHelper(){
        return DataBaseModule.getInstance().getDataBaseHelper();
    }

    private ExceptionHandler getExceptionHandler(){
        return ModuleExceptions.getInstance().getHandler();
    }

    private ServicesManager getServicesManager(){
        return ServicesModule.getInstance().getServicesManager();
    }


}
