package ayds.dictionary.delta.model;

public class Concept {
    private String concept;
    private String meaning;
    private Source source;

    public String getConcept() {
        return concept;
    }

    public String getMeaning() {
        return meaning;
    }

    public Source getSource() {
        return source;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setSource(Source source) {
        this.source = source;
    }

}
