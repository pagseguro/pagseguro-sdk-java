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
 * Class for shipping
 *
 * @author PagSeguro Internet Ltda.
 */
public class ShippingType {

  private final int typeId;

  /**
   * Constructor
   *
   * @param typeId Type id
   */
  public ShippingType(int typeId) {
    this.typeId = typeId;
  }

  /**
   * Constructor
   *
   * @param type Type
   * @see Type
   */
  public ShippingType(Type type) {
    this.typeId = type.getTypeId();
  }

  /**
   * Get type id
   *
   * @return Type id
   */
  public Integer getTypeId() {
    return typeId;
  }

  /**
   * Get type by id
   *
   * @return Type
   */
  public Type getType() {
    return Type.fromTypeId(typeId);
  }

  /**
   * Type enum
   */
  public enum Type {

    /**
     * PAC
     */
    PAC(1),

    /**
     * SEDEX
     */
    SEDEX(2),

    /**
     * USER CHOICE
     * TODO: Verificar nome
     */
    USER_CHOISE(3),

    /**
     * UNRECOGNIZED
     */
    UNRECOGNIZED(null);

    private final Integer typeId;

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
    public static Type fromTypeId(int typeId) {
      for (Type type : Type.values()) {
        if (type.typeId != null && type.typeId == typeId) {
          return type;
        }
      }
      return UNRECOGNIZED;
    }

    /**
     * Get type id
     *
     * @return Type id
     */
    public Integer getTypeId() {
      return typeId;
    }

  }
}
