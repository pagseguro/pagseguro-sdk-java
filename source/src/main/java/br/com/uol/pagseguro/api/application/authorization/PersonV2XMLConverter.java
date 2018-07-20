package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.converterXML.AddressV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentsV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.PhonesV2XMLConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "documents", "birthDate", "phones", "address" })
public class PersonV2XMLConverter {

    private String name;
    private DocumentsV2XMLConverter documents;
    private String birthDate;
    private PhonesV2XMLConverter phones;
    @XmlElement(name = "address")
    private AddressV2XMLConverter address;

    PersonV2XMLConverter() {
    }

    PersonV2XMLConverter(String name, DocumentsV2XMLConverter documents, String birthDate, PhonesV2XMLConverter phones,
           AddressV2XMLConverter address) {
        this.name = name;
        this.documents = documents;
        this.birthDate = birthDate;
        this.phones = phones;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentsV2XMLConverter getDocuments() {
        return documents;
    }

    public void setDocuments(DocumentsV2XMLConverter documents) {
        this.documents = documents;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public PhonesV2XMLConverter getPhones() {
        return phones;
    }

    public void setPhones(PhonesV2XMLConverter phones) {
        this.phones = phones;
    }

    public AddressV2XMLConverter getAddressn() {
        return address;
    }

    public void setAddress(AddressV2XMLConverter address) {
        this.address = address;
    }
}
