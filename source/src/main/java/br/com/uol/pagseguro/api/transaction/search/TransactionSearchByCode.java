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
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.PagSeguroCommand;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Search transaction by code
 *
 * @author PagSeguro Internet Ltda.
 * @see PagSeguroCommand
 * @see TransactionDetail
 */
class TransactionSearchByCode implements PagSeguroCommand<TransactionDetail> {

  private static final Log LOGGER = LoggerFactory.getLogger(TransactionSearchByCode.class);

  private final String code;

  /**
   * Constructor
   *
   * @param code Code of transaction
   */
  TransactionSearchByCode(String code) {
    this.code = code;
  }

  /**
   * Execute search transaction by code
   *
   * @param pagseguro  Pagseguro instance
   * @param httpClient Http Client
   * @return Transaction Detail
   * @see TransactionDetail
   * @see PagSeguroCommand#execute(PagSeguro, HttpClient)
   * @see HttpClient#execute(HttpMethod, String, Map, HttpRequestBody)
   */
  @Override
  public TransactionDetail execute(PagSeguro pagseguro, HttpClient httpClient) {
    LOGGER.info("Iniciando busca de transacao por codigo");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: transactionCode:%s", code));
      response = httpClient.execute(HttpMethod.GET,
          String.format(Endpoints.TRANSACTION_SEARCH_BY_CODE, pagseguro.getHost(), code), null,
          null);
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar busca de transacao por codigo");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    TransactionDetail transaction = response.parseXMLContent(pagseguro, TransactionDetailXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Busca de transacao por codigo finalizada");
    return transaction;
  }

}
