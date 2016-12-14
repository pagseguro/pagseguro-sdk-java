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
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for address
 *
 * @author PagSeguro Internet Ltda.
 */
public final class AddressBuilder implements Builder<Address> {

  private String country = "BRA";

  private String state;

  private String city;

  private String postalCode;

  private String district;

  private String street;

  private String number;

  private String complement;

  /**
   * Set city of address
   *
   * @param city City of address
   * @return Builder for address
   */
  public AddressBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  /**
   * Set complement of address
   *
   * @param complement Complement of address
   * @return Builder for address
   */
  public AddressBuilder withComplement(String complement) {
    this.complement = complement;
    return this;
  }

  /**
   * Set country of address
   *
   * @param country Country of address
   * @return Builder for address
   */
  public AddressBuilder withCountry(String country) {
    this.country = country;
    return this;
  }

  /**
   * Set state of address
   *
   * @param state State of address
   * @return Builder for address
   */
  public AddressBuilder withState(String state) {
    this.state = state;
    return this;
  }

  /**
   * Set state of address
   *
   * @param state State of address
   * @return Builder for address
   */
  public AddressBuilder withState(State state) {
    this.state = state.getStringValue();
    return this;
  }

  /**
   * Set postal code of address
   *
   * @param postalCode Postal code of address
   * @return Builder for address
   */
  public AddressBuilder withPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * Set district of address
   *
   * @param district District of address
   * @return Builder for address
   */
  public AddressBuilder withDistrict(String district) {
    this.district = district;
    return this;
  }

  /**
   * Set street of address
   *
   * @param street Street of address
   * @return Builder for address
   */
  public AddressBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  /**
   * Set number of address
   *
   * @param number Number of address
   * @return Builder for address
   */
  public AddressBuilder withNumber(String number) {
    this.number = number;
    return this;
  }

  /**
   * Build the address
   *
   * @return Interface for address
   * @see Address
   */
  @Override
  public Address build() {
    return new SimpleAddress(this);
  }

  /**
   * Implementation of {@code Address}
   */
  private static class SimpleAddress implements Address {

    private final AddressBuilder addressBuilder;

    public SimpleAddress(AddressBuilder addressBuilder) {
      this.addressBuilder = addressBuilder;
    }

    @Override
    public String getCountry() {
      return addressBuilder.country;
    }

    @Override
    public String getState() {
      return addressBuilder.state;
    }

    @Override
    public String getPostalCode() {
      return addressBuilder.postalCode;
    }

    @Override
    public String getDistrict() {
      return addressBuilder.district;
    }

    @Override
    public String getStreet() {
      return addressBuilder.street;
    }

    @Override
    public String getNumber() {
      return addressBuilder.number;
    }

    @Override
    public String getComplement() {
      return addressBuilder.complement;
    }

    @Override
    public String getCity() {
      return addressBuilder.city;
    }

  }

}
