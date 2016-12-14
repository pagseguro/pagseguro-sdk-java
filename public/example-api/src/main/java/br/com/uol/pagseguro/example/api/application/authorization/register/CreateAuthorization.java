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
package br.com.uol.pagseguro.example.api.application.authorization.register;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistration;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistrationBuilder;
import br.com.uol.pagseguro.api.application.authorization.RegisteredAuthorization;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.credential.Credential;


/**
 * @author PagSeguro Internet Ltda.
 */
public class CreateAuthorization {

  public static void main(String[] args) {


    String appId = "your_app_id";
    String appKey = "your_app_key";


    try{

      final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(appId,
          appKey), PagSeguroEnv.SANDBOX);


      // Registra as autorizações
      AuthorizationRegistration authorizationRegistration =
          new AuthorizationRegistrationBuilder()
              .withNotificationUrl("www.lojatesteste.com.br/notification")
              .withReference("REF_001")
              .withRedirectURL("www.lojatesteste.com.br")
              .addPermission(PermissionCode.Code.CREATE_CHECKOUTS)
              .build();

      RegisteredAuthorization ra = pagSeguro.authorizations().register(authorizationRegistration);
      System.out.print(ra);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
