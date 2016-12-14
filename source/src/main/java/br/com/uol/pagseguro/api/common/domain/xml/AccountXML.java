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

package br.com.uol.pagseguro.api.common.domain.xml;

import javax.xml.bind.annotation.XmlElement;

import br.com.uol.pagseguro.api.common.domain.Account;

/**
 * Class used to parse response of account
 *
 * @author PagSeguro Internet Ltda.
 */
public class AccountXML implements Account {
  private String publicKey;

  /**
   * Get public key
   *
   * @return Public Key of Account
   */
  public String getPublicKey() {
    return publicKey;
  }

  /**
   * Set public key
   *
   * @param publicKey Public Key of Account
   */
  @XmlElement
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountXML)) return false;

    AccountXML accountXML = (AccountXML) o;

    return publicKey != null ? publicKey.equals(accountXML.publicKey) : accountXML.publicKey == null;

  }

  @Override
  public String toString() {
    return "Account{" +
        "publicKey='" + publicKey + '\'' +
        '}';
  }
}
