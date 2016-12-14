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

package br.com.uol.pagseguro.api.environment;

import java.util.LinkedList;
import java.util.List;

import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

import static br.com.uol.pagseguro.api.environment.PagSeguroGlobalConfig.SYSTEM_PROPERTIES_FILE;

/**
 * Class responsible for providing default configurations environment
 *
 * @author PagSeguro Internet Ltda.
 */
public class DefaultEnvironmentProviderChain {

  private static final Log LOGGER = LoggerFactory.getLogger(DefaultEnvironmentProviderChain.class);

  private final List<EnvironmentProvider> environmentProviders = new LinkedList<EnvironmentProvider>();

  /**
   * Constructor
   */
  public DefaultEnvironmentProviderChain() {
    environmentProviders.add(new JVMEnvVariableEnvironmentProvider());
    environmentProviders.add(new SystemPropEnvironmentProvider(SYSTEM_PROPERTIES_FILE));
    environmentProviders.add(new SystemEnvVariableEnvironmentProvider());
  }

  /**
   * Get configurations environment
   *
   * @return Configurations environment
   */
  public PagSeguroEnv getEnvironment() {
    LOGGER.info("Iniciando leitura das configuracoes de ambiente");
    for (EnvironmentProvider environmentProvider : environmentProviders) {
      try {
        return environmentProvider.getEnvironment();
      } catch (Exception e) {
      }
    }
    throw new PagSeguroLibException(new RuntimeException("Can not create PagSeguro Environment " +
        "from providers"));
  }
}
