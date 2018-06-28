package ayds.dictionary.delta.model.services;

import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;

public abstract class ServiceDef {
    protected FormatChecker formatChecker;

    abstract String getMeaning(String term) throws ConnectionErrorException, EmptyResultException, BadFormatException;

    public void checkForBadMeaning(String meaning) throws EmptyResultException {
        formatChecker.checkBadResult(meaning);
    }

    public void checkTerm(String term) throws BadFormatException {
        if (!(formatChecker.isValidTerm(term)))
            throw new BadFormatException();
    }
}
