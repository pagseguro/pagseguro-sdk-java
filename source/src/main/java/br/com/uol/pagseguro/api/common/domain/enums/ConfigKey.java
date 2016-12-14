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

package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * Config enum. Used in payment methods.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum ConfigKey {

  /**
   * Used to set the percentage discount for a means of payment
   */
  DISCOUNT_PERCENT("DISCOUNT_PERCENT"),

  /**
   * Used to set the installment threshold setting and value as the number of shares you wish to
   * present to the client (2-18 installments).
   */
  MAX_INSTALLMENTS_LIMIT("MAX_INSTALLMENTS_LIMIT"),

  /**
   * Used to set the installment configuration without extra and value as the number of shares you
   * want to take (2-18 installments).
   */
  MAX_INSTALLMENTS_NO_INTEREST("MAX_INSTALLMENTS_NO_INTEREST"),

  /**
   * When config is null
   */
  UNRECOGNIZED(null);

  private final String value;

  /**
   * Constructor
   *
   * @param value Value of enum config
   */
  ConfigKey(String value) {
    this.value = value;
  }

  /**
   * Get enum value
   *
   * @return Enum value
   */
  public String getValue() {
    return value;
  }

  public static ConfigKey fromValue(String value) {
    for (ConfigKey configKey : ConfigKey.values()) {
      if (configKey.value != null && configKey.value.equalsIgnoreCase(value)) {
        return configKey;
      }
    }
    return UNRECOGNIZED;
  }
}
