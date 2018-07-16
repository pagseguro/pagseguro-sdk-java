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
package br.com.uol.pagseguro.api.session;

import java.io.IOException;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Factory to create session
 *
 * @author PagSeguro Internet Ltda.
 */
public class SessionResource {

  private final PagSeguro pagSeguro;
  private final HttpClient httpClient;

  public SessionResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * Create session by application
   *
   * @param authorizationCode Authorization code
   * @return Response of session create
   * @see CreatedSession
   */
  public CreatedSession create(String authorizationCode) {
    final RequestMap map = new RequestMap();
    map.putString("authorizationCode", authorizationCode);
    final HttpResponse response;
    try {
      response = httpClient.execute(HttpMethod.POST,
          String.format(Endpoints.SESSION_CREATE_APPLICATION, pagSeguro.getHost(),
              map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
    } catch (IOException e) {
      throw new PagSeguroLibException(e);
    }

    return response.parseXMLContent(pagSeguro, CreatedSessionXML.class);
  }

  /**
   * Create seller session
   *
   * @return Response of session create
   * @see CreatedSession
   */
  public CreatedSession create() {
    final HttpResponse response;
    try {
      response = httpClient.execute(HttpMethod.POST, String.format(Endpoints.SESSION_CREATE,
          pagSeguro.getHost()), null, null);
    } catch (IOException e) {
      throw new PagSeguroLibException(e);
    }

    return response.parseXMLContent(pagSeguro, CreatedSessionXML.class);
  }

  /**
   * Create session by application for direct pre approval
   *
   * @param authorizationCode Authorization code
   * @return Response of session create
   * @see CreatedSession
   */
  public CreatedSession createDirectPreApproval(String authorizationCode) {
    final RequestMap map = new RequestMap();
    map.putString("authorizationCode", authorizationCode);
    final HttpResponse response;
    try {
      response = httpClient.execute(HttpMethod.POST,
              String.format(Endpoints.SESSION_DIRECT_PRE_APPROVAL_CREATE_APPLICATION, pagSeguro.getHost(),
                      map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
    } catch (IOException e) {
      throw new PagSeguroLibException(e);
    }

    return response.parseXMLContent(pagSeguro, CreatedSessionXML.class);
  }

  /**
   * Create seller session for direct pre approval
   *
   * @return Response of session create
   * @see CreatedSession
   */
  public CreatedSession createDirectPreApproval() {
    final HttpResponse response;
    try {
      response = httpClient.execute(HttpMethod.POST, String.format(Endpoints.SESSION_DIRECT_PRE_APPROVAL_CREATE,
              pagSeguro.getHost()), null, null);
    } catch (IOException e) {
      throw new PagSeguroLibException(e);
    }

    return response.parseXMLContent(pagSeguro, CreatedSessionXML.class);
  }
}
