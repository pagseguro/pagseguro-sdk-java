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
package br.com.uol.pagseguro.api.transaction.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.CreditorFee;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.TransactionMethod;
import br.com.uol.pagseguro.api.common.domain.TransactionPaymentMethod;
import br.com.uol.pagseguro.api.common.domain.TransactionStatus;
import br.com.uol.pagseguro.api.common.domain.TransactionType;
import br.com.uol.pagseguro.api.transaction.TransactionIdentify;

/**
 * Interface for transaction detail response
 *
 * @author PagSeguro Internet Ltda.
 */
public interface TransactionDetail extends TransactionIdentify {


  /**
   * Informs the code that was used to make reference to the payment. This code was provided at the
   * time of payment and is useful for linking the transactions of PagSeguro to sales recorded in
   * your system.
   *
   * @return Reference
   */
  String getReference();

  /**
   * Creation Date of Transaction
   *
   * @return Date
   */
  Date getDate();

  /**
   * Informs the moment in which occurred the last change in status of the transaction.
   *
   * @return Last Event
   */
  Date getLastEvent();

  /**
   * Informs the gross value of the transaction, calculated as the sum of the prices
   *
   * @return Gross Amount
   */
  BigDecimal getGrossAmount();

  /**
   * Informs the value of the discount given to buyers who have chosen to pay by debit card online
   * or online. This discount applies when you choose to include in the price of the products the
   * cost of the parceling of payments with credit card. The discount is given not to encumber the
   * buyers who opted for the sea.
   *
   * @return Discount Amount
   */
  BigDecimal getDiscountAmount();

  /**
   * Informs the net value of the transaction, which corresponds to the gross value, less the value
   * of the fees. If present, the value of extraAmount (which may be positive or negative) is also
   * considered in the calculation.
   *
   * @return Net Amount
   */
  BigDecimal getNetAmount();

  /**
   * Informs an extra value that was added or subtracted from the amount paid by the buyer. This
   * value is specified by you in the payment and may represent a value that you want to charge
   * separately from the buyer or a discount you want to give it to him.
   *
   * @return Net Amount
   */
  BigDecimal getExtraAmount();

  /**
   * Payment Link
   *
   * @return Payment Link
   */
  String getPaymentLink();

  /**
   * Data from the means of payment used by the buyer.
   *
   * @return Payment Method
   * @see TransactionPaymentMethod
   */
  TransactionPaymentMethod getPaymentMethod();

  /**
   * Status of Transaction
   *
   * @return Status
   * @see TransactionStatus
   */
  TransactionStatus getStatus();

  /**
   * Represents the type of the transaction received.
   * The most common values for this field and their respective results are described below.
   *
   * @return Transaction Type
   * @see TransactionType
   */
  TransactionType getType();

  /**
   * Buyer Data
   *
   * @return Sender
   * @see Sender
   */
  Sender getSender();

  /**
   * Shipping Data
   *
   * @return Shipping
   * @see Shipping
   */
  Shipping getShipping();

  /**
   * List of items
   *
   * @return Items
   */
  List<? extends PaymentItem> getItems();

  /**
   * Fee amount
   *
   * @return Fee Amount
   */
  BigDecimal getFeeAmount();

  /**
   * Mode
   *
   * @return Mode
   */
  String getMode();

  /**
   * Transaction method
   *
   * @return Transaction method
   * @see TransactionMethod
   */
  TransactionMethod getMethod();

  /**
   * Escrow end date
   *
   * @return Escrow end date
   */
  Date getEscrowEndDate();

  /**
   * Cancellation Source. Specifies the origin of the cancellation of the transaction: by financial
   * institutions (Issuing Bank or Card Operator) or by PagSeguro. When transactionStatus is equal
   * to 7
   *
   * @return Cancellation Source.
   */
  String getCancellationSource();

  /**
   * Creditor Fee
   *
   * @return Creditor Fee
   * @see CreditorFee
   */
  CreditorFee getCreditorFees();

}
