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
 * Abstract json converter. Used to convert objects in the request json.
 * All converters must implement this class.
 *
 * @param <T> Class to be converted
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractJsonConverter<T> implements JsonConverter<T> {
  private static final RequestJson EMPTY_JSON =
          new RequestJson(new StringBuilder());

  public AbstractJsonConverter() {
  }

  @Override
  public final RequestJson convert(T object) {
    if (object == null) {
      return EMPTY_JSON;
    }
    final RequestJson requestJson = new RequestJson();
    convert(requestJson, object);
    return requestJson;
  }

  /**
   * Converts an object to a request json. This method must be implemented
   *
   * @param requestJson Request Json used to pass params to api
   * @param object     Object to be converted
   */
  abstract protected void convert(RequestJson requestJson, T object);

}
