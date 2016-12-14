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

import br.com.uol.pagseguro.api.common.domain.Split;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Split
 *
 * @author PagSeguro Internet Ltda.
 */
public final class SplitBuilder implements Builder<Split> {

  private BigDecimal amount;
  private BigDecimal ratePercent;
  private BigDecimal feePercent;

  /**
   * Set amount of split
   *
   * @param amount Amount
   * @return Builder for split
   * @see Split#getAmount()
   */
  public SplitBuilder withAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Set rate percent of split
   *
   * @param ratePercent Rate percent
   * @return Builder for split
   * @see Split#getRatePercent()
   */
  public SplitBuilder withRatePercent(BigDecimal ratePercent) {
    this.ratePercent = ratePercent;
    return this;
  }

  /**
   * Set fee percent of split
   *
   * @param feePercent Fee percent
   * @return Builder for split
   * @see Split#getFeePercent()
   */
  public SplitBuilder withFeePercent(BigDecimal feePercent) {
    this.feePercent = feePercent;
    return this;
  }

  /**
   * Build the Split
   *
   * @return Interface for split
   * @see Split
   */
  @Override
  public Split build() {
    return new SimpleSplitBuilder(this);
  }

  /**
   * Implementation of {@code Split}
   */
  private static class SimpleSplitBuilder implements Split {

    private final SplitBuilder splitBuilder;

    private SimpleSplitBuilder(SplitBuilder splitBuilder) {
      this.splitBuilder = splitBuilder;
    }

    @Override
    public BigDecimal getAmount() {
      return splitBuilder.amount;
    }

    @Override
    public BigDecimal getRatePercent() {
      return splitBuilder.amount;
    }

    @Override
    public BigDecimal getFeePercent() {
      return splitBuilder.feePercent;
    }
  }
}
