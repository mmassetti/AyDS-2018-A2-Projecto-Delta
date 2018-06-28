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

    public boolean isValidTerm(String term) {
        boolean validTerm = true;
        final String emptyString = "";
        final String spaceString = " ";
        boolean nullTerm = term == null;
        boolean spaceTerm = term.equals(spaceString);
        boolean emptyTerm = term.equals(emptyString);
        if (nullTerm || emptyTerm || spaceTerm) {
            validTerm = false;
        }
        return validTerm;
    }

    private boolean isWellFormedTermFormat(String term) {
        char termLetter;
        boolean wellFormedTerm = true;
        for (int i = 0; i < term.length() && wellFormedTerm; i++) {
            termLetter = term.charAt(i);
            if (!validCharacter(term, i, termLetter)) {
                wellFormedTerm = false;
            }
        }
        return wellFormedTerm;
    }

    private boolean validCharacter(String term, int position, char character) {
        if (!isLetter(character) || isAnotherSpace(term, position, character))
            return false;
        else
            return true;
    }

    private boolean isLetter(char character) {
        return Character.isLetter(character);
    }

    private boolean isAnotherSpace(String term, int position, char actualChar) {
        if (position != 0) {
            char previousChar = term.charAt(position - 1);
            if (Character.isSpaceChar(previousChar) && Character.isSpaceChar(actualChar))
                return true;
            else
                return false;
        } else
            return false;
    }


}
