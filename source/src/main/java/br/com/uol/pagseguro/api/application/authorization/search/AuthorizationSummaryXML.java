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

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.xml.AccountXML;
import br.com.uol.pagseguro.api.common.domain.xml.PermissionXML;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

/**
 * Implementation of {@code AuthorizationSummary}
 *
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationSummaryXML implements AuthorizationSummary, XMLUnmarshallListener {

  private PagSeguro pagseguro;
  private String code;
  private String authorizerEmail;
  private Date creationDate;
  private String reference;
  private AccountXML account;
  private List<PermissionXML> permissions;

  AuthorizationSummaryXML() {
  }

  /**
   * Executed after unmarshal
   *
   * @param pagseguro Pagesguro instance
   * @param rawData   The response xml returned by api
   */
  @Override
  public void onUnmarshal(PagSeguro pagseguro, String rawData) {
    this.pagseguro = pagseguro;
  }

  @Override
  public AuthorizationDetail getDetail() {
    return pagseguro.authorizations().search().byCode(getCode());
  }

  @Override
  public String getCode() {
    return code;
  }

  @XmlElement
  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getAuthorizerEmail() {
    return authorizerEmail;
  }

  @XmlElement
  public void setAuthorizerEmail(String authorizerEmail) {
    this.authorizerEmail = authorizerEmail;
  }

  @Override
  public Date getCreationDate() {
    return creationDate;
  }

  @XmlElement
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public String getReference() {
    return reference;
  }

  @XmlElement
  public void setReference(String reference) {
    this.reference = reference;
  }

  @Override
  public AccountXML getAccount() {
    return account;
  }

  @XmlElement
  public void setAccount(AccountXML account) {
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

  @Override
  public String toString() {
    return "AuthorizationSummaryXML{" +
           "pagseguro=" + pagseguro +
           ", code='" + code + '\'' +
           ", authorizerEmail='" + authorizerEmail + '\'' +
           ", creationDate=" + creationDate +
           ", reference='" + reference + '\'' +
           ", account=" + account +
           ", permissions=" + permissions +
           '}';
  }
}
