package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.Company;
import br.com.uol.pagseguro.api.common.domain.converterXML.AddressV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentsV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.PhonesV2XMLConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "documents", "displayName", "websiteURL", "partner", "phones", "address"})
public class CompanyV2XMLConverter {

    private final static DocumentsV2XMLConverter DOCUMENTS_V_2_XML_CONVERTER = new DocumentsV2XMLConverter();
    private final static PartnerV2XMLConverter PARTNER_V_2_XML_CONVERTER = new PartnerV2XMLConverter();
    private final static PhonesV2XMLConverter PHONES_V_2_XML_CONVERTER = new PhonesV2XMLConverter();
    private final static AddressV2XMLConverter ADDRESS_V_2_XML_CONVERTER = new AddressV2XMLConverter();

    private String name;
    private DocumentsV2XMLConverter documents;
    private String displayName;
    private String websiteURL;
    private String birthDate;
    private PartnerV2XMLConverter partner;
    private PhonesV2XMLConverter phones;
    @XmlElement(name = "address")
    private AddressV2XMLConverter address;

    public CompanyV2XMLConverter() {
    }

    public CompanyV2XMLConverter(String name, DocumentsV2XMLConverter documents, String displayName, String websiteURL,
                                 PhonesV2XMLConverter phones, PartnerV2XMLConverter partner, AddressV2XMLConverter address) {
        this.name = name;
        this.documents = documents;
        this.displayName = displayName;
        this.websiteURL = websiteURL;
        this.phones = phones;
        this.partner = partner;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public PhonesV2XMLConverter getPhones() {
        return phones;
    }

    public void setPhones(PhonesV2XMLConverter phones) {
        this.phones = phones;
    }

    public PartnerV2XMLConverter getPartner() {
        return partner;
    }

    public void setPartner(PartnerV2XMLConverter partner) {
        this.partner = partner;
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
     * @param company Company data
     * @see Company
     * @return CompanyV2XMLConverter
     */
    public CompanyV2XMLConverter convert(Company company) {
        if (company == null) {
            return null;
        }

        CompanyV2XMLConverter convertedCompany = new CompanyV2XMLConverter();
        convertedCompany.setName(company.getName());
        convertedCompany.setDocuments(DOCUMENTS_V_2_XML_CONVERTER.convert(company.getDocuments()));
        convertedCompany.setDisplayName(company.getDisplayName());
        convertedCompany.setWebsiteURL(company.getWebsiteUrl());
        convertedCompany.setPartner(PARTNER_V_2_XML_CONVERTER.convert(company.getPartner()));
        convertedCompany.setPhones(PHONES_V_2_XML_CONVERTER.convert(company.getPhones()));
        convertedCompany.setAddress(ADDRESS_V_2_XML_CONVERTER.convert(company.getAddress()));
        return convertedCompany;
    }
}
