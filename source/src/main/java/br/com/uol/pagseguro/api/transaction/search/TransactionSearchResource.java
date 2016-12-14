package br.com.uol.pagseguro.api.transaction.search;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.http.HttpClient;

/**
 * Factory to transactions search
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionSearchResource {

  private final PagSeguro pagSeguro;

  private final HttpClient httpClient;

  public TransactionSearchResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * Search transactions by code
   *
   * @param code The transaction code that you want to search
   * @return Transaction Detail
   * @see TransactionDetail
   */
  public TransactionDetail byCode(String code) {
    return new TransactionSearchByCode(code).execute(pagSeguro, httpClient);
  }

  /**
   * Search transactions by date range and reference
   *
   * @param transactionSearch Interface of Transaction Search
   * @return Transactions List
   * @see DataList
   * @see TransactionSummary
   * @see TransactionSearch
   */
  public DataList<? extends TransactionSummary> byDateRange(TransactionSearch transactionSearch) {
    return new TransactionSearchByDateRange(transactionSearch).execute(pagSeguro, httpClient);
  }

  /**
   * Search transactions by reference
   *
   * @param reference The reference of transaction that you want to search
   * @return Transactions List
   * @see DataList
   * @see TransactionSummary
   */
  public DataList<? extends TransactionSummary> byReference(String reference) {
    return byDateRange(new TransactionSearchBuilder().withReference(reference).build());
  }

  /**
   * Search transactions by date range and reference
   *
   * @param dateRangeBuilder The date range builder of date range that you want to search
   * @param reference        The reference of transaction that you want search
   * @param page             The page that you want to search
   * @param maxResults       The max results that you want to search
   * @return Transactions List
   * @see DataList
   * @see DateRangeBuilder
   * @see TransactionSummary
   * @see DataList
   */
  public DataList<? extends TransactionSummary> byDateRange(DateRangeBuilder dateRangeBuilder,
                                                            String reference, int page,
                                                            int maxResults) {
    return byDateRange(new TransactionSearchBuilder().withDateRange(dateRangeBuilder)
        .withReference(reference).withPage(page).withMaxResults(maxResults).build());
  }

  /**
   * Search transactions by date range
   *
   * @param dateRangeBuilder The date range builder of date range that you want to search
   * @param page             The page that you want to search
   * @param maxResults       The max results that you want to search
   * @return Transactions List
   * @see DataList
   * @see DateRangeBuilder
   * @see TransactionSummary
   * @see DataList
   */
  public DataList<? extends TransactionSummary> byDateRange(DateRangeBuilder dateRangeBuilder,
                                                            int page, int maxResults) {
    return byDateRange(new TransactionSearchBuilder().withDateRange(dateRangeBuilder)
        .withPage(page).withMaxResults(maxResults).build());
  }

  /**
   * Search transactions by date range and reference
   *
   * @param transactionSearchBuilder Builder for interface of Transaction Search
   * @return Transactions List
   * @see DataList
   * @see TransactionSummary
   * @see TransactionSearchBuilder
   */
  public DataList<? extends TransactionSummary> byDateRange(
      TransactionSearchBuilder transactionSearchBuilder) {
    return byDateRange(transactionSearchBuilder.build());
  }

  /**
   * Search abandoned transactions by date range and reference
   *
   * @param transactionSearch Interface for transactions search
   * @return Transactions List
   */
  public DataList<? extends TransactionSummary> abandoned(TransactionSearch transactionSearch) {
    return new TransactionSearchByAbandoned(transactionSearch).execute(pagSeguro, httpClient);
  }

  /**
   * Search abandoned transactions by date range and reference
   *
   * @param dateRangeBuilder The date range builder of date range that you want to search
   * @param page             The page that you want to search
   * @param maxResults       The max results that you want to search
   * @return Transactions list
   */
  public DataList<? extends TransactionSummary> abandoned(DateRangeBuilder dateRangeBuilder,
                                                          int page, int maxResults) {
    return abandoned(new TransactionSearchBuilder().withDateRange(dateRangeBuilder)
        .withPage(page).withMaxResults(maxResults).build());
  }

  /**
   * Search abandoned transactions by date range and reference
   *
   * @param transactionSearchBuilder Builder for transaction search
   * @return Transactions List
   */
  public DataList<? extends TransactionSummary> abandoned(
      TransactionSearchBuilder transactionSearchBuilder) {
    return abandoned(transactionSearchBuilder.build());
  }

  /**
   * Search transactions by notification code
   *
   * @param notificationCode Notification code
   * @return Transactions list
   */
  public TransactionDetail byNotificationCode(String notificationCode) {
    return new TransactionSearchByNotification(notificationCode).execute(pagSeguro, httpClient);
  }
}
