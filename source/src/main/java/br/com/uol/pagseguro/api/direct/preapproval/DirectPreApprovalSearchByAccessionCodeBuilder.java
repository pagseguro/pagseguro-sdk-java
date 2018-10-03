package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

public final class DirectPreApprovalSearchByAccessionCodeBuilder implements Builder<DirectPreApprovalSearchByAccessionCode> {

    private String code = null;

    /**
     * Set Pre Approval Code of pre approval search by accession code
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval Search By Accession Code
     * @see DirectPreApprovalSearchByAccessionCode#getCode()
     */
    public DirectPreApprovalSearchByAccessionCodeBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Build the Direct Pre Approval Search By Accession Code
     *
     * @return Interface for Direct Pre Approval Search By Accession Code
     * @see DirectPreApprovalSearchByAccessionCode
     */
    @Override
    public DirectPreApprovalSearchByAccessionCode build() {
        return new SimpleDirectPreApprovalSearchByAccessionCode(this);
    }

    /**
     * Implementation of {@getCode DirectPreApprovalSearchByAccessionCode}
     */
    private static class SimpleDirectPreApprovalSearchByAccessionCode implements DirectPreApprovalSearchByAccessionCode {

        private final DirectPreApprovalSearchByAccessionCodeBuilder directPreApprovalSearchByAccessionCodeBuilder;

        public SimpleDirectPreApprovalSearchByAccessionCode(DirectPreApprovalSearchByAccessionCodeBuilder directPreApprovalSearchByAccessionCodeBuilder) {
            this.directPreApprovalSearchByAccessionCodeBuilder = directPreApprovalSearchByAccessionCodeBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalSearchByAccessionCodeBuilder.code;
        }

    }
}
