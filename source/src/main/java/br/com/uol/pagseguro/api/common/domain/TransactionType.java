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
 * Class for transaction type
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionType {

  private final Integer typeId;

  /**
   * Constructor
   *
   * @param typeId Type id
   */
  public TransactionType(Integer typeId) {
    this.typeId = typeId;
  }

  /**
   * Get type id
   *
   * @return Type id
   */
  public int getTypeId() {
    return typeId;
  }

  /**
   * Get type by type id
   *
   * @return Type
   */
  public Type getType() {
    return Type.fromTypeId(typeId);
  }

  /**
   * Type enym
   */
  public enum Type {

    /**
     * CHECKOUT
     */
    CHECKOUT(1),

    /**
     * TRANSFER
     */
    TRANSFER(2),

    /**
     * FUNDS ADDITION
     */
    FUNDS_ADDITION(3),

    /**
     * WITHDRAW
     */
    WITHDRAW(4),

    /**
     * CHARGING
     */
    CHARGING(5),

    /**
     * DONATION
     */
    DONATION(6),

    /**
     * BONUS
     */
    BONUS(7),

    /**
     * BONUS REPASS
     */
    BONUS_REPASS(8),

    /**
     * OPERATIONAL
     */
    OPERATIONAL(9),

    /**
     * POLITICAL DONATION
     */
    POLITICAL_DONATION(10),

    /**
     * PAYMENT PRE APPROVED
     */
    PAYMENT_PRE_APPROVED(11),

    /**
     * BONUS CAMPAIGN
     */
    BONUS_CAMPAIGN(12),

    /**
     * SECONDARY
     */
    SECONDARY(13),

    /**
     * VALIDATOR
     */
    VALIDATOR(14),

    /**
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
     * Get type by id
     *
     * @param typeId Type id
     * @return Type enum
     */
    public static Type fromTypeId(Integer typeId) {
      for (Type type : Type.values()) {
        if (type.typeId != null && type.typeId == typeId) {
          return type;
        }
      }
      return UNRECOGNIZED;
    }

  }

}
