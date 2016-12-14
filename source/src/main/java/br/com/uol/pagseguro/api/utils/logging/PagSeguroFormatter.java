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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Logger formatter. Formats in standard PagSeguro
 *
 * @author PagSeguro Internet Ltda.
 */
public class PagSeguroFormatter extends Formatter {

  PagSeguroFormatter() {
  }

  /**
   * Formats logs
   *
   * @param record Log record to be formatted
   * @return Log record formatted
   */
  @Override
  public String format(LogRecord record) {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String date = dateFormat.format(new Date(record.getMillis()));
    String service = record.getLoggerName();
    String level = record.getLevel().getName();
    String message = record.getMessage();
    return String.format("%s PagSeguro.%s[%s]: %s\n", date, service, level, message);
  }
}
