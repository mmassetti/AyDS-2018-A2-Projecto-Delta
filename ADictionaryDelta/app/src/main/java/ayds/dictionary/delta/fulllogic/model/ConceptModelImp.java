package ayds.dictionary.delta.fulllogic.model;

import ayds.dictionary.delta.fulllogic.model.exceptions.ModuleExceptions;
import ayds.dictionary.delta.fulllogic.model.listeners.ConceptModelListener;
import ayds.dictionary.delta.fulllogic.model.listeners.ErrorListener;

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
                String meaning = searchTermOnRepository(term);
                if (meaning != null)
                    notifyListenerConceptModel(meaning, term);
            }
        }).start();
    }

    private void notifyListenerConceptModel(String meaning, String term) {
        if (conceptListener != null) {
            conceptListener.didUpdateTerm(meaning, term);
        }
    }

    private String searchTermOnRepository(String term) {
        return repository.searchTerm(term);
    }

    private void setHandlerListener(ErrorListener listener) {
        ModuleExceptions.getInstance().getHandler().setListener(listener);
    }
}
