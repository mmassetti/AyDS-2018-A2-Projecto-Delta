package ayds.dictionary.delta.controller;

import ayds.dictionary.delta.model.ConceptModel;

class MeaningControllerImp implements MeaningController {
    private ConceptModel conceptModel;

    MeaningControllerImp(ConceptModel conceptModel) {
        this.conceptModel = conceptModel;
    }

    public void searchMeaning(String term) {
        conceptModel.searchTerm(term);
    }
}
