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

package br.com.uol.pagseguro.api.preapproval;

import java.io.IOException;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.preapproval.cancel.CancelPreApprovalResponseXML;
import br.com.uol.pagseguro.api.preapproval.cancel.CancelledPreApproval;
import br.com.uol.pagseguro.api.preapproval.cancel.PreApprovalCancellation;
import br.com.uol.pagseguro.api.preapproval.cancel.PreApprovalCancellationV2MapConverter;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalSearchResource;
import br.com.uol.pagseguro.api.utils.Builder;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;


/**
 * Factory to pre approval
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalsResource {

  private static final Log LOGGER = LoggerFactory.getLogger(PreApprovalsResource.class.getName());

  private static final PreApprovalRegistrationV2MapConverter PRE_APPROVAL_REGISTRATION_MC =
      new PreApprovalRegistrationV2MapConverter();

  private static final PreApprovalChargingV2MapConverter PRE_APPROVAL_CHARGING_MC =
      new PreApprovalChargingV2MapConverter();

  private static final PreApprovalCancellationV2MapConverter PRE_APPROVAL_CANCELLATION_MC =
      new PreApprovalCancellationV2MapConverter();

  private final PagSeguro pagSeguro;
  private final HttpClient httpClient;

  public PreApprovalsResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * Pre Approval Registration
   *
   * @param preApprovalRegistrationBuilder Builder for Pre Approval Registration
   * @return Response of pre approval registration
   * @see PreApprovalRegistration
   * @see RegisteredPreApproval
   */
  public RegisteredPreApproval register(
      Builder<PreApprovalRegistration> preApprovalRegistrationBuilder) {
    return register(preApprovalRegistrationBuilder.build());
  }

  /**
   * Pre Approval Registration
   *
   * @param preApprovalRegistration Pre Approval Registration
   * @return Response of pre approval registration
   * @see PreApprovalRegistration
   * @see RegisteredPreApproval
   */
  public RegisteredPreApproval register(PreApprovalRegistration preApprovalRegistration) {
    LOGGER.info("Iniciando registro pre approval");
    LOGGER.info("Convertendo valores");
    final RequestMap map = PRE_APPROVAL_REGISTRATION_MC.convert(preApprovalRegistration);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.POST, String.format(Endpoints.PRE_APPROVAL_REQUEST,
          pagSeguro.getHost()), null, map.toHttpRequestBody(CharSet.ENCODING_ISO));
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar registro pre approval");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    RegisterPreApprovalResponseXML registeredPreApproval = response.parseXMLContent(pagSeguro,
        RegisterPreApprovalResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Registro pre approval finalizado");
    return registeredPreApproval;
  }

  /**
   * Pre Approval Cancellation
   *
   * @param preApprovalCancellationBuilder Builder for Pre Approval Cancellation
   * @return Response of Pre Approval Cancellation
   * @see PreApprovalCancellation
   * @see CancelledPreApproval
   */
  public CancelledPreApproval cancel(
      Builder<PreApprovalCancellation> preApprovalCancellationBuilder) {
    return cancel(preApprovalCancellationBuilder.build());
  }

  /**
   * Pre Approval Cancellation
   *
   * @param preApprovalCancellation Pre Approval Cancellation
   * @return Response of Pre Approval Cancellation
   * @see PreApprovalCancellation
   * @see CancelledPreApproval
   */
  public CancelledPreApproval cancel(PreApprovalCancellation preApprovalCancellation) {
    LOGGER.info("Iniciando cancelamento pre approval");
    LOGGER.info("Convertendo valores");
    final RequestMap map = PRE_APPROVAL_CANCELLATION_MC.convert(preApprovalCancellation);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: preApprovalCode:%s, %s",
          preApprovalCancellation.getCode(), map));
      response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.PRE_APPROVAL_CANCEL,
          pagSeguro.getHost(), preApprovalCancellation.getCode(),
          map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
      LOGGER.info(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar cancelamento pre approval");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    CancelPreApprovalResponseXML cancelledPreApproval = response.parseXMLContent(pagSeguro,
        CancelPreApprovalResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Cancelamento pre approval finalizado");
    return cancelledPreApproval;
  }

  /**
   * Pre Approval Search
   *
   * @return Factory to pre approval search
   * @see PreApprovalSearchResource
   */
  public PreApprovalSearchResource search() {
    return new PreApprovalSearchResource(pagSeguro, httpClient);
  }

  /**
   * Pre Approval Charging
   *
   * @param preApprovalChargingBuilder Builder for Pre Approval Charging
   * @return Response of Pre Approval Charging
   * @see PreApprovalCharging
   * @see ChargedPreApproval
   */
  public ChargedPreApproval charge(Builder<PreApprovalCharging> preApprovalChargingBuilder) {
    return charge(preApprovalChargingBuilder.build());
  }

  /**
   * Pre Approval Charging
   *
   * @param preApprovalCharging Pre Approval Charging
   * @return Response of Pre Approval Charging
   * @see PreApprovalCharging
   * @see ChargedPreApproval
   */
  public ChargedPreApproval charge(PreApprovalCharging preApprovalCharging) {
    LOGGER.info("Iniciando cobranca");
    LOGGER.info("Convertendo valores");
    final RequestMap map = PRE_APPROVAL_CHARGING_MC.convert(preApprovalCharging);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.POST, String.format(Endpoints.PRE_APPROVAL_CHARGE,
          pagSeguro.getHost()), null, map.toHttpRequestBody(CharSet.ENCODING_ISO));
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar cobranca");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    ChargePreApprovalResponseXML chargedPreApproval = response.parseXMLContent(pagSeguro,
        ChargePreApprovalResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Cobranca finalizada");
    return chargedPreApproval;
  }
}
