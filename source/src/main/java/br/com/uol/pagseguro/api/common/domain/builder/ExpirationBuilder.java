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

import br.com.uol.pagseguro.api.common.domain.Expiration;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for expiration
 *
 * @author PagSeguro Internet Ltda.
 */
public final class ExpirationBuilder implements Builder<Expiration> {

  private Integer value;

  private String unit;

  /**
   * Set value of expiration
   *
   * @param value Expiration
   * @return Builder for expiration
   * @see Expiration#getValue()
   */
  public ExpirationBuilder withValue(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * Set unit of expiration
   *
   * @param unit Expiration
   * @return Builder for expiration
   * @see Expiration#getUnit()
   */
  public ExpirationBuilder withUnit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Build the phone
   *
   * @return Interface for Phone
   * @see Expiration
   */
  @Override
  public Expiration build() {
    return new SimpleExpiration(this);
  }

  /**
   * Implementation of {@code Phone}
   */
  private static class SimpleExpiration implements Expiration {

    private ExpirationBuilder expirationBuilder;

    private SimpleExpiration(ExpirationBuilder expirationBuilder) {
      this.expirationBuilder = expirationBuilder;
    }

    @Override
    public Integer getValue() {
      return expirationBuilder.value;
    }

    @Override
    public String getUnit() {
      return expirationBuilder.unit;
    }

  }

}
