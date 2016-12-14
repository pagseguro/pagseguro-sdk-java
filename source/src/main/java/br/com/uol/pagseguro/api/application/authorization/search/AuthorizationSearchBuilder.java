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

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for authorizations search.
 * This class is responsible for building the parameters for search of authorizations
 *
 * @author PagSeguro Internet Ltda.
 * @see AuthorizationSearch
 */
public final class AuthorizationSearchBuilder implements Builder<AuthorizationSearch> {

  private DateRange dateRange = new DateRangeBuilder().build();

  private String reference = null;

  private Integer page = null;

  private Integer maxResults = null;

  /**
   * Set the date range for authorizations search
   *
   * @param dateRange The date range that you want to search
   * @return Builder for authorizations search
   * @see AuthorizationSearch#getDateRange()
   */
  public AuthorizationSearchBuilder withDateRange(DateRange dateRange) {
    this.dateRange = dateRange;
    return this;
  }

  /**
   * Set builder for date range. Used in authorizations search
   *
   * @param dateRangeBuilder Builder for date range
   * @return Builder for authorizations search
   * @see AuthorizationSearch#getDateRange()
   */
  public AuthorizationSearchBuilder withDateRange(DateRangeBuilder dateRangeBuilder) {
    return withDateRange(dateRangeBuilder.build());
  }

  /**
   * Set reference of authorization for authorizations search
   *
   * @param reference The reference of authorization that you want search
   * @return Builder for Authorization Search
   * @see AuthorizationSearch#getReference()
   */
  public AuthorizationSearchBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set the page for authorizations search
   *
   * @param page The page that you want to search
   * @return Builder for Authorization Search
   * @see AuthorizationSearch#getPage()
   */
  public AuthorizationSearchBuilder withPage(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Set the max results for authorizations search
   *
   * @param maxResults The max results that you want to search
   * @return Builder for Authorization Search
   * @see AuthorizationSearch#getMaxResults()
   */
  public AuthorizationSearchBuilder withMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
    return this;
  }

  /**
   * Build authorization search
   *
   * @return Authorization Search
   */
  @Override
  public AuthorizationSearch build() {
    return new SimpleAuthorizationSearch(this);
  }

  /**
   * Interface of authorization search built.
   * Implementation of {@code AuthorizationSearch}
   */
  private static class SimpleAuthorizationSearch implements AuthorizationSearch {

    private final AuthorizationSearchBuilder authorizationSearchBuilder;

    public SimpleAuthorizationSearch(AuthorizationSearchBuilder authorizationSearchBuilder) {
      this.authorizationSearchBuilder = authorizationSearchBuilder;
    }

    @Override
    public DateRange getDateRange() {
      return authorizationSearchBuilder.dateRange;
    }

    @Override
    public String getReference() {
      return authorizationSearchBuilder.reference;
    }

    @Override
    public Integer getPage() {
      return authorizationSearchBuilder.page;
    }

    @Override
    public Integer getMaxResults() {
      return authorizationSearchBuilder.maxResults;
    }
  }
}
