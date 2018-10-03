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

import br.com.uol.pagseguro.api.common.domain.Expiration;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Abstract converter for phone.
 * Used to convert attributes of phone in request map.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractExpirationJsonConverter extends AbstractJsonConverter<Expiration> {

  /**
   * Convert attributes of address in request map
   *
   * @param requestJson Request Map used to pass params to api
   * @param expiration      The interface of expiration
   * @see RequestJson
   * @see Expiration
   * @see AbstractJsonConverter#convert(Object)
   */
  protected void convert(RequestJson requestJson, Expiration expiration) {
    requestJson.putInteger(getValueKey(), expiration.getValue());
    requestJson.putString(getUnitKey(), expiration.getUnit());
  }

  /**
   * Get area code key
   *
   * @return The key to the value is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getValueKey();

  /**
   * Get phone number key
   *
   * @return The key to the unit is a key dynamic inserted in the implementation of this
   * class.
   */
  protected abstract String getUnitKey();

}
