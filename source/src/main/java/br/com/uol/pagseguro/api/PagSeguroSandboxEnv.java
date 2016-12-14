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
package br.com.uol.pagseguro.api;

import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.HttpClient;

/**
 * Pagseguro sandbox environment
 *
 * @author PagSeguro Internet Ltda.
 */
public class PagSeguroSandboxEnv extends PagSeguro {

  /**
   * Constructor
   *
   * @param httpClient Http client
   * @param credential Credential
   */
  PagSeguroSandboxEnv(HttpClient httpClient, Credential credential) {
    super(httpClient, credential);
  }

  /**
   * Get host
   *
   * @return Host
   */
  @Override
  public String getHost() {
    return "https://ws.sandbox.pagseguro.uol.com.br";
  }

  /**
   * Get redirect host
   *
   * @return Redirect Host
   */
  @Override
  public String getHostRedirect() {
    return "https://sandbox.pagseguro.uol.com.br";
  }

  @Override
  public String toString() {
    return "pagseguro-sandbox-env";
  }

}
