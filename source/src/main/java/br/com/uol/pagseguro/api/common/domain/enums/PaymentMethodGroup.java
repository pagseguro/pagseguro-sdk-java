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

package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * Payment method group. Used to define the payment method group.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum PaymentMethodGroup {

  /**
   * Balance PagSeguro
   */
  BALANCE("BALANCE"),

  /**
   * Bank Slip
   */
  BANK_SLIP("BOLETO"),

  /**
   * Credit card
   */
  CREDIT_CARD("CREDIT_CARD"),

  /**
   * Deposit
   */
  DEPOSIT("DEPOSIT"),

  /**
   * Online Debit
   */
  ONLINE_DEBIT("EFT"),

  /**
   * Unrecognized
   */
  UNRECOGNIZED(null);

  private final String value;

  /**
   * Constructor
   *
   * @param value Enum value
   */
  PaymentMethodGroup(String value) {
    this.value = value;
  }

  /**
   * Get value
   *
   * @return Enum value
   */
  public String getValue() {
    return value;
  }

  /**
   * Get enum by value
   *
   * @param value Enum value
   * @return Enum
   */
  public static PaymentMethodGroup fromValue(String value) {
    for (PaymentMethodGroup paymentMethodGroup : PaymentMethodGroup.values()) {
      if (paymentMethodGroup.value != null && paymentMethodGroup.value.equalsIgnoreCase(value)) {
        return paymentMethodGroup;
      }
    }
    return UNRECOGNIZED;
  }
}
