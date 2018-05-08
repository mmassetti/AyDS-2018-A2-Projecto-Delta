package ayds.dictionary.delta.fulllogic.model.exceptions;

public class ModuleExceptions {
    private static ModuleExceptions instance;
    private ExceptionHandler handler;

    private ModuleExceptions() {
        handler = new ExceptionHandlerImp();
    }

    public static ModuleExceptions getInstance() {
        if (instance == null) {
            instance= new ModuleExceptions();
        }
        return  instance;
    }

    public ExceptionHandler getHandler(){
        return handler;
    }
}
