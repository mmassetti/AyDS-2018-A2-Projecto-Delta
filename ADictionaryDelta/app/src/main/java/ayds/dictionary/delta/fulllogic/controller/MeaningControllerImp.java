package ayds.dictionary.delta.fulllogic.controller;

import ayds.dictionary.delta.fulllogic.modelo.ConceptModel;

public class MeaningControllerImp implements MeaningController {
    private ConceptModel conceptModel;

    public MeaningControllerImp(ConceptModel conceptModel) {
        this.conceptModel = conceptModel;
    }

    public void searchMeaning(String term) {
        conceptModel.buscarTermino(term);
    }
}
