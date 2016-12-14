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

package br.com.uol.pagseguro.api.preapproval.cancel;

import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V2 Pre Approval Cancellation
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalCancellationV2MapConverter extends
    AbstractMapConverter<PreApprovalCancellation> {

  private static final ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  /**
   * Convert Interface for Pre Approval Cancellation in Request Map
   *
   * @param requestMap              Request Map used to pass params to api
   * @param preApprovalCancellation Interface for Pre Approval Cancellation
   * @see RequestMap
   * @see PreApprovalCancellation
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, PreApprovalCancellation preApprovalCancellation) {
    requestMap.putMap(PARAMETER_MC.convert(preApprovalCancellation.getParameters()));
  }
}
