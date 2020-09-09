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
 * Payment method name. Used to define the payment method name.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum PaymentMethodName {

  /**
   * Debit Bradesco
   */
  DEBIT_BRADESCO("DEBITO_BRADESCO"),

  /**
   * Debit Itau
   */
  DEBIT_ITAU("DEBITO_ITAU"),

  /**
   * Debit Unibanco
   */
  DEBIT_UNIBANCO("DEBITO_UNIBANCO"),

  /**
   * Debit Banco do Brasil
   */
  DEBIT_BANCO_BRASIL("DEBITO_BANCO_BRASIL"),

  /**
   * Debit Banrisul
   */
  DEBIT_BANRISUL("DEBITO_BANRISUL"),

  /**
   * Bank slip
   */
  BANK_SLIP("BOLETO"),

  /**
   * VISA
   */
  VISA("VISA"),

  /**
   * MASTERCARD
   */
  MASTERCARD("MASTERCARD"),

  /**
   * AMEX
   */
  AMEX("AMEX"),

  /**
   * DINERS
   */
  DINERS("DINERS"),

  /**
   * HIPERCARD
   */
  HIPERCARD("HIPERCARD"),

  /**
   * AURA
   */
  AURA("AURA"),

  /**
   * ELO
   */
  ELO("ELO"),

  /**
   * PLENOCARD
   */
  PLENOCARD("PLENOCARD"),

  /**
   * PERSONALCARD
   */
  PERSONALCARD("PERSONALCARD"),

  /**
   * JCB
   */
  JCB("JCB"),
  DISCOVER("DISCOVER"),

  /**
   * BRASILCARD
   */
  BRASILCARD("BRASILCARD"),

  /**
   * FORTBRASIL
   */
  FORTBRASIL("FORTBRASIL"),

  /**
   * CARDBAN
   */
  CARDBAN("CARDBAN"),

  /**
   * VALECARD
   */
  VALECARD("VALECARD"),

  /**
   * CABAL
   */
  CABAL("CABAL"),

  /**
   * MAIS
   */
  MAIS("MAIS"),

  /**
   * AVISTA
   */
  AVISTA("AVISTA"),

  /**
   * GRANDCARD
   */
  GRANDCARD("GRANDCARD"),

  /**
   * SOROCRED
   */
  SOROCRED("SOROCRED"),

  /**
   * BANESECARD
   */
  BANESECARD("BANESECARD"),

  /**
   * UPBRASIL
   */
  UPBRASIL("UPBRASIL"),

  /**
   * UNRECOGNIZED
   */
  UNRECOGNIZED(null);

  private final String value;

  /**
   * Constructor
   *
   * @param value Value
   */
  PaymentMethodName(String value) {
    this.value = value;
  }

  /**
   * Get value
   *
   * @return Value
   */
  public String getValue() {
    return value;
  }

  /**
   * Get enum by value
   *
   * @param value Value
   * @return Enum
   */
  public static PaymentMethodName fromValue(String value) {
    for (PaymentMethodName paymentMethodName : PaymentMethodName.values()) {
      if (paymentMethodName.value != null && paymentMethodName.value.equalsIgnoreCase(value)) {
        return paymentMethodName;
      }
    }
    return UNRECOGNIZED;
  }
}
