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

import br.com.uol.pagseguro.api.common.domain.PaymentMethod;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Abstract converter for payment method.
 * Used to convert attributes of payment method in request map.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractPaymentMethodMapConverter extends
    AbstractMapConverter<Iterable<? extends PaymentMethod>> {

  /**
   * Convert attributes of payment methods in request map
   *
   * @param requestMap     Request Map used to pass params to api
   * @param paymentMethods The interfaces of Payment methods
   * @see RequestMap
   * @see PaymentMethod
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Iterable<? extends PaymentMethod> paymentMethods) {
    StringBuilder groupBuilder = new StringBuilder();
    StringBuilder nameBuilder = new StringBuilder();
    for (PaymentMethod paymentMethod : paymentMethods) {
      if (paymentMethod.getGroup() != null) {
        if (groupBuilder.length() == 0) {
          groupBuilder.append(paymentMethod.getGroup().getValue());
        } else {
          groupBuilder.append("," + paymentMethod.getGroup().getValue());
        }
      }
      if (paymentMethod.getName() != null) {
        if (nameBuilder.length() == 0) {
          nameBuilder.append(paymentMethod.getName().getValue());
        } else {
          nameBuilder.append("," + paymentMethod.getName().getValue());
        }
      }
    }
    requestMap.putString(getPaymentMethodGroupKey(), groupBuilder);
    requestMap.putString(getPaymentMethodNameKey(), nameBuilder);
  }

  /**
   * Get payment method group key
   *
   * @return The key to the payment method group is a key dynamic inserted in the implementation of
   * this class.
   */
  protected abstract String getPaymentMethodGroupKey();

  /**
   * Get payment method name key
   *
   * @return The key to the payment method name is a key dynamic inserted in the implementation of
   * this class.
   */
  protected abstract String getPaymentMethodNameKey();
}
