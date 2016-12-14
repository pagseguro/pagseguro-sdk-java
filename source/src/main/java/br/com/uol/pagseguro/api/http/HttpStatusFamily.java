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
package br.com.uol.pagseguro.api.http;

/**
 * Http status family enum
 *
 * @author PagSeguro Internet Ltda.
 */
public enum HttpStatusFamily {

  /**
   * OTHER
   */
  OTHER(null),

  /**
   * INFORMATIONAL
   */
  INFORMATIONAL(1),

  /**
   * SUCCESSFUL
   */
  SUCCESSFUL(2),

  /**
   * REDIRECTION
   */
  REDIRECTION(3),

  /**
   * CLIENT ERROR
   */
  CLIENT_ERROR(4),

  /**
   * SERVER ERROR
   */
  SERVER_ERROR(5);

  private final Integer startWith;

  /**
   * Http status family enum
   */
  HttpStatusFamily(Integer startWith) {
    this.startWith = startWith;
  }

  /**
   * Get http status family by status id
   *
   * @param status Status Id
   * @return Status
   */
  public static HttpStatusFamily fromStatus(int status) {
    int statusFamily = status / 100;
    for (HttpStatusFamily family : HttpStatusFamily.values()) {
      if (family.startWith != null && statusFamily == family.startWith) {
        return family;
      }
    }
    return OTHER;
  }

}
