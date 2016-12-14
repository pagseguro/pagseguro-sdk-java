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

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.AcceptedPaymentMethods;
import br.com.uol.pagseguro.api.common.domain.PaymentMethod;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for accepted payments method
 *
 * @author PagSeguro Internet Ltda.
 */
public final class AcceptedPaymentMethodsBuilder implements Builder<AcceptedPaymentMethods> {

  private List<PaymentMethod> includes = new ArrayList<PaymentMethod>();

  private List<PaymentMethod> excludes = new ArrayList<PaymentMethod>();

  /**
   * Add include payment method
   *
   * @param paymentMethod Payment Method
   * @return Builder for Accepted Payment Methods
   * @see AcceptedPaymentMethods#getIncludes()
   */
  public AcceptedPaymentMethodsBuilder addInclude(PaymentMethod paymentMethod) {
    includes.add(paymentMethod);
    return this;
  }

  /**
   * Add include payment method
   *
   * @param paymentMethodBuilder Builder Payment Method
   * @return Builder for Accepted Payment Methods
   * @see AcceptedPaymentMethods#getIncludes()
   */
  public AcceptedPaymentMethodsBuilder addInclude(Builder<PaymentMethod> paymentMethodBuilder) {
    return addInclude(paymentMethodBuilder.build());
  }

  /**
   * Add include payment method
   *
   * @param paymentMethods Payment Methods
   * @return Builder for Accepted Payment Methods
   * @see AcceptedPaymentMethods#getIncludes()
   */
  public AcceptedPaymentMethodsBuilder addIncludes(
      Iterable<? extends PaymentMethod> paymentMethods) {
    for (PaymentMethod paymentMethod : paymentMethods) {
      addInclude(paymentMethod);
    }
    return this;
  }

  /**
   * Add excludes payment method
   *
   * @param paymentMethod Payment Method
   * @return Builder for Accepted Payment Methods
   * @see AcceptedPaymentMethods#getExcludes()
   */
  public AcceptedPaymentMethodsBuilder addExclude(PaymentMethod paymentMethod) {
    excludes.add(paymentMethod);
    return this;
  }

  /**
   * Add excludes payment method
   *
   * @param paymentMethodBuilder Builder for Payment Method
   * @return Builder for Accepted Payment Methods
   * @see AcceptedPaymentMethods#getExcludes()
   */
  public AcceptedPaymentMethodsBuilder addExclude(Builder<PaymentMethod> paymentMethodBuilder) {
    return addExclude(paymentMethodBuilder.build());
  }

  /**
   * Add excludes payment method
   *
   * @param paymentMethods Payment Methods
   * @return Builder for Accepted Payment Methods
   * @see AcceptedPaymentMethods#getExcludes()
   */
  public AcceptedPaymentMethodsBuilder addExcludes(
      Iterable<? extends PaymentMethod> paymentMethods) {
    for (PaymentMethod paymentMethod : paymentMethods) {
      addExclude(paymentMethod);
    }
    return this;
  }

  /**
   * Build the Accepted Payment Methods
   *
   * @return Interface for Accepted Payment Methods
   * @see AcceptedPaymentMethods
   */
  @Override
  public AcceptedPaymentMethods build() {
    return new SimpleAcceptedPaymentMethods(this);
  }

  /**
   * Implementation of {@code AcceptedPaymentMethods}
   */
  private static class SimpleAcceptedPaymentMethods implements AcceptedPaymentMethods {

    private final AcceptedPaymentMethodsBuilder acceptedPaymentMethodsBuilder;

    public SimpleAcceptedPaymentMethods(
        AcceptedPaymentMethodsBuilder acceptedPaymentMethodsBuilder) {
      this.acceptedPaymentMethodsBuilder = acceptedPaymentMethodsBuilder;
    }

    @Override
    public List<? extends PaymentMethod> getIncludes() {
      return acceptedPaymentMethodsBuilder.includes;
    }

    @Override
    public List<? extends PaymentMethod> getExcludes() {
      return acceptedPaymentMethodsBuilder.excludes;
    }
  }
}
