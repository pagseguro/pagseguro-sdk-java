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

package br.com.uol.pagseguro.api.preapproval.search;

import java.util.List;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.Parameter;

/**
 * Interface to search pre approval(signature)
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PreApprovalSearch {

  /**
   * Date Range Param
   *
   * @return Date Range
   */
  DateRange getDateRange();

  /**
   * Reference Param
   *
   * @return Reference
   */
  String getReference();

  /**
   * Page Param
   *
   * @return Page
   */
  Integer getPage();

  /**
   * Max Results Param
   *
   * @return Max Results
   */
  Integer getMaxResults();

  /**
   * Parameters to maintain compatibility with all versions of lib
   * Pass key and value.
   *
   * @return Parameters
   * @see Parameter
   */
  List<? extends Parameter> getParameters();
}
