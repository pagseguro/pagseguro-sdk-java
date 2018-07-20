package br.com.uol.pagseguro.api.common.domain;

import java.util.Date;
import java.util.List;

/**
 * Interface for Partner
 *
 * @author PagSeguro Internet Ltda.
 */
public interface Partner {

    /**
     * Get partner's name
     *
     * @return Name
     */
    String getName();

    /**
     * Get partner's birth date
     *
     * @return Birth Date
     */
    String getBirthDate();

    /**
     * Get partner's documents
     *
     * @return Documents
     * @see Document
     */
    List<? extends Document> getDocuments();
}
