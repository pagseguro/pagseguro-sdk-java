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

package br.com.uol.pagseguro.api.common.domain;

/**
 * Class used to set a bank name
 *
 * @author PagSeguro Internet Ltda.
 */
public class BankName {

  private final String stringName;

  /**
   * Constructor
   *
   * @param stringName String name
   */
  public BankName(String stringName) {
    this.stringName = stringName;
  }

  /**
   * Constructor
   *
   * @param name Name
   */
  public BankName(Name name) {
    this.stringName = name.getStringName();
  }

  /**
   * Get string name
   *
   * @return string name
   */
  public String getStringName() {
    return stringName;
  }

  /**
   * Get bank name by string name
   * @return Name
   */
  public Name getName() {
    return Name.fromName(stringName);
  }

  /**
   * Enum name of Bank
   */
  public enum Name {

    /**
     * BRADESCO
     */
    BRADESCO("bradesco"),

    /**
     * ITAU
     */
    ITAU("itau"),

    /**
     * BANCO DO BRASIL
     */
    BANCO_DO_BRASIL("bancodobrasil"),

    /**
     * HENRISUL
     */
    BANRISUL("banrisul"),

    /**
     * HSBC
     */
    HSBC("hsbc"),

    /**
     * OTHER
     */
    OTHER(null);

    private final String stringName;

    /**
     * Constructor
     *
     * @param stringName String name
     */
    Name(String stringName) {
      this.stringName = stringName;
    }

    /**
     * Get string name
     *
     * @return String name
     */
    public String getStringName() {
      return stringName;
    }

    /**
     * Get enum from string name
     *
     * @param stringName String name
     * @return Enum
     */
    public static Name fromName(String stringName) {
      for (Name name : Name.values()) {
        if (name.stringName != null && name.stringName.equalsIgnoreCase(stringName)) {
          return name;
        }
      }
      return OTHER;
    }
  }
}
