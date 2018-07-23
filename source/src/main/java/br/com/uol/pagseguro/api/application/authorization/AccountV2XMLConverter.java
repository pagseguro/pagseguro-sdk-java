package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.AccountRegisterSuggestion;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "email", "type", "person", "company" })
public class AccountV2XMLConverter {

    private final static PersonV2XMLConverter PERSON_V_2_XML_CONVERTER = new PersonV2XMLConverter();
    private final static CompanyV2XMLConverter COMPANY_V_2_XML_CONVERTER = new CompanyV2XMLConverter();

    private String email;
    private String type;
    private PersonV2XMLConverter person;
    private CompanyV2XMLConverter company;

    public AccountV2XMLConverter() {
    }

    public AccountV2XMLConverter(String email, String type, PersonV2XMLConverter person, CompanyV2XMLConverter company) {
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

    /**
     * Convert attributes in request XML
     *
     * @param account Account register suggestion data
     * @see AccountRegisterSuggestion
     * @return AccountV2XMLConverter
     */
    public AccountV2XMLConverter convert(AccountRegisterSuggestion account) {
        if (account == null) {
            return null;
        }

        AccountV2XMLConverter convertedAccount = new AccountV2XMLConverter();
        convertedAccount.setEmail(account.getEmail());
        convertedAccount.setType(account.getType());
        convertedAccount.setPerson(PERSON_V_2_XML_CONVERTER.convert(account.getPerson()));
        convertedAccount.setCompany(COMPANY_V_2_XML_CONVERTER.convert(account.getCompany()));
        return convertedAccount;
    }
}
