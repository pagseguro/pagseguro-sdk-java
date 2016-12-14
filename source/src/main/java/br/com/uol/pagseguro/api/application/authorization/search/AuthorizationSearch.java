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

package br.com.uol.pagseguro.api.application.authorization.search;

import br.com.uol.pagseguro.api.common.domain.DateRange;

/**
 * Interface for authorization search.
 * Used to set parameters to authorizations search.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface AuthorizationSearch {

  /**
   * Determines the date range for authorizations search
   *
   * @return The date range that you want to search
   */
  DateRange getDateRange();

  /**
   * Determines the reference of authorization for authorizations search
   *
   * @return The reference of authorization that you want search
   */
  String getReference();

  /**
   * Determines the page for authorizations search
   *
   * @return The page that you want to search
   */
  Integer getPage();

  /**
   * Determines the max results for authorizations search
   *
   * @return The max results that you want to search
   */
  Integer getMaxResults();
}
