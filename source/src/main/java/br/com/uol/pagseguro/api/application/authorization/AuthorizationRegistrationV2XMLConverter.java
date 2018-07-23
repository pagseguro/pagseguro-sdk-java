package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.utils.RequestXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Converter for V2 XML Authorization Register
 *
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationRegistrationV2XMLConverter {

    private final static PermissionsV2XMLConverter PERMISSION_V_2_XML_CONVERTER = new PermissionsV2XMLConverter();
    private final static AccountV2XMLConverter ACCOUNT_V_2_XML_CONVERTER = new AccountV2XMLConverter();

    AuthorizationRegistrationV2XMLConverter() {
    }

    /**
     * Convert Interface for Authorization Registration in XML
     *
     * @param authorizationRegistration Interface for Authorization Registration
     * @see AuthorizationRegistration
     * @return RequestXML
     * @see RequestXML
     * @throws JAXBException Exception if a problem occurs during the XML parsing process
     */
    public RequestXML convert(AuthorizationRegistration authorizationRegistration) throws JAXBException {
        AuthorizationRequestV2XMLConverter authorizationRequest = new AuthorizationRequestV2XMLConverter();
        authorizationRequest.setReference(authorizationRegistration.getReference());
        authorizationRequest.setRedirectURL(authorizationRegistration.getRedirectURL());
        authorizationRequest.setPermissions(PERMISSION_V_2_XML_CONVERTER.convert(authorizationRegistration.getPermissions()));
        authorizationRequest.setNotificationURL(authorizationRegistration.getNotificationURL());
        authorizationRequest.setAccount(ACCOUNT_V_2_XML_CONVERTER.convert(authorizationRegistration.getAccount()));

        JAXBContext jaxbContext = JAXBContext.newInstance(AuthorizationRequestV2XMLConverter.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        jaxbMarshaller.marshal(authorizationRequest, stringWriter);

        return new RequestXML(stringWriter.toString());
    }
}