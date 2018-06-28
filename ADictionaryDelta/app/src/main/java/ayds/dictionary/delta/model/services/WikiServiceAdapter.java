package ayds.dictionary.delta.model.services;

import java.io.IOException;

import DataWikipedia.DataWikipedia;
import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;

class WikiServiceAdapter extends ServiceDef {
    private DataWikipedia dataWikipedia;

    WikiServiceAdapter(DataWikipedia dataWikipedia, FormatChecker formatChecker){
        this.dataWikipedia = dataWikipedia;
        this.formatChecker = formatChecker;
    }

    public String getMeaning(String term) throws ConnectionErrorException, EmptyResultException, BadFormatException {
        try {
            checkTerm(term);
            String meaning = dataWikipedia.getMeaning(term);
            checkForBadMeaning(meaning);
            return meaning;
        } catch (BadFormatException e){
            throw e;
        } catch (EmptyResultException e){
            throw e;
        } catch (Exception e){
            throw new ConnectionErrorException();
        }
    }
}
