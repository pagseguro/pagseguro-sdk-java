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

import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Receiver;

/**
 * Interface for split payment registration. You can set one or more sellers to receive part of the
 * payment. You can split the payment adding the parameter primaryReceiver to set the seller who is
 * receive the transaction, set the parameter receivers with one or more receivers and set the
 * transaction amount, rate and fee percentage designated for each one receive. You can define the
 * primaryReceiver and the other receivers by using the seller publicKey, received through the
 * application mode
 *
 * @author PagSeguro Internet Ltda.
 */
public interface SplitPaymentRegistration extends TransactionRegistration {

  /**
   * Specifies the seller that will receive part of the payment.
   *
   * @return Receivers
   * @see Receiver
   */
  List<? extends Receiver> getReceivers();

  /**
   * Specifies the seller that will receive the payment and the transaction will be linked to him.
   * If a primaryReceiver wasn’t informed, the application account owner will receive the
   * transaction.
   *
   * @return Primary Receiver
   * @see Receiver
   */
  Receiver getPrimaryReceiver();

}
