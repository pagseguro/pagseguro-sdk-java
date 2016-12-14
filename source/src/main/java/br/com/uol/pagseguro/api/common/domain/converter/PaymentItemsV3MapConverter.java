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

import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for payment items. Used in version 3 of the application
 * Used to convert attributes of payment items in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PaymentItemsV3MapConverter extends AbstractMapConverter<Iterable<? extends PaymentItem>> {

  /**
   * Convert attributes of payment items in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param items      The list of interface of payment items
   * @see RequestMap
   * @see PaymentItem
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Iterable<? extends PaymentItem> items) {
    int i = 1;
    for (PaymentItem item : items) {
      String index = String.format("item[%d]", i);
      requestMap.putString(index.concat(".id"), item.getId());
      requestMap.putString(index.concat(".description"), item.getDescription());
      requestMap.putCurrency(index.concat(".amount"), item.getAmount());
      requestMap.putInteger(index.concat(".quantity"), item.getQuantity());
      requestMap.putInteger(index.concat(".weight"), item.getWeight());
      requestMap.putCurrency(index.concat(".shippingCost"), item.getShippingCost());
      i++;
    }
  }
}
