package ayds.dictionary.delta.model.services;

import com.example.yandex.service.TranslatorService;

import java.io.IOException;

import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;

class YandexServiceAdapter extends ServiceDef {
    private TranslatorService translatorService;

    YandexServiceAdapter(TranslatorService translatorService, FormatChecker formatChecker) {
        this.translatorService = translatorService;
        this.formatChecker = formatChecker;
    }

    @Override
    public String getMeaning(String term) throws ConnectionErrorException, EmptyResultException, BadFormatException {
        try {
            checkTerm(term);
            String meaning = translatorService.callCreateTranslatedWord(term);
            checkForBadMeaning(meaning);
            return meaning;
        } catch (BadFormatException e) {
            throw e;
        } catch (EmptyResultException e) {
            throw e;
        } catch (Exception e) {
            throw new ConnectionErrorException();
        }
    }
}
