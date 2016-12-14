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

package br.com.uol.pagseguro.api.installment;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for installment listing
 *
 * @author PagSeguro Internet Ltda.
 */
public final class InstallmentRequestBuilder implements Builder<InstallmentRequest> {

  private String cardBrand = null;
  private BigDecimal amount = null;
  private Integer maxInstallmentNoInterest = null;
  private List<Parameter> parameters = new LinkedList<Parameter>();

  /**
   * Set card brand of installment
   *
   * @param cardBrand Card brand
   * @return Builder for installment
   * @see InstallmentRequest#getCardBrand()
   */
  public InstallmentRequestBuilder withCardBrand(String cardBrand) {
    this.cardBrand = cardBrand;
    return this;
  }

  /**
   * Set amount of installment
   *
   * @param amount Amount
   * @return Builder for installment
   * @see InstallmentRequest#getAmount()
   */
  public InstallmentRequestBuilder withAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Set max installment no interest of installment
   *
   * @param maxInstallmentNoInterest Max installment no interest
   * @return Builder for installment
   * @see InstallmentRequest#getMaxInstallmentNoInterest()
   */
  public InstallmentRequestBuilder withMaxInstallmentNoInterest(Integer maxInstallmentNoInterest) {
    this.maxInstallmentNoInterest = maxInstallmentNoInterest;
    return this;
  }

  /**
   * Add parameter to installment
   *
   * @param parameter Parameter
   * @return Builder for installment
   * @see InstallmentRequest#getParameters()
   */
  public InstallmentRequestBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add parameter to installment
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for installment
   * @see InstallmentRequest#getParameters()
   */
  public InstallmentRequestBuilder addParamenter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add parameters to installment
   *
   * @param parameters Parameters
   * @return Builder for installment
   * @see InstallmentRequest#getParameters()
   */
  public InstallmentRequestBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the installment listing
   *
   * @return Interface for installment listing
   */
  @Override
  public InstallmentRequest build() {
    return new SimpleInstallmentRequest(this);
  }

  /**
   * Implementation of {@code InstallmentRequest}
   */
  private static class SimpleInstallmentRequest implements InstallmentRequest {

    private final InstallmentRequestBuilder installmentRequestBuilder;

    public SimpleInstallmentRequest(
        InstallmentRequestBuilder installmentRequestBuilder) {
      this.installmentRequestBuilder = installmentRequestBuilder;
    }

    @Override
    public String getCardBrand() {
      return installmentRequestBuilder.cardBrand;
    }

    @Override
    public BigDecimal getAmount() {
      return installmentRequestBuilder.amount;
    }

    @Override
    public Integer getMaxInstallmentNoInterest() {
      return installmentRequestBuilder.maxInstallmentNoInterest;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return installmentRequestBuilder.parameters;
    }
  }
}
