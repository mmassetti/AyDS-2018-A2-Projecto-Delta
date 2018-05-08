package ayds.dictionary.delta.fulllogic.view;

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
import ayds.dictionary.delta.fulllogic.model.ConceptModel;
import ayds.dictionary.delta.fulllogic.model.listeners.ConceptModelListener;
import ayds.dictionary.delta.fulllogic.model.listeners.ErrorListener;
import ayds.dictionary.delta.fulllogic.model.ModelModule;


public class MainActivity extends AppCompatActivity {
    private EditText wordField;
    private Button goButton;
    private TextView resultPane;
    private MeaningController meaningController;
    private ConceptModel conceptModel;
    private TextConverterHelper textConverterHelper = new TextConverterHelperImp();

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
                        String term = getTextFromWordField();
                        searchMeaningOfTheTerm(term);
                    }
                }).start();
            }
        });
        conceptModel.addConceptListener(new ConceptModelListener() {
            @Override
            public void didUpdateTerm(String meaning, String term) {
                final String textToSet = transformMeaningAndTerm(meaning,term);
                resultPane.post(new Runnable() {
                    public void run() {
                        setTextOnResultPane(textToSet);
                    }
                });
            }
        });
        conceptModel.addErrorListener(new ErrorListener() {
            @Override
            public void didErrorOcurr(String message) {
                final String textToSet = message;
                resultPane.post(new Runnable() {
                    public void run() {
                        setTextOnResultPane(textToSet);
                    }
                });
            }
        });
    }

    private void initializeAttributes() {
        this.setApplicationContext();
        meaningController = getController();
        conceptModel = getConceptModel();
        setContentView(R.layout.activity_main);
        wordField = findViewById(R.id.wordField);
        goButton = findViewById(R.id.goButton);
        resultPane = findViewById(R.id.resultPane);
    }

    private void setApplicationContext(){
        ViewModule.getInstance().setContext(getApplicationContext());
    }

    private MeaningController getController(){
        return ControllerModule.getInstance().getController();
    }

    private ConceptModel getConceptModel(){
        return ModelModule.getInstance().getConceptModel();
    }

    private String getTextFromWordField(){
        return wordField.getText().toString();
    }

    private void setTextOnResultPane(String textToSet){
        resultPane.setText(Html.fromHtml(textToSet));
    }

    private void searchMeaningOfTheTerm(String term){
        meaningController.searchMeaning(term);
    }

    private String transformMeaningAndTerm(String meaning, String term){
        return textConverterHelper.textToHTML(meaning, term);
    }
}
