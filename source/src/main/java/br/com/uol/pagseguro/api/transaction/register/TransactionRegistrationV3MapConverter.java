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

import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PaymentItemsV3MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.SenderV3MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.ShippingV3MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V3 Transaction Register
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionRegistrationV3MapConverter extends
    AbstractMapConverter<TransactionRegistration> {

  private static final PaymentItemsV3MapConverter PAYMENT_ITEMS_MC =
      new PaymentItemsV3MapConverter();

  private static final SenderV3MapConverter SENDER_MC = new SenderV3MapConverter();

  private static final ShippingV3MapConverter SHIPPING_MC = new ShippingV3MapConverter();

  private static final ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  TransactionRegistrationV3MapConverter() {
  }

  /**
   * Convert Interface for Transaction Registration in Request Map
   *
   * @param requestMap              Request Map used to pass params to api
   * @param transactionRegistration Interface for Transaction Registration
   * @see RequestMap
   * @see TransactionRegistration
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, TransactionRegistration transactionRegistration) {
    requestMap.putString("payment.mode", transactionRegistration.getPaymentMode());
    requestMap.putCurrency("currency", transactionRegistration.getCurrency());
    requestMap.putMap(PAYMENT_ITEMS_MC.convert(transactionRegistration.getItems()));
    requestMap.putString("notificationURL", transactionRegistration.getNotificationURL());
    requestMap.putString("reference", transactionRegistration.getReference());
    requestMap.putMap(SENDER_MC.convert(transactionRegistration.getSender()));
    requestMap.putMap(SHIPPING_MC.convert(transactionRegistration.getShipping()));
    requestMap.putMap(PARAMETER_MC.convert(transactionRegistration.getParameters()));
    requestMap.putCurrency("extraAmount", transactionRegistration.getExtraAmount());
  }
}
