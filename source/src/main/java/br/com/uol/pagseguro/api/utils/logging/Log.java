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
 * Interface responsible for logger
 *
 * @author PagSeguro Internet Ltda.
 */
public interface Log {

  /**
   * Write messages fatal level logs
   *
   * @param message Message
   */
  void fatal(Object message);

  /**
   * Write messages fatal level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  void fatal(Object message, Throwable t);

  /**
   * Write messages error level logs
   *
   * @param message Message
   */
  void error(Object message);

  /**
   * Write messages error level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  void error(Object message, Throwable t);

  /**
   * Write messages warn level logs
   *
   * @param message Message
   */
  void warn(Object message);

  /**
   * Write messages warn level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  void warn(Object message, Throwable t);

  /**
   * Write messages info level logs
   *
   * @param message Message
   */
  void info(Object message);

  /**
   * Write messages info level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  void info(Object message, Throwable t);

  /**
   * Write messages debug level logs
   *
   * @param message Message
   */
  void debug(Object message);

  /**
   * Write messages debug level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  void debug(Object message, Throwable t);

  /**
   * Write messages trace level logs
   *
   * @param message Message
   */
  void trace(Object message);

  /**
   * Write messages trace level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  void trace(Object message, Throwable t);

  /**
   * Get is fatal log level is enable
   *
   * @return If fatal log level is enable
   */
  boolean isFatalEnabled();

  /**
   * Get is error log level is enable
   *
   * @return If error log level is enable
   */
  boolean isErrorEnabled();

  /**
   * Get is warn log level is enable
   *
   * @return If warn log level is enable
   */
  boolean isWarnEnabled();

  /**
   * Get is info log level is enable
   *
   * @return If info log level is enable
   */
  boolean isInfoEnabled();

  /**
   * Get is debug log level is enable
   *
   * @return If debug log level is enable
   */
  boolean isDebugEnabled();

  /**
   * Get is trace log level is enable
   *
   * @return If trace log level is enable
   */
  boolean isTraceEnabled();
}
