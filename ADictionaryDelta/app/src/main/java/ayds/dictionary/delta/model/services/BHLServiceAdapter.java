package ayds.dictionary.delta.model.services;

import android.util.Log;

import ayds.dictionary.delta.model.FormatChecker;
import ayds.dictionary.delta.services.BigHugeLabsService;

class BHLServiceAdapter implements ServiceDef {
    BigHugeLabsService bigHugeLabsService;
    FormatChecker formatChecker;

    BHLServiceAdapter(BigHugeLabsService bigHugeLabsService, FormatChecker formatChecker){
        this.bigHugeLabsService = bigHugeLabsService;
        this.formatChecker= formatChecker;
    }

    @Override
    public String getMeaning(String term) throws Exception{
        formatChecker.checkFormat(term);
        return bigHugeLabsService.getMeaning(term);
    }
}
