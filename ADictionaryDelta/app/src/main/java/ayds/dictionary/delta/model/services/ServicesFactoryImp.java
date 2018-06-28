package ayds.dictionary.delta.model.services;

import com.example.yandex.service.TranslatorService;

import java.util.Map;
import java.util.TreeMap;

import DataWikipedia.DataWikipedia;
import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.Source;
import ayds.dictionary.delta.services.BigHugeLabsService;

class ServicesFactoryImp implements ServicesFactory {
    private Map<Source, ServiceDef> mapSourceService;

    ServicesFactoryImp(FormatChecker formatChecker,BigHugeLabsService bigHugeLabsService, DataWikipedia dataWikipedia, TranslatorService translatorService){
        mapSourceService = new TreeMap<>();
        ServiceDef BHLService = new BHLServiceAdapter(bigHugeLabsService,formatChecker);
        ServiceDef WikiService = new WikiServiceAdapter(dataWikipedia, formatChecker);
        ServiceDef YandexService = new YandexServiceAdapter(translatorService, formatChecker);
        mapSourceService.put(Source.BIGHUGELABS,BHLService);
        mapSourceService.put(Source.WIKIPEDIA,WikiService);
        mapSourceService.put(Source.YANDEX,YandexService);
    }

        @Override
    public Map<Source, ServiceDef> getServices() {
        return mapSourceService;
    }
}
