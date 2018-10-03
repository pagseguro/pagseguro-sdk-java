package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.Partner;
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Builder for Partner
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PartnerBuilder implements Builder<Partner> {

    private String name;
    private String birthDate;
    private List<Document> documents = new ArrayList<Document>();

    /**
     * Set partner's name
     *
     * @param name Name
     * @return Builder for partner
     * @see Partner#getName()
     */
    public PartnerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set partner's birth date
     *
     * @param birthDate Brith Date
     * @return Builder for partner
     * @see Partner#getBirthDate()
     */
    public PartnerBuilder withBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Add document to partner
     *
     * @param document Document
     * @return Builder for partner
     * @see Partner#getDocuments()
     */
    public PartnerBuilder addDocument(Document document) {
        documents.add(document);
        return this;
    }

    /**
     * Add document to partner
     *
     * @param documentBuilder Builder for Document
     * @return Builder for partner
     * @see Partner#getDocuments()
     */
    public PartnerBuilder addDocument(Builder<Document> documentBuilder) {
        return addDocument(documentBuilder.build());
    }

    /**
     * Add document to partner
     *
     * @param documents Documents
     * @return Builder for partner
     * @see Partner#getDocuments()
     */
    public PartnerBuilder addDocuments(Iterable<? extends Document> documents) {
        for (Document document : documents) {
            addDocument(document);
        }
        return this;
    }

    /**
     * Build the partner
     *
     * @return Interface for partner
     * @see Partner
     */
    @Override
    public Partner build() {
        return new SimplePartner(this);
    }

    /**
     * Implementation of {@code Partner}
     */
    private static class SimplePartner implements Partner {

        private final PartnerBuilder partnerBuilder;

        SimplePartner(PartnerBuilder partnerBuilder) {
            this.partnerBuilder = partnerBuilder;
        }

        @Override
        public String getName() {
            return partnerBuilder.name;
        }

        @Override
        public String getBirthDate() {
            return partnerBuilder.birthDate;
        }

        @Override
        public List<Document> getDocuments() {
            return partnerBuilder.documents;
        }

    }

}
