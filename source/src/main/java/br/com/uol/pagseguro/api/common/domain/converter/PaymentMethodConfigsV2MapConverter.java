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

import br.com.uol.pagseguro.api.common.domain.PaymentMethodConfig;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for payment method config. Used in version 2 of the application
 * Used to convert attributes of payment method configs in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PaymentMethodConfigsV2MapConverter extends
    AbstractMapConverter<Iterable<? extends PaymentMethodConfig>> {

  /**
   * Convert attributes of payment items in request map
   *
   * @param requestMap           Request Map used to pass params to api
   * @param paymentMethodConfigs The list of interface of payment method configs
   * @see RequestMap
   * @see PaymentMethodConfig
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap,
                         Iterable<? extends PaymentMethodConfig> paymentMethodConfigs) {
    int i = 1;
    for (PaymentMethodConfig paymentMethodConfig : paymentMethodConfigs) {
      if (paymentMethodConfig.getPaymentMethod().getGroup() != null) {
        requestMap.putString(String.format("paymentMethodGroup%d", i),
            paymentMethodConfig.getPaymentMethod().getGroup().getValue());
        requestMap.putString(String.format("paymentMethodConfigKey%d_1", i),
            paymentMethodConfig.getConfig().getKey().getValue());
        requestMap.putConfigValue(String.format("paymentMethodConfigValue%d_1", i),
            paymentMethodConfig.getConfig());
        i++;
      }
    }
  }
}
