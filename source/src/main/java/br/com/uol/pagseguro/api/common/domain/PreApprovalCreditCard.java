package br.com.uol.pagseguro.api.common.domain;

/**
 * Interface for Pre Approval Credit Card
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PreApprovalCreditCard {
    /**
     * Get credit card token
     * @return
     */
    String getToken();

    /**
     * Get pre approval credit card holder
     * @return
     */
    PreApprovalHolder getHolder();
}
