package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;

public interface FormatChecker {
    void checkBadResult(String meaning) throws EmptyResultException;
    void checkFormat(String term) throws BadFormatException;
}
