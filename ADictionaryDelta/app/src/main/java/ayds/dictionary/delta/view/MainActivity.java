package ayds.dictionary.delta.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ayds.dictionary.delta.R;
import ayds.dictionary.delta.controller.ControllerModule;
import ayds.dictionary.delta.controller.MeaningController;
import ayds.dictionary.delta.model.Concept;
import ayds.dictionary.delta.model.ConceptModel;
import ayds.dictionary.delta.model.FinalConceptResult;
import ayds.dictionary.delta.model.ModelModule;
import ayds.dictionary.delta.model.listeners.ConceptModelListener;
import ayds.dictionary.delta.model.listeners.ErrorListener;

public class MainActivity extends AppCompatActivity {
    private EditText wordField;
    private Button goButton;
    private ProgressBar progressBar;
    private TextView resultPane;
    private MeaningController meaningController;
    private ConceptModel conceptModel;
    private TextConverterHelper textConverterHelper;
    private ErrorMessageHelper errorMessageHelper;
    private Handler progressBarHandler;

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
                        showProgressBar();
                        String term = getTextFromWordField();
                        searchMeaningOfTheTerm(term);
                    }
                }).start();
            }
        });
        conceptModel.addConceptListener(new ConceptModelListener() {
            @Override
            public void didUpdateTerm(final List<FinalConceptResult> meanings) {
                final String textToSet = buildString(meanings);
                resultPane.post(new Runnable() {
                    public void run() {
                        setTextOnResultPane(textToSet);
                    }
                });
            }
        });
        conceptModel.addErrorListener(new ErrorListener() {
            @Override
            public void didErrorOccur(String message) {
                final String textToSet = message;
                resultPane.post(new Runnable() {
                    public void run() {
                        //setTextOnResultPane("");
                        errorMessageHelper.showPopUpMessage(textToSet, MainActivity.this);
                        removeProgressBar();
                    }
                });
            }
        });
    }

    private void showProgressBar() {
        progressBarHandler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
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
        progressBar = findViewById(R.id.progressBar);
        resultPane = findViewById(R.id.resultPane);
        progressBarHandler = new Handler();
        textConverterHelper = new TextConverterHelperImp();
        errorMessageHelper = new ErrorMessageHelperImp();
    }

    private void setApplicationContext() {
        ViewModule.getInstance().setContext(getApplicationContext());
    }

    private MeaningController getController() {
        return ControllerModule.getInstance().getController();
    }

    private ConceptModel getConceptModel() {
        return ModelModule.getInstance().getConceptModel();
    }

    private String getTextFromWordField() {
        return wordField.getText().toString();
    }

    private void setTextOnResultPane(String textToSet) {
        String finalText = textToSet.replaceAll("(\r\n|\n)", "<br />"); //Keep new lines
        resultPane.setText(Html.fromHtml(finalText));
        removeProgressBar();
    }

    private void removeProgressBar() {
        progressBarHandler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void searchMeaningOfTheTerm(String term) {
        meaningController.searchMeaning(term);
    }


    private String buildString(List<FinalConceptResult> meanings) {
        final String simpleSpace = "" + "\n";
        final String doubleSpace = "" + simpleSpace + simpleSpace;
        String finalString = "";
        String meaningText;
        for (FinalConceptResult concept : meanings) {
            String stringSource = concept.getSource().toString();
            meaningText = addBoldSource(stringSource) + simpleSpace;
            meaningText += transformResultAndTerm(concept.getSourceResult(), concept.getTerm()) + doubleSpace;
            finalString += meaningText;
        }
        return finalString;
    }


    private String transformResultAndTerm(String meaning, String term) {
        return textConverterHelper.textToHTML(meaning, term);
    }

    private String addBoldSource(String source){
        return textConverterHelper.textBold(source);
    }


}