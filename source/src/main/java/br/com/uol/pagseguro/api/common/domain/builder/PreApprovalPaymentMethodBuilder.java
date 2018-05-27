package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.PreApprovalCreditCard;
import br.com.uol.pagseguro.api.common.domain.PreApprovalPaymentMethod;
import br.com.uol.pagseguro.api.utils.Builder;

public class PreApprovalPaymentMethodBuilder implements Builder<PreApprovalPaymentMethod>{
    String type = null;
    PreApprovalCreditCard creditCard = null;

    /**
     * Set type of pre approval payment method
     * @param type Type
     * @return Builder for pre approval payment method
     * @see PreApprovalPaymentMethod#getType()
     */
    public PreApprovalPaymentMethodBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Set credit card of direct pre approval payment method
     *
     * @param creditCard  PreApprovalCreditCard
     * @return Builder for pre approval payment method
     * @see PreApprovalPaymentMethod#getPreApprovalCreditCard() ()
     */
    public PreApprovalPaymentMethodBuilder withCreditCard(PreApprovalCreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    /**
     * Set credit card of direct pre approval payment method
     *
     * @param creditCardBuilder Builder for pre approval payment method
     * @return Builder for pre approval payment method
     * @see PreApprovalPaymentMethod#getPreApprovalCreditCard()
     */
    public PreApprovalPaymentMethodBuilder withCreditCard(Builder<PreApprovalCreditCard> creditCardBuilder) {
        return withCreditCard(creditCardBuilder.build());
    }

    /**
     * Build the pre approval payment method
     *
     * @return Interface for PreApprovalPaymentMethod
     * @see PreApprovalPaymentMethod
     */
    @Override
    public PreApprovalPaymentMethod build() {
        return new SimplePreApprovalPaymentMethod(this);
    }

    /**
     * Implementation of {@code PreApprovalPaymentMethod}
     */
    private static class SimplePreApprovalPaymentMethod implements PreApprovalPaymentMethod {

        private final PreApprovalPaymentMethodBuilder preApprovalPaymentMethodBuilder;

        SimplePreApprovalPaymentMethod(PreApprovalPaymentMethodBuilder preApprovalPaymentMethodBuilder) {
            this.preApprovalPaymentMethodBuilder = preApprovalPaymentMethodBuilder;
        }

        @Override
        public String getType() {
            return preApprovalPaymentMethodBuilder.type;
        }

        @Override
        public PreApprovalCreditCard getPreApprovalCreditCard() {
            return preApprovalPaymentMethodBuilder.creditCard;
        }
    }
}
