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
package br.com.uol.pagseguro.api.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.UUID;

import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Responsible for adding the user's credentials and correlation id to url and execute the requests
 * Responsible for communicating with the api.
 *
 * @author PagSeguro Internet Ltda.
 */
public class AuthenticatedHttpClient implements HttpClient {

  private static Log LOGGER = LoggerFactory.getLogger(AuthenticatedHttpClient.class.getName());

  private final HttpClient httpClient;

  private final String credentialQuery;

  /**
   * Constructor
   *
   * @param httpClient Http client
   * @param credential Credential
   */
  public AuthenticatedHttpClient(HttpClient httpClient, Credential credential) {
    this.httpClient = httpClient;
    try {
      this.credentialQuery = URLDecoder.decode(credential.asMap().toUrlEncode(CharSet.ENCODING_UTF),
          CharSet.ENCODING_UTF);
    } catch (UnsupportedEncodingException e) {
      throw new PagSeguroLibException(e);
    }
  }

  /**
   * Execute the request
   *
   * @param method    Http Method
   * @param targetURL Target url
   * @param headers   Headers
   * @param body      Http Request Body
   * @return Http Response
   */
  @Override
  public HttpResponse execute(HttpMethod method, String targetURL, Map<String, String> headers,
                              HttpRequestBody body) throws IOException {
    try {
      return httpClient.execute(method, appendParameters(targetURL), headers, body);
    } catch (URISyntaxException e) {
      throw new PagSeguroLibException(e);
    }
  }
  //@TODO validar json
  public HttpResponse executeJson(HttpMethod method, String targetURL, Map<String, String> headers,
                                  HttpJsonRequestBody body) throws IOException {
    try {
      return httpClient.executeJson(method, appendParameters(targetURL), headers, body);
    } catch (URISyntaxException e) {
      throw new PagSeguroLibException(e);
    }
  }

  /**
   * Append parameters to uri
   *
   * @param uri Uri
   * @return Url with parameters
   */
  private String appendParameters(String uri) throws UnsupportedEncodingException,
      URISyntaxException {
    return appendCorrelationId(appendCredential(uri));
  }

  /**
   * Add correlation id to url
   *
   * @param uri Url
   * @return Url with correlation id
   * @throws URISyntaxException if invalid url is used
   * @throws UnsupportedEncodingException if not accepted encode is used
   */
  public String appendCorrelationId(String uri) throws URISyntaxException,
      UnsupportedEncodingException {
    URI oldUri = new URI(uri);
    String newQuery = oldUri.getQuery();
    String correlationId = UUID.randomUUID().toString();
    RequestMap correlationMap = new RequestMap();
    correlationMap.putString("correlationId", correlationId);
    LOGGER.info(String.format("Correlation Id: %s", correlationId));
    String correlationIdQuery = URLDecoder.decode(correlationMap.toUrlEncode(CharSet.ENCODING_UTF),
        CharSet.ENCODING_UTF);
    if (newQuery == null) {
      newQuery = correlationIdQuery;
    } else {
      newQuery += "&" + correlationIdQuery;
    }
    return new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(), newQuery,
        oldUri.getFragment()).toString();
  }

  /**
   * Add credential to url
   *
   * @param uri Url
   * @return Url with credential
   * @throws URISyntaxException if invalid url is used
   * @throws UnsupportedEncodingException if not accepted encode is used
   */
  public String appendCredential(String uri) throws URISyntaxException,
      UnsupportedEncodingException {
    URI oldUri = new URI(uri);
    String newQuery = oldUri.getQuery();
    if (newQuery == null) {
      newQuery = credentialQuery;
    } else {
      newQuery += "&" + credentialQuery;
    }
    return new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(), newQuery,
        oldUri.getFragment()).toString();
  }
}
