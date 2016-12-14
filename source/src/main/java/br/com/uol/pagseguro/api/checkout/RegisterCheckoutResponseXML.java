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
package br.com.uol.pagseguro.api.checkout;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

/**
 * Implementation of {@code RegisteredCheckout} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "checkout")
public class RegisterCheckoutResponseXML implements RegisteredCheckout, XMLUnmarshallListener {

  private PagSeguro pagSeguro;

  private String code;
  private Date date;

  RegisterCheckoutResponseXML() {
  }

  public String getCode() {
    return code;
  }

  @XmlElement
  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  @XmlElement
  public void setDate(Date date) {
    this.date = date;
  }

  public String getRedirectURL() {
    return String.format(Endpoints.CHECKOUT_REDIRECT_URL, pagSeguro.getHostRedirect(), getCheckoutCode());
  }

  @Override
  public String getCheckoutCode() {
    return getCode();
  }

  @Override
  public void onUnmarshal(PagSeguro pagseguro, String rawData) {
    this.pagSeguro = pagseguro;
  }

  @Override
  public String toString() {
    return "RegisterCheckoutResponseXML{" +
        "pagSeguro=" + pagSeguro +
        ", code='" + code + '\'' +
        ", date=" + date +
        '}';
  }
}
