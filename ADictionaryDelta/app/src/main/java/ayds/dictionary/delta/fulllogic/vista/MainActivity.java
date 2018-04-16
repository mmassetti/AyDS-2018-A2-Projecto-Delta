package ayds.dictionary.delta.fulllogic.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.delta.R;
import ayds.dictionary.delta.fulllogic.controller.MeaningController;
import ayds.dictionary.delta.fulllogic.controller.ControllerModule;
import ayds.dictionary.delta.fulllogic.modelo.ConceptModel;
import ayds.dictionary.delta.fulllogic.modelo.ConceptModelListener;
import ayds.dictionary.delta.fulllogic.modelo.ModelModule;


public class MainActivity extends AppCompatActivity {

    private EditText wordField;
    private Button goButton;
    private TextView resultPane;

    private MeaningController meaningController;
    private ConceptModel conceptModel;
    private TranslateHelper translateHelper = new TranslateHelperImp();


    private void initializeAttributes() {
        ViewModule.getInstance().setContext(getApplicationContext());
        meaningController = ControllerModule.getInstance().getControlador();
        conceptModel = ModelModule.getInstance().getConceptModel();

        setContentView(R.layout.activity_main);
        wordField = findViewById(R.id.wordField);
        goButton = findViewById(R.id.goButton);
        resultPane = findViewById(R.id.resultPane);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeAttributes();

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String term = wordField.getText().toString();
                        meaningController.searchMeaning(term);
                    }
                }).start();
            }
        });

        conceptModel.setListener(new ConceptModelListener() {
            @Override
            public void didUpdateTerm(String meaning, String term) {
                final String textToSet = translateHelper.textToHTML(meaning, term);
                resultPane.post(new Runnable() {
                    public void run() {
                        resultPane.setText(Html.fromHtml(textToSet));
                    }
                });
            }
        });
    }

}
