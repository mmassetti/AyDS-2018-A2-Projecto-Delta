package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;

public class FormatCheckerImp implements FormatChecker {

    public void checkBadResult(String meaning) throws EmptyResultException {
        boolean nullMeaning = meaning == null;
        if (nullMeaning) {
            throw new EmptyResultException();
        }
    }

    public void checkFormat(String term) throws BadFormatException {
        if (!isWellFormedTermFormat(term) || !isValidTerm(term))
            throw new BadFormatException();
    }

    public boolean isWellFormedTermFormat(String term) {
        char termLetter;
        boolean wellFormedTerm = true;
        for (int i = 0; i < term.length() && wellFormedTerm; i++) {
            termLetter = term.charAt(i);
            if (!Character.isLetter(termLetter)) {
                wellFormedTerm = false;
            }
        }
        return wellFormedTerm;
    }

    public boolean isValidTerm(String term) {
        boolean validTerm = true;
        final String emptyString = "";
        boolean nullTerm = term == null;
        boolean emptyTerm = term.equals(emptyString);
        if (nullTerm || emptyTerm)
            validTerm = false;
        return validTerm;
    }
}
