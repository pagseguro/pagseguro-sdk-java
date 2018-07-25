package br.com.uol.pagseguro.api.common.domain;

/**
 * Interface for Account Register Suggestion
 *
 * @author PagSeguro Internet Ltda.
 */
public interface AccountRegisterSuggestion {

    /**
     * Get email account
     *
     * @return Email
     */
    String getEmail();

    /**
     * Get account type
     *
     * @return Type
     */
    String getType();

    /**
     * Get person account data
     *
     * @return Person
     */
    Person getPerson();

    /**
     * Get company account data
     *
     * @return Company
     */
    Company getCompany();
}
