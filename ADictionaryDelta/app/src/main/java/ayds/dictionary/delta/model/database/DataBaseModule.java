package ayds.dictionary.delta.model.database;

import android.content.Context;
import ayds.dictionary.delta.view.ViewModule;

public class DataBaseModule {
    private static DataBaseModule instance;
    private DataBaseHelper dataBaseHelper;

    private DataBaseModule() {
        Context context = ViewModule.getInstance().getContext();
        dataBaseHelper = new DataBaseHelperImp(context);
    }

    public static DataBaseModule getInstance() {
        if (instance == null) {
            instance = new DataBaseModule();
        }
        return instance;
    }

    public DataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }
}
