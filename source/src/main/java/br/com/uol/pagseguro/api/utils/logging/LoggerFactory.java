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

package br.com.uol.pagseguro.api.utils.logging;

/**
 * Factory to logger
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class LoggerFactory {

  private static LoggerFactory loggerFactory;

  /**
   * Configure logger factory
   *
   * @param loggerFactory Logger Factory
   */
  public static void configureLoggerFactory(LoggerFactory loggerFactory) {
    LoggerFactory.loggerFactory = loggerFactory;
  }

  /**
   * Get logger by name
   *
   * @param name Name
   * @return Logger
   */
  public static Log getLogger(String name) {
    if (loggerFactory == null) {
      configureLoggerFactory(new SimpleLoggerFactory());
    }
    return loggerFactory.doGetLog(name);
  }

  /**
   * Get logger by class
   *
   * @param clazz Class
   * @return Logger
   */
  public static Log getLogger(Class<?> clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * Constructs the logger by name
   *
   * @param name Name
   * @return Instance of commons log
   */
  protected abstract Log doGetLog(String name);

  /**
   * Constructs the logger by class
   *
   * @param clazz Class
   * @return Instance of commons log
   */
  protected abstract Log doGetLog(Class<?> clazz);
}
