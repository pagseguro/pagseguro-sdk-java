package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.*;
import br.com.uol.pagseguro.api.common.domain.converterXML.AddressV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentsV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.PhonesV2XMLConverter;
import br.com.uol.pagseguro.api.utils.ParseUtils;
import br.com.uol.pagseguro.api.utils.RequestXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

/**
 * Converter for V2 XML Authorization Register
 *
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationRegistrationV2XMLConverter {

    AuthorizationRegistrationV2XMLConverter() {
    }

    /**
     * Convert Interface for Authorization Registration in XML
     *
     * @param authorizationRegistration Interface for Authorization Registration
     * @see AuthorizationRegistration
     */
    public RequestXML convert(AuthorizationRegistration authorizationRegistration) throws JAXBException {
        PermissionsV2XMLConverter convertedPermissions = convertPermissions(authorizationRegistration.getPermissions());

        AuthorizationRequestV2XMLConverter authorizationRequest = new AuthorizationRequestV2XMLConverter();
        authorizationRequest.setReference(authorizationRegistration.getReference());
        authorizationRequest.setRedirectURL(authorizationRegistration.getRedirectURL());
        authorizationRequest.setPermissions(convertedPermissions);
        authorizationRequest.setNotificationURL(authorizationRegistration.getNotificationURL());

        if (null != authorizationRegistration.getAccount()) {
            AccountV2XMLConverter convertedAccount = convertAccount(authorizationRegistration.getAccount());
            authorizationRequest.setAccount(convertedAccount);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(AuthorizationRequestV2XMLConverter.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        jaxbMarshaller.marshal(authorizationRequest, stringWriter);
System.out.println(stringWriter.toString());
        return new RequestXML(stringWriter.toString());
    }

    private PermissionsV2XMLConverter convertPermissions(List<PermissionCode.Code> codeList) {
        PermissionsV2XMLConverter convertedPermissions = new PermissionsV2XMLConverter();
        convertedPermissions.setCode(ParseUtils.parseToCodeStringList(codeList));
        return convertedPermissions;
    }

    private AccountV2XMLConverter convertAccount(AccountRegisterSuggestion account) {
        AccountV2XMLConverter convertedAccount = new AccountV2XMLConverter();
        convertedAccount.setEmail(account.getEmail());
        convertedAccount.setType(account.getType());

        if (null != account.getPerson()) {
            PersonV2XMLConverter convertedPerson = convertPerson(account.getPerson());
            convertedAccount.setPerson(convertedPerson);

        } else if (null != account.getCompany()) {
            CompanyV2XMLConverter convertedCompany = convertCompany(account.getCompany());
            convertedAccount.setCompany(convertedCompany);

        } else {
            // Exception ... não pode ter "account" sem "person" ou "company"
        }

        return convertedAccount;
    }

    private PersonV2XMLConverter convertPerson(Person person) {
        DocumentsV2XMLConverter personDocuments = convertDocuments(person.getDocuments());
        PhonesV2XMLConverter personPhones = convertPhones(person.getPhones());
        AddressV2XMLConverter personAddress = convertAddress(person.getAddress());

        PersonV2XMLConverter convertedPerson = new PersonV2XMLConverter();
        convertedPerson.setName(person.getName());
        convertedPerson.setDocuments(personDocuments);
        convertedPerson.setBirthDate(person.getBirthDate());
        convertedPerson.setPhones(personPhones);
        convertedPerson.setAddress(personAddress);
        return convertedPerson;
    }

    private CompanyV2XMLConverter convertCompany(Company company) {
        DocumentsV2XMLConverter companyDocuments = convertDocuments(company.getDocuments());
        PartnerV2XMLConverter convertedPartner = convertPartner(company.getPartner());
        PhonesV2XMLConverter companyPhones = convertPhones(company.getPhones());
        AddressV2XMLConverter companyAddress = convertAddress(company.getAddress());

        CompanyV2XMLConverter convertedCompany = new CompanyV2XMLConverter();
        convertedCompany.setName(company.getName());
        convertedCompany.setDocuments(companyDocuments);
        convertedCompany.setDisplayName(company.getDisplayName());
        convertedCompany.setWebsiteURL(company.getWebsiteUrl());
        convertedCompany.setPartner(convertedPartner);
        convertedCompany.setPhones(companyPhones);
        convertedCompany.setAddress(companyAddress);
        return convertedCompany;
    }

    private PartnerV2XMLConverter convertPartner(Partner partner) {
        DocumentsV2XMLConverter partnerDocuments = convertDocuments(partner.getDocuments());

        PartnerV2XMLConverter convertedPartner = new PartnerV2XMLConverter();
        convertedPartner.setName(partner.getName());
        convertedPartner.setBirthDate(partner.getBirthDate());
        convertedPartner.setDocuments(partnerDocuments);
        return convertedPartner;
    }

    private DocumentsV2XMLConverter convertDocuments(List<? extends Document> documentAccountList) {
        DocumentsV2XMLConverter convertedDocument = new DocumentsV2XMLConverter();
        convertedDocument.setDocument(ParseUtils.parseToDocumentList(documentAccountList));
        return convertedDocument;
    }

    private PhonesV2XMLConverter convertPhones(List<? extends PhoneAccount> phoneAccountList) {
        PhonesV2XMLConverter convertedPhone = new PhonesV2XMLConverter();
        convertedPhone.setPhone(ParseUtils.parseToPhoneList(phoneAccountList));
        return convertedPhone;
    }

    private AddressV2XMLConverter convertAddress(Address address) {
        AddressV2XMLConverter convertedAddress = new AddressV2XMLConverter();
        convertedAddress.setPostalCode(address.getPostalCode());
        convertedAddress.setStreet(address.getStreet());
        convertedAddress.setNumber(address.getNumber());
        convertedAddress.setComplement(address.getComplement());
        convertedAddress.setDistrict(address.getDistrict());
        convertedAddress.setCity(address.getCity());
        convertedAddress.setState(address.getState());
        convertedAddress.setCountry(address.getCountry());
        return convertedAddress;
    }
}