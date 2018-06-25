package ayds.dictionary.delta.model.services;

import DataWikipedia.DataWikipedia;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;

class WikiServiceAdapter implements ServiceDef {
    private DataWikipedia dataWikipedia;

    WikiServiceAdapter(DataWikipedia dataWikipedia){
        this.dataWikipedia = dataWikipedia;
    }

    public String getMeaning(String term) throws ConnectionErrorException {
        try {
            return dataWikipedia.getMeaning(term);
        } catch (Exception e){
            throw new ConnectionErrorException();
        }
    }
}
