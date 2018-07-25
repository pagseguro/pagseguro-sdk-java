package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.*;
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.List;

public final class CompanyBuilder implements Builder<Company> {

    private String name;
    private String displayName;
    private String websiteUrl;
    private Partner partner;
    private Address address;
    private List<Document> documents = new ArrayList<Document>();
    private List<PhoneAccount> phones = new ArrayList<PhoneAccount>();

    /**
     * Set company's name
     *
     * @param name Name
     * @return Builder for company
     * @see Company#getName()
     */
    public CompanyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set company's display name
     *
     * @param displayName Display Name
     * @return Builder for company
     * @see Company#getDisplayName()
     */
    public CompanyBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Set company's website URL
     *
     * @param websiteUrl Website URL
     * @return Builder for company
     * @see Company#getWebsiteUrl()
     */
    public CompanyBuilder withWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
        return this;
    }

    /**
     * Set company's address
     *
     * @param partner Partner
     * @return Builder for company
     * @see Company#getPartner()
     */
    public CompanyBuilder withPartner(Partner partner) {
        this.partner = partner;
        return this;
    }

    /**
     * Set company's partner
     *
     * @param partnerBuilder Builder for Address
     * @return Builder for company
     * @see Company#getPartner()
     */
    public CompanyBuilder withPartner(Builder<Partner> partnerBuilder) {
        return withPartner(partnerBuilder.build());
    }

    /**
     * Set company's address
     *
     * @param address Address
     * @return Builder for company
     * @see Company#getAddress()
     */
    public CompanyBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     * Set company's address
     *
     * @param addressBuilder Builder for Address
     * @return Builder for company
     * @see Company#getAddress()
     */
    public CompanyBuilder withAddress(Builder<Address> addressBuilder) {
        return withAddress(addressBuilder.build());
    }

    /**
     * Add document to company
     *
     * @param document Document
     * @return Builder for company
     * @see Company#getDocuments()
     */
    public CompanyBuilder addDocument(Document document) {
        documents.add(document);
        return this;
    }

    /**
     * Add document to company
     *
     * @param documentBuilder Builder for Document
     * @return Builder for company
     * @see Company#getDocuments()
     */
    public CompanyBuilder addDocument(Builder<Document> documentBuilder) {
        return addDocument(documentBuilder.build());
    }

    /**
     * Add document to company
     *
     * @param documents Documents
     * @return Builder for company
     * @see Company#getDocuments()
     */
    public CompanyBuilder addDocuments(Iterable<? extends Document> documents) {
        for (Document document : documents) {
            addDocument(document);
        }
        return this;
    }

    /**
     * Add phone to company
     *
     * @param phone Phone
     * @return Builder for company
     * @see Company#getPhones()
     */
    public CompanyBuilder addPhone(PhoneAccount phone) {
        phones.add(phone);
        return this;
    }

    /**
     * Add phone to company
     *
     * @param phoneAccountBuilder Builder for Phone Account
     * @return Builder for company
     * @see Company#getPhones()
     */
    public CompanyBuilder addPhone(Builder<PhoneAccount> phoneAccountBuilder) {
        return addPhone(phoneAccountBuilder.build());
    }

    /**
     * Add phone to company
     *
     * @param phones Phone
     * @return Builder for company
     * @see Company#getPhones()
     */
    public CompanyBuilder addPhones(Iterable<? extends PhoneAccount> phones) {
        for (PhoneAccount phone : phones) {
            addPhone(phone);
        }
        return this;
    }

    /**
     * Build the company
     *
     * @return Interface for company
     * @see Company
     */
    @Override
    public Company build() {
        return new SimpleCompany(this);
    }

    /**
     * Implementation of {@code Company}
     */
    private static class SimpleCompany implements Company {

        private final CompanyBuilder companyBuilder;

        SimpleCompany(CompanyBuilder companyBuilder) {
            this.companyBuilder = companyBuilder;
        }

        @Override
        public String getName() {
            return companyBuilder.name;
        }

        @Override
        public String getDisplayName() {
            return companyBuilder.displayName;
        }

        @Override
        public String getWebsiteUrl() {
            return companyBuilder.websiteUrl;
        }

        @Override
        public Partner getPartner() {
            return companyBuilder.partner;
        }

        @Override
        public Address getAddress() {
            return companyBuilder.address;
        }

        @Override
        public List<Document> getDocuments() {
            return companyBuilder.documents;
        }

        @Override
        public List<PhoneAccount> getPhones() {
            return companyBuilder.phones;
        }

    }
}