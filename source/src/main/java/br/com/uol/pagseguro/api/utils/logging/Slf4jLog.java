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

import org.slf4j.Logger;
import org.slf4j.MarkerFactory;

/**
 * Class responsible for logger of slf4j
 *
 * @author PagSeguro Internet Ltda.
 */
public class Slf4jLog implements Log {

  private final Logger logger;

  /**
   * Constructor Logger of slf4j
   */
  Slf4jLog(Logger logger) {
    this.logger = logger;
  }

  /**
   * Write messages fatal level logs
   *
   * @param message Message
   */
  @Override
  public void fatal(Object message) {
    logger.error(MarkerFactory.getMarker("FATAL"), String.valueOf(message));
  }

  /**
   * Write messages fatal level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  @Override
  public void fatal(Object message, Throwable t) {
    logger.error(MarkerFactory.getMarker("FATAL"), String.valueOf(message), t);
  }

  /**
   * Write messages error level logs
   *
   * @param message Message
   */
  @Override
  public void error(Object message) {
    logger.error(String.valueOf(message));
  }

  /**
   * Write messages error level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  @Override
  public void error(Object message, Throwable t) {
    logger.error(String.valueOf(message), t);
  }

  /**
   * Write messages warn level logs
   *
   * @param message Message
   */
  @Override
  public void warn(Object message) {
    logger.warn(String.valueOf(message));
  }

  /**
   * Write messages warn level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  @Override
  public void warn(Object message, Throwable t) {
    logger.warn(String.valueOf(message), t);
  }

  /**
   * Write messages info level logs
   *
   * @param message Message
   */
  @Override
  public void info(Object message) {
    logger.warn(String.valueOf(message));
  }

  /**
   * Write messages info level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  @Override
  public void info(Object message, Throwable t) {
    logger.warn(String.valueOf(message), t);
  }

  /**
   * Write messages debug level logs
   *
   * @param message Message
   */
  @Override
  public void debug(Object message) {
    logger.debug(String.valueOf(message));
  }

  /**
   * Write messages debug level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  @Override
  public void debug(Object message, Throwable t) {
    logger.debug(String.valueOf(message), t);
  }

  /**
   * Write messages trace level logs
   *
   * @param message Message
   */
  @Override
  public void trace(Object message) {
    logger.trace(String.valueOf(message));
  }

  /**
   * Write messages trace level logs and your throwable
   *
   * @param message Message
   * @param t       Throwable
   */
  @Override
  public void trace(Object message, Throwable t) {
    logger.trace(String.valueOf(message), t);
  }

  /**
   * Get is fatal log level is enable
   *
   * @return If fatal log level is enable
   */
  @Override
  public boolean isFatalEnabled() {
    return logger.isErrorEnabled(MarkerFactory.getMarker("FATAL"));
  }

  /**
   * Get is error log level is enable
   *
   * @return If error log level is enable
   */
  @Override
  public boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  /**
   * Get is warn log level is enable
   *
   * @return If warn log level is enable
   */
  @Override
  public boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  /**
   * Get is info log level is enable
   *
   * @return If info log level is enable
   */
  @Override
  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  /**
   * Get is debug log level is enable
   *
   * @return If debug log level is enable
   */
  @Override
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  /**
   * Get is trace log level is enable
   *
   * @return If trace log level is enable
   */
  @Override
  public boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }
}
