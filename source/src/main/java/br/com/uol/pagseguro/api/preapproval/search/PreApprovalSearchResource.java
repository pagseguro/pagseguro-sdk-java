/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 * 
 * NOTICE OF LICENSE
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */

package br.com.uol.pagseguro.api.preapproval.search;

import java.util.List;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Factory to search pre approval
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalSearchResource {

  private final PagSeguro pagSeguro;

  private final HttpClient httpClient;

  public PreApprovalSearchResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * Search pre approval by notification code
   *
   * @param code Notification code of pre approval
   * @return Pre approval detail
   * @see PreApprovalDetail
   * @see PreApprovalSearchByNotification#execute(PagSeguro, HttpClient)
   */
  public PreApprovalDetail byNotificationCode(String code) {
    return new PreApprovalSearchByNotification(code).execute(pagSeguro, httpClient);
  }

  /**
   * Search pre approval by code
   *
   * @param code Pre approval code
   * @return Pre approval detail
   * @see PreApprovalDetail
   * @see PreApprovalSearchByCode#execute(PagSeguro, HttpClient)
   */
  public PreApprovalDetail byCode(String code) {
    return new PreApprovalSearchByCode(code).execute(pagSeguro, httpClient);
  }

  /**
   * Search pre approval by Interval
   *
   * @param interval Interval in days
   * @return Pre Approval List
   * @see DataList
   * @see PreApprovalSummary
   * @see PreApprovalSearchByInterval#execute(PagSeguro, HttpClient)
   */
  public DataList<? extends PreApprovalSummary> byInterval(int interval) {
    return new PreApprovalSearchByInterval(interval).execute(pagSeguro, httpClient);
  }

  /**
   * Search pre approval by date range
   *
   * @param preApprovalSearch Interface for Pre Approval Search
   * @return Pre Approval list
   * @see DataList
   * @see PreApprovalSummary
   * @see PreApprovalSearchByDateRange#execute(PagSeguro, HttpClient)
   */
  public DataList<? extends PreApprovalSummary> byDateRange(PreApprovalSearch preApprovalSearch) {
    return new PreApprovalSearchByDateRange(preApprovalSearch).execute(pagSeguro, httpClient);
  }

  /**
   * Search pre approval by date range
   *
   * @param preApprovalSearchBuilder Builder for Interface for Pre Approval Search
   * @return Pre Approval list
   * @see DataList
   * @see PreApprovalSummary
   * @see PreApprovalSearchByDateRange#execute(PagSeguro, HttpClient)
   */
  public DataList<? extends PreApprovalSummary> byDateRange(
      Builder<PreApprovalSearch> preApprovalSearchBuilder) {
    return byDateRange(preApprovalSearchBuilder.build());
  }

  /**
   * Search pre approval by date range
   *
   * @param dateRangeBuilder Builder for Dante Range
   * @param page             Page o search
   * @param maxResults       Max results of search
   * @return Pre Approval list
   * @see DataList
   * @see PreApprovalSummary
   * @see PreApprovalSearchByDateRange#execute(PagSeguro, HttpClient)
   */
  public DataList<? extends PreApprovalSummary> byDateRange(DateRangeBuilder dateRangeBuilder,
                                                            int page, int maxResults) {
    return byDateRange(new PreApprovalSearchBuilder().withDateRange(dateRangeBuilder).withPage(page)
        .withMaxResults(maxResults).build());
  }

  /**
   * Search pre approval by date range and parameters
   *
   * @param dateRangeBuilder Builder for Date Range
   * @param page             Page of search
   * @param maxResults       Max Results of search
   * @param parameters       Parameters
   * @return Pre Approval List
   * @see DataList
   * @see PreApprovalSummary
   * @see Parameter
   */
  public DataList<? extends PreApprovalSummary> byDateRange(DateRangeBuilder dateRangeBuilder,
                                                            int page, int maxResults,
                                                            List<? extends Parameter> parameters) {
    return byDateRange(new PreApprovalSearchBuilder().withDateRange(dateRangeBuilder).withPage(page)
        .withMaxResults(maxResults).addParameters(parameters).build());
  }

  /**
   * Search pre approval by reference
   *
   * @param reference Reference of pre approval
   * @return Pre Approval List
   * @see DataList
   * @see PreApprovalSummary
   */
  public DataList<? extends PreApprovalSummary> byReference(String reference) {
    return byDateRange(new PreApprovalSearchBuilder().withReference(reference).build());
  }

}
