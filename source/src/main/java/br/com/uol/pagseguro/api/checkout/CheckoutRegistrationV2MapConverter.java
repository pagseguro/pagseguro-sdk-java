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

import br.com.uol.pagseguro.api.common.domain.converter.AcceptedPaymentMethodsV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PaymentItemsV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PaymentMethodConfigsV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PreApprovalV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.SenderV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.ShippingV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V2 Checkout Register
 *
 * @author PagSeguro Internet Ltda.
 */
public class CheckoutRegistrationV2MapConverter extends AbstractMapConverter<CheckoutRegistration> {

  private final static ShippingV2MapConverter SHIPPING_MC = new ShippingV2MapConverter();

  private final static SenderV2MapConverter SENDER_MC = new SenderV2MapConverter();

  private final static PaymentItemsV2MapConverter PAYMENT_ITEMS_MC =
      new PaymentItemsV2MapConverter();

  private final static PreApprovalV2MapConverter PRE_APPROVAL_MC = new PreApprovalV2MapConverter();

  private final static ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  private final static AcceptedPaymentMethodsV2MapConverter ACCEPTED_PAYMENT_METHODS_MC =
      new AcceptedPaymentMethodsV2MapConverter();

  private final static PaymentMethodConfigsV2MapConverter PAYMENT_METHOD_CONFIGS_MC =
      new PaymentMethodConfigsV2MapConverter();

  CheckoutRegistrationV2MapConverter() {
  }

  /**
   * Convert Interface for Checkout Registration in Request Map
   *
   * @param requestMap           Request Map used to pass params to api
   * @param checkoutRegistration Interface for Checkout Registration
   * @see RequestMap
   * @see CheckoutRegistration
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, CheckoutRegistration checkoutRegistration) {
    requestMap.putCurrency("currency", checkoutRegistration.getCurrency());
    requestMap.putString("reference", checkoutRegistration.getReference());
    requestMap.putCurrency("extraAmount", checkoutRegistration.getExtraAmount());
    requestMap.putMap(SHIPPING_MC.convert(checkoutRegistration.getShipping()));
    requestMap.putMap(SENDER_MC.convert(checkoutRegistration.getSender()));
    requestMap.putMap(PAYMENT_ITEMS_MC.convert(checkoutRegistration.getItems()));
    requestMap.putMap(PRE_APPROVAL_MC.convert(checkoutRegistration.getPreApproval()));
    requestMap.putMap(PARAMETER_MC.convert(checkoutRegistration.getParameters()));
    requestMap.putMap(ACCEPTED_PAYMENT_METHODS_MC.convert(checkoutRegistration
        .getAcceptedPaymentMethods()));
    requestMap.putMap(PAYMENT_METHOD_CONFIGS_MC.convert(checkoutRegistration
        .getPaymentMethodConfigs()));
  }

}
