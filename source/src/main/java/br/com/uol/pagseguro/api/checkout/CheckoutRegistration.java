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

import java.math.BigDecimal;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.*;
import br.com.uol.pagseguro.api.common.domain.PreApprovalRequest;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;

/**
 * Interface to make payments through payment API
 *
 * @author PagSeguro Internet Ltda.
 */
public interface CheckoutRegistration {

  /**
   * Define a code to refer to the payment.
   * This code is associated with the transaction created by the payment and is useful
   * to link PagSeguro transactions to sales registered on your system.
   * Format: Free, with the 200-character limit.
   *
   * Optional
   *
   * @return Reference Code.
   */
  String getReference();

  /**
   * Extra value. Specifies an extra value to be added or subtracted from the total amount of
   * payment. This value may represent an extra fee to be charged in the payment or a discount to be
   * granted if the value is negative. Format: Decimal (positive or negative), to two decimal places
   * separated by a point (bp, or 1234.56 -1234.56), greater than or equal to -9999999.00 and less
   * than or equal to 9999999.00. When negative, this value can not be greater than or equal to the
   * sum of the values ​​of the products.
   *
   * Optional
   *
   * @return Extra Amout
   */
  BigDecimal getExtraAmount();

  /**
   * Indicates the currency in which payment will be made.
   *
   * Required
   *
   * @return Currency used
   * @see Currency
   */
  Currency getCurrency();

  /**
   * Shipping data
   *
   * @return Shipping
   *
   * Optional
   * @see Shipping
   */
  Shipping getShipping();

  /**
   * Sender data
   *
   * @return Sender Data
   *
   * Optional
   * @see Sender
   */
  Sender getSender();

  /**
   * List of items contained in the payment
   *
   * @return List of Payment Items
   * @see PaymentItem
   */
  List<? extends PaymentItem> getItems();

  /**
   * Pre Approval
   *
   * @return Pre Approval
   * @see PreApprovalRequest
   */
  PreApprovalRequest getPreApproval();

  /**
   * Get Parameters
   *
   * @return Parameters
   * @see Parameter
   */
  List<? extends Parameter> getParameters();

  /**
   * Used to include and exclude groups of means of payment and means of payment for a transaction
   *
   * @return Accepted Payment Methods
   * @see AcceptedPaymentMethods
   */
  AcceptedPaymentMethods getAcceptedPaymentMethods();

  /**
   * Configurations to payment methods.
   *
   * @return Configurations to payment methods.
   */
  List<? extends PaymentMethodConfig> getPaymentMethodConfigs();

}
