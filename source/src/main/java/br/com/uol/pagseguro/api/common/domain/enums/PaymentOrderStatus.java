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

package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * Payment Order Status enum, used to define the id related to each status of a payment order
 *
 * @author PagSeguro Internet Ltda.
 */
public enum PaymentOrderStatus {

    /**
     *  Payment order is waiting the scheduled date to be processed
     */
    AGENDADA(1),

    /**
     * Payment order is being processed by the system
     */
    PROCESSANDO(2),

    /**
     * Payment order can't be processed because of some internal fault
     */
    NAO_PROCESSADA(3),

    /**
     * Payment order was not considered because in the scheduled processing date the recurring was suspended
     */
    SUSPENSA(4),

    /**
     * Payment order was paid
     */
    PAGA(5),

    /**
     * Payment order can't be paid
     */
    NAO_PAGA(6),

    /**
     * Unrecognized
     */
    UNRECOGNIZED(null);

    private final Integer value;

    /**
     * Constructor
     *
     * @param value Enum value
     */

    PaymentOrderStatus(Integer value) {
        this.value = value;
    }

    /**
     * Get value
     *
     * @return Enum value
     */
    public Integer getValue() { return value; }

    /**
     * Get enum by value
     *
     * @param value Enum value
     * @return Enum
     */
    public static PaymentOrderStatus fromValue(Integer value) {
        for (PaymentOrderStatus paymentOrderStatus : PaymentOrderStatus.values()) {
            if (paymentOrderStatus.value != null && paymentOrderStatus.value.equals(value)) {
                return paymentOrderStatus;
            }
        }
        return UNRECOGNIZED;
    }
}
