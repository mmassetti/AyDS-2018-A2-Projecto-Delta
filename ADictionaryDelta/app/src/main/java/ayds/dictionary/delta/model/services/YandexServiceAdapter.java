package ayds.dictionary.delta.model.services;

import com.example.yandex.service.TranslatorService;

class YandexServiceAdapter implements ServiceDef{
    private TranslatorService translatorService;

    YandexServiceAdapter(TranslatorService translatorService){
        this.translatorService = translatorService;
    }

    @Override
    public String getMeaning(String term) throws Exception {
        return translatorService.callCreateTranslatedWord(term);
    }
}
