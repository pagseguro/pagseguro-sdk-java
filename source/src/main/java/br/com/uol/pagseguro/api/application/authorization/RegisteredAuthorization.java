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
package br.com.uol.pagseguro.api.application.authorization;


/**
 * Interface for Response of Authorization Registration
 *
 * @author PagSeguro Internet Ltda.
 */
public interface RegisteredAuthorization {

  /**
   * Code returned by the Authorization Registration
   *
   * @return Code of Authorization Registration
   */
  String getCode();

  /**
   * After performing a successful call to the API,
   * you must direct the buyer to the authorization flow using the returned request code
   *
   * @return Redirect URL
   */
  String getRedirectURL();
}
