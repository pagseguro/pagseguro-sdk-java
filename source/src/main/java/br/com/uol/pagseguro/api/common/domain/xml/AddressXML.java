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
package br.com.uol.pagseguro.api.common.domain.xml;

import javax.xml.bind.annotation.XmlElement;

import br.com.uol.pagseguro.api.common.domain.Address;

/**
 * Implementation of {@code Address}
 *
 * @author PagSeguro Internet Ltda.
 */
public class AddressXML implements Address {

  private String country;

  private String state;

  private String city;

  private String postalCode;

  private String district;

  private String street;

  private String number;

  private String complement;

  AddressXML() {
  }

  public String getCountry() {
    return country;
  }

  @XmlElement
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String getState() {
    return state;
  }

  @XmlElement
  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String getCity() {
    return city;
  }

  @XmlElement
  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String getPostalCode() {
    return postalCode;
  }

  @XmlElement
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public String getDistrict() {
    return district;
  }

  @XmlElement
  public void setDistrict(String district) {
    this.district = district;
  }

  @Override
  public String getStreet() {
    return street;
  }

  @XmlElement
  public void setStreet(String street) {
    this.street = street;
  }

  @Override
  public String getNumber() {
    return number;
  }

  @XmlElement
  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public String getComplement() {
    return complement;
  }

  @XmlElement
  public void setComplement(String complement) {
    this.complement = complement;
  }

  @Override
  public String toString() {
    return "AddressXML{" +
        "country='" + country + '\'' +
        ", state='" + state + '\'' +
        ", city='" + city + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", district='" + district + '\'' +
        ", street='" + street + '\'' +
        ", number='" + number + '\'' +
        ", complement='" + complement + '\'' +
        '}';
  }
}
