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

import java.util.List;

import br.com.uol.pagseguro.api.common.domain.PermissionCode;

/**
 * Interface for authorization registration. This class is responsible for determining the
 * attributes of the authorization to perform the register of authorization
 *
 * @author PagSeguro Internet Ltda.
 */
public interface AuthorizationRegistration {

  /**
   * Identifier used to refer to the permission of your request.
   *
   * Optional
   *
   * @return Reference of Authorization Register
   */
  String getReference();

  /**
   * List of permissions in this authorization.
   *
   * Required
   *
   * @return List of permissions in this authorization
   * @see PermissionCode.Code
   */
  List<PermissionCode.Code> getPermissions();

  /**
   * Uri to where the PagSeguro checkout page should redirect the user after the payment information
   * is processed. Typically this is a confirmation page on your web site.
   *
   * Required
   *
   * @return Redirect URL
   */
  String getRedirectURL();

  /**
   * Determines for which url PagSeguro will send the order related notifications changes. A new
   * notification will be send to this url if any change happens in the transaction status. You can
   * use that for update the related order.
   *
   * Optional
   *
   * @return Notification URL
   */
  String getNotificationURL();


}
