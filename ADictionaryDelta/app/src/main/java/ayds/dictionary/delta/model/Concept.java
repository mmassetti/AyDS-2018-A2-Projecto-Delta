package ayds.dictionary.delta.model;

public class Concept {
    private String term;
    private String meaning;
    private Source source;

    public String getTerm() {
        return term;
    }

    public String getMeaning() {
        return meaning;
    }

    public Source getSource() {
        return source;
    }

    public void setTerm(String concept) {
        this.term = concept;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setSource(Source source) {
        this.source = source;
    }

}
