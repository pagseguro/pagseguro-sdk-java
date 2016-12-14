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
package br.com.uol.pagseguro.api.application.authorization.search;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.http.HttpClient;

/**
 * Factory to search authorizations.
 * This class is responsible for build the searches for authorizations
 *
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationSearchResource {

  private final PagSeguro pagSeguro;
  private final HttpClient httpClient;

  public AuthorizationSearchResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * Search authorization by code
   *
   * @param code The authorization code that you want to search
   * @return Authorization detail
   * @see AuthorizationDetail
   * @see AuthorizationSearchByCode#execute(PagSeguro, HttpClient)
   */
  public AuthorizationDetail byCode(String code) {
    return new AuthorizationSearchByCode(code).execute(pagSeguro, httpClient);
  }

  /**
   * Search authorization by notification code
   *
   * @param code The notification code of authorization that you want to search
   * @return Authorization detail
   * @see AuthorizationDetail
   * @see AuthorizationSearchByNotification#execute(PagSeguro, HttpClient)
   */
  public AuthorizationDetail byNotificationCode(String code) {
    return new AuthorizationSearchByNotification(code).execute(pagSeguro, httpClient);
  }

  /**
   * Search authorizations by reference
   *
   * @param reference The reference of authorization that you want to search
   * @return Authorizations list
   * @see DataList
   * @see AuthorizationSummary
   * @see AuthorizationSearchBuilder
   */
  public DataList<? extends AuthorizationSummary> byReference(String reference) {
    return byDateRange(new AuthorizationSearchBuilder().withReference(reference).build());
  }

  /**
   * Search authorizations by date range and reference
   *
   * @param dateRangeBuilder The date range builder of date range that you want to search
   * @param reference        The reference of authorization that you want search
   * @param page             The page that you want to search
   * @param maxResults       The max results that you want to search
   * @return Authorizations List
   * @see DataList
   * @see DateRangeBuilder
   * @see AuthorizationSummary
   */
  public DataList<? extends AuthorizationSummary> byDateRange(DateRangeBuilder dateRangeBuilder,
                                                              String reference, int page,
                                                              int maxResults) {
    return byDateRange(new AuthorizationSearchBuilder().withDateRange(dateRangeBuilder)
        .withReference(reference).withPage(page).withMaxResults(maxResults).build());
  }

  /**
   * Search authorizations by date range
   *
   * @param dateRangeBuilder The date range builder of date range that you want to search
   * @param page             The page that you want to search
   * @param maxResults       The max results that you want to search
   * @return Authorizations List
   * @see AuthorizationSummary
   * @see DateRangeBuilder
   * @see AuthorizationSearchBuilder
   * @see DataList
   */
  public DataList<? extends AuthorizationSummary> byDateRange(DateRangeBuilder dateRangeBuilder,
                                                              int page, int maxResults) {
    return byDateRange(new AuthorizationSearchBuilder().withDateRange(dateRangeBuilder)
        .withPage(page).withMaxResults(maxResults).build());
  }

  /**
   * Search authorizations by builder for authorization search
   *
   * @param authorizationSearchBuilder Builder for interface authorization with params to search
   * @return Authorizations List
   * @see AuthorizationSummary
   * @see DataList
   * @see AuthorizationSearchBuilder
   */
  public DataList<? extends AuthorizationSummary> byDateRange(
      AuthorizationSearchBuilder authorizationSearchBuilder) {
    return byDateRange(authorizationSearchBuilder.build());
  }

  /**
   * Search authorizations by interface for authorization search
   *
   * @param authorizationSearch Interface authorization with params to search
   * @return Authorizations List
   * @see AuthorizationSummary
   * @see DataList
   * @see AuthorizationSearch
   */
  public DataList<? extends AuthorizationSummary> byDateRange(
      AuthorizationSearch authorizationSearch) {
    return new AuthorizationSearchByDateRange(authorizationSearch).execute(pagSeguro, httpClient);
  }
}
