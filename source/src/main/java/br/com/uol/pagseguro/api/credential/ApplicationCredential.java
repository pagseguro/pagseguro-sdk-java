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

import java.util.Objects;

/**
 * The PagSeguro applications model allows your application to create checkouts, receive payment
 * notifications and other functions on behalf of the client without the need to configure tokens,
 * return URL and other information in your account PagSeguro.
 *
 * @author PagSeguro Internet Ltda.
 * @see Credential
 */
public class ApplicationCredential extends Credential {

  private String appId;
  private String appKey;
  private String authorizationCode;

  /**
   * Constructor
   *
   * @param appId  The application id
   * @param appKey Specifies the corresponding token to PagSeguro application that is making the
   *               request.
   */
  ApplicationCredential(String appId, String appKey) {
    if (appId == null || appKey == null) {
      throw new NullPointerException();
    }
    this.appId = appId;
    this.appKey = appKey;
  }

  /**
   * Constructor
   *
   * @param appId  The application id
   * @param appKey Specifies the corresponding token to PagSeguro application that is making the
   *               request.
   */
  ApplicationCredential(String appId, String appKey, String authorizationCode) {
    if (appId == null || appKey == null || authorizationCode==null) {
      throw new NullPointerException();
    }
    this.appId = appId;
    this.appKey = appKey;
    this.authorizationCode=authorizationCode;
  }

  /**
   * Convert attributes of credential to request map
   *
   * @return Request map. Used to set parameters of api
   * @see RequestMap
   * @see Credential#asMap()
   */
  @Override
  public RequestMap asMap() {
    final RequestMap form = new RequestMap();
    form.putString("appId", appId);
    form.putString("appKey", appKey);
    if(authorizationCode!=null)
      form.putString("authorizationCode", authorizationCode);
    return form;
  }

  @Override
  public String toString() {
    return "ApplicationCredential{" +
            "appId='" + appId + '\'' +
            ", appKey='" + appKey + '\'' +
            ", authorizationCode='" + authorizationCode + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApplicationCredential that = (ApplicationCredential) o;
    return Objects.equals(appId, that.appId) &&
            Objects.equals(appKey, that.appKey) &&
            Objects.equals(authorizationCode, that.authorizationCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appId, appKey, authorizationCode);
  }
}
