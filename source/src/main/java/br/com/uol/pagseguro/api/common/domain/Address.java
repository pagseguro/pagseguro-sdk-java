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
 * Interface of address. This class is responsible for determining the
 * attributes of the address.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface Address {

  /**
   * Get country.
   * Determines the country of address
   *
   * @return The country attribute of address
   */
  String getCountry();

  /**
   * Get State.
   * Determines the state of address
   *
   * @return The state of address
   */
  String getState();

  /**
   * Get city.
   * Determines the city of address
   *
   * @return The city of address
   */
  String getCity();

  /**
   * Get postal code.
   * Determines the postal code of address
   *
   * @return The postal code of address
   */
  String getPostalCode();

  /**
   * Get district.
   * Determines the district of address
   *
   * @return The district of address
   */
  String getDistrict();

  /**
   * Get street.
   * Determines the street of address
   *
   * @return The street of address
   */
  String getStreet();

  /**
   * Get number.
   * Determines the number of address
   *
   * @return The number of address
   */
  String getNumber();

  /**
   * Get complement.
   * Determines the complement of address
   *
   * @return The complemenet of address
   */
  String getComplement();

}
