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

import br.com.uol.pagseguro.api.common.domain.PaymentItem;

/**
 * Implementation of {@code PaymentItem}
 *
 * @author PagSeguro Internet Ltda.
 */
public class PaymentItemXML implements PaymentItem {

  private String id;

  private String description;

  private Integer quantity;

  private BigDecimal amount;

  @XmlElement
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  @XmlElement
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @XmlElement
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public BigDecimal getAmount() {
    return amount;
  }

  @XmlElement
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public Integer getQuantity() {
    return quantity;
  }

  @Override
  public Integer getWeight() {
    // servidor não responde isso
    return null;
  }

  @Override
  public BigDecimal getShippingCost() {
    // servidor não responde isso
    return null;
  }

  @Override
  public String toString() {
    return "PaymentItemXML{" +
        "id='" + id + '\'' +
        ", description='" + description + '\'' +
        ", quantity=" + quantity +
        ", amount=" + amount +
        '}';
  }
}
