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

import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for shipping. Used in version 3 of the application
 * Used to convert attributes of shipping in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class ShippingV3MapConverter extends AbstractMapConverter<Shipping> {

  private final static ShippingAddressV3MapConverter SHIPPING_ADDRESS_MAP_CONVERTER =
      new ShippingAddressV3MapConverter();

  /**
   * Convert attributes of shipping in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param shipping   The interface of shipping
   * @see RequestMap
   * @see Shipping
   * @see AbstractMapConverter#convert(Object)
   */
  protected void convert(RequestMap requestMap, Shipping shipping) {
    final ShippingType shippingType = shipping.getShippingType();
    if (shippingType != null) {
      requestMap.putInteger("shipping.type", shippingType.getTypeId());
    }
    requestMap.putCurrency("shipping.cost", shipping.getCost());
    requestMap.putMap(SHIPPING_ADDRESS_MAP_CONVERTER.convert(shipping.getAddress()));
  }

  /**
   * Implementation of {@code AbstractAddressMapConverter}. Used to set key values
   *
   * @see AbstractAddressMapConverter
   */
  private static class ShippingAddressV3MapConverter extends AbstractAddressMapConverter {

    @Override
    protected String getCountryKey() {
      return "shipping.address.country";
    }

    @Override
    protected String getStateKey() {
      return "shipping.address.state";
    }

    @Override
    protected String getCityKey() {
      return "shipping.address.city";
    }

    @Override
    protected String getPostalCodeKey() {
      return "shipping.address.postalCode";
    }

    @Override
    protected String getDistrictKey() {
      return "shipping.address.district";
    }

    @Override
    protected String getStreetKey() {
      return "shipping.address.street";
    }

    @Override
    protected String getNumberKey() {
      return "shipping.address.number";
    }

    @Override
    protected String getComplementKey() {
      return "shipping.address.complement";
    }
  }

}
