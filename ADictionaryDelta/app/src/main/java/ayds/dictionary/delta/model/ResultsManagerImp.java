package ayds.dictionary.delta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ayds.dictionary.delta.model.exceptions.ExceptionHandler;

class ResultsManagerImp implements ResultsManager {
    private ExceptionHandler handler;

    ResultsManagerImp(ExceptionHandler handler) {
        this.handler = handler;
    }

    public List<FinalConceptResult> buildList(String term, List<Concept> meaningsList, Map<Source, Exception> sourceExceptionMap) {
        List<FinalConceptResult> finalList = new ArrayList<>();
        for (Concept concept : meaningsList) {
            FinalConceptResult finalConceptResult = createSourceTextView(concept.getTerm(), concept.getSource(), concept.getMeaning());
            finalList.add(finalConceptResult);
        }
        for (Map.Entry<Source, Exception> entry : sourceExceptionMap.entrySet()) {
            String text = getExceptionMessage(entry.getValue());
            FinalConceptResult finalConceptResult = createSourceTextView(term, entry.getKey(), text);
            finalList.add(finalConceptResult);
        }
        return finalList;
    }

    private FinalConceptResult createSourceTextView(String term, Source source, String text) {
        FinalConceptResult finalConceptResult = new FinalConceptResult();
        finalConceptResult.setTerm(term);
        finalConceptResult.setSource(source);
        finalConceptResult.setSourceResult(text);
        return finalConceptResult;
    }

    private String getExceptionMessage(Exception e) {
        return handler.getExceptionMessage(e);
    }

}
