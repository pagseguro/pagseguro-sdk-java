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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.ShippingType;

/**
 * Implementation of {@code Shipping}
 *
 * @author PagSeguro Internet Ltda.
 */
public class ShippingXML implements Shipping {

  private BigDecimal cost;

  private Integer shippingTypeId;

  private AddressXML address;

  @XmlElement
  public void setAddress(AddressXML address) {
    this.address = address;
  }

  @Override
  public AddressXML getAddress() {
    return address;
  }

  @XmlElement(name = "type")
  public void setShippingTypeId(Integer shippingTypeId) {
    this.shippingTypeId = shippingTypeId;
  }

  public Integer getShippingTypeId() {
    return shippingTypeId;
  }

  @Override
  public ShippingType getShippingType() {
    return shippingTypeId != null ? new ShippingType(shippingTypeId) : null;
  }

  @XmlElement
  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  @Override
  public BigDecimal getCost() {
    return cost;
  }

  @Override
  public String toString() {
    return "ShippingXML{" +
        "cost=" + cost +
        ", shippingTypeId=" + shippingTypeId +
        ", address=" + address +
        '}';
  }
}
