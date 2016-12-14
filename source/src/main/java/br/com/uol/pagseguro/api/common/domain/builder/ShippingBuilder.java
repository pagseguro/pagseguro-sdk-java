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

import br.com.uol.pagseguro.api.common.domain.Address;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Shipping
 *
 * @author PagSeguro Internet Ltda.
 */
public final class ShippingBuilder implements Builder<Shipping> {

  private Address address;

  private ShippingType shippingType;

  private BigDecimal cost;

  /**
   * Set Type of shipping
   *
   * @param shippingType Shipping type
   * @return Builder for shipping
   * @see Shipping#getShippingType()
   */
  public ShippingBuilder withType(ShippingType.Type shippingType) {
    this.shippingType = new ShippingType(shippingType);
    return this;
  }

  /**
   * Set cost of shipping
   *
   * @param cost Cost
   * @return Builder for shipping
   * @see Shipping#getCost()
   */
  public ShippingBuilder withCost(BigDecimal cost) {
    this.cost = cost;
    return this;
  }

  /**
   * Set address of shipping
   *
   * @param addressBuilder Builder for address
   * @return Builder for shipping
   * @see Shipping#getAddress()
   */
  public ShippingBuilder withAddress(Builder<Address> addressBuilder) {
    return withAddress(addressBuilder.build());
  }

  /**
   * Set address of shipping
   *
   * @param address Address
   * @return Builder for shipping
   * @see Shipping#getAddress()
   */
  public ShippingBuilder withAddress(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Build the shipping
   *
   * @return Interface for Shipping
   */
  @Override
  public Shipping build() {
    return new SimpleShipping(this);
  }

  /**
   * Implementation of {@code Shipping}
   */
  private static class SimpleShipping implements Shipping {

    private final ShippingBuilder shippingBuilder;

    public SimpleShipping(ShippingBuilder shippingBuilder) {
      this.shippingBuilder = shippingBuilder;
    }

    @Override
    public Address getAddress() {
      return shippingBuilder.address;
    }

    @Override
    public ShippingType getShippingType() {
      return shippingBuilder.shippingType;
    }

    @Override
    public BigDecimal getCost() {
      return shippingBuilder.cost;
    }

  }
}
