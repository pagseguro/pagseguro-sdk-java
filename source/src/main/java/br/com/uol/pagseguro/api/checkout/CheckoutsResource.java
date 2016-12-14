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
package br.com.uol.pagseguro.api.checkout;

import java.io.IOException;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
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
 * Factory to checkout registration
 *
 * @author PagSeguro Internet Ltda.
 */
public class CheckoutsResource {

  private static final Log LOGGER = LoggerFactory.getLogger(CheckoutsResource.class.getName());

  private static final CheckoutRegistrationV2MapConverter CHECKOUT_REGISTRATION_MC =
      new CheckoutRegistrationV2MapConverter();

  private final PagSeguro pagSeguro;

  private final HttpClient httpClient;

  public CheckoutsResource(PagSeguro pagSeguroAPI, HttpClient httpClient) {
    this.pagSeguro = pagSeguroAPI;
    this.httpClient = httpClient;
  }

  /**
   * Checkout Registration
   *
   * @param checkoutRegistrationBuilder Builder for Interface with attributes for checkout
   *                                    registration
   * @return Response of checkout registration
   * @see CheckoutRegistration
   * @see RegisteredCheckout
   */
  public RegisteredCheckout register(Builder<CheckoutRegistration> checkoutRegistrationBuilder) {
    return register(checkoutRegistrationBuilder.build());
  }

  /**
   * Checkout Registration
   *
   * @param checkoutRegistration Interface with attributes for checkout registration
   * @return Response of checkout registration
   * @see CheckoutRegistration
   * @see RegisteredCheckout
   */
  public RegisteredCheckout register(CheckoutRegistration checkoutRegistration) {
    LOGGER.info("Iniciando checkout");
    LOGGER.info("Convertendo valores");
    final RequestMap map = CHECKOUT_REGISTRATION_MC.convert(checkoutRegistration);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.POST, String.format(Endpoints.CHECKOUT_REQUEST,
          pagSeguro.getHost()), null, map.toHttpRequestBody(CharSet.ENCODING_ISO));
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar checkout");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    RegisterCheckoutResponseXML registeredCheckout = response.parseXMLContent(pagSeguro,
        RegisterCheckoutResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Checkout finalizado");
    return registeredCheckout;
  }

}
