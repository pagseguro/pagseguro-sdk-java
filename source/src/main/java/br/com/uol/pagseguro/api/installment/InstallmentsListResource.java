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
package br.com.uol.pagseguro.api.installment;

import java.io.IOException;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.Builder;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Factory to installment list
 *
 * @author PagSeguro Internet Ltda.
 */
public class InstallmentsListResource {

  private static final Log LOGGER = LoggerFactory.getLogger(InstallmentsListResource.class);

  private static final InstallmentRequestV2MapConverter INSTALLMENT_LISTING_MC =
      new InstallmentRequestV2MapConverter();

  private final PagSeguro pagSeguro;
  private final HttpClient httpClient;

  /**
   * Constructor
   *
   * @param pagSeguro  Pagseguro
   * @param httpClient HttpClient
   */
  public InstallmentsListResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * List installments
   *
   * @param installmentListingBuilder Builder for Installment listing
   * @return Installment list
   */
  public DataList<? extends InstallmentDetail> list(
      Builder<InstallmentRequest> installmentListingBuilder) {
    return list(installmentListingBuilder.build());
  }

  /**
   * List installments
   *
   * @param installmentRequest Interface for Installment listing
   * @return Installment list
   */
  public DataList<? extends InstallmentDetail> list(InstallmentRequest installmentRequest) {
    LOGGER.info("Iniciando installment");
    LOGGER.info("Convertendo valores");
    final RequestMap map = INSTALLMENT_LISTING_MC.convert(installmentRequest);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.INSTALLMENT_SEARCH,
          pagSeguro.getHost(), map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar installment");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    DataList<? extends InstallmentDetail> installmentsDetail = response.parseXMLContent(pagSeguro,
        InstallmentListingResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Installment finalizado");
    return installmentsDetail;
  }
}
