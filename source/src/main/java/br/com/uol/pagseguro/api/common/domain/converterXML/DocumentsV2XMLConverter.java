package br.com.uol.pagseguro.api.common.domain.converterXML;

import br.com.uol.pagseguro.api.common.domain.Document;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Convert attributes in request XML
     *
     * @param documentAccountList Document data
     * @see Document
     * @return DocumentsV2XMLConverter
     */
    public DocumentsV2XMLConverter convert(List<? extends Document> documentAccountList) {
        DocumentsV2XMLConverter convertedDocument = new DocumentsV2XMLConverter();
        convertedDocument.setDocument(parseToDocumentList(documentAccountList));
        return convertedDocument;
    }

    /**
     * Parse a Document list to a list of converted documents
     *
     * @param documentsList List of Document
     * @see Document
     * @return ArrayList<DocumentV2XMLConverter> List of converted documents
     */
    private ArrayList<DocumentV2XMLConverter> parseToDocumentList(List<? extends Document> documentsList) {
        ArrayList<DocumentV2XMLConverter> documentList = new ArrayList<DocumentV2XMLConverter>();

        for (Document document : documentsList) {
            DocumentV2XMLConverter documentConverter = new DocumentV2XMLConverter(
                document.getType().toString(), document.getValue()
            );
            documentList.add(documentConverter);
        }

        return documentList;
    }
}
