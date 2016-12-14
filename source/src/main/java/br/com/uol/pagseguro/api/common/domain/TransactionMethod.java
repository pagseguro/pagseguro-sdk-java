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

package br.com.uol.pagseguro.api.common.domain;

/**
 * Class for transaction method
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionMethod {

  private final String description;

  /**
   * Constructor
   *
   * @param description Description
   */
  public TransactionMethod(String description) {
    this.description = description;
  }

  /**
   * Constructor
   *
   * @param paymentMethod Payment method
   * @see PaymentMethod
   */
  public TransactionMethod(PaymentMethod paymentMethod) {
    this.description = paymentMethod.getName();
  }

  /**
   * Get description of transaction method
   *
   * @return Description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get payment method of transaction method
   *
   * @return Payment method
   */
  public PaymentMethod getPaymentMethod() {
    return PaymentMethod.fromName(description);
  }

  /**
   * Payment method of transaction method
   */
  public enum PaymentMethod {

    /**
     * BANK SLIP
     */
    BANK_SLIP("boleto"),

    /**
     * CREDIT CARD
     */
    CREDIT_CARD("creditCard"),

    /**
     * ONLINE DEBIT
     */
    ONLINE_DEBIT("eft"),

    /**
     * OTHER
     */
    OTHER(null);

    private final String name;

    /**
     * Constructor
     *
     * @param name Name
     */
    PaymentMethod(String name) {
      this.name = name;
    }

    /**
     * Get name
     *
     * @return Name
     */
    public String getName() {
      return name;
    }

    /**
     * Get Payment method Enum by name
     *
     * @param name Name
     * @return Payment method enum
     */
    public static PaymentMethod fromName(String name) {
      for (PaymentMethod paymentMethod : PaymentMethod.values()) {
        if (paymentMethod.name != null && paymentMethod.name.equalsIgnoreCase(name)) {
          return paymentMethod;
        }
      }
      return OTHER;
    }
  }
}
