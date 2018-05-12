package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;

interface FormatChecker {
    void checkBadResult(String meaning) throws EmptyResultException;
    void checkFormat(String term) throws BadFormatException;
    boolean isWellFormedTermFormat(String term);
    boolean isValidTerm(String term);
}
