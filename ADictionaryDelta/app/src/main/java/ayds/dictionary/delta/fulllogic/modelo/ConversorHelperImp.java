package ayds.dictionary.delta.fulllogic.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ConversorHelperImp implements ConversorHelper {

    public ConversorHelperImp() {
    }

    public String convertString(String meaning) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new java.io.StringReader(meaning)));

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

            meaning = extract.toString().replace("\\n", "<br>");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return meaning;
    }
}
