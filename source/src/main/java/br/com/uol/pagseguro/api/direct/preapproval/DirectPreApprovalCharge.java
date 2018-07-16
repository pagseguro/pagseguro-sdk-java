package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.PaymentItem;

import java.util.List;

public interface DirectPreApprovalCharge {

    /**
     * Get Direct Pre Approval Code
     *
     * @return Direct Pre Approval Code
     */
    String getPreApprovalCode();

    /**
     * Get Direct Pre Approval Reference
     *
     * @return Direct Pre Approval Reference
     */
    String getReference();

    /**
     * Get Direct Pre Approval SenderHash
     *
     * @return Direct Pre Approval SenderHash
     */
    String getSenderHash();

    /**
     * Get Direct Pre Approval SenderIp
     *
     * @return Direct Pre Approval SenderIp
     */
    String getSenderIp();

    /**
     * Get Direct Pre Approval Items
     *
     * @return Direct Pre Approval Items
     */
    List<PaymentItem> getItems();
}
