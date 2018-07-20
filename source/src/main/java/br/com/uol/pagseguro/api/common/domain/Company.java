package br.com.uol.pagseguro.api.common.domain;

import java.util.List;

/**
 * Interface for Company
 *
 * @author PagSeguro Internet Ltda.
 */
public interface Company {

    /**
     * Get company's name
     *
     * @return Name
     */
    String getName();

    /**
     * Get company's display name
     *
     * @return Display Name
     */
    String getDisplayName();

    /**
     * Get company's website URL
     *
     * @return Website URL
     */
    String getWebsiteUrl();

    /**
     * Get company's partner
     *
     * @return Partner
     * @see Partner
     */
    Partner getPartner();

    /**
     * Get company's address
     *
     * @return Address
     * @see Address
     */
    Address getAddress();

    /**
     * Get company's documents
     *
     * @return Documents
     * @see Document
     */
    List<? extends Document> getDocuments();

    /**
     * Get company's phones
     *
     * @return Phones
     * @see PhoneAccount
     */
    List<? extends PhoneAccount> getPhones();
}
