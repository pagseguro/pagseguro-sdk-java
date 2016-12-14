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
 * Converter for shipping. Used in version 2 of the application
 * Used to convert attributes of shipping in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class ShippingV2MapConverter extends AbstractMapConverter<Shipping> {

  private final static ShippingAddressV2MapConverter SHIPPING_ADDRESS_MAP_CONVERTER =
      new ShippingAddressV2MapConverter();

  /**
   * Convert attributes of shipping in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param shipping   The interface of billing
   * @see RequestMap
   * @see Shipping
   * @see AbstractMapConverter#convert(Object)
   */
  protected void convert(RequestMap requestMap, Shipping shipping) {
    final ShippingType shippingType = shipping.getShippingType();
    if (shippingType != null) {
      requestMap.putInteger("shippingType", shippingType.getTypeId());
    }
    requestMap.putCurrency("shippingCost", shipping.getCost());
    requestMap.putMap(SHIPPING_ADDRESS_MAP_CONVERTER.convert(shipping.getAddress()));
  }

  /**
   * Implementation of {@code AbstractAddressMapConverter}. Used to set key values
   *
   * @see AbstractAddressMapConverter
   */
  private static class ShippingAddressV2MapConverter extends AbstractAddressMapConverter {

    @Override
    protected String getCountryKey() {
      return "shippingAddressCountry";
    }

    @Override
    protected String getStateKey() {
      return "shippingAddressState";
    }

    @Override
    protected String getCityKey() {
      return "shippingAddressCity";
    }

    @Override
    protected String getPostalCodeKey() {
      return "shippingAddressPostalCode";
    }

    @Override
    protected String getDistrictKey() {
      return "shippingAddressDistrict";
    }

    @Override
    protected String getStreetKey() {
      return "shippingAddressStreet";
    }

    @Override
    protected String getNumberKey() {
      return "shippingAddressNumber";
    }

    @Override
    protected String getComplementKey() {
      return "shippingAddressComplement";
    }
  }

}
