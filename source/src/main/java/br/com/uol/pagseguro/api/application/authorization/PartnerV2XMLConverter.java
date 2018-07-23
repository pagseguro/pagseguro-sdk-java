package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.Partner;
import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentsV2XMLConverter;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "documents", "birthDate" })
public class PartnerV2XMLConverter {

    private final static DocumentsV2XMLConverter DOCUMENTS_V_2_XML_CONVERTER = new DocumentsV2XMLConverter();

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

    /**
     * Convert attributes in request XML
     *
     * @param partner Partner data
     * @see Partner
     * @return PartnerV2XMLConverter
     */
    public PartnerV2XMLConverter convert(Partner partner) {
        PartnerV2XMLConverter convertedPartner = new PartnerV2XMLConverter();
        convertedPartner.setName(partner.getName());
        convertedPartner.setBirthDate(partner.getBirthDate());
        convertedPartner.setDocuments(DOCUMENTS_V_2_XML_CONVERTER.convert(partner.getDocuments()));
        return convertedPartner;
    }
}
