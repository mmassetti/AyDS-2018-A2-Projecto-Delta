package ayds.dictionary.delta.model.services;

import com.example.yandex.service.YandexModule;

import DataWikipedia.DataWikipediaModule;
import ayds.dictionary.delta.services.BigHugeLabsModule;

public class ServicesModule {
    private static ServicesModule instance;
    private ServicesFactory servicesFactory;
    private ServicesManager servicesManager;

    private ServicesModule() {
        servicesFactory = new ServicesFactoryImp(
            BigHugeLabsModule.getInstance().getBigHugeLabsService(),
            DataWikipediaModule.getInstance().getDataWikipedia(),
            YandexModule.getInstance().getTranslatorService()
        );

        servicesManager = new ServicesManagerImp(servicesFactory);
    }

    public static ServicesModule getInstance() {
        if (instance == null) {
            instance = new ServicesModule();
        }
        return instance;
    }

    public ServicesManager getServicesManager(){
        return servicesManager;
    }
}