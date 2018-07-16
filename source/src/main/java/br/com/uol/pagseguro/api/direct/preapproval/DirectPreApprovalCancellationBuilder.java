package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval cancellation.
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalCancellationBuilder implements Builder<DirectPreApprovalCancellation> {

    private String code = null;

    /**
     * Set Pre Approval Code of pre approval cancellation
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Cancellation
     * @see DirectPreApprovalCancellation#getCode()
     */
    public DirectPreApprovalCancellationBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Build the Direct Pre Approval Cancellation
     *
     * @return Interface for Direct Pre Approval Cancellation
     * @see DirectPreApprovalCancellation
     */
    @Override
    public DirectPreApprovalCancellation build() {
        return new SimpleDirectPreApprovalCancellation(this);
    }

    /**
     * Implementation of {@getCode DirectPreApprovalCancellation}
     */
    private static class SimpleDirectPreApprovalCancellation implements DirectPreApprovalCancellation {

        private final DirectPreApprovalCancellationBuilder directPreApprovalCancellationBuilder;

        public SimpleDirectPreApprovalCancellation(DirectPreApprovalCancellationBuilder directPreApprovalCancellationBuilder) {
            this.directPreApprovalCancellationBuilder = directPreApprovalCancellationBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalCancellationBuilder.code;
        }

    }
}
