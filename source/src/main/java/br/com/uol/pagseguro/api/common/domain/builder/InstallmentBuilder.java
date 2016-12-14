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

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.common.domain.Installment;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for installment
 *
 * @author PagSeguro Internet Ltda.
 */
public final class InstallmentBuilder implements Builder<Installment> {

  private Integer quantity;
  private BigDecimal value;
  private Integer noInterestInstallmentQuantity;

  /**
   * Set quantity of installment
   *
   * @param quantity Quantity
   * @return Builder for installment
   * @see Installment#getQuantity()
   */
  public InstallmentBuilder withQuantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Set value of installment
   *
   * @param value Value
   * @return Builder for installment
   * @see Installment#getValue()
   */
  public InstallmentBuilder withValue(BigDecimal value) {
    this.value = value;
    return this;
  }

  /**
   * Set no interest installment quantity of installment
   *
   * @param noInterestInstallmentQuantity No interest installment quantity of installment
   * @return Builder for installment
   * @see Installment#getNoInterestInstallmentQuantity()
   */
  public InstallmentBuilder withNoInterestInstallmentQuantity(Integer noInterestInstallmentQuantity) {
    this.noInterestInstallmentQuantity = noInterestInstallmentQuantity;
    return this;
  }

  /**
   * Build the Installment
   *
   * @return Interface for installment
   * @see Installment
   */
  @Override
  public Installment build() {
    return new SimpleInstallmentBuilder(this);
  }

  /**
   * Implementation of {@code Installment}
   */
  private static class SimpleInstallmentBuilder implements Installment {
    private final InstallmentBuilder installmentBuilder;

    private SimpleInstallmentBuilder(InstallmentBuilder installmentBuilder) {
      this.installmentBuilder = installmentBuilder;
    }

    @Override
    public Integer getQuantity() {
      return installmentBuilder.quantity;
    }

    @Override
    public BigDecimal getValue() {
      return installmentBuilder.value;
    }

    @Override
    public Integer getNoInterestInstallmentQuantity() {
      return installmentBuilder.noInterestInstallmentQuantity;
    }
  }
}
