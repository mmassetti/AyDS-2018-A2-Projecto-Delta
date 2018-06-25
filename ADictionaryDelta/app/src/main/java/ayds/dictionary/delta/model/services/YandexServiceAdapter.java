package ayds.dictionary.delta.model.services;

import com.example.yandex.service.TranslatorService;

import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;

class YandexServiceAdapter implements ServiceDef{
    private TranslatorService translatorService;

    YandexServiceAdapter(TranslatorService translatorService){
        this.translatorService = translatorService;
    }

    @Override
    public String getMeaning(String term) throws ConnectionErrorException {
        try {
            return translatorService.callCreateTranslatedWord(term);
        } catch (Exception e){
            throw new ConnectionErrorException();
        }
    }
}
