package br.com.uol.pagseguro.api.direct.preapproval;

/**
 * Response of direct pre approval edition
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalEdition {

    /**
     * Get Direct Pre Approval Code
     *
     * @return Direct Pre Approval Code
     */
    String getCode();

    /**
     * Get Direct Pre Approval Amount Per Payment
     *
     * @return Direct Pre Approval Amount Per Payment
     */
    String getAmountPerPayment();

    /**
     * Get Direct Pre Approval Update Subscriptions
     *
     * @return Direct Pre Approval Update Subscriptions
     */
    Boolean getUpdateSubscriptions();

}
