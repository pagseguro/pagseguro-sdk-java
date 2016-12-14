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

/**
 * Pagseguro environment enum
 *
 * @author PagSeguro Internet Ltda.
 */
public enum PagSeguroEnv {

  /**
   * PRODUCTION
   */
  PRODUCTION("production"),

  /**
   * SANDBOX
   */
  SANDBOX("sandbox");

  private final String name;

  /**
   * Constructor
   *
   * @param name Environment Name
   */
  PagSeguroEnv(String name) {
    this.name = name;
  }

  /**
   * Get pagseguro environment enum by environment name
   *
   * @param name Environment name
   * @return Pagseguro environment enum
   */
  public static PagSeguroEnv fromName(String name) {
    for (PagSeguroEnv environment : PagSeguroEnv.values()) {
      if (environment.name != null && name.equalsIgnoreCase(environment.name)) {
        return environment;
      }
    }
    throw new IllegalArgumentException("Environment name not exists");
  }
}
