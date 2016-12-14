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
    return form;
  }

  @Override
  public String toString() {
    return "ApplicationCredential{" +
           "appId='" + appId + '\'' +
           ", appKey='" + appKey + '\'' +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ApplicationCredential)) return false;

    ApplicationCredential that = (ApplicationCredential) o;

    if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
    return appKey != null ? appKey.equals(that.appKey) : that.appKey == null;

  }

}
