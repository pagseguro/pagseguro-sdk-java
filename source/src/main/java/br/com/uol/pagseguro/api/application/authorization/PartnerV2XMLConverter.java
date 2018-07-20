package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentsV2XMLConverter;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "documents", "birthDate" })
public class PartnerV2XMLConverter {

    private String name;
    private DocumentsV2XMLConverter documents;
    private String birthDate;

    public PartnerV2XMLConverter(){
    }

    public PartnerV2XMLConverter(String name, DocumentsV2XMLConverter documents, String birthDate) {
        this.name = name;
        this.documents = documents;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public PartnerV2XMLConverter setName(String name) {
        this.name = name;
        return this;
    }

    public DocumentsV2XMLConverter getDocuments() {
        return documents;
    }

    public PartnerV2XMLConverter setDocuments(DocumentsV2XMLConverter documents) {
        this.documents = documents;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PartnerV2XMLConverter setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
