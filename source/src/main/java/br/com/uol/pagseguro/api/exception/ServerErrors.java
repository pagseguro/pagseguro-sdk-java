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
package br.com.uol.pagseguro.api.exception;

/**
 * Interface for list response server errors
 *
 * @author PagSeguro Internet Ltda.
 */
public interface ServerErrors {

  /**
   * Get size of list errors
   *
   * @return Size
   */
  int size();

  /**
   * Get server error by code
   *
   * @param code Error code
   * @return Server error
   * @see ServerError
   */
  ServerError getError(int code);

  /**
   * Get server errors
   *
   * @return Iterable of server errors
   * @see ServerError
   */
  Iterable<? extends ServerError> getErrors();

  /**
   * Verify if contains server error by code
   *
   * @param code Error code
   * @return If contains error
   */
  boolean contains(int code);

}
