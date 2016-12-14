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

package br.com.uol.pagseguro.api.transaction.search;

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
 * Search transactions abandoned
 *
 * @author PagSeguro Internet Ltda.
 * @see PagSeguroCommand
 * @see TransactionSummary
 */
class TransactionSearchByAbandoned implements PagSeguroCommand<DataList<? extends
    TransactionSummary>> {

  private static final Log LOGGER = LoggerFactory.getLogger(TransactionSearchByAbandoned.class);

  private static final TransactionSearchV2MapConverter TRANSACTION_SEARCH_MP =
      new TransactionSearchV2MapConverter();

  private final TransactionSearch transactionSearch;

  /**
   * Constructor
   *
   * @param transactionSearch Interface for Transaction Search
   */
  TransactionSearchByAbandoned(TransactionSearch transactionSearch) {
    this.transactionSearch = transactionSearch;
  }

  /**
   * Execute Search Transactions Abandoned
   *
   * @param pagseguro  Pageseguro instance
   * @param httpClient Http Client
   * @return Transactions Abandoned List
   * @see PagSeguroCommand#execute(PagSeguro, HttpClient)
   * @see TransactionSummary
   * @see HttpClient#execute(HttpMethod, String, Map, HttpRequestBody)
   */
  @Override
  public DataList<? extends TransactionSummary> execute(PagSeguro pagseguro,
                                                        HttpClient httpClient) {
    LOGGER.info("Iniciando busca de transacao abandonada");
    LOGGER.info("Convertendo valores");
    final RequestMap map = TRANSACTION_SEARCH_MP.convert(transactionSearch);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.TRANSACTION_ABANDONED,
          pagseguro.getHost(), map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar busca de transacao abandonada");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    DataList<? extends TransactionSummary> transactionsSummary = response.parseXMLContent(pagseguro,
        TransactionSearchResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Busca de transacao abandonada finalizada");
    return transactionsSummary;
  }
}
