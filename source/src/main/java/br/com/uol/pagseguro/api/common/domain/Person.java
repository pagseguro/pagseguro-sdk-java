package br.com.uol.pagseguro.api.common.domain;

import java.util.Date;
import java.util.List;

/**
 * Interface for Person
 *
 * @author PagSeguro Internet Ltda.
 */
public interface Person {

    /**
     * Get person's name
     *
     * @return Name
     */
    String getName();

    /**
     * Get person's birth date
     *
     * @return Birth Date
     */
    String getBirthDate();

    /**
     * Get person's address
     *
     * @return Address
     * @see Address
     */
    Address getAddress();

    /**
     * Get person's documents
     *
     * @return Documents
     * @see Document
     */
    List<? extends Document> getDocuments();

    /**
     * Get person's phones
     *
     * @return Phones
     * @see PhoneAccount
     */
    List<? extends PhoneAccount> getPhones();
}