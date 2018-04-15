package ayds.dictionary.delta.fulllogic.modelo.servicios;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ayds.dictionary.delta.fulllogic.modelo.bdd.room.DataBase;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.io.IOException;

public class ServicioApiImp implements ServicioApi {
    WordsBighugelabsAPI wikiAPI;

    public ServicioApiImp(){
        conectar();
    }

    public void conectar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://words.bighugelabs.com/api/2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
       wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }


    /* Revisar método, deberia buscar el termino en la api y despues devolver algo que no se si es un String*/
    public String getMeaning(String term) { /*Devuelve un String?*/
        String meaning;
        Response<String> callResponse;
        try {
            callResponse = wikiAPI.getTerm(term).execute();

            Log.e("**", "XML " + callResponse.body());

            if (callResponse.body() == null) {
                meaning = "No Results";
            } else {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new java.io.StringReader(callResponse.body())));

                NodeList nodes = doc.getDocumentElement().getElementsByTagName("w");

                StringBuilder extract = new StringBuilder();
                extract.append("<b>Nouns:</b><br>");

                boolean startVerbs = false;

                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);

                    String p = node.getAttributes().getNamedItem("p").getTextContent();
                    String r = node.getAttributes().getNamedItem("r").getTextContent();

                    if (r.equals("syn")) {
                        extract.append(node.getTextContent()).append(", ");
                    }

                    if (!startVerbs && p.equals("verb")) {
                        extract.append("<br><br>");
                        extract.append("<b>Verbs:</b><br>");
                        startVerbs = true;
                    }

                }


                /* Que hacer con esta parte? */
                text = extract.toString().replace("\\n", "<br>");
                text = textToHtml(text, textField1.getText().toString());

                // save to DB  <o/
                DataBase.saveTerm(term,text);

                return meaning;
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
