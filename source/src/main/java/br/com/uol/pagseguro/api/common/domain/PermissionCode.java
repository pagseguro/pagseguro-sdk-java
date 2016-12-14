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
 * Class for permission code
 *
 * @author PagSeguro Internet Ltda.
 */
public class PermissionCode {

  private String codeId;

  /**
   * Constructor
   *
   * @param codeId Code id
   */
  public PermissionCode(String codeId) {
    this.codeId = codeId;
  }

  /**
   * Get code id
   *
   * @return Code id
   */
  public String getCodeId() {
    return codeId;
  }

  /**
   * Get code by code id
   *
   * @return Code
   * @see Code
   */
  public Code getCode() {
    return Code.fromStringCode(codeId);
  }

  /**
   * Code enum
   */
  public enum Code {

    /**
     * The application can direct buyers to PagSeguro and intermediate payments
     */
    CREATE_CHECKOUTS("CREATE_CHECKOUTS"),

    /**
     * The application may receive and see notifications of transactions it brokered
     */
    RECEIVE_TRANSACTION_NOTIFICATIONS("RECEIVE_TRANSACTION_NOTIFICATIONS"),

    /**
     * The application can query the transactions it brokered
     */
    SEARCH_TRANSACTIONS("SEARCH_TRANSACTIONS"),

    /**
     * The application can manage and use pre-approvals payments
     */
    MANAGE_PAYMENT_PRE_APPROVALS("MANAGE_PAYMENT_PRE_APPROVALS"),

    /**
     * The application can manage payments via transparent checkout.
     * Attention: You need to release through the commercial area of ​​PagSeguro
     */
    DIRECT_PAYMENT("DIRECT_PAYMENT"),

    /**
     * UNRECOGNIZED
     */
    UNRECOGNIZED(null);



    private final String stringCode;

    Code(String stringCode) {
      this.stringCode = stringCode;
    }

    public String getStringCode() {
      return stringCode;
    }

    public static Code fromStringCode(String stringCode) {
      for (Code code : Code.values()) {
        if (code.stringCode != null && code.stringCode.equalsIgnoreCase(stringCode)) {
          return code;
        }
      }
      return UNRECOGNIZED;
    }
  }
}
