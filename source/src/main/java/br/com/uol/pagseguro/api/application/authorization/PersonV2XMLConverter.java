package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.Person;
import br.com.uol.pagseguro.api.common.domain.converterXML.AddressV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentsV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.PhonesV2XMLConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "documents", "birthDate", "phones", "address" })
public class PersonV2XMLConverter {

    private final static DocumentsV2XMLConverter DOCUMENTS_V_2_XML_CONVERTER = new DocumentsV2XMLConverter();
    private final static PhonesV2XMLConverter PHONES_V_2_XML_CONVERTER = new PhonesV2XMLConverter();
    private final static AddressV2XMLConverter ADDRESS_V_2_XML_CONVERTER = new AddressV2XMLConverter();

    private String name;
    private DocumentsV2XMLConverter documents;
    private String birthDate;
    private PhonesV2XMLConverter phones;
    @XmlElement(name = "address")
    private AddressV2XMLConverter address;

    public PersonV2XMLConverter() {
    }

    public PersonV2XMLConverter(String name, DocumentsV2XMLConverter documents, String birthDate, PhonesV2XMLConverter phones,
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

    /**
     * Convert attributes in request XML
     *
     * @param person Person data
     * @see Person
     * @return PersonV2XMLConverter
     */
    public PersonV2XMLConverter convert(Person person) {
        if (person == null) {
            return null;
        }

        PersonV2XMLConverter convertedPerson = new PersonV2XMLConverter();
        convertedPerson.setName(person.getName());
        convertedPerson.setDocuments(DOCUMENTS_V_2_XML_CONVERTER.convert(person.getDocuments()));
        convertedPerson.setBirthDate(person.getBirthDate());
        convertedPerson.setPhones(PHONES_V_2_XML_CONVERTER.convert(person.getPhones()));
        convertedPerson.setAddress(ADDRESS_V_2_XML_CONVERTER.convert(person.getAddress()));
        return convertedPerson;
    }
}
