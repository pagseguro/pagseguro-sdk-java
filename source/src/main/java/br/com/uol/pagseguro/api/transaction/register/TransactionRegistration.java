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

import java.math.BigDecimal;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;

/**
 * Interface for Transaction Registration. Super class of the Direct Payment
 *
 * @author PagSeguro Internet Ltda.
 */
interface TransactionRegistration {

  /**
   * Payment mode.
   * Accepts the default option
   *
   * @return Payment Mode
   */
  String getPaymentMode();

//  PaymentMethod getPaymentMethod();

  /**
   * Currency used
   *
   * @return Currency
   * @see Currency
   */
  Currency getCurrency();

  /**
   * Items of transaction
   *
   * @return Items
   * @see PaymentItem
   */
  List<? extends PaymentItem> getItems();

  /**
   * Url for the sending notifications
   *
   * @return Notification url
   */
  String getNotificationURL();

  /**
   * Defines a code that refers to the payment. This code is associated with the PagSeguro
   * transaction and is useful to link the transaction in PagSeguro with the a sale in your system.
   *
   * @return Reference code.
   */
  String getReference();

  /**
   * Buyer's data.
   *
   * @return Sender
   */
  Sender getSender();

  /**
   * Shipping data
   *
   * @return Shipping
   * @see Shipping
   */
  Shipping getShipping();

  /**
   * Parameters
   *
   * @return Parameters
   * @see Parameter
   */
  List<? extends Parameter> getParameters();

  /**
   * Informs an extra value that was added to or subtracted from the amount paid by the buyer. This
   * value is specified by you in the payment and may represent a value that you want to charge
   * separately buyer or a discount you want to give to it.
   *
   * @return Extra Value
   */
  BigDecimal getExtraAmount();
}
