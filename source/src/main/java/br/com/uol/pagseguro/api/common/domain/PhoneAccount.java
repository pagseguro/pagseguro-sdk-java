package br.com.uol.pagseguro.api.common.domain;

/**
 * Interface for phone account
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PhoneAccount extends Phone {

    /**
     * Get type of phone account
     *
     * @return Type
     */
    String getType();
}
