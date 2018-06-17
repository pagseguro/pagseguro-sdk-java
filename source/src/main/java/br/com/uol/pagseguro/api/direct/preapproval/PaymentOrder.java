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

package br.com.uol.pagseguro.api.direct.preapproval;

import java.math.BigDecimal;
import java.util.List;

/**
 * Response for direct pre approval Payment Order
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PaymentOrder {
    /**
     * Payment Order Code
     *
     * @return Code
     */
    String getCode();

    /**
     * Payment Order Status
     *
     * @return Status
     */
    Integer getStatus();

    /**
     * Payment Order Amount
     *
     * @return Amount
     */
    BigDecimal getAmount();

    /**
     * Payment Order Gross Amount
     *
     * @return Amount
     */
    BigDecimal getGrossAmount();

    /**
     * Payment Order Last Event Date
     *
     * @return Last Event Date
     */
    String getLastEeventDate();

    /**
     * Payment Order Scheduling Date
     *
     * @return Scheduling Date
     */
    String getSchedulingDate();

    /**
     * Payment Order Transaction List
     *
     * @return Transaction List
     */
    List<? extends Transaction> getTransactions();

    /**
     * Payment Order Discount
     *
     * @return Discount
     */
    Discount getDiscount();
}
