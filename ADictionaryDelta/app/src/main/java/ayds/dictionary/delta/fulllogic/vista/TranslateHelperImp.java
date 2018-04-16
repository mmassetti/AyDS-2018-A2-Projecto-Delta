package ayds.dictionary.delta.fulllogic.vista;

public class TranslateHelperImp implements TranslateHelper {

    public TranslateHelperImp(){

    }

    public String textToHTML(String text, String term) {

        StringBuilder builder = new StringBuilder();

        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");

        builder.append(textWithBold);

        return builder.toString();
    }
}
