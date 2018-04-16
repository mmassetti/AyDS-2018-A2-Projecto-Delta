package ayds.dictionary.delta.fulllogic.modelo;

import ayds.dictionary.delta.fulllogic.modelo.bdd.BaseDatos;
import ayds.dictionary.delta.fulllogic.modelo.bdd.BaseDatosImp;
import ayds.dictionary.delta.fulllogic.modelo.servicios.Servicio;
import ayds.dictionary.delta.fulllogic.modelo.servicios.ServicioImp;
import ayds.dictionary.delta.fulllogic.vista.ModuloVista;

public class ModuloModelo {
    private static ModuloModelo instance;
    private ModeloConcepto modeloConcepto;

    private ModuloModelo(){
        BaseDatos baseDatos = new BaseDatosImp(ModuloVista.getInstance().getContext());
        HelperConversor helperConversor = new HelperConversorImp();
        Servicio servicio = new ServicioImp(helperConversor);
        Repositorio repositorio = new RepositorioImp(servicio, baseDatos);
        modeloConcepto = new ModeloConceptoImp(repositorio);
    }

    public static ModuloModelo getInstance(){
        if(instance==null)
            instance = new ModuloModelo();
        return instance;
    }

    public ModeloConcepto getModeloConcepto(){
        return modeloConcepto;
    }



}
