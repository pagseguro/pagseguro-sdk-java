package br.com.uol.pagseguro.api.direct.preapproval;

import java.util.List;

/**
 * Response for direct pre approval By Date Interval
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalByDateInterval {

    /**
     * @return ResultsInThisPage
     */
    Integer getResultsInThisPage();

    /**
     * @return CurrentPage
     */
    Integer getCurrentPage();

    /**
     * @return TotalPages
     */
    Integer getTotalPages();

    /**
     * @return Date
     */
    String getDate();

    /**
     * Direct Pre Approval Data List
     *
     * @return Direct Pre Approval Data List
     */
    List<? extends DirectPreApprovalData> getPreApprovalData();
}
