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

import br.com.uol.pagseguro.api.common.domain.Address;
import br.com.uol.pagseguro.api.common.domain.CreditCard;
import br.com.uol.pagseguro.api.common.domain.Holder;
import br.com.uol.pagseguro.api.common.domain.Installment;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Credit Card
 *
 * @author PagSeguro Internet Ltda.
 */
public final class CreditCardBuilder implements Builder<CreditCard> {

  private String token;
  private Holder holder;
  private Installment installment;
  private Address billingAddress;

  /**
   * Set token of credit card
   *
   * @param token Token of credit card
   * @return Builder for credit card
   * @see CreditCard#getToken()
   */
  public CreditCardBuilder withToken(String token) {
    this.token = token;
    return this;
  }

  /**
   * Set holder of credit card
   *
   * @param holder Holder of Credit Card
   * @return Builder for credit card
   * @see CreditCard#getHolder()
   */
  public CreditCardBuilder withHolder(Holder holder) {
    this.holder = holder;
    return this;
  }

  /**
   * Set holder of credit card
   *
   * @param holderBuilder Builder for Holder of Credit Card
   * @return Builder for credit card
   * @see CreditCard#getHolder()
   */
  public CreditCardBuilder withHolder(Builder<Holder> holderBuilder) {
    return withHolder(holderBuilder.build());
  }

  /**
   * Set installment of credit card
   *
   * @param installment Installment of credit card
   * @return Builder for credit card
   * @see CreditCard#getInstallment()
   */
  public CreditCardBuilder withInstallment(Installment installment) {
    this.installment = installment;
    return this;
  }

  /**
   * Set installment of credit card
   *
   * @param installmentBuilder Builder for Installment of credit card
   * @return Builder for credit card
   * @see CreditCard#getInstallment()
   */
  public CreditCardBuilder withInstallment(Builder<Installment> installmentBuilder) {
    return withInstallment(installmentBuilder.build());
  }

  /**
   * Set billing address of credit card
   *
   * @param billingAddress Billing Address of credit card
   * @return Builder for credit card
   * @see CreditCard#getBillingAddress()
   */
  public CreditCardBuilder withBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
    return this;
  }

  /**
   * Set billing address of credit card
   *
   * @param billingAddressBuilder Builder for Billing Address of credit card
   * @return Builder for credit card
   * @see CreditCard#getBillingAddress()
   */
  public CreditCardBuilder withBillingAddress(Builder<Address> billingAddressBuilder) {
    return withBillingAddress(billingAddressBuilder.build());
  }

  /**
   * Build the Credit Card
   *
   * @return Interface for Credit Card
   * @see CreditCard
   */
  @Override
  public CreditCard build() {
    return new SimpleCreditCard(this);
  }

  /**
   * Implementation of {@code CreditCard}
   */
  private static class SimpleCreditCard implements CreditCard {

    private final CreditCardBuilder creditCardBuilder;

    private SimpleCreditCard(CreditCardBuilder creditCardBuilder) {
      this.creditCardBuilder = creditCardBuilder;
    }

    @Override
    public String getToken() {
      return creditCardBuilder.token;
    }

    @Override
    public Holder getHolder() {
      return creditCardBuilder.holder;
    }

    @Override
    public Installment getInstallment() {
      return creditCardBuilder.installment;
    }

    @Override
    public Address getBillingAddress() {
      return creditCardBuilder.billingAddress;
    }
  }
}
