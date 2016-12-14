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
import br.com.uol.pagseguro.api.common.domain.converter.PreApprovalV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.SenderV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.ShippingV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V2 Pre Approval Registration
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalRegistrationV2MapConverter extends
    AbstractMapConverter<PreApprovalRegistration> {

  private final static ShippingV2MapConverter SHIPPING_MC = new ShippingV2MapConverter();
  private final static SenderV2MapConverter SENDER_MC = new SenderV2MapConverter();
  private final static PreApprovalV2MapConverter PRE_APPROVAL_MC = new PreApprovalV2MapConverter();
  private final static ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  PreApprovalRegistrationV2MapConverter() {
  }

  /**
   * Convert Interface for Pre Approval Registration in Request Map
   *
   * @param requestMap              Request Map used to pass params to api
   * @param preApprovalRegistration Interface for Pre Approval Registration
   * @see RequestMap
   * @see PreApprovalRegistration
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, PreApprovalRegistration preApprovalRegistration) {
    requestMap.putString("reference", preApprovalRegistration.getReference());
    requestMap.putString("redirectURL", preApprovalRegistration.getRedirectURL());
    requestMap.putString("notificationURL", preApprovalRegistration.getNotificationURL());
    requestMap.putCurrency("extraAmount", preApprovalRegistration.getExtraAmount());
    requestMap.putCurrency("currency", preApprovalRegistration.getCurrency());
    requestMap.putMap(SHIPPING_MC.convert(preApprovalRegistration.getShipping()));
    requestMap.putMap(SENDER_MC.convert(preApprovalRegistration.getSender()));
    requestMap.putMap(PRE_APPROVAL_MC.convert(preApprovalRegistration.getPreApproval()));
    requestMap.putMap(PARAMETER_MC.convert(preApprovalRegistration.getParameters()));
  }
}
