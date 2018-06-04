package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval cancellation.
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalRequestCancellationBuilder implements Builder<DirectPreApprovalRequestCancellation> {

    private String code = null;

    /**
     * Set Pre Approval Code of pre approval cancellation
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Cancellation
     * @see DirectPreApprovalRequestCancellation#getCode()
     */
    public DirectPreApprovalRequestCancellationBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Build the Direct Pre Approval Cancellation
     *
     * @return Interface for Direct Pre Approval Cancellation
     * @see DirectPreApprovalRequestCancellation
     */
    @Override
    public DirectPreApprovalRequestCancellation build() {
        return new DirectPreApprovalRequestCancellationBuilder.SimpleDirectPreApprovalRequestCancellation(this);
    }

    /**
     * Implementation of {@getCode DirectPreApprovalRequestCancellation}
     */
    private static class SimpleDirectPreApprovalRequestCancellation implements DirectPreApprovalRequestCancellation {

        private final DirectPreApprovalRequestCancellationBuilder directPreApprovalRequestCancellationBuilder;

        public SimpleDirectPreApprovalRequestCancellation(DirectPreApprovalRequestCancellationBuilder directPreApprovalRequestCancellationBuilder) {
            this.directPreApprovalRequestCancellationBuilder = directPreApprovalRequestCancellationBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalRequestCancellationBuilder.code;
        }

    }
}
