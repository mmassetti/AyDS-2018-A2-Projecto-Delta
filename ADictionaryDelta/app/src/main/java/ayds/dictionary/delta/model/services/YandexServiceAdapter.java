package ayds.dictionary.delta.model.services;

import com.example.yandex.service.TranslatorService;

import java.io.IOException;

import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;

class YandexServiceAdapter implements ServiceDef{
    private TranslatorService translatorService;

    YandexServiceAdapter(TranslatorService translatorService){
        this.translatorService = translatorService;
    }

    @Override
    public String getMeaning(String term) throws Exception {
        try {
            return translatorService.callCreateTranslatedWord(term);
        } catch (IOException e){
            throw new ConnectionErrorException();
        }
    }
}
