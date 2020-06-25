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
 * States Enum
 *
 * @author PagSeguro Internet Ltda.
 */
public enum State {

  /**
   * Acronym for Alagoas
   */
  AL("AL"),

  /**
   * Acronym for Amapá
   */
  AP("AP"),

  /**
   * Acronym for Amazonas
   */
  AM("AM"),

  /**
   * Acronym for Bahia
   */
  BA("BA"),

  /**
   * Acronym for Ceará
   */
  CE("CE"),

  /**
   * Acronym for Espírito Santo
   */
  ES("ES"),

  /**
   * Acronym for Goías
   */
  GO("GO"),

  /**
   * Acronym for Maranhão
   */
  MA("MA"),

  /**
   * Acronym for Mato Grosso
   */
  MT("MT"),

  /**
   * Acronym for Mato Grosso do Sul
   */
  MS("MS"),

  /**
   * Acronym for Minas Gerais
   */
  MG("MG"),

  /**
   * Acronym for Pará
   */
  PA("PA"),

  /**
   * Acronym for Paraíba
   */
  PB("PB"),

  /**
   * Acronym for Pernambuco
   */
  PE("PE"),
    
  /**
   * Acronym for Paraná
   */
  PR("PR"),
  
  /**
   * Acronym for Piauí
   */
  PI("PI"),

  /**
   * Acronym for Rio de Janeiro
   */
  RJ("RJ"),

  /**
   * Acronym for Rio Grande do Norte
   */
  RN("RN"),

  /**
   * Acronym for Rio Grande do Sul
   */
  RS("RS"),

  /**
   * Acronym for Rondonia
   */
  RO("RO"),

  /**
   * Acronym for Roraima
   */
  RR("RR"),

  /**
   * Acronym for Santa Catarina
   */
  SC("SC"),

  /**
   * Acronym for São Paulo
   */
  SP("SP"),

  /**
   * Acronym for Sergipe
   */
  SE("SE"),

  /**
   * Acronym for Tocantins
   */
  TO("TO"),

  /**
   * Acronym for Distrito Federal
   */
  DF("DF"),

  /**
   * Acronym for Example
   */
  XX("Example");

  private final String stringValue;

  /**
   * Constructor
   *
   * @param stringValue Value of Enum
   */
  State(String stringValue) {
    this.stringValue = stringValue;
  }

  /**
   * @return Value of Enum
   */
  public String getStringValue() {
    return stringValue;
  }

}
