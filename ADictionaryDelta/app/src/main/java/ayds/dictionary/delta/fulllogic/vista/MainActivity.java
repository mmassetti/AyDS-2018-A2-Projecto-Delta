package ayds.dictionary.delta.fulllogic.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.delta.R;
import ayds.dictionary.delta.fulllogic.controlador.ControladorSignificado;
import ayds.dictionary.delta.fulllogic.controlador.ModuloControlador;
import ayds.dictionary.delta.fulllogic.modelo.ModeloConcepto;
import ayds.dictionary.delta.fulllogic.modelo.ModeloConceptoListener;
import ayds.dictionary.delta.fulllogic.modelo.ModuloModelo;


public class MainActivity extends AppCompatActivity {

    private EditText textField1;
    private Button goButton;
    private TextView textPane1;

    private ControladorSignificado controlador;
    private ModeloConcepto modeloConcepto;
    private HelperTraductor helperTraductor = new HelperTraductorImp();


    private void inicializarAtributos() {
        ModuloVista.getInstance().setContext(getApplicationContext());
        controlador = ModuloControlador.getInstance().getControlador();
        modeloConcepto = ModuloModelo.getInstance().getModeloConcepto();


        setContentView(R.layout.activity_main);
        textField1 = findViewById(R.id.textField1);
        goButton = findViewById(R.id.goButton);
        textPane1 = findViewById(R.id.textPane1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarAtributos();

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String term = textField1.getText().toString();
                        controlador.buscarSignificado(term);
                    }
                }).start();
            }
        });

        modeloConcepto.setListener(new ModeloConceptoListener() {
            @Override
            public void didUpdateTerm(String meaning, String term) {
                final String textToSet = helperTraductor.textToHTML(meaning, term);
                textPane1.post(new Runnable() {
                    public void run() {
                        textPane1.setText(Html.fromHtml(textToSet));
                    }
                });
            }
        });
    }

}
