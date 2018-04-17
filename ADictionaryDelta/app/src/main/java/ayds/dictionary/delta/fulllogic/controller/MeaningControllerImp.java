package ayds.dictionary.delta.fulllogic.controller;

import ayds.dictionary.delta.fulllogic.model.ConceptModel;

public class MeaningControllerImp implements MeaningController {
    private ConceptModel conceptModel;

    public MeaningControllerImp(ConceptModel conceptModel) {
        this.conceptModel = conceptModel;
    }

    public void searchMeaning(String term) {
        conceptModel.searchTerm(term);
    }
}
