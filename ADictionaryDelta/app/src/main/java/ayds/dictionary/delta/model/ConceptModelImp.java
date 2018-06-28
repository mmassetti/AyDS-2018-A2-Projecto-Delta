package ayds.dictionary.delta.model;

import java.util.List;

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
                List<FinalConceptResult> meanings = searchTermOnRepository(term);
                if (!meanings.isEmpty())
                    notifyListenerConceptModel(meanings);
            }
        }).start();
    }

    private void notifyListenerConceptModel(List<FinalConceptResult> meanings) {
        if (conceptListener != null) {
            conceptListener.didUpdateTerm(meanings);
        }
    }

    private List<FinalConceptResult> searchTermOnRepository(String term) {
        return repository.searchTerm(term);
    }

    private void setHandlerListener(ErrorListener listener) {
        ModuleExceptions.getInstance().getHandler().setListener(listener);
    }
}