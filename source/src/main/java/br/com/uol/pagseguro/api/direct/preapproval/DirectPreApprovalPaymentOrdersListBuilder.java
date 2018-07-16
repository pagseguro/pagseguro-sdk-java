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

import br.com.uol.pagseguro.api.common.domain.enums.PaymentOrderStatus;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval payment order list
 * This class is responsible for building the attributes for list payment orders
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalPaymentOrdersListBuilder implements Builder<DirectPreApprovalPaymentOrdersList> {
    private String code = null;
    private Integer status = null;
    private Integer page = null;
    private Integer maxPageResults = null;

    /**
     * Set the direct pre approval payment order  code
     *
     * @param code Pre approval code
     * @return Builder for direct pre approval payment orders list
     * @see DirectPreApprovalPaymentOrdersList#getCode()
     */
    public DirectPreApprovalPaymentOrdersListBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Set the direct pre approval payment order  status to filter
     *
     * @param status Status of the order payment
     * @return Builder for direct pre approval payment orders list
     * @see DirectPreApprovalPaymentOrdersList#getStatus()
     */
    public DirectPreApprovalPaymentOrdersListBuilder withStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Set the direct pre approval payment order  status to filter
     *
     * @param status Status of the order payment
     * @return Builder for direct pre approval payment orders list
     * @see DirectPreApprovalPaymentOrdersList#getStatus()
     */
    public DirectPreApprovalPaymentOrdersListBuilder withStatus(PaymentOrderStatus status) {
        this.status = status.getValue();
        return this;
    }

    /**
     * Set the direct pre approval payment order  page to filter
     *
     * @param page Page number
     * @return Builder for direct pre approval payment orders list
     * @see DirectPreApprovalPaymentOrdersList#getPage()
     */
    public DirectPreApprovalPaymentOrdersListBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Set the direct pre approval payment order  max page results to filter
     *
     * @param maxPageResults Max number of results returned in each page
     * @return Builder for direct pre approval payment orders list
     * @see DirectPreApprovalPaymentOrdersList#getMaxPageResults()
     */
    public DirectPreApprovalPaymentOrdersListBuilder withMaxPageResults(Integer maxPageResults) {
        this.maxPageResults = maxPageResults;
        return this;
    }

    /**
     * Build the direct pre approval payment order list
     *
     * @return DirectPreApprovalPaymentOrdersList build
     */
    @Override
    public DirectPreApprovalPaymentOrdersList build() { return new SimpleDirectPreApprovalPaymentOrderList(this); }

    private static class SimpleDirectPreApprovalPaymentOrderList implements DirectPreApprovalPaymentOrdersList {
        private final DirectPreApprovalPaymentOrdersListBuilder directPreApprovalPaymentOrdersListBuilder;

        public SimpleDirectPreApprovalPaymentOrderList(DirectPreApprovalPaymentOrdersListBuilder directPreApprovalPaymentOrdersListBuilder) {
            this.directPreApprovalPaymentOrdersListBuilder = directPreApprovalPaymentOrdersListBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalPaymentOrdersListBuilder.code;
        }

        @Override
        public Integer getStatus() {
            return directPreApprovalPaymentOrdersListBuilder.status;
        }

        @Override
        public Integer getPage() {
            return directPreApprovalPaymentOrdersListBuilder.page;
        }

        @Override
        public Integer getMaxPageResults() {
            return directPreApprovalPaymentOrdersListBuilder.maxPageResults;
        }
    }
}
