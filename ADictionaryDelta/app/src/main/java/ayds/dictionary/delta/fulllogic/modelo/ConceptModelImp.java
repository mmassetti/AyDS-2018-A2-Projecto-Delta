package ayds.dictionary.delta.fulllogic.modelo;

public class ConceptModelImp implements ConceptModel {
    private Repository repository;
    private ConceptModelListener listener;

    ConceptModelImp(Repository repository) {
        this.repository = repository;
    }

    public void setListener(ConceptModelListener listener) {
        this.listener = listener;
    }

    public void buscarTermino(final String term) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String meaning = repository.searchTerm(term);
                notifyListener(meaning, term);
            }
        }).start();
    }


    private void notifyListener(String meaning, String term) {
        if (listener != null) {
            listener.didUpdateTerm(meaning, term);
        }
    }

}
