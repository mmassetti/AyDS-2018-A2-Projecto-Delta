package ayds.dictionary.delta.model.services;

import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;

public abstract class ServiceDef {
    protected FormatChecker formatChecker;

    abstract String getMeaning(String term) throws Exception;

    public void checkForBadMeaning(String meaning) throws EmptyResultException {
        formatChecker.checkBadResult(meaning);
    }
}
