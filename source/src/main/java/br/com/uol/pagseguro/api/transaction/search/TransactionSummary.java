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
package br.com.uol.pagseguro.api.transaction.search;

import java.math.BigDecimal;
import java.util.Date;

import br.com.uol.pagseguro.api.common.domain.TransactionPaymentMethod;
import br.com.uol.pagseguro.api.common.domain.TransactionStatus;
import br.com.uol.pagseguro.api.common.domain.TransactionType;
import br.com.uol.pagseguro.api.transaction.TransactionIdentify;

/**
 * Interface for transaction summary response
 *
 * @author PagSeguro Internet Ltda.
 */
public interface TransactionSummary extends TransactionIdentify {

  /**
   * Get date
   *
   * @return date
   */
  Date getDate();

  /**
   * Get reference
   *
   * @return Reference
   */
  String getReference();

  /**
   * Get type
   *
   * @return Transaction type
   */
  TransactionType getType();

  /**
   * Get status
   *
   * @return Status
   * @see TransactionStatus
   */
  TransactionStatus getStatus();

  /**
   * Get payment method
   *
   * @return Payment method
   * @see TransactionPaymentMethod
   */
  TransactionPaymentMethod getPaymentMethod();

  /**
   * Get gross amount
   *
   * @return Gross amount
   */
  BigDecimal getGrossAmount();

  /**
   * Get discount amount
   *
   * @return Discount amount
   */
  BigDecimal getDiscountAmount();

  /**
   * Get fee amount
   *
   * @return Fee amount
   */
  BigDecimal getFeeAmount();

  /**
   * Get net amount
   *
   * @return Net amount
   */
  BigDecimal getNetAmount();

  /**
   * Get extra amount
   *
   * @return Extra amount
   */
  BigDecimal getExtraAmount();

  /**
   * Get last event
   *
   * @return Last event
   */
  Date getLastEvent();

  /**
   * Get transaction detail
   *
   * @return Transaction detail
   */
  TransactionDetail getDetail();

}
