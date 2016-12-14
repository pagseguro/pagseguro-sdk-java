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
package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.Address;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Abstract converter for address.
 * Used to convert attributes of address in request map.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractAddressMapConverter extends AbstractMapConverter<Address> {

  /**
   * Convert attributes of address in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param address    The interface of Address
   * @see RequestMap
   * @see Address
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Address address) {
    requestMap.putString(getCountryKey(), address.getCountry());
    requestMap.putString(getStateKey(), address.getState());
    requestMap.putString(getCityKey(), address.getCity());
    requestMap.putString(getPostalCodeKey(), address.getPostalCode());
    requestMap.putString(getDistrictKey(), address.getDistrict());
    requestMap.putString(getStreetKey(), address.getStreet());
    requestMap.putString(getNumberKey(), address.getNumber());
    requestMap.putString(getComplementKey(), address.getComplement());
  }

  /**
   * Get country key
   *
   * @return The key to the country is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getCountryKey();

  /**
   * Get state key
   *
   * @return The key to the state is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getStateKey();

  /**
   * Get city key
   *
   * @return The key to the city is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getCityKey();

  /**
   * Get postal code key
   *
   * @return The key to the postal code is a key dynamic inserted in the implementation of this
   * class.
   */
  protected abstract String getPostalCodeKey();

  /**
   * Get district key
   *
   * @return The key to the district is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getDistrictKey();

  /**
   * Get street key
   *
   * @return The key to the street is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getStreetKey();

  /**
   * Get number key
   *
   * @return The key to the number is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getNumberKey();

  /**
   * Get complement key
   *
   * @return The key to the complement is a key dynamic inserted in the implementation of this
   * class.
   */
  protected abstract String getComplementKey();
}
