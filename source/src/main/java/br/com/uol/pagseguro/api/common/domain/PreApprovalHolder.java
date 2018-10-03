package br.com.uol.pagseguro.api.common.domain;

public interface PreApprovalHolder extends Holder{
    /**
     * Get billing address of pre approval holder
     *
     * @return Billing Address
     * @see Address
     */
    Address getBillingAddress();
}
