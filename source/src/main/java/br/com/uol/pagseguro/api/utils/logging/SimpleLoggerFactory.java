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

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory to simple logger
 *
 * @author PagSeguro Internet Ltda.
 */
public class SimpleLoggerFactory extends LoggerFactory {

  public SimpleLoggerFactory() {
    LoggerFactory.configureLoggerFactory(this);
  }

  private static FileHandler FILEHANDLER;

  /**
   * Get log by name
   *
   * @param name Name
   * @return Log
   */
  private Logger getLog(String name) {
    final Logger logger = Logger.getLogger(name);
    logger.addHandler(getFileHandler());
    logger.setLevel(Level.ALL);
    return logger;
  }

  /**
   * Add log to a file
   *
   * @return File Handler
   */
  private FileHandler getFileHandler() {
    try {
      if (FILEHANDLER == null) {
        FILEHANDLER = new FileHandler("./pagseguro.log", true);
        FILEHANDLER.setFormatter(new PagSeguroFormatter());
        FILEHANDLER.setEncoding("UTF-8");
        FILEHANDLER.setLevel(Level.ALL);
      }
      return FILEHANDLER;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructs the logger by name
   *
   * @param name Name
   * @return Instance of simple log
   */
  @Override
  protected Log doGetLog(String name) {
    return new SimpleLog(getLog(name));
  }

  /**
   * Constructs the logger by class
   *
   * @param clazz Class
   * @return Instance of simple log
   */
  @Override
  protected Log doGetLog(Class<?> clazz) {
    return new SimpleLog(getLog(clazz.getName()));
  }
}