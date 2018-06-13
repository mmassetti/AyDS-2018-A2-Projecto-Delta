package ayds.dictionary.delta.model.services;

import ayds.dictionary.delta.services.BigHugeLabsService;

class BHLServiceAdapter implements ServiceDef {
    BigHugeLabsService bigHugeLabsService;

    BHLServiceAdapter(BigHugeLabsService bigHugeLabsService){
        this.bigHugeLabsService = bigHugeLabsService;
    }

    @Override
    public String getMeaning(String term) throws Exception{
        return bigHugeLabsService.getMeaning(term);
    }
}
