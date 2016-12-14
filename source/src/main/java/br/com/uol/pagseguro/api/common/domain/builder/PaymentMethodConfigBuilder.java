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

import br.com.uol.pagseguro.api.common.domain.Config;
import br.com.uol.pagseguro.api.common.domain.PaymentMethod;
import br.com.uol.pagseguro.api.common.domain.PaymentMethodConfig;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Payment method config
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PaymentMethodConfigBuilder implements Builder<PaymentMethodConfig> {

  private PaymentMethod paymentMethod = null;

  private Config config = null;

  /**
   * Set payment method of Payment method config
   *
   * @param paymentMethod Payment method
   * @return Builder for Payment method config
   * @see PaymentMethodConfig#getPaymentMethod()
   */
  public PaymentMethodConfigBuilder withPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  /**
   * Set payment method of Payment method config
   *
   * @param paymentMethodBuilder Builder for Payment method
   * @return Builder for Payment method config
   * @see PaymentMethodConfig#getPaymentMethod()
   */
  public PaymentMethodConfigBuilder withPaymentMethod(Builder<PaymentMethod> paymentMethodBuilder) {
    return withPaymentMethod(paymentMethodBuilder.build());
  }

  /**
   * Set config of Payment method config
   *
   * @param config Config
   * @return Builder for Payment method config
   * @see PaymentMethodConfig#getConfig()
   */
  public PaymentMethodConfigBuilder withConfig(Config config) {
    this.config = config;
    return this;
  }

  /**
   * Set config of Payment method config
   *
   * @param configBuilder Builder for Config
   * @return Builder for Payment method config
   * @see PaymentMethodConfig#getConfig()
   */
  public PaymentMethodConfigBuilder withConfig(Builder<Config> configBuilder) {
    return withConfig(configBuilder.build());
  }

  /**
   * Build the Payment method config
   *
   * @return Interface for Payment method config
   * @see PaymentMethodConfig
   */
  @Override
  public PaymentMethodConfig build() {
    return new SimplePaymentMethodConfig(this);
  }

  /**
   * Implementation of {@code PaymentMethodConfig}
   */
  private static class SimplePaymentMethodConfig implements PaymentMethodConfig {

    private final PaymentMethodConfigBuilder paymentMethodConfigBuilder;

    public SimplePaymentMethodConfig(PaymentMethodConfigBuilder paymentMethodConfigBuilder) {
      this.paymentMethodConfigBuilder = paymentMethodConfigBuilder;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
      return paymentMethodConfigBuilder.paymentMethod;
    }

    @Override
    public Config getConfig() {
      return paymentMethodConfigBuilder.config;
    }
  }
}
