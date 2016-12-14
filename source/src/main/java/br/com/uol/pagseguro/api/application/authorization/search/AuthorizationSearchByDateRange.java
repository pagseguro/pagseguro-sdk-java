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

import java.io.IOException;
import java.util.Map;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.PagSeguroCommand;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Search authorizations by date range
 *
 * @author PagSeguro Internet Ltda.
 */
class AuthorizationSearchByDateRange implements PagSeguroCommand<DataList<? extends
    AuthorizationSummary>> {

  private static final Log LOGGER = LoggerFactory.getLogger(AuthorizationSearchByDateRange.class);

  private final AuthorizationSearch authorizationSearch;

  private static final AuthorizationSearchV2MapConverter AUTHORIZATION_SEARCH_MP =
      new AuthorizationSearchV2MapConverter();

  /**
   * @param authorizationSearch The interface with params that you want to search
   */
  AuthorizationSearchByDateRange(AuthorizationSearch authorizationSearch) {
    this.authorizationSearch = authorizationSearch;
  }

  /**
   * Execute search by date range
   *
   * @param pagseguro  The instance of Pagseguro. Used on unmarshal and used to get current host.
   * @param httpClient Http client instance. Used to execute the search.
   * @return Authorizations list
   * @see AuthorizationSummary
   * @see DataList
   * @see PagSeguro
   * @see HttpClient#execute(HttpMethod, String, Map, HttpRequestBody)
   */
  @Override
  public DataList<? extends AuthorizationSummary> execute(PagSeguro pagseguro,
                                                          HttpClient httpClient) {
    LOGGER.info("Iniciando busca de autorizacao por intervalo de data");
    LOGGER.info("Convertendo valores");
    final RequestMap map = AUTHORIZATION_SEARCH_MP.convert(authorizationSearch);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.AUTHORIZATION_SEARCH,
          pagseguro.getHost(), map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar busca de autorizacao por intervalo de data");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    DataList<? extends AuthorizationSummary> authorizationsSummary =
        response.parseXMLContent(pagseguro, AuthorizationSearchResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Busca de autorizacao por intervalo de data finalizada");
    return authorizationsSummary;
  }
}
