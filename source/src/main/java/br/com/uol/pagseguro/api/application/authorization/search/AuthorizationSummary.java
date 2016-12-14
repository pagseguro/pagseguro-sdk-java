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

import br.com.uol.pagseguro.api.common.domain.xml.AccountXML;
import br.com.uol.pagseguro.api.common.domain.xml.PermissionXML;

/**
 * Interface of the response of authorizations search.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface AuthorizationSummary {

  /**
   * Get authorization Code
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
   * Get reference of authorization
   *
   * @return The identifier that was used to make reference to the authorization at the time of your
   * request.
   */
  String getReference();

  /**
   * Get account of authorization
   *
   * @return The account with the public key used in authorization
   * @see AccountXML
   */
  AccountXML getAccount();

  /**
   * Get Permissions list of authorization
   *
   * @return The permissions list returned by authorization
   * @see PermissionXML
   */
  List<PermissionXML> getPermissions();

  /**
   * Get Authorizer email of authorization
   *
   * @return The authorizer email of authorization
   */
  String getAuthorizerEmail();

  /**
   * Get Authorization Detail
   *
   * @return Authorization Detail
   * @see AuthorizationDetail
   */
  AuthorizationDetail getDetail();
}
