package br.com.uol.pagseguro.api.application.authorization;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "email", "type", "person", "company" })
public class AccountV2XMLConverter {

    private String email;
    private String type;
    private PersonV2XMLConverter person;
    private CompanyV2XMLConverter company;

    AccountV2XMLConverter() {
    }

    AccountV2XMLConverter(String email, String type, PersonV2XMLConverter person, CompanyV2XMLConverter company) {
        this.email = email;
        this.type = type;
        this.person = person;
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PersonV2XMLConverter getPerson() {
        return person;
    }

    public void setPerson(PersonV2XMLConverter person) {
        this.person = person;
    }

    public CompanyV2XMLConverter getCompany() {
        return company;
    }

    public void setCompany(CompanyV2XMLConverter company) {
        this.company = company;
    }
}
