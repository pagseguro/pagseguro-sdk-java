package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.*;
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PreApprovalHolderBuilder implements Builder<PreApprovalHolder> {
    private String name = null;
    private Date birthDate = null;
    private List<Document> documents = new ArrayList<Document>();
    private Phone phone = null;
    private Address billingAddress = null;

    /**
     * Set name of pre approval holder
     *
     * @param name Name of pre approval holder
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getName()
     */
    public PreApprovalHolderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Add document to pre approval holder
     *
     * @param document Document
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getDocuments()
     */
    public PreApprovalHolderBuilder addDocument(Document document) {
        documents.add(document);
        return this;
    }

    /**
     * Add document to pre approval holder
     *
     * @param documentBuilder Builder for Document
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getDocuments()
     */
    public PreApprovalHolderBuilder addDocument(Builder<Document> documentBuilder) {
        return addDocument(documentBuilder.build());
    }

    /**
     * Add document to pre approval holder
     *
     * @param documents Documents
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getDocuments()
     */
    public PreApprovalHolderBuilder addDocuments(Iterable<? extends Document> documents) {
        for (Document document : documents) {
            addDocument(document);
        }
        return this;
    }

    /**
     * Set birth date of pre approval holder
     *
     * @param birthDate Birth Date
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getBirthDate()
     */
    public PreApprovalHolderBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Set phone of pre approval holder
     *
     * @param phone Phone
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getPhone()
     */
    public PreApprovalHolderBuilder withPhone(Phone phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Set phone of pre approval holder
     *
     * @param phoneBuilder Builder for Phone
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getPhone()
     */
    public PreApprovalHolderBuilder withPhone(Builder<Phone> phoneBuilder) {
        return withPhone(phoneBuilder.build());
    }

    /**
     * Set billing address of pre approval holder
     *
     * @param billingAddress Address
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getBillingAddress()
     */
    public PreApprovalHolderBuilder withBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    /**
     * Set billing address of pre approval holder
     *
     * @param billingAddressBuilder Builder for Address
     * @return Builder for pre approval holder
     * @see PreApprovalHolder#getBillingAddress()
     */
    public PreApprovalHolderBuilder withBillingAddress(Builder<Address> billingAddressBuilder) {
        return withBillingAddress(billingAddressBuilder.build());
    }


    /**
     * Build the Pre Approval Holder
     *
     * @return Interface for Pre Approval Holder
     * @see PreApprovalHolder
     */
    @Override
    public PreApprovalHolder build() { return new SimplePreApprovalHolder(this); }

    /**
     * Implementation of {@code Holder}
     */
    private static class SimplePreApprovalHolder implements PreApprovalHolder {

        private final PreApprovalHolderBuilder preApprovalHolderBuilder;

        private SimplePreApprovalHolder(PreApprovalHolderBuilder preApprovalHolderBuilder) {
            this.preApprovalHolderBuilder = preApprovalHolderBuilder;
        }

        @Override
        public String getName() {
            return preApprovalHolderBuilder.name;
        }

        @Override
        public List<Document> getDocuments() {
            return preApprovalHolderBuilder.documents;
        }

        @Override
        public Date getBirthDate() {
            return preApprovalHolderBuilder.birthDate;
        }

        @Override
        public Phone getPhone() {
            return preApprovalHolderBuilder.phone;
        }

        @Override
        public Address getBillingAddress() {return preApprovalHolderBuilder.billingAddress; }

    }
}
