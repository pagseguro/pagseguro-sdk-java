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
package br.com.uol.pagseguro.api.credential;

import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * This class is responsible for retrieving the credentials through the jvm environments variable.
 *
 * @author PagSeguro Internet Ltda.
 */
public class JVMEnvVariableCredentialProvider implements CredentialProvider {

  private static final Log LOGGER = LoggerFactory.getLogger(JVMEnvVariableCredentialProvider.class);

  JVMEnvVariableCredentialProvider() {
  }

  /**
   * Retrieve the credentials through the jvm environments variable.
   *
   * @return Credential
   */
  @Override
  public Credential getCredential() {
    LOGGER.info("Lendo credenciais");
    final Credential credential;
    if (System.getProperty("pagseguro.email") != null
        && System.getProperty("pagseguro.token") != null) {
      credential = Credential.sellerCredential(System.getProperty("pagseguro.email").trim(),
          System.getProperty("pagseguro.token").trim());
    } else if (System.getProperty("pagseguro.appId") != null
        && System.getProperty("pagseguro.appKey") != null) {
      credential = Credential.applicationCredential(System.getProperty("pagseguro.appId").trim(),
          System.getProperty("pagseguro.appKey").trim());
    } else {
      throw new IllegalArgumentException("Seller credential and Application credential not found");
    }
    LOGGER.info("Credenciais lidas");
    return credential;
  }
}
