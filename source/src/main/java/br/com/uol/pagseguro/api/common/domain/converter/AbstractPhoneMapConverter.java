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

import br.com.uol.pagseguro.api.common.domain.Phone;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Abstract converter for phone.
 * Used to convert attributes of phone in request map.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractPhoneMapConverter extends AbstractMapConverter<Phone> {

  /**
   * Convert attributes of address in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param phone      The interface of phone
   * @see RequestMap
   * @see Phone
   * @see AbstractMapConverter#convert(Object)
   */
  protected void convert(RequestMap requestMap, Phone phone) {
    requestMap.putString(getAreaCodeKey(), phone.getAreaCode());
    requestMap.putString(getPhoneNumberKey(), phone.getNumber());
  }

  /**
   * Get area code key
   *
   * @return The key to the area code is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getAreaCodeKey();

  /**
   * Get phone number key
   *
   * @return The key to the phone number is a key dynamic inserted in the implementation of this
   * class.
   */
  protected abstract String getPhoneNumberKey();

}
