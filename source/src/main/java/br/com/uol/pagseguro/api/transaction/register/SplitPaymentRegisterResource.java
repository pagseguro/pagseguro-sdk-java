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

package br.com.uol.pagseguro.api.transaction.register;

import java.io.IOException;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.Bank;
import br.com.uol.pagseguro.api.common.domain.CreditCard;
import br.com.uol.pagseguro.api.common.domain.TransactionMethod;
import br.com.uol.pagseguro.api.common.domain.converter.BankV3MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.CreditCardV3MapConverter;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetailXML;
import br.com.uol.pagseguro.api.utils.Builder;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Factory to register direct payments.
 * You can create direct payments with bank slip,
 * online debit, credit card national.
 *
 * @author PagSeguro Internet Ltda.
 */
public class SplitPaymentRegisterResource {

  private static final Log LOGGER = LoggerFactory.getLogger(SplitPaymentRegisterResource.class);

  private final PagSeguro pagSeguro;

  private final HttpClient httpClient;

  private final SplitPaymentRegistration splitPaymentRegistration;

  private static final SplitPaymentRegistrationV3MapConverter SPLIT_PAYMENT_REGISTRATION_MC =
      new SplitPaymentRegistrationV3MapConverter();

  private static final CreditCardV3MapConverter CREDIT_CARD_MC = new CreditCardV3MapConverter();

  private static final BankV3MapConverter BANK_MC = new BankV3MapConverter();

  /**
   * Constructor
   *
   * @param pagSeguro                Pagseguro
   * @param httpClient               Http Client
   * @param splitPaymentRegistration Interface with the attributes of Split Payment Registration.
   */
  public SplitPaymentRegisterResource(PagSeguro pagSeguro,
                                      HttpClient httpClient,
                                      SplitPaymentRegistration splitPaymentRegistration) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
    this.splitPaymentRegistration = splitPaymentRegistration;
  }

  /**
   * Execute split payment with bank slip
   *
   * @return Response of split payment registration
   * @see TransactionDetail
   */
  public TransactionDetail withBankSlip() {
    LOGGER.info("Iniciando split de pagamento com boleto");
    LOGGER.info("Convertendo valores");

    final RequestMap map = SPLIT_PAYMENT_REGISTRATION_MC.convert(splitPaymentRegistration);
    map.putString("payment.method", TransactionMethod.PaymentMethod.BANK_SLIP.getName());

    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.POST,
          String.format(Endpoints.SPLIT_PAYMENT, pagSeguro.getHost()), null,
          map.toHttpRequestBody(CharSet.ENCODING_ISO));
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar split de pagamento com boleto");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    TransactionDetail transaction = response.parseXMLContent(pagSeguro, TransactionDetailXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Split de pagamento com boleto finalizado");
    return transaction;
  }

  /**
   * Execute split payment with credit card
   *
   * @param creditCard Interface with attributes of Credit Card
   * @return Response of direct payment registration
   * @see CreditCard
   * @see TransactionDetail
   */
  public TransactionDetail withCreditCard(CreditCard creditCard) {
    LOGGER.info("Iniciando split de pagamento com cartao de credito");
    LOGGER.info("Convertendo valores");
    final RequestMap map = SPLIT_PAYMENT_REGISTRATION_MC.convert(splitPaymentRegistration);
    map.putString("payment.method", TransactionMethod.PaymentMethod.CREDIT_CARD.getName());
    map.putMap(CREDIT_CARD_MC.convert(creditCard));
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.POST,
          String.format(Endpoints.SPLIT_PAYMENT, pagSeguro.getHost()), null,
          map.toHttpRequestBody(CharSet.ENCODING_ISO));
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar split de pagamento com cartao de credito");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    TransactionDetail transaction = response.parseXMLContent(pagSeguro, TransactionDetailXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Split de pagamento com cartao de credito finalizado");
    return transaction;
  }

  /**
   * Execute split payment with credit card
   *
   * @param creditCardBuilder Builder for attributes of Credit Card
   * @return Response of split payment registration
   * @see CreditCard
   * @see TransactionDetail
   */
  public TransactionDetail withCreditCard(Builder<CreditCard> creditCardBuilder) {
    return withCreditCard(creditCardBuilder.build());
  }

  /**
   * Execute split payment with online debit
   *
   * @param bank Interface with attributes of bank slip
   * @return Response of split payment registration
   * @see TransactionDetail
   * @see Bank
   */
  public TransactionDetail withOnlineDebit(Bank bank) {
    LOGGER.info("Iniciando split de pagamento com debito online");
    LOGGER.info("Convertendo valores");
    final RequestMap map = SPLIT_PAYMENT_REGISTRATION_MC.convert(splitPaymentRegistration);
    map.putString("payment.method", TransactionMethod.PaymentMethod.ONLINE_DEBIT.getName());
    map.putMap(BANK_MC.convert(bank));
    LOGGER.info("Valores convertidos");

    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));

      response = httpClient.execute(HttpMethod.POST,
          String.format(Endpoints.SPLIT_PAYMENT, pagSeguro.getHost()), null,
          map.toHttpRequestBody(CharSet.ENCODING_ISO));

      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar split de pagamento com debito online");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    TransactionDetail transaction = response.parseXMLContent(pagSeguro, TransactionDetailXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Split de pagamento com debito online finalizado");
    return transaction;
  }

  /**
   * Execute split payment with online debit
   *
   * @param bankBuilder Builder for attributes of bank
   * @return Response of split payment registration
   * @see Bank
   * @see TransactionDetail
   */
  public TransactionDetail withOnlineDebit(Builder<Bank> bankBuilder) {
    return withOnlineDebit(bankBuilder.build());
  }

}
