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

package br.com.uol.pagseguro.api.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PaymentItemsV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V2 Pre Approval Charging
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalChargingV2MapConverter extends AbstractMapConverter<PreApprovalCharging> {

  private final static PaymentItemsV2MapConverter PAYMENT_ITEMS_MC =
      new PaymentItemsV2MapConverter();

  private final static ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  PreApprovalChargingV2MapConverter() {
  }

  /**
   * Convert Interface for Pre Approval Charging in Request Map
   *
   * @param requestMap          Request Map used to pass params to api
   * @param preApprovalCharging Interface for Pre Approval Charging
   * @see RequestMap
   * @see PreApprovalCharging
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, PreApprovalCharging preApprovalCharging) {
    requestMap.putString("reference", preApprovalCharging.getReference());
    requestMap.putString("preApprovalCode", preApprovalCharging.getCode());
    requestMap.putMap(PAYMENT_ITEMS_MC.convert(preApprovalCharging.getItems()));
    requestMap.putMap(PARAMETER_MC.convert(preApprovalCharging.getParameters()));
  }
}
