/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */
package br.com.uol.pagseguro.api.application.authorization.search;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.xml.AccountXML;
import br.com.uol.pagseguro.api.common.domain.xml.PermissionXML;

/**
 * Implementation of {@code AuthorizationDetail}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "authorization")
public class AuthorizationDetailXML implements AuthorizationDetail {

  private String code;
  private String authorizerEmail;
  private Date creationDate;
  private String reference;
  private AccountXML account;
  private List<PermissionXML> permissions;

  AuthorizationDetailXML() {
  }

  public String getCode() {
    return code;
  }

  @XmlElement
  public void setCode(String code) {
    this.code = code;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  @XmlElement
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getReference() {
    return reference;
  }

  @XmlElement
  public void setReference(String reference) {
    this.reference = reference;
  }

  public AccountXML getAccount() {
    return account;
  }

  @XmlElement(name = "account")
  public void setAccountXML(AccountXML account) {
    this.account = account;
  }

  @Override
  public List<PermissionXML> getPermissions() {
    return permissions;
  }

  @XmlElementWrapper(name = "permissions")
  @XmlElement(name = "permission")
  public void setPermissions(List<PermissionXML> permissions) {
    this.permissions = permissions;
  }

  public String getAuthorizerEmail() {
    return authorizerEmail;
  }

  @XmlElement
  public void setAuthorizerEmail(String authorizerEmail) {
    this.authorizerEmail = authorizerEmail;
  }


  @Override
  public String toString() {
    return "AuthorizationDetailXML{" +
        "code='" + code + '\'' +
        ", authorizerEmail='" + authorizerEmail + '\'' +
        ", creationDate=" + creationDate +
        ", reference='" + reference + '\'' +
        ", account=" + account +
        ", permissions=" + permissions +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AuthorizationDetailXML)) return false;

    AuthorizationDetailXML that = (AuthorizationDetailXML) o;

    if (code != null ? !code.equals(that.code) : that.code != null) return false;
    if (authorizerEmail != null ? !authorizerEmail.equals(that.authorizerEmail) : that.authorizerEmail != null)
      return false;
    if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null)
      return false;
    if (reference != null ? !reference.equals(that.reference) : that.reference != null)
      return false;
    if (account != null ? !account.equals(that.account) : that.account != null) return false;
    return permissions != null ? permissions.equals(that.permissions) : that.permissions == null;

  }
}
