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
package br.com.uol.pagseguro.api.application.authorization.search;

import java.util.Date;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Account;
import br.com.uol.pagseguro.api.common.domain.Permission;
import br.com.uol.pagseguro.api.common.domain.xml.AccountXML;

/**
 * Interface of the response of authorization search.
 * This interface is used to get response by api
 *
 * @author PagSeguro Internet Ltda.
 */
public interface AuthorizationDetail {

  /**
   * Get authorization code
   *
   * @return The authorization code
   */
  String getCode();

  /**
   * Get creation date of authorization
   *
   * @return The creation date of authorization
   */
  Date getCreationDate();

  /**
   * Get reference of authorization.
   *
   * @return Reference of Authorization. Identifier that was used to make reference to the
   * authorization at the time of request.
   */
  String getReference();

  /**
   * Get account of authorization
   *
   * @return The account with the public key used in authorization
   * @see Account
   */
  Account getAccount();

  /**
   * Get permissions list of authorization
   *
   * @return The permissions list returned by authorization
   * @see Permission
   */
  List<? extends Permission> getPermissions();

  /**
   * Get authorizer email of authorization
   *
   * @return The authorizer email of authorization
   */
  String getAuthorizerEmail();
}

