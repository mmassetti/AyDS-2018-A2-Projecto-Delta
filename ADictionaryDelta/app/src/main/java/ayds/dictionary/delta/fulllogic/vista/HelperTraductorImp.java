package ayds.dictionary.delta.fulllogic.vista;

public class HelperTraductorImp implements HelperTraductor {

    public HelperTraductorImp(){

    }

    public String textToHTML(String text, String term) {

        StringBuilder builder = new StringBuilder();

        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");

        builder.append(textWithBold);

        return builder.toString();
    }
}
