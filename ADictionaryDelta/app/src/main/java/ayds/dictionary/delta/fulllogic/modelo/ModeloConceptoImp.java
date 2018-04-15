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

     public void buscarTermino(String term){
         repositorio.buscarTermino(term);
         /* El metodo deberia pasarle el resultado al listener, para que MainActivity
            lo obtenga directamente por parametro en el listener
          */
         notifyListener();
     }


     private void notifyListener(){     /*Una vez que lo obtuvo, en el listener aparece como parametro*/
         String meaning=null;
         if(listener!=null) {
             listener.didUpdateTerm(meaning);
         }
     }

}
