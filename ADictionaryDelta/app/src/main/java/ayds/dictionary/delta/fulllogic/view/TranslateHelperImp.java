package ayds.dictionary.delta.fulllogic.view;

class TranslateHelperImp implements TranslateHelper {


    public String textToHTML(String text, String term) {

        StringBuilder builder = new StringBuilder();

        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");

        builder.append(textWithBold);

        return builder.toString();
    }
}
