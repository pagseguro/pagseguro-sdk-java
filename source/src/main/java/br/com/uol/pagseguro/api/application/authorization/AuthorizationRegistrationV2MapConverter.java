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

import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V2 Authorization Register
 *
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationRegistrationV2MapConverter extends
    AbstractMapConverter<AuthorizationRegistration> {

  private final static PermissionV2MapConverter PERMISSION_V_2_MAP_CONVERTER =
      new PermissionV2MapConverter();

  AuthorizationRegistrationV2MapConverter() {
  }

  /**
   * Convert Interface for Authorization Registration in Request Map
   *
   * @param requestMap                Request Map used to pass params to api
   * @param authorizationRegistration Interface for Authorization Registration
   * @see RequestMap
   * @see AuthorizationRegistration
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap,
                         AuthorizationRegistration authorizationRegistration) {
    requestMap.putString("reference", authorizationRegistration.getReference());
    requestMap.putString("notificationURL", authorizationRegistration.getNotificationURL());
    requestMap.putString("redirectURL", authorizationRegistration.getRedirectURL());
    requestMap.putMap(PERMISSION_V_2_MAP_CONVERTER.convert(
        authorizationRegistration.getPermissions()));
  }
}
