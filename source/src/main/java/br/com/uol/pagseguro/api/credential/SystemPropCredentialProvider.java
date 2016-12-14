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

import java.io.InputStream;
import java.util.Properties;

import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * This class is responsible for retrieving the credentials through the system settings
 * (.properties)
 *
 * @author PagSeguro Internet Ltda.
 */
public class SystemPropCredentialProvider implements CredentialProvider {

  private static final Log LOGGER = LoggerFactory.getLogger(SystemPropCredentialProvider.class);

  private final String file;

  /**
   * Constructor
   *
   * @param file Path file
   */
  SystemPropCredentialProvider(String file) {
    this.file = file;
  }

  /**
   * Retrieve the credentials through the system settings (.properties)
   *
   * @return Credentials by system properties
   */
  @Override
  public Credential getCredential() throws Exception {
    LOGGER.info("Lendo credenciais");
    final Credential credential;
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
    Properties properties = new Properties();
    properties.load(inputStream);
    if (properties.getProperty("email") != null && properties.getProperty("token") != null) {
      credential = Credential.sellerCredential(properties.getProperty("email"),
          properties.getProperty("token"));
    } else if (properties.getProperty("appId") != null
        && properties.getProperty("appKey") != null) {
      credential = Credential.applicationCredential(properties.getProperty("appId"),
          properties.getProperty("appKey"));
    } else {
      throw new IllegalArgumentException("Seller credential and Application credential not found");
    }
    inputStream.close();
    LOGGER.info("Credenciais lidas");
    return credential;
  }
}
