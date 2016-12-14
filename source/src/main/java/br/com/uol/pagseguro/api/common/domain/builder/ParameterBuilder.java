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

package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Parameter
 *
 * @author PagSeguro Internet Ltda.
 */
public final class ParameterBuilder implements Builder<Parameter> {

  private String name;

  private String value;

  /**
   * Set name of parameter
   *
   * @param name Name
   * @return Builder for Parameter
   * @see Parameter#getName()
   */
  public ParameterBuilder withName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Set value of parameter
   *
   * @param value Value of parameter
   * @return Builder for Parameter
   * @see Parameter#getValue()
   */
  public ParameterBuilder withValue(String value) {
    this.value = value;
    return this;
  }

  /**
   * Build the Parameter
   *
   * @return Interface for Parameter
   * @see Parameter
   */
  @Override
  public Parameter build() {
    return new SimpleParameter(this);
  }

  /**
   * Implementation of {@code Parameter}
   */
  private static class SimpleParameter implements Parameter {

    private final ParameterBuilder parameterBuilder;

    public SimpleParameter(ParameterBuilder parameterBuilder) {
      this.parameterBuilder = parameterBuilder;
    }

    @Override
    public String getName() {
      return parameterBuilder.name;
    }

    @Override
    public String getValue() {
      return parameterBuilder.value;
    }
  }
}
