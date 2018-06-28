package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.Sender;

/**
 * Interface for searched direct pre approval by notification code
 *
 * @author PagSeguro Internet Ltda.
 */
public interface SearchedDirectPreApprovalByNotificationCode {

    /**
     * @return Name
     */
    String getName();

    /**
     * @return Code
     */
    String getCode();

    /**
     * @return Tracker
     */
    String getTracker();

    /**
     * @return Status
     */
    String getStatus();

    /**
     * @return Last Event Date
     */
    String getLastEventDate();

    /**
     * @return Reference
     */
    String getReference();

    /**
     * @return Charge
     */
    String getCharge();

    /**
     * @return Sender
     */
    Sender getSender();

    /**
     * @return Date
     */
    String getDate();
}
