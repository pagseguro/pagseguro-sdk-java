package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.PreApprovalCreditCard;
import br.com.uol.pagseguro.api.common.domain.PreApprovalHolder;
import br.com.uol.pagseguro.api.common.domain.PreApprovalPaymentMethod;
import br.com.uol.pagseguro.api.utils.Builder;

public class PreApprovalCreditCardBuilder implements Builder<PreApprovalCreditCard>{
    String token = null;
    PreApprovalHolder holder = null;

    /**
     * Set token of pre approval credit card
     * @param token Token
     * @return Builder for pre approval credit card
     * @see PreApprovalCreditCard#getToken()
     */
    public PreApprovalCreditCardBuilder withToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Set credit card holder of direct pre approval
     *
     * @param holder  Pre Approval Credit Card Holder
     * @return Builder for pre approval credit card
     * @see PreApprovalCreditCard#getHolder()
     */
    public PreApprovalCreditCardBuilder withHolder(PreApprovalHolder holder) {
        this.holder = holder;
        return this;
    }

    /**
     * Set credit card of direct pre approval payment method
     *
     * @param holderBuilder Builder for Pre Approval Credit Card Holder
     * @return Builder for pre approval credit card
     * @see PreApprovalCreditCard#getHolder()
     */
    public PreApprovalCreditCardBuilder withHolder(Builder<PreApprovalHolder> holderBuilder) {
        return withHolder(holderBuilder.build());
    }

    /**
     * Build the PreApprovalCreditCard
     *
     * @return Interface for PreApprovalCreditCard
     * @see PreApprovalPaymentMethod
     */
    @Override
    public PreApprovalCreditCard build() {
        return new SimplePreApprovalCreditCard(this);
    }

    /**
     * Implementation of {@code PreApprovalCreditCard}
     */
    private static class SimplePreApprovalCreditCard implements PreApprovalCreditCard {

        private final PreApprovalCreditCardBuilder preApprovalCreditCardBuilder;

        SimplePreApprovalCreditCard(PreApprovalCreditCardBuilder preApprovalCreditCardBuilder) {
            this.preApprovalCreditCardBuilder = preApprovalCreditCardBuilder;
        }

        @Override
        public String getToken() {
            return preApprovalCreditCardBuilder.token;
        }

        @Override
        public PreApprovalHolder getHolder() {
            return preApprovalCreditCardBuilder.holder;
        }
    }
}
