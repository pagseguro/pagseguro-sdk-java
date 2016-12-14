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

package br.com.uol.pagseguro.api.transaction.search;

import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;


/**
 * Converter for V2 Transaction Search
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionSearchV2MapConverter extends AbstractMapConverter<TransactionSearch> {

  TransactionSearchV2MapConverter() {
  }

  /**
   * Convert Interface for Transaction Search in Request Map
   *
   * @param requestMap        Request Map used to pass params to api
   * @param transactionSearch Interface for Transaction Search
   * @see RequestMap
   * @see TransactionSearch
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, TransactionSearch transactionSearch) {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    requestMap.putInteger("page", transactionSearch.getPage());
    requestMap.putInteger("maxPageResults", transactionSearch.getMaxResults());
    requestMap.putDate("initialDate", transactionSearch.getDateRange().getFrom(), sdf);
    requestMap.putDate("finalDate", transactionSearch.getDateRange().getTo(), sdf);
    requestMap.putString("reference", transactionSearch.getReference());
  }
}
