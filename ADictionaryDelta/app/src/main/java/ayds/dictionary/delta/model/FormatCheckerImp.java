package ayds.dictionary.delta.model;

import android.util.Log;

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
        Log.i("msg","El termino es "+term);
        if (!isWellFormedTermFormat(term) || !isValidTerm(term))
            throw new BadFormatException();
    }

    private boolean isWellFormedTermFormat(String term) {
        char termLetter;
        boolean wellFormedTerm = true;
        for (int i = 0; i < term.length() && wellFormedTerm; i++) {
            termLetter = term.charAt(i);
            if (!validCharacter(term,i,termLetter)) {
                wellFormedTerm = false;
            }
        }
        Log.i("valor1","El valor que devuelve isWellFormedTerm es "+wellFormedTerm);
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
                return  true;
            else
                return false;
        } else
            return false;
    }

    private boolean isValidTerm(String term) {
        boolean validTerm = true;
        final String emptyString = "";
        boolean nullTerm = term == null;
        boolean emptyTerm = term.equals(emptyString);
        if (nullTerm || emptyTerm) {
            validTerm = false;
        }
        Log.i("valor2","El valor que devuelve isValidTerm es "+validTerm);
        return validTerm;
    }
}
