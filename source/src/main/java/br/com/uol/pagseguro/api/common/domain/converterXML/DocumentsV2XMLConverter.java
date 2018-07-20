package br.com.uol.pagseguro.api.common.domain.converterXML;

import java.util.ArrayList;

public class DocumentsV2XMLConverter {

    private ArrayList<DocumentV2XMLConverter> document;

    public ArrayList<DocumentV2XMLConverter> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentV2XMLConverter>();
        }
        return document;
    }

    public void setDocument(ArrayList<DocumentV2XMLConverter> document) {
        this.document = document;
    }
}
