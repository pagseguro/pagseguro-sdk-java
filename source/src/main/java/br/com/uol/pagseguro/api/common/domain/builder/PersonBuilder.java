package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.Address;
import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.Person;
import br.com.uol.pagseguro.api.common.domain.PhoneAccount;
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Builder for Person
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PersonBuilder implements Builder<Person> {

    private String name;
    private String birthDate;
    private Address address;
    private List<Document> documents = new ArrayList<Document>();
    private List<PhoneAccount> phones = new ArrayList<PhoneAccount>();

    /**
     * Set person's name
     *
     * @param name Name
     * @return Builder for person
     * @see Person#getName()
     */
    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set person's birth date
     *
     * @param birthDate Brith Date
     * @return Builder for person
     * @see Person#getBirthDate()
     */
    public PersonBuilder withBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Set person's address
     *
     * @param address Address
     * @return Builder for person
     * @see Person#getAddress()
     */
    public PersonBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     * Set person's address
     *
     * @param addressBuilder Builder for Address
     * @return Builder for person
     * @see Person#getAddress()
     */
    public PersonBuilder withAddress(Builder<Address> addressBuilder) {
        return withAddress(addressBuilder.build());
    }

    /**
     * Add document to person
     *
     * @param document Document
     * @return Builder for person
     * @see Person#getDocuments()
     */
    public PersonBuilder addDocument(Document document) {
        documents.add(document);
        return this;
    }

    /**
     * Add document to person
     *
     * @param documentBuilder Builder for Document
     * @return Builder for person
     * @see Person#getDocuments()
     */
    public PersonBuilder addDocument(Builder<Document> documentBuilder) {
        return addDocument(documentBuilder.build());
    }

    /**
     * Add document to person
     *
     * @param documents Documents
     * @return Builder for person
     * @see Person#getDocuments()
     */
    public PersonBuilder addDocuments(Iterable<? extends Document> documents) {
        for (Document document : documents) {
            addDocument(document);
        }
        return this;
    }

    /**
     * Add phone to person
     *
     * @param phone Phone
     * @return Builder for person
     * @see Person#getPhones()
     */
    public PersonBuilder addPhone(PhoneAccount phone) {
        phones.add(phone);
        return this;
    }

    /**
     * Add phone to person
     *
     * @param phoneAccountBuilder Builder for Phone Account
     * @return Builder for person
     * @see Person#getPhones()
     */
    public PersonBuilder addPhone(Builder<PhoneAccount> phoneAccountBuilder) {
        return addPhone(phoneAccountBuilder.build());
    }

    /**
     * Add phone to person
     *
     * @param phones Phone
     * @return Builder for person
     * @see Person#getPhones()
     */
    public PersonBuilder addPhones(Iterable<? extends PhoneAccount> phones) {
        for (PhoneAccount phone : phones) {
            addPhone(phone);
        }
        return this;
    }

    /**
     * Build the person
     *
     * @return Interface for person
     * @see Person
     */
    @Override
    public Person build() {
        return new SimplePerson(this);
    }

    /**
     * Implementation of {@code Person}
     */
    private static class SimplePerson implements Person {

        private final PersonBuilder personBuilder;

        SimplePerson(PersonBuilder personBuilder) {
            this.personBuilder = personBuilder;
        }

        @Override
        public String getName() {
            return personBuilder.name;
        }

        @Override
        public String getBirthDate() {
            return personBuilder.birthDate;
        }

        @Override
        public Address getAddress() {
            return personBuilder.address;
        }

        @Override
        public List<Document> getDocuments() {
            return personBuilder.documents;
        }

        @Override
        public List<PhoneAccount> getPhones() {
            return personBuilder.phones;
        }

    }
}
