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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.uol.pagseguro.api.common.domain.Config;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.http.HttpRequestBody;

/**
 * Class used to convert the data on a map to pass the parameters of the requests in api
 *
 * @author PagSeguro Internet Ltda.
 */
public final class RequestMap {

  private final Map<String, String> map;

  /**
   * Constructor
   */
  public RequestMap() {
    this(new HashMap<String, String>());
  }

  /**
   * Constructor
   *
   * @param map Map to pass the parameters of the requests in api
   */
  public RequestMap(Map<String, String> map) {
    this.map = map;
  }

  /**
   * Put all map data
   *
   * @param requestMap Request map
   * @return Request map
   */
  public RequestMap putMap(RequestMap requestMap) {
    if (requestMap == null) {
      throw new NullPointerException();
    }
    return putMap(requestMap.map);
  }

  /**
   * Put all map data
   *
   * @param map Map
   * @return Request map
   */
  public RequestMap putMap(Map<String, String> map) {
    if (map == null) {
      throw new NullPointerException();
    }
    if (!map.isEmpty()) {
      this.map.putAll(map);
    }
    return this;
  }

  /**
   * Put string on map
   *
   * @param key   Key
   * @param value String value
   * @return Request map
   */
  public RequestMap putString(String key, String value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null) {
      return this;
    }
    map.put(key, value);
    return this;
  }

  /**
   * Put string on map
   *
   * @param key   Key
   * @param value String Builder value
   * @return Request map
   */
  public RequestMap putString(String key, StringBuilder value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null || value.toString().isEmpty()) {
      return this;
    }
    map.put(key, value.toString());
    return this;
  }

  /**
   * Put integer on map
   *
   * @param key   Key
   * @param value Integer value
   * @return Request map
   */
  public RequestMap putInteger(String key, Integer value) {
    return putString(key, value == null ? null : value.toString());
  }

  /**
   * Put currency on map
   *
   * @param key   Key
   * @param value BigDecimal value
   * @return Request map
   */
  public RequestMap putCurrency(String key, BigDecimal value) {
    return putString(key, value == null ? null : value.setScale(2, RoundingMode.HALF_EVEN).toString());
  }

  /**
   * Put currency on map
   *
   * @param key   Key
   * @param value Currency value
   * @return Request map
   * @see Currency
   */
  public RequestMap putCurrency(String key, Currency value) {
    return putString(key, value == null ? null : value.getStringValue());
  }

  /**
   * Put date on map
   *
   * @param key        Key
   * @param value      Date value
   * @param dateFormat Date format
   * @return Request map
   */
  public RequestMap putDate(String key, Date value, DateFormat dateFormat) {
    return putString(key, value == null ? null : dateFormat.format(value));
  }

  /**
   * Put config on map
   *
   * @param key    Key
   * @param config Config value
   * @return Request map
   * @see Config
   */
  public RequestMap putConfigValue(String key, Config config) {
    if (ConfigKey.DISCOUNT_PERCENT.equals(config.getKey())) {
      return putCurrency(key, config.getValue());
    } else if (ConfigKey.MAX_INSTALLMENTS_LIMIT.equals(config.getKey())) {
      return putInteger(key, config.getValue().intValue());
    } else if (ConfigKey.MAX_INSTALLMENTS_NO_INTEREST.equals(config.getKey())) {
      return putInteger(key, config.getValue().intValue());
    }
    return this;
  }

  /**
   * Convert map on url encoded
   *
   * @param charset Encoding
   * @return Map converted in a url encoded
   */
  public String toUrlEncode(String charset) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      if (sb.length() > 0) {
        sb.append("&");
      }
      sb.append(String.format("%s=%s", URLEncoder.encode(entry.getKey(), charset),
          URLEncoder.encode(entry.getValue(), charset)));
    }
    return sb.toString();
  }

  /**
   * Convert to http request body
   *
   * @param charset Encoding
   * @return Http Request Body
   * @see HttpRequestBody
   */
  public HttpRequestBody toHttpRequestBody(String charset) throws UnsupportedEncodingException {
    return new HttpRequestBody(//
        String.format("application/x-www-form-urlencoded; charset=%s", charset), //
        toUrlEncode(charset), //
        charset);
  }

  @Override
  public String toString() {
    return "RequestMap{" +
           "map=" + map +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RequestMap)) return false;

    RequestMap map1 = (RequestMap) o;

    return map != null ? map.equals(map1.map) : map1.map == null;

  }

}
