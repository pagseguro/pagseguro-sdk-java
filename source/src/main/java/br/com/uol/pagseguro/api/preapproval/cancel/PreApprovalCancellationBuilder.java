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

package br.com.uol.pagseguro.api.preapproval.cancel;

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Pre Approval Cancellation
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PreApprovalCancellationBuilder implements Builder<PreApprovalCancellation> {

  private String code = null;

  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set Pre Approval Code of Pre Approval Cancellation
   *
   * @param code Pre Approval Code
   * @return Builder for Pre Approval Cancellation
   * @see PreApprovalCancellation#getCode()
   */
  public PreApprovalCancellationBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  /**
   * Add Pre Approval Parameter of Pre Approval Cancellation
   *
   * @param parameter Parameter
   * @return Builder for Pre Approval Cancellation
   * @see PreApprovalCancellation#getParameters()
   */
  public PreApprovalCancellationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Pre Approval Parameter of Pre Approval Cancellation
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for Pre Approval Cancellation
   * @see PreApprovalCancellation#getParameters()
   */
  public PreApprovalCancellationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Pre Approval Parameter of Pre Approval Cancellation
   *
   * @param parameters Parameters
   * @return Builder for Pre Approval Cancellation
   * @see PreApprovalCancellation#getParameters()
   */
  public PreApprovalCancellationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }


  /**
   * Build the Pre Approval Cancellation
   *
   * @return Interface for Pre Approval Cancellation
   * @see PreApprovalCancellation
   */
  @Override
  public PreApprovalCancellation build() {
    return new SimplePreApprovalCancellation(this);
  }

  /**
   * Implementation of {@code PreApprovalCancellation}
   */
  private static class SimplePreApprovalCancellation implements PreApprovalCancellation {

    private final PreApprovalCancellationBuilder preApprovalCancellationBuilder;

    public SimplePreApprovalCancellation(PreApprovalCancellationBuilder preApprovalCancellationBuilder) {
      this.preApprovalCancellationBuilder = preApprovalCancellationBuilder;
    }

    @Override
    public String getCode() {
      return preApprovalCancellationBuilder.code;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return preApprovalCancellationBuilder.parameters;
    }
  }
}
