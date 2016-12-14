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

package br.com.uol.pagseguro.api.transaction;

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for transaction identify
 *
 * @author PagSeguro Internet Ltda.
 */
public final class TransactionIdentifyBuilder implements Builder<TransactionIdentify> {

  private String code = null;

  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set code of transaction identify
   *
   * @param code Code
   * @return Builder for transaction identify
   * @see TransactionIdentify#getCode()
   */
  public TransactionIdentifyBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  /**
   * Add parameter to transaction identify
   *
   * @param parameter Parameter
   * @return Builder for transaction identify
   * @see TransactionIdentify#getParameters()
   */
  public TransactionIdentifyBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add parameter to transaction identify
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for transaction identify
   * @see TransactionIdentify#getParameters()
   */
  public TransactionIdentifyBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add parameters to transaction identify
   *
   * @param parameters Parameters
   * @return Builder for transaction identify
   * @see TransactionIdentify#getParameters()
   */
  public TransactionIdentifyBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the transaction identify
   *
   * @return Interface for transaction identify
   */
  @Override
  public TransactionIdentify build() {
    return new SimpleTransactionIdentify(this);
  }

  /**
   * Implementation of {@code TransactionIdentify}
   */
  private static class SimpleTransactionIdentify implements TransactionIdentify {

    private final TransactionIdentifyBuilder transactionIdentifyBuilder;

    public SimpleTransactionIdentify(TransactionIdentifyBuilder transactionIdentifyBuilder) {
      this.transactionIdentifyBuilder = transactionIdentifyBuilder;
    }

    @Override
    public String getCode() {
      return transactionIdentifyBuilder.code;
    }

    @Override
    public List<Parameter> getParameters() {
      return transactionIdentifyBuilder.parameters;
    }
  }
}
