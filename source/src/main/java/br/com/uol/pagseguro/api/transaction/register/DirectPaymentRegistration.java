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

/**
 * Interface for direct payment registration.
 *
 * The Transparent Checkout API (Direct Payment) provides greater control and flexibility over the
 * payment process. With this integration the customer is in the environment of your e-commerce site
 * or throughout the buying process, no need to register or intermediate pages of payment. For this
 * integration the PagSeguro created some services that together make it possible to build an
 * integrated checkout, safe and invisible to the buyer.
 *
 * @author PagSeguro Internet Ltda.
 * @see TransactionRegistration
 */
public interface DirectPaymentRegistration extends TransactionRegistration {

  /**
   * Specifies the e-mail should will get paid
   *
   * @return Receiver Email
   */
  String getReceiverEmail();

}
