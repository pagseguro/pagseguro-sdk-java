package br.com.uol.pagseguro.api.direct.preapproval;

/**
 * Response of direct pre approval changing status
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalChangingStatus {

    /**
     * Get Direct Pre Approval Code
     *
     * @return Direct Pre Approval Code
     */
    String getCode();

    /**
     * Get Direct Pre Approval Status
     *
     * @return Direct Pre Approval Status
     */
    String getStatus();
}