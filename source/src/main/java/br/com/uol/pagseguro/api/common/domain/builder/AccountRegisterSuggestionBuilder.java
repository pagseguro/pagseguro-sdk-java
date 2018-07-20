package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.AccountRegisterSuggestion;
import br.com.uol.pagseguro.api.common.domain.Company;
import br.com.uol.pagseguro.api.common.domain.Person;
import br.com.uol.pagseguro.api.common.domain.enums.AccountType;
import br.com.uol.pagseguro.api.utils.Builder;

public final class AccountRegisterSuggestionBuilder implements Builder<AccountRegisterSuggestion> {

    private String email;
    private String type;
    private Person person;
    private Company company;

    /**
     * Set email of account register suggestion
     *
     * @param email Account Register Suggestion Email
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getEmail()
     */
    public AccountRegisterSuggestionBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Set type of account register suggestion
     *
     * @param type Account Register Suggestion Type
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getType()
     */
    public AccountRegisterSuggestionBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Set type of account register suggestion
     *
     * @param type Account Register Suggestion Type
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getType()
     */
    public AccountRegisterSuggestionBuilder withType(AccountType type) {
        this.type = type.getValue();
        return this;
    }

    /**
     * Set person of account register suggestion
     *
     * @param person Person
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getPerson()
     */
    public AccountRegisterSuggestionBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Set person account of account register suggestion
     *
     * @param personBuilder Builder for Person
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getPerson()
     */
    public AccountRegisterSuggestionBuilder withPerson(Builder<Person> personBuilder) {
        return withPerson(personBuilder.build());
    }

    /**
     * Set company of account register suggestion
     *
     * @param company Company
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getCompany()
     */
    public AccountRegisterSuggestionBuilder withCompany(Company company) {
        this.company = company;
        return this;
    }

    /**
     * Set company of account register suggestion
     *
     * @param companyBuilder Builder for Company
     * @return Builder for Account Register Suggestion
     * @see AccountRegisterSuggestion#getCompany()
     */
    public AccountRegisterSuggestionBuilder withCompany(Builder<Company> companyBuilder) {
        return withCompany(companyBuilder.build());
    }

    /**
     * Build the Account Register Suggestion
     *
     * @return Interface for Account Register Suggestion
     * @see AccountRegisterSuggestion
     */
    @Override
    public AccountRegisterSuggestion build() {
        return new SimpleAccountRegisterSuggestion(this);
    }

    /**
     * Implementation of {@code AccountRegisterSuggestion}
     */
    private static class SimpleAccountRegisterSuggestion implements AccountRegisterSuggestion {

        private final AccountRegisterSuggestionBuilder accountRegisterSuggestionBuilder;

        SimpleAccountRegisterSuggestion(AccountRegisterSuggestionBuilder accountRegisterSuggestionBuilder) {
            this.accountRegisterSuggestionBuilder = accountRegisterSuggestionBuilder;
        }

        @Override
        public String getEmail() {
            return accountRegisterSuggestionBuilder.email;
        }

        @Override
        public String getType() {
            return accountRegisterSuggestionBuilder.type;
        }

        @Override
        public Person getPerson() {
            return accountRegisterSuggestionBuilder.person;
        }

        @Override
        public Company getCompany() {
            return accountRegisterSuggestionBuilder.company;
        }

    }
}
