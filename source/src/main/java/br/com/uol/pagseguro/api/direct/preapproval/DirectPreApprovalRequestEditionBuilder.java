package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval edition.
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalRequestEditionBuilder implements Builder<DirectPreApprovalRequestEdition> {

    private String code = null;
    private String amountPerPayment = null;
    private Boolean updateSubscriptions = false;

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalRequestEdition#getCode()
     */
    public DirectPreApprovalRequestEditionBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Set amountPerPayment of pre approval edition
     *
     * @param amountPerPayment Amount PerPayment
     * @return Builder for direct pre approval edition
     * @see DirectPreApprovalRequestEdition#getAmountPerPayment()
     */
    public DirectPreApprovalRequestEditionBuilder withAmountPerPayment(String amountPerPayment) {
        this.amountPerPayment = amountPerPayment;
        return this;
    }

    /**
     * Set amountPerPayment of pre approval edition
     *
     * @param updateSubscriptions Amount PerPayment
     * @return Builder for direct pre approval edition
     * @see DirectPreApprovalRequestEdition#getUpdateSubscriptions()
     */
    public DirectPreApprovalRequestEditionBuilder withUpdateSubscriptions(Boolean updateSubscriptions) {
        this.updateSubscriptions = updateSubscriptions;
        return this;
    }

    /**
     * Build the Direct Pre Approval Edition
     *
     * @return Interface for Direct Pre Approval Edition
     * @see DirectPreApprovalRequestEdition
     */
    @Override
    public DirectPreApprovalRequestEdition build() {
        return new SimpleDirectPreApprovalRequestEdition(this);
    }

    /**
     * Implementation of {@amountPerPayment DirectPreApprovalRequestEdition and @updateSubscriptions DirectPreApprovalRequestEdition}
     */
    private static class SimpleDirectPreApprovalRequestEdition implements DirectPreApprovalRequestEdition {

        private final DirectPreApprovalRequestEditionBuilder directPreApprovalRequestEditionBuilder;

        public SimpleDirectPreApprovalRequestEdition(DirectPreApprovalRequestEditionBuilder directPreApprovalRequestEditionBuilder) {
            this.directPreApprovalRequestEditionBuilder = directPreApprovalRequestEditionBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalRequestEditionBuilder.code;
        }

        @Override
        public String getAmountPerPayment() {
            return directPreApprovalRequestEditionBuilder.amountPerPayment;
        }

        @Override
        public Boolean getUpdateSubscriptions() {
            return directPreApprovalRequestEditionBuilder.updateSubscriptions;
        }

    }

}
