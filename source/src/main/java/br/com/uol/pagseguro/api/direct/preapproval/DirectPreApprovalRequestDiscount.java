package br.com.uol.pagseguro.api.direct.preapproval;

/**
 * Response of direct pre approval discount
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalRequestDiscount {

    /**
     * Get Direct Pre Approval Code
     *
     * @return Direct Pre Approval Code
     */
    String getCode();

    /**
     * Get Direct Pre Approval Type
     *
     * @return Direct Pre Approval Type
     */
    String getType();

    /**
     * Get Direct Pre Approval Value
     *
     * @return Direct Pre Approval Value
     */
    String getValue();
}
