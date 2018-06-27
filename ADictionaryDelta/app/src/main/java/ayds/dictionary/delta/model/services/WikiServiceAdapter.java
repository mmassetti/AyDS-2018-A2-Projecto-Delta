package ayds.dictionary.delta.model.services;

import java.io.IOException;

import DataWikipedia.DataWikipedia;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;

class WikiServiceAdapter implements ServiceDef {
    private DataWikipedia dataWikipedia;

    WikiServiceAdapter(DataWikipedia dataWikipedia){
        this.dataWikipedia = dataWikipedia;
    }

    public String getMeaning(String term) throws Exception {
        try {
            return dataWikipedia.getMeaning(term);
        } catch (IOException e){
            throw new ConnectionErrorException();
        }
    }
}
