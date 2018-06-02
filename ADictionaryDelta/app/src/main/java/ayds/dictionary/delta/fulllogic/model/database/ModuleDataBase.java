package ayds.dictionary.delta.fulllogic.model.database;

import android.content.Context;

import ayds.dictionary.delta.fulllogic.view.ViewModule;

public class ModuleDataBase {
    private static ModuleDataBase instance;
    private DataBaseHelper dataBaseHelper;

    private ModuleDataBase() {
        Context context = ViewModule.getInstance().getContext();
        dataBaseHelper= new DataBaseHelperImp(context);
    }

    public static ModuleDataBase getInstance() {
        if (instance == null) {
            instance= new ModuleDataBase();
        }
        return  instance;
    }

    public DataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }
}
