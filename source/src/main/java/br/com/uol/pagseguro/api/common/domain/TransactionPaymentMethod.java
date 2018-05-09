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
 * Interface for transaction payment method
 *
 * @author PagSeguro Internet Ltda.
 */
public interface TransactionPaymentMethod {

  /**
   * Get type id of transaction payment method
   *
   * @return Type id
   */
  Integer getTypeId();

  /**
   * Get code id of transaction payment method
   *
   * @return Code id
   */
  Integer getCodeId();

  /**
   * Get code of transaction payment method
   *
   * @return Code
   * @see Code
   */
  Code getCode();

  /**
   * Get type of transaction payment method
   *
   * @return Type
   * @see Type
   */
  Type getType();

  /**
   * Code enum
   */
  enum Code {

    /**
     * VISA
     */
    VISA(101),

    /**
     * MASTERCARD
     */
    MASTERCARD(102),

    /**
     * AMEX
     */
    AMEX(103),

    /**
     * BRADESCO
     */
    BRADESCO(201),


    /**
     * Used when they have not the code
     *
     * UNKNOW
     */
    UNKNOW(null),

    /**
     * Used when you have the code but is not mapped in lib.
     *
     * UNRECOGNIZED
     */
    UNRECOGNIZED(null);

    private Integer codeId;

    /**
     * Constructor
     */
    Code(Integer codeId) {
      this.codeId = codeId;
    }

    /**
     * Get enum code by code id
     *
     * @param codeId Code id
     * @return Enum code
     */
    public static Code fromCodeId(int codeId) {
      for (Code code : Code.values()) {
        if (code.codeId != null && code.codeId == codeId) {
          return code;
        }
      }
      return UNRECOGNIZED;
    }

  }

  /**
   * Type enum
   */
  enum Type {

    /**
     * CREDIT CARD
     */
    CREDIT_CARD(1),

    /**
     * BANK SLIP
     */
    BOLETO(2),

    /**
     * ONLINE DEBIT
     */
    ONLINE_DEBIT(3),

    SALDO_PAGSEGURO(4),

    DEPOSITO_EM_CONTA(7),

    /**
     * Used when they have not the TYPE
     *
     * UNKNOW
     */
    UNKNOW(null),

    /**
     * Used when you have the TYPE but is not mapped in lib.
     *
     * UNRECOGNIZED
     */
    UNRECOGNIZED(null);

    private Integer typeId;

    /**
     * Constructor
     *
     * @param typeId Type id
     */
    Type(Integer typeId) {
      this.typeId = typeId;
    }

    /**
     * Get type enum by type id
     *
     * @param typeId Type id
     * @return Type enum
     */
    public static Type fromTypeId(int typeId) {
      for (Type type : Type.values()) {
        if (type.typeId != null && type.typeId == typeId) {
          return type;
        }
      }
      return UNRECOGNIZED;
    }

  }
}
