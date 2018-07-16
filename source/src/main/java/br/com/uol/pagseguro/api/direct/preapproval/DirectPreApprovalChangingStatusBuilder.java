package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.enums.PreApprovalStatus;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval changing status.
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalChangingStatusBuilder implements Builder<DirectPreApprovalChangingStatus> {

    private String code = null;
    private String status = null;

    /**
     * Set Pre Approval Code of pre approval changing status
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Changing Status
     * @see DirectPreApprovalChangingStatus#getCode()
     */
    public DirectPreApprovalChangingStatusBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Set Status of pre approval changing status
     *
     * @param status Pre Approval Status
     * @return Builder for Pre Approval Changing Status
     * @see DirectPreApprovalChangingStatus#getStatus()
     */
    public DirectPreApprovalChangingStatusBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Set Status of pre approval changing status
     *
     * @param status Pre Approval Status
     * @return Builder for Pre Approval Changing Status
     * @see DirectPreApprovalChangingStatus#getStatus()
     */
    public DirectPreApprovalChangingStatusBuilder withStatus(PreApprovalStatus status) {
        this.status = status.getValue();
        return this;
    }

    /**
     * Build the Direct Pre Approval Changing Status
     *
     * @return Interface for Direct Pre Approval Changin Status
     * @see DirectPreApprovalChangingStatus
     */
    @Override
    public DirectPreApprovalChangingStatus build() {
        return new SimpleDirectPreApprovalChangingStatus(this);
    }

    /**
     * Implementation of {@getCode DirectPreApprovalChangingStatus}
     */
    private static class SimpleDirectPreApprovalChangingStatus implements DirectPreApprovalChangingStatus {

        private final DirectPreApprovalChangingStatusBuilder directPreApprovalChangingStatusBuilder;

        public SimpleDirectPreApprovalChangingStatus(DirectPreApprovalChangingStatusBuilder directPreApprovalChangingStatusBuilder) {
            this.directPreApprovalChangingStatusBuilder = directPreApprovalChangingStatusBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalChangingStatusBuilder.code;
        }

        @Override
        public String getStatus() {
            return directPreApprovalChangingStatusBuilder.status;
        }
    }
}