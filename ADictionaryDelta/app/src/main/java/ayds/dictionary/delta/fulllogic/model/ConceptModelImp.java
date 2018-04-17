package ayds.dictionary.delta.fulllogic.model;

public class ConceptModelImp implements ConceptModel {
    private Repository repository;
    private ConceptModelListener listener;

    ConceptModelImp(Repository repository) {
        this.repository = repository;
    }

    public void setListener(ConceptModelListener listener) {
        this.listener = listener;
    }

    public void searchTerm(final String term) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String meaning = searchTermOnRepository(term);
                notifyListener(meaning, term);
            }
        }).start();
    }


    private void notifyListener(String meaning, String term) {
        if (listener != null) {
            listener.didUpdateTerm(meaning, term);
        }
    }

    private String searchTermOnRepository(String term){
        return repository.searchTerm(term);
    }

}
