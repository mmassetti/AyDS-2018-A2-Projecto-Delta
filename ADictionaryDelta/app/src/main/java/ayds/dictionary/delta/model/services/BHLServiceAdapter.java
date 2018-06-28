package ayds.dictionary.delta.model.services;


import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;
import ayds.dictionary.delta.services.BigHugeLabsService;

class BHLServiceAdapter extends ServiceDef {
    BigHugeLabsService bigHugeLabsService;

    BHLServiceAdapter(BigHugeLabsService bigHugeLabsService, FormatChecker formatChecker) {
        this.bigHugeLabsService = bigHugeLabsService;
        this.formatChecker = formatChecker;
    }

    @Override
    public String getMeaning(String term) throws ConnectionErrorException, BadFormatException, EmptyResultException {
        try {
            formatChecker.checkFormat(term);
            String meaning = bigHugeLabsService.getMeaning(term);
            checkForBadMeaning(meaning);
            return meaning;
        } catch (EmptyResultException | BadFormatException e) {
            throw e;
        } catch (Exception e) {
            throw new ConnectionErrorException();
        }
    }
}
