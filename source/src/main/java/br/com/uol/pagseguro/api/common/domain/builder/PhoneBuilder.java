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

import br.com.uol.pagseguro.api.common.domain.Phone;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for phone
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PhoneBuilder implements Builder<Phone> {

  private String areaCode;

  private String phoneNumber;

  /**
   * Set area code of phone
   *
   * @param areaCode Area code
   * @return Builder for phone
   * @see Phone#getAreaCode()
   */
  public PhoneBuilder withAreaCode(String areaCode) {
    this.areaCode = areaCode;
    return this;
  }

  /**
   * Set Number of phone
   *
   * @param phoneNumber Phone number
   * @return Builder for Phone
   * @see Phone#getNumber()
   */
  public PhoneBuilder withNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Build the phone
   *
   * @return Interface for Phone
   * @see Phone
   */
  @Override
  public Phone build() {
    return new SimplePhone(this);
  }

  /**
   * Implementation of {@code Phone}
   */
  private static class SimplePhone implements Phone {

    private PhoneBuilder phoneBuilder;

    private SimplePhone(PhoneBuilder phoneBuilder) {
      this.phoneBuilder = phoneBuilder;
    }

    @Override
    public String getAreaCode() {
      return phoneBuilder.areaCode;
    }

    @Override
    public String getNumber() {
      return phoneBuilder.phoneNumber;
    }

  }

}
