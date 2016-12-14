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
package br.com.uol.pagseguro.api.utils;

import java.util.Collections;

/**
 * Abstract map converter. Used to convert objects in the request map.
 * All converters must implement this class.
 *
 * @param <T> Class to be converted
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractMapConverter<T> implements MapConverter<T> {

  private static final RequestMap EMPTY_MAP =
      new RequestMap(Collections.unmodifiableMap(Collections.<String, String>emptyMap()));

  public AbstractMapConverter() {
  }

  /**
   * Converts an object to a request map
   *
   * @param object Object to be converted
   * @return Request Map
   */
  @Override
  public final RequestMap convert(T object) {
    if (object == null) {
      return EMPTY_MAP;
    }
    final RequestMap requestMap = new RequestMap();
    convert(requestMap, object);
    return requestMap;
  }

  /**
   * Converts an object to a request map. This method must be implemented
   *
   * @param requestMap Request Map used to pass params to api
   * @param object     Object to be converted
   */
  abstract protected void convert(RequestMap requestMap, T object);

}
