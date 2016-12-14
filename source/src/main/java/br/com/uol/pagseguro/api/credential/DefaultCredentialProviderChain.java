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

import java.util.LinkedList;
import java.util.List;

import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

import static br.com.uol.pagseguro.api.environment.PagSeguroGlobalConfig.SYSTEM_PROPERTIES_FILE;

/**
 * This class is responsible for determining where the credentials will be sought.
 *
 * @author PagSeguro Internet Ltda.
 */
public class DefaultCredentialProviderChain {

  private static final Log LOGGER = LoggerFactory.getLogger(DefaultCredentialProviderChain.class);

  private final List<CredentialProvider> credentialProviders = new LinkedList<CredentialProvider>();

  /**
   * Constructor
   *
   * Used to add credentials provider
   *
   * @see JVMEnvVariableCredentialProvider
   * @see SystemPropCredentialProvider
   * @see SystemEnvVariableCredentialProvider
   */
  public DefaultCredentialProviderChain() {
    credentialProviders.add(new JVMEnvVariableCredentialProvider());
    credentialProviders.add(new SystemPropCredentialProvider(SYSTEM_PROPERTIES_FILE));
    credentialProviders.add(new SystemEnvVariableCredentialProvider());
  }

  /**
   * The credentials can be obtained from the following, in order of priority:
   * jvm variables,
   * system .properties variables
   * and system environment
   *
   * @return Credential
   * @see Credential
   */
  public Credential getCredential() {
    LOGGER.info("Iniciando leitura das credenciais");
    for (CredentialProvider credentialProvider : credentialProviders) {
      try {
        return credentialProvider.getCredential();
      } catch (Exception e) {
      }
    }
    throw new PagSeguroLibException(new RuntimeException("Can not create Credentials " +
        "from providers"));
  }
}
