package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.DateRange;

/**
 * Interface for Direct Pre Approval By Day Interval List
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalByDayIntervalList {

    /**
     * Number of the page desired to see the result
     *
     * @return Page number
     */
    Integer getPage();

    /**
     * Max number of registers by page
     *
     * @return Max Page Results
     */
    Integer getMaxPageResults();

    /**
     * Interval (in days) of registers
     *
     * @return Interval (in days)
     */
    Integer getInterval();
}
