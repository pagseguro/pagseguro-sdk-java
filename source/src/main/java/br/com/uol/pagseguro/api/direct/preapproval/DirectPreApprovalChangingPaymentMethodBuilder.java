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

import br.com.uol.pagseguro.api.common.PreApprovalSenderRiskData;
import br.com.uol.pagseguro.api.common.domain.PreApprovalCreditCard;
import br.com.uol.pagseguro.api.common.domain.enums.PreApprovalPaymentMethodType;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval change payment method.
 * This class is responsible for building the attributes for changing direct pre approval payment method
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalChangingPaymentMethodBuilder implements Builder<DirectPreApprovalChangingPaymentMethod> {
    private String preApprovalCode;
    private String type;
    private PreApprovalSenderRiskData senderRiskData;
    private PreApprovalCreditCard creditCard;

    /**
     * Set direct pre appproval code
     *
     * @param preApprovalCode Pre Approval Code
     * @return Builder for DirectPreApprovalChangingPaymentMethod
     * @see DirectPreApprovalChangingPaymentMethod#getPreApprovalCode()
     */
    public DirectPreApprovalChangingPaymentMethodBuilder withPreApprovalCode(String preApprovalCode) {
        this.preApprovalCode = preApprovalCode;
        return this;
    }

    /**
     * Set payment method type
     *
     * @param type Payment Method Type
     * @return Builder for DirectPreApprovalChangingPaymentMethod
     * @see PreApprovalPaymentMethodType
     * @see DirectPreApprovalChangingPaymentMethod#getType()
     */
    public DirectPreApprovalChangingPaymentMethodBuilder withType(PreApprovalPaymentMethodType type) {
        this.type = type.getValue();
        return this;
    }

    /**
     * Set pre approval sender risk data
     *
     * @param senderRiskData Pre Approval Sender Risk Data
     * @return Builder for DirectPreApprovalChangingPaymentMethod
     * @see PreApprovalSenderRiskData
     * @see DirectPreApprovalChangingPaymentMethod#getSenderRiskData()
     */
    public DirectPreApprovalChangingPaymentMethodBuilder withSenderRiskData(PreApprovalSenderRiskData senderRiskData) {
        this.senderRiskData = senderRiskData;
        return this;
    }

    /**
     * Set credit card data for payment method change
     *
     * @param senderRiskDataBuilder Pre Approval Sender Risk Data builder
     * @return Builder for DirectPreApprovalChangingPaymentMethod
     * @see DirectPreApprovalChangingPaymentMethod#getCreditCard()
     */
    public DirectPreApprovalChangingPaymentMethodBuilder withSenderRiskData(Builder<PreApprovalSenderRiskData> senderRiskDataBuilder) {
        return withSenderRiskData(senderRiskDataBuilder.build());
    }

    /**
     * Set credit card data for payment method change
     *
     * @param creditCard Pre Approval Credit Card data
     * @return Builder for DirectPreApprovalChangingPaymentMethod
     * @see DirectPreApprovalChangingPaymentMethod#getCreditCard()
     */
    public DirectPreApprovalChangingPaymentMethodBuilder withCreditCard(PreApprovalCreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    /**
     * Set credit card data for payment method change
     *
     * @param creditCardBuilder Pre Approval Credit Card builder
     * @return Builder for DirectPreApprovalChangingPaymentMethod
     * @see DirectPreApprovalChangingPaymentMethod#getCreditCard()
     */
    public DirectPreApprovalChangingPaymentMethodBuilder withCreditCard(Builder<PreApprovalCreditCard> creditCardBuilder) {
        return withCreditCard(creditCardBuilder.build());
    }

    /**
     * Build the Direct Pre Approval Change Payment class
     *
     * @return Class built from Interface DirectPreApprovalChangingPaymentMethod
     * @see DirectPreApprovalChangingPaymentMethod
     */
    @Override
    public DirectPreApprovalChangingPaymentMethod build() {
        return new SimpleDirectPreApprovalChangingPaymentMethod(this);
    }

    /**
     * Implementation of {@code DirectPreApprovalChangingPaymentMethod}
     */
    private static class SimpleDirectPreApprovalChangingPaymentMethod implements DirectPreApprovalChangingPaymentMethod {

        private final DirectPreApprovalChangingPaymentMethodBuilder directPreApprovalChangingPaymentMethodBuilder;

        public SimpleDirectPreApprovalChangingPaymentMethod(DirectPreApprovalChangingPaymentMethodBuilder directPreApprovalChangingPaymentMethodBuilder) {
            this.directPreApprovalChangingPaymentMethodBuilder = directPreApprovalChangingPaymentMethodBuilder;
        }

        @Override
        public String getPreApprovalCode() {
            return directPreApprovalChangingPaymentMethodBuilder.preApprovalCode;
        }

        @Override
        public String getType() {
            return directPreApprovalChangingPaymentMethodBuilder.type;
        }

        @Override
        public PreApprovalSenderRiskData getSenderRiskData() {
            return directPreApprovalChangingPaymentMethodBuilder.senderRiskData;
        }

        @Override
        public PreApprovalCreditCard getCreditCard() {
            return directPreApprovalChangingPaymentMethodBuilder.creditCard;
        }
    }
}
