package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

public final class DirectPreApprovalSearchByNotificationCodeBuilder implements Builder<DirectPreApprovalSearchByNotificationCode> {

    private String code = null;

    /**
     * Set Pre Approval Code of pre approval search by notification code
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Search By Notification Code
     * @see DirectPreApprovalSearchByNotificationCode#getCode()
     */
    public DirectPreApprovalSearchByNotificationCodeBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Build the Direct Pre Approval Search By Notification Code
     *
     * @return Interface for Direct Pre Approval Search By Notification Code
     * @see DirectPreApprovalSearchByNotificationCode
     */
    @Override
    public DirectPreApprovalSearchByNotificationCode build() {
        return new DirectPreApprovalSearchByNotificationCodeBuilder.SimpleDirectPreApprovalSearchByNotificationCode(this);
    }

    /**
     * Implementation of {@getCode DirectPreApprovalSearchByNotificationCode}
     */
    private static class SimpleDirectPreApprovalSearchByNotificationCode implements DirectPreApprovalSearchByNotificationCode {

        private final DirectPreApprovalSearchByNotificationCodeBuilder directPreApprovalSearchByNotificationCodeBuilder;

        public SimpleDirectPreApprovalSearchByNotificationCode(DirectPreApprovalSearchByNotificationCodeBuilder directPreApprovalSearchByNotificationCodeBuilder) {
            this.directPreApprovalSearchByNotificationCodeBuilder = directPreApprovalSearchByNotificationCodeBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalSearchByNotificationCodeBuilder.code;
        }

    }

}