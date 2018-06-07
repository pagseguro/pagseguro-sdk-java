package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval edition.
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalEditionBuilder implements Builder<DirectPreApprovalEdition> {

    private String code = null;
    private String amountPerPayment = null;
    private Boolean updateSubscriptions = false;

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalEdition#getCode()
     */
    public DirectPreApprovalEditionBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Set amountPerPayment of pre approval edition
     *
     * @param amountPerPayment Amount PerPayment
     * @return Builder for direct pre approval edition
     * @see DirectPreApprovalEdition#getAmountPerPayment()
     */
    public DirectPreApprovalEditionBuilder withAmountPerPayment(String amountPerPayment) {
        this.amountPerPayment = amountPerPayment;
        return this;
    }

    /**
     * Set amountPerPayment of pre approval edition
     *
     * @param updateSubscriptions Amount PerPayment
     * @return Builder for direct pre approval edition
     * @see DirectPreApprovalEdition#getUpdateSubscriptions()
     */
    public DirectPreApprovalEditionBuilder withUpdateSubscriptions(Boolean updateSubscriptions) {
        this.updateSubscriptions = updateSubscriptions;
        return this;
    }

    /**
     * Build the Direct Pre Approval Edition
     *
     * @return Interface for Direct Pre Approval Edition
     * @see DirectPreApprovalEdition
     */
    @Override
    public DirectPreApprovalEdition build() {
        return new SimpleDirectPreApprovalEdition(this);
    }

    /**
     * Implementation of {@amountPerPayment DirectPreApprovalEdition and @updateSubscriptions DirectPreApprovalEdition}
     */
    private static class SimpleDirectPreApprovalEdition implements DirectPreApprovalEdition {

        private final DirectPreApprovalEditionBuilder directPreApprovalEditionBuilder;

        public SimpleDirectPreApprovalEdition(DirectPreApprovalEditionBuilder directPreApprovalEditionBuilder) {
            this.directPreApprovalEditionBuilder = directPreApprovalEditionBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalEditionBuilder.code;
        }

        @Override
        public String getAmountPerPayment() {
            return directPreApprovalEditionBuilder.amountPerPayment;
        }

        @Override
        public Boolean getUpdateSubscriptions() {
            return directPreApprovalEditionBuilder.updateSubscriptions;
        }

    }

}
