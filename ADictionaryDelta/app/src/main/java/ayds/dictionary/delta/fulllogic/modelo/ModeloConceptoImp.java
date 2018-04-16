package ayds.dictionary.delta.fulllogic.modelo;

public class ModeloConceptoImp implements ModeloConcepto {
    private Repositorio repositorio;
    private ModeloConceptoListener listener;

     ModeloConceptoImp(Repositorio repositorio){
         this.repositorio=repositorio;
     }

     public void setListener(ModeloConceptoListener listener){
         this.listener=listener;
     }

     public void buscarTermino(final String term){
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     String meaning = repositorio.buscarTermino(term);
                     notifyListener(meaning,term);
                 }
             }).start();
    }


    private void notifyListener(String meaning, String term){
        if(listener!=null) {
            listener.didUpdateTerm(meaning,term);
        }
    }

}
