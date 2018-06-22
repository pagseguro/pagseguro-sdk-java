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
package br.com.uol.pagseguro.api.common.domain;

import java.util.Date;
import java.util.List;

/**
 * Interface for data list, used in searches to list many results.
 *
 * @param <T> Class that will be listed
 * @author PagSeguro Internet Ltda.
 */
public interface DataList<T> extends Iterable<T> {

  /**
   * List of class that will be listed
   *
   * @return Data
   */
  List<T> getData();

  /**
   * Total pages of search
   *
   * @return Total Pages
   */
  Integer getTotalPages();

  /**
   * Size of list
   *
   * @return Size
   */
  Integer size();

  /**
   * Return if list is empty
   *
   * @return Is empty
   */
  Boolean isEmpty();

  /**
   * Current page of the search
   *
   * @return Current Page
   */
  Integer getCurrentPage();

  /**
   * Date of the search
   *
   * @return Date
   */
  Date getDate();

}
