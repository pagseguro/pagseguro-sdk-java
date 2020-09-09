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
 * Credentials of api.
 * Used to authenticate in api
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class Credential {

  /**
   * Convert the credential on a request map
   *
   * @return The request map
   */
  public abstract RequestMap asMap();

  /**
   * Seller credential.
   * The seller uses this credential.
   *
   * If you are creating sales for your own store. then you should use this type of authentication.
   *
   * @param email The e-mail seller
   * @param token The token seller
   * @return Seller Credential
   */
  public static Credential sellerCredential(String email, String token) {
    return new SellerCredential(email, token);
  }

  /**
   * Application Credential.
   * Use this credential when you create checkouts,
   * receive payment notifications and other functions on behalf of the client without the need to
   *
   * @param appId  The Application id
   * @param appKey Specifies the corresponding token to PagSeguro application that is making the
   *               request.
   * @return ApplicationCredential
   */
  public static Credential applicationCredential(String appId, String appKey) {
    return new ApplicationCredential(appId, appKey);
  }

  /**
   * Application Credential.
   * Use this credential when you create checkouts,
   * receive payment notifications and other functions on behalf of the client without the need to
   *
   * @param appId  The Application id
   * @param appKey Specifies the corresponding token to PagSeguro application that is making the
   *               request.
   * @return ApplicationCredential
   */
  public static Credential applicationCredential(String appId, String appKey, String authorizationCode) {
    return new ApplicationCredential(appId, appKey, authorizationCode);
  }

}
