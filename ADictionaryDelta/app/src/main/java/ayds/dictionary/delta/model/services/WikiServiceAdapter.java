package ayds.dictionary.delta.model.services;

import DataWikipedia.DataWikipedia;

class WikiServiceAdapter implements ServiceDef {
    private DataWikipedia dataWikipedia;

    WikiServiceAdapter(DataWikipedia dataWikipedia){
        this.dataWikipedia = dataWikipedia;
    }

    public String getMeaning(String term) throws Exception {
        return dataWikipedia.getMeaning(term);
    }
}
