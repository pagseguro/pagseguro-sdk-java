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
 * Interface for installment. If you want to add installments without interest, enter the number of
 * installments in the "NoInterestInstallmentQuantity". Otherwise, you must add the interest as the
 * amount of installments and the flag card
 *
 * @author PagSeguro Internet Ltda.
 */
public interface Installment {

  /**
   * Get quantity of installments
   *
   * @return Quantity
   */
  Integer getQuantity();

  /**
   * Get value of installment. Remember to check the value of the plot, adding the interest as the
   * number of interest-free installments and the flag card
   *
   * @return Value
   */
  BigDecimal getValue();

  /**
   * Get no interest installment quantity of installment
   *
   * @return No interest installment quantity
   */
  Integer getNoInterestInstallmentQuantity();

}
