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

import java.io.InputStream;
import java.util.Properties;

import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Class responsible for providing the environment configurations of properties file
 *
 * @author PagSeguro Internet Ltda.
 */

public class SystemPropEnvironmentProvider implements EnvironmentProvider {

  private static final Log LOGGER =
      LoggerFactory.getLogger(SystemPropEnvironmentProvider.class);

  private final String file;

  SystemPropEnvironmentProvider(String file) {
    this.file = file;
  }

  /**
   * Get configurations environment
   *
   * @return Configurations environment
   */
  @Override
  public PagSeguroEnv getEnvironment() throws Exception {
    LOGGER.info("Lendo configuracoes de ambiente");
    final PagSeguroEnv environment;
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
    Properties properties = new Properties();
    properties.load(inputStream);
    if (properties.getProperty("environment") != null) {
      environment = PagSeguroEnv.fromName(properties.getProperty("environment"));
    } else {
      throw new IllegalArgumentException("Environment property not found");
    }
    LOGGER.info("Configuracoes de ambiente lidas");
    return environment;
  }
}
