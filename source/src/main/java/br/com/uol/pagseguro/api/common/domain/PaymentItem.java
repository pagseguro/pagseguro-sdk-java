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
package br.com.uol.pagseguro.api.common.domain;

import java.math.BigDecimal;

/**
 * Interface for payment item.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PaymentItem {

  /**
   * Get id of payment item
   *
   * @return Id of payment
   */
  String getId();

  /**
   * Get shipping cost of payment item
   *
   * @return Shipping cost
   */
  BigDecimal getShippingCost();

  /**
   * Get description of payment item
   *
   * @return Description
   */
  String getDescription();

  /**
   * Get amount of payment item
   *
   * @return Amount
   */
  BigDecimal getAmount();

  /**
   * Get quantity of payment item
   *
   * @return Quantity
   */
  Integer getQuantity();

  /**
   * Get weight of payment item
   *
   * @return Weight
   */
  Integer getWeight();

}
