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
package br.com.uol.pagseguro.api.common.domain.converter;

import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.common.domain.PreApproval;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for pre approval. Used in version 2 of the application
 * Used to convert attributes of pre approval in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalV2MapConverter extends AbstractMapConverter<PreApproval> {

  /**
   * Convert attributes of pre approval in request map
   *
   * @param requestMap  Request Map used to pass params to api
   * @param preApproval The interface of pre approval
   * @see RequestMap
   * @see PreApproval
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, PreApproval preApproval) {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
    requestMap.putString("preApprovalCharge", preApproval.getCharge());
    requestMap.putString("preApprovalName", preApproval.getName());
    requestMap.putString("preApprovalDetails", preApproval.getDetails());
    requestMap.putCurrency("preApprovalAmountPerPayment", preApproval.getAmountPerPayment());
    requestMap.putCurrency("preApprovalMaxAmountPerPayment", preApproval.getMaxAmountPerPayment());
    requestMap.putCurrency("preApprovalMaxTotalAmount", preApproval.getMaxTotalAmount());
    requestMap.putInteger("preApprovalMaxPaymentsPerPeriod", preApproval.getMaxPaymentsPerPeriod());
    requestMap.putCurrency("preApprovalMaxAmountPerPeriod", preApproval.getMaxAmountPerPeriod());
    requestMap.putString("preApprovalPeriod", preApproval.getPeriod());
    requestMap.putDate("preApprovalInitialDate", preApproval.getDateRange().getFrom(), sdf);
    requestMap.putDate("preApprovalFinalDate", preApproval.getDateRange().getTo(), sdf);
  }
}
