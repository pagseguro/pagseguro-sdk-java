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

import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for payment item
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PaymentItemBuilder implements Builder<PaymentItem> {

  private String id;

  private String description;

  private BigDecimal amount;

  private Integer quantity;

  private Integer weight;

  private BigDecimal shippingCost;

  /**
   * Set id of payment item
   *
   * @param id Id
   * @return Builder for payment item
   * @see PaymentItem#getId()
   */
  public PaymentItemBuilder withId(String id) {
    this.id = id;
    return this;
  }

  /**
   * Set description of payment item
   *
   * @param description Description
   * @return Builder for payment item
   * @see PaymentItem#getDescription()
   */
  public PaymentItemBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  /**
   * Set amount of payment item
   *
   * @param amount Amount
   * @return Builder for payment item
   * @see PaymentItem#getAmount()
   */
  public PaymentItemBuilder withAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Set quantity of payment item
   *
   * @param quantity Quantity
   * @return Builder for payment item
   * @see PaymentItem#getQuantity()
   */
  public PaymentItemBuilder withQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Set weight of payment item
   *
   * @param weight Wight
   * @return Builder for payment item
   * @see PaymentItem#getWeight()
   */
  public PaymentItemBuilder withWeight(int weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Set shipping cost of payment item
   *
   * @param shippingCost Shipping cost
   * @return Builder for payment item
   * @see PaymentItem#getShippingCost()
   */
  public PaymentItemBuilder withShippingCost(BigDecimal shippingCost) {
    this.shippingCost = shippingCost;
    return this;
  }

  /**
   * Build the Payment item
   *
   * @return Interface for Payment item
   * @see PaymentItem
   */
  @Override
  public PaymentItem build() {
    return new SimplePaymentItem(this);
  }

  /**
   * Implementation of {@code PaymentItem}
   */
  private static class SimplePaymentItem implements PaymentItem {

    private final PaymentItemBuilder paymentItemBuilder;

    public SimplePaymentItem(PaymentItemBuilder paymentItemBuilder) {
      this.paymentItemBuilder = paymentItemBuilder;
    }

    @Override
    public String getId() {
      return paymentItemBuilder.id;
    }

    @Override
    public String getDescription() {
      return paymentItemBuilder.description;
    }

    @Override
    public BigDecimal getAmount() {
      return paymentItemBuilder.amount;
    }

    @Override
    public Integer getQuantity() {
      return paymentItemBuilder.quantity;
    }

    @Override
    public Integer getWeight() {
      return paymentItemBuilder.weight;
    }

    @Override
    public BigDecimal getShippingCost() {
      return paymentItemBuilder.shippingCost;
    }

  }

}
