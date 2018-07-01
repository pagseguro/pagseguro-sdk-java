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

import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval retries payment.
 * This class is responsible for building the attributes for retries direct pre approval payment
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalPaymentRetryBuilder implements Builder<DirectPreApprovalPaymentRetry> {

    String preApprovalCode = null;
    String paymentOrderCode = null;

    /**
     * Set the direct pre approval code
     *
     * @param preApprovalCode Direct Pre Approval Code
     * @return Builder for direct pre approval payment retry builder
     * @see DirectPreApprovalPaymentRetry#getPreApprovalCode()
     */
    public DirectPreApprovalPaymentRetryBuilder withPreApprovalCode(String preApprovalCode) {
        this.preApprovalCode = preApprovalCode;
        return this;
    }

    /**
     * Set the direct pre approval payment order code
     *
     * @param paymentOrderCode Direct Pre Approval Payment Order code
     * @return Builder for direct pre approval payment retry builder
     * @see DirectPreApprovalPaymentRetry#getPaymentOrderCode()
     */
    public DirectPreApprovalPaymentRetryBuilder withPaymentOrderCode(String paymentOrderCode) {
        this.paymentOrderCode = paymentOrderCode;
        return this;
    }

    @Override
    public DirectPreApprovalPaymentRetry build() {
        return new SimpleDirectPreApprovalPaymentRetry(this);
    }

    /**
     * Implementation of {@code DirectPreApprovalPaymentRetry}
     */
    private class SimpleDirectPreApprovalPaymentRetry implements DirectPreApprovalPaymentRetry {

        private final DirectPreApprovalPaymentRetryBuilder directPreApprovalPaymentRetryBuilder;

        public SimpleDirectPreApprovalPaymentRetry(DirectPreApprovalPaymentRetryBuilder directPreApprovalPaymentRetryBuilder) {
            this.directPreApprovalPaymentRetryBuilder = directPreApprovalPaymentRetryBuilder;
        }

        @Override
        public String getPreApprovalCode() {
            return directPreApprovalPaymentRetryBuilder.preApprovalCode;
        }

        @Override
        public String getPaymentOrderCode() {
            return directPreApprovalPaymentRetryBuilder.paymentOrderCode;
        }
    }
}
