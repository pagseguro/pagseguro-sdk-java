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
package br.com.uol.pagseguro.api.application.authorization;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;


/**
 * Implementation of {@code RegisteredAuthorization} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "authorizationRequest")
public class RegisteredAuthorizationResponseXML implements RegisteredAuthorization,
    XMLUnmarshallListener {

  private String code;
  private Date date;
  private String redirectURL;
  private PagSeguro pagSeguro;

  RegisteredAuthorizationResponseXML() {
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
  public String getRedirectURL() {
    return String.format(Endpoints.AUTHORIZATION_REDIRECT_URL, pagSeguro.getHostRedirect(),
        getCode());
  }

  public void setRedirectURL(String redirectURL) {
    this.redirectURL = redirectURL;
  }

  public Date getDate() {
    return date;
  }

  @XmlElement
  public void setDate(Date date) {
    this.date = date;
  }

  public PagSeguro getPagSeguro() {
    return pagSeguro;
  }

  public void setPagSeguro(PagSeguro pagSeguro) {
    this.pagSeguro = pagSeguro;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RegisteredAuthorizationResponseXML)) return false;

    RegisteredAuthorizationResponseXML that = (RegisteredAuthorizationResponseXML) o;

    if (code != null ? !code.equals(that.code) : that.code != null) return false;
    return date != null ? date.equals(that.date) : that.date == null;

  }

  @Override
  public String toString() {
    return "RegisteredAuthorizationResponseXML{" +
        "code='" + code + '\'' +
        ", date=" + date +
        ", redirectURL='" + redirectURL + '\'' +
        '}';
  }

  /**
   * After unmarshal, this method is executed
   *
   * @param pagSeguro PagSeguro intance
   * @param rawData   Raw data by xml parsed
   */
  @Override
  public void onUnmarshal(PagSeguro pagSeguro, String rawData) {
    this.pagSeguro = pagSeguro;
    this.redirectURL = String.format(Endpoints.AUTHORIZATION_REDIRECT_URL, pagSeguro.getHostRedirect(), this.code);
  }
}
