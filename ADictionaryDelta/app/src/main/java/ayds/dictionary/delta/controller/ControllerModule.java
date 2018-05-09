package ayds.dictionary.delta.controller;

import ayds.dictionary.delta.model.ModelModule;

public class ControllerModule {
    private static ControllerModule instance;
    private MeaningController meaningController;

    private ControllerModule() {
        meaningController = new MeaningControllerImp(ModelModule.getInstance().getConceptModel());
    }

    public static ControllerModule getInstance() {
        if (instance == null) {
            instance = new ControllerModule();
        }
        return instance;
    }

    public MeaningController getController() {
        return meaningController;
    }
}
