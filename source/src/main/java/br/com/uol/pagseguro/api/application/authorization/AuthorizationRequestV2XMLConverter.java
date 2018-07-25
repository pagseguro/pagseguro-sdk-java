package br.com.uol.pagseguro.api.application.authorization;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "authorizationRequest")
@XmlType(propOrder = {"reference", "permissions", "redirectURL", "notificationURL", "account"})
public class AuthorizationRequestV2XMLConverter {

    private String reference;
    private String redirectURL;
    private String notificationURL;
    private PermissionsV2XMLConverter permissions;
    private AccountV2XMLConverter account;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public PermissionsV2XMLConverter getPermissions() {
        return permissions;
    }

    public void setPermissions(PermissionsV2XMLConverter permissions) {
        this.permissions = permissions;
    }

    public String getNotificationURL() {
        return notificationURL;
    }

    public void setNotificationURL(String notificationURL) {
        this.notificationURL = notificationURL;
    }

    public AccountV2XMLConverter getAccount() {
        return account;
    }

    public void setAccount(AccountV2XMLConverter account) {
        this.account = account;
    }
}
