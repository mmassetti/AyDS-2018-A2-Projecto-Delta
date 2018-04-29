package ayds.dictionary.delta.fulllogic.model;

class ConceptModelImp implements ConceptModel {
    private Repository repository;
    private ConceptModelListener listenerConceptModel;
    private CheckConnectionListener listenerConnection;

    ConceptModelImp(Repository repository) {
        this.repository = repository;
    }

    public void setListenerConceptModel(ConceptModelListener listener) {
        listenerConceptModel = listener;
    }

    public void setListenerConnection (CheckConnectionListener listener){
        listenerConnection = listener;
    }

    public void searchTerm(final String term) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String meaning = searchTermOnRepository(term);
                notifyListenerConceptModel(meaning, term);
            }
        }).start();
    }


    private void notifyListenerConceptModel(String meaning, String term) {
        if (listenerConceptModel != null) {
            listenerConceptModel.didUpdateTerm(meaning, term);
        }
    }

    private void notifyConnectionListener(){
        if (listenerConnection != null) {
            listenerConnection.didNotConnect();
        }
    }

    private String searchTermOnRepository(String term){
        //  Manejo excepcion conexion
        notifyConnectionListener();
        return repository.searchTerm(term);
    }

}
