package ayds.dictionary.delta.fulllogic.view;

import android.content.Context;

public class ViewModule {
    private static ViewModule instance;
    private Context context;

    private ViewModule() {
    }

    public static ViewModule getInstance() {
        if (instance == null)
            instance = new ViewModule();
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}

