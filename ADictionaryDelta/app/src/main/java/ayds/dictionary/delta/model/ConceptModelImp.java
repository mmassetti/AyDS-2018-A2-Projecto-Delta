package ayds.dictionary.delta.model;

import ayds.dictionary.delta.model.exceptions.ModuleExceptions;
import ayds.dictionary.delta.model.listeners.ConceptModelListener;
import ayds.dictionary.delta.model.listeners.ErrorListener;

class ConceptModelImp implements ConceptModel {
    private Repository repository;
    private ConceptModelListener conceptListener;

    ConceptModelImp(Repository repository) {
        this.repository = repository;
    }

    public void addConceptListener(ConceptModelListener listener) {
        conceptListener = listener;
    }

    public void addErrorListener(ErrorListener listener) {
        setHandlerListener(listener);
    }

    public void searchTerm(final String term) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Concept concept = searchTermOnRepository(term);
                if (concept.getMeaning() != null)
                    notifyListenerConceptModel(concept);
            }
        }).start();
    }

    private void notifyListenerConceptModel(Concept concept) {
        if (conceptListener != null) {
            conceptListener.didUpdateTerm(concept);
        }
    }

    private Concept searchTermOnRepository(String term) {
        return repository.searchTerm(term);
    }

    private void setHandlerListener(ErrorListener listener) {
        ModuleExceptions.getInstance().getHandler().setListener(listener);
    }
}
