package ayds.dictionary.delta.model.services;

import android.util.Log;

import java.io.IOException;

import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;
import ayds.dictionary.delta.services.BigHugeLabsService;

class BHLServiceAdapter implements ServiceDef {
    BigHugeLabsService bigHugeLabsService;
    FormatChecker formatChecker;

    BHLServiceAdapter(BigHugeLabsService bigHugeLabsService, FormatChecker formatChecker){
        this.bigHugeLabsService = bigHugeLabsService;
        this.formatChecker= formatChecker;
    }

    @Override
    public String getMeaning(String term) throws Exception {
        try {
            formatChecker.checkFormat(term);
            return bigHugeLabsService.getMeaning(term);
        } catch (IOException e){
            throw new ConnectionErrorException();
        } catch (BadFormatException e){
            throw new BadFormatException();
        }
    }
}
