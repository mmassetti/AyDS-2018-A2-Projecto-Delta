package ayds.dictionary.delta.fulllogic.model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class ConversorHelperImp implements ConversorHelper {

    //TODO
    public String convertString(String meaning) {
        try {
            NodeList nodes= createNode(meaning);
            StringBuilder extract = new StringBuilder();
            extract.append("<b>Nouns:</b><br>");
            boolean startVerbs = false;

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                String p = formatToString("p",node);
                String r = formatToString("r",node);

                if (r.equals("syn")) {
                    extract.append(node.getTextContent()).append(", ");
                }
                if (!startVerbs && p.equals("verb")) {
                    extract.append("<br><br>");
                    extract.append("<b>Verbs:</b><br>");
                    startVerbs = true;
                }
            }
            meaning = replaceString(extract);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return meaning;
    }

    private NodeList createNode(String meaning) throws ParserConfigurationException, IOException,SAXException{
        DocumentBuilder db =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(new InputSource(new java.io.StringReader(meaning)));
        return doc.getDocumentElement().getElementsByTagName("w");
    }

    private String formatToString(String name, Node node){
        return node.getAttributes().getNamedItem(name).getTextContent();
    }

    private String replaceString(StringBuilder extract){
        return extract.toString().replace("\\n", "<br>");
    }
}
