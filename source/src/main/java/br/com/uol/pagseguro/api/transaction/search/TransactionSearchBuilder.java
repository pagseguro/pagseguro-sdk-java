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

package br.com.uol.pagseguro.api.transaction.search;

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Transaction Search
 *
 * @author PagSeguro Internet Ltda.
 */
public final class TransactionSearchBuilder implements Builder<TransactionSearch> {

  private DateRange dateRange = new DateRangeBuilder().build();

  private String reference = null;

  private Integer page = null;

  private Integer maxResults = null;

  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set Date Range
   *
   * @param dateRange Date Range
   * @return Builder for Transaction Search
   * @see TransactionSearch#getDateRange()
   */
  public TransactionSearchBuilder withDateRange(DateRange dateRange) {
    this.dateRange = dateRange;
    return this;
  }

  /**
   * Set Date Range by Builder
   *
   * @param dateRangeBuilder Date Range Builder
   * @return Builder for Transaction Search
   * @see TransactionSearch#getDateRange()
   */
  public TransactionSearchBuilder withDateRange(Builder<DateRange> dateRangeBuilder) {
    return withDateRange(dateRangeBuilder.build());
  }

  /**
   * Set Reference
   *
   * @param reference Reference
   * @return Builder for Transaction Search
   * @see TransactionSearch#getReference()
   */
  public TransactionSearchBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set page
   *
   * @param page Page
   * @return Builder for Transaction Search
   * @see TransactionSearch#getPage()
   */
  public TransactionSearchBuilder withPage(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Max Results
   *
   * @param maxResults Max Results
   * @return Builder for Transaction Search
   * @see TransactionSearch#getMaxResults()
   */
  public TransactionSearchBuilder withMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
    return this;
  }

  /**
   * Add parameter
   *
   * @param parameter Parameter
   * @return Builder for Transaction Search
   * @see TransactionSearch#getParameters()
   */
  public TransactionSearchBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add parameter
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for Transaction Search
   * @see TransactionSearch#getParameters()
   */
  public TransactionSearchBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add parameters
   *
   * @param parameters Parameters
   * @return Builder for Transaction Search
   * @see TransactionSearch#getParameters()
   */
  public TransactionSearchBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the Transaction Search
   *
   * @return Interface for Transaction Search
   * @see TransactionSearch
   */
  @Override
  public TransactionSearch build() {
    return new SimpleTransactionSearch(this);
  }

  /**
   * Implementation of {@code TransactionSearch}
   */
  private static class SimpleTransactionSearch implements TransactionSearch {

    private final TransactionSearchBuilder transactionSearchBuilder;

    public SimpleTransactionSearch(TransactionSearchBuilder transactionSearchBuilder) {
      this.transactionSearchBuilder = transactionSearchBuilder;
    }

    @Override
    public DateRange getDateRange() {
      return transactionSearchBuilder.dateRange;
    }

    @Override
    public String getReference() {
      return transactionSearchBuilder.reference;
    }

    @Override
    public Integer getPage() {
      return transactionSearchBuilder.page;
    }

    @Override
    public Integer getMaxResults() {
      return transactionSearchBuilder.maxResults;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return transactionSearchBuilder.parameters;
    }
  }
}
