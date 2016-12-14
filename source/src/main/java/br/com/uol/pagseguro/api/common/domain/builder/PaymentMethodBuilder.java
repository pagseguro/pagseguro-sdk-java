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

package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.PaymentMethod;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodName;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Payment Method
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PaymentMethodBuilder implements Builder<PaymentMethod> {

  private PaymentMethodGroup group = null;

  private PaymentMethodName name = null;

  /**
   * Set group of payment method
   *
   * @param group Group
   * @return Builder for payment method
   * @see PaymentMethod#getGroup()
   */
  public PaymentMethodBuilder withGroup(PaymentMethodGroup group) {
    this.group = group;
    return this;
  }

  /**
   * Set name of payment method
   *
   * @param name Name
   * @return Builder for payment method
   * @see PaymentMethod#getName()
   */
  public PaymentMethodBuilder withName(PaymentMethodName name) {
    this.name = name;
    return this;
  }

  /**
   * Build the Payment method
   *
   * @return Interface for Payment method
   * @see PaymentMethod
   */
  @Override
  public PaymentMethod build() {
    return new SimplePaymentMethod(this);
  }


  /**
   * Implementation of {@code PaymentMethod}
   */
  private static class SimplePaymentMethod implements PaymentMethod {

    private final PaymentMethodBuilder paymentMethodBuilder;

    public SimplePaymentMethod(PaymentMethodBuilder paymentMethodBuilder) {
      this.paymentMethodBuilder = paymentMethodBuilder;
    }

    @Override
    public PaymentMethodGroup getGroup() {
      return paymentMethodBuilder.group;
    }

    @Override
    public PaymentMethodName getName() {
      return paymentMethodBuilder.name;
    }
  }
}
