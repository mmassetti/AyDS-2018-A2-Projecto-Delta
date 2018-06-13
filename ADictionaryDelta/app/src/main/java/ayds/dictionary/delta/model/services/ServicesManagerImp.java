package ayds.dictionary.delta.model.services;

import java.util.Set;

import ayds.dictionary.delta.model.Source;

class ServicesManagerImp implements ServicesManager {
    ServicesFactory servicesFactory;

    ServicesManagerImp(ServicesFactory servicesFactory){
        this.servicesFactory = servicesFactory;
    }

    public Set<Source> getAllServices(){
        return servicesFactory.getServices().keySet();
    }
    @Override
    public String getMeaning(String term, Source source) throws Exception {
        ServiceDef onSearchService = getSearchService(source);
        String searchResult = onSearchService.getMeaning(term);
        return searchResult;
    }

    private ServiceDef getSearchService(Source source){
        return servicesFactory.getServices().get(source);
    }

}
