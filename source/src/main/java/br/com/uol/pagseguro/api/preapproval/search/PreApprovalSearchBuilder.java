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

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for pre approval (signature) search.
 * This class is responsible for building the attributes for search pre approval (signature)
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PreApprovalSearchBuilder implements Builder<PreApprovalSearch> {

  private DateRange dateRange = new DateRangeBuilder().build();

  private String reference = null;

  private Integer page = null;

  private Integer maxResults = null;

  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set Date Range
   *
   * @param dateRange Date Range
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getDateRange()
   */
  public PreApprovalSearchBuilder withDateRange(DateRange dateRange) {
    this.dateRange = dateRange;
    return this;
  }

  /**
   * Set Date Range
   *
   * @param dateRangeBuilder Builder for Date Range
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getDateRange()
   */
  public PreApprovalSearchBuilder withDateRange(Builder<DateRange> dateRangeBuilder) {
    return withDateRange(dateRangeBuilder.build());
  }

  /**
   * Set reference
   *
   * @param reference Reference of pre approval
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getReference()
   */
  public PreApprovalSearchBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set page
   *
   * @param page Page of search
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getPage()
   */
  public PreApprovalSearchBuilder withPage(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Set max results
   *
   * @param maxResults Max results of search
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getMaxResults()
   */
  public PreApprovalSearchBuilder withMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
    return this;
  }

  /**
   * Add Parameter
   *
   * @param parameter Parameter
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getParameters()
   */
  public PreApprovalSearchBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add parameter
   *
   * @param parameterBuilder Builder for parameter
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getParameters()
   */
  public PreApprovalSearchBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add parameters
   *
   * @param parameters Parameters
   * @return Builder for Pre Approval Search
   * @see PreApprovalSearch#getParameters()
   */
  public PreApprovalSearchBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the Pre Approval Search
   *
   * @return Interface for Pre Approval Search
   * @see PreApprovalSearch
   */
  @Override
  public PreApprovalSearch build() {
    return new SimplePreApprovalSearch(this);
  }

  /**
   * Implementation of {@code PreApprovalSearch}
   */
  private static class SimplePreApprovalSearch implements PreApprovalSearch {

    private final PreApprovalSearchBuilder preApprovalSearchBuilder;

    public SimplePreApprovalSearch(PreApprovalSearchBuilder preApprovalSearchBuilder) {
      this.preApprovalSearchBuilder = preApprovalSearchBuilder;
    }

    @Override
    public DateRange getDateRange() {
      return preApprovalSearchBuilder.dateRange;
    }

    @Override
    public String getReference() {
      return preApprovalSearchBuilder.reference;
    }

    @Override
    public Integer getPage() {
      return preApprovalSearchBuilder.page;
    }

    @Override
    public Integer getMaxResults() {
      return preApprovalSearchBuilder.maxResults;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return preApprovalSearchBuilder.parameters;
    }
  }
}
