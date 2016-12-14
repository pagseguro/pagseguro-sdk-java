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
package br.com.uol.pagseguro.api.credential;

import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Seller credential.
 * The seller uses this credential.
 *
 * If you are creating sales for your own store. then you should use this type of authentication.
 *
 * @author PagSeguro Internet Ltda.
 * @see Credential
 */
public class SellerCredential extends Credential {

  private final String email;

  private final String token;

  /**
   * Constructor
   *
   * @param email The email of seller
   * @param token The token of seller
   */
  SellerCredential(String email, String token) {
    if (email == null || token == null) {
      throw new NullPointerException();
    }
    this.email = email;
    this.token = token;
  }

  /**
   * Convert attributes of credentials on a request map
   *
   * @return Request map
   * @see RequestMap
   */
  @Override
  public RequestMap asMap() {
    final RequestMap form = new RequestMap();
    form.putString("email", email);
    form.putString("token", token);
    return form;
  }

  @Override
  public String toString() {
    return "SellerCredential{" +
           "email='" + email + '\'' +
           ", token='" + token + '\'' +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SellerCredential)) return false;

    SellerCredential that = (SellerCredential) o;

    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    return token != null ? token.equals(that.token) : that.token == null;

  }

}
