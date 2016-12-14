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

import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Responsible for converting the parameters in a map for the search of pre approval. Used in V2.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalSearchV2MapConverter extends AbstractMapConverter<PreApprovalSearch> {

  private static final ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  PreApprovalSearchV2MapConverter() {
  }

  /**
   * Convert params of pre approval in request map
   *
   * @param requestMap        The map used in all searches. It contains the parameters for the
   *                          searches.
   * @param preApprovalSearch Interface with params of pre approval search
   * @see PreApprovalSearch
   * @see RequestMap
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, PreApprovalSearch preApprovalSearch) {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    requestMap.putInteger("page", preApprovalSearch.getPage());
    requestMap.putInteger("maxPageResults", preApprovalSearch.getMaxResults());
    requestMap.putDate("initialDate", preApprovalSearch.getDateRange().getFrom(), sdf);
    requestMap.putDate("finalDate", preApprovalSearch.getDateRange().getTo(), sdf);
    requestMap.putString("reference", preApprovalSearch.getReference());
    requestMap.putMap(PARAMETER_MC.convert(preApprovalSearch.getParameters()));
  }
}
