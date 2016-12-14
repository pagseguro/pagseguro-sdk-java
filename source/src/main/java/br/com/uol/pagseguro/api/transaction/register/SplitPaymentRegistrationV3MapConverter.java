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

import br.com.uol.pagseguro.api.common.domain.converter.ReceiverV3MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V3 Split Payment Register
 *
 * @author PagSeguro Internet Ltda.
 */
public class SplitPaymentRegistrationV3MapConverter extends
    AbstractMapConverter<SplitPaymentRegistration> {

  private static final TransactionRegistrationV3MapConverter TRANSACTION_REGISTRATION_MC =
      new TransactionRegistrationV3MapConverter();

  private final static ReceiverV3MapConverter RECEIVER_MC = new ReceiverV3MapConverter();

  SplitPaymentRegistrationV3MapConverter() {
  }

  /**
   * Convert Interface for Split Payment Registration in Request Map
   *
   * @param requestMap               Request Map used to pass params to api
   * @param splitPaymentRegistration Interface for Split Payment Registration
   * @see RequestMap
   * @see SplitPaymentRegistration
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, SplitPaymentRegistration splitPaymentRegistration) {
    requestMap.putMap(TRANSACTION_REGISTRATION_MC.convert(splitPaymentRegistration));
    if (splitPaymentRegistration.getPrimaryReceiver() != null) {
      requestMap.putString("primaryReceiver.publicKey",
          splitPaymentRegistration.getPrimaryReceiver().getPublicKey());
    }
    requestMap.putMap(RECEIVER_MC.convert(splitPaymentRegistration.getReceivers()));
  }
}
