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

import br.com.uol.pagseguro.api.common.domain.AcceptedPaymentMethods;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for accepted payment methods. Used in version 2.
 * Used to convert attributes of accepted payment methods in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class AcceptedPaymentMethodsV2MapConverter extends
    AbstractMapConverter<AcceptedPaymentMethods> {

  private static final IncludePaymentMethodV2MapConverter INCLUDE_PAYMENT_METHOD_MC =
      new IncludePaymentMethodV2MapConverter();

  private static final ExcludePaymentMethodV2MapConverter EXCLUDE_PAYMENT_METHOD_MC =
      new ExcludePaymentMethodV2MapConverter();

  /**
   * Convert attributes of accepted payment methods in request map
   *
   * @param requestMap             Request Map used to pass params to api
   * @param acceptedPaymentMethods The interface of accepted payment methods
   * @see RequestMap
   * @see AcceptedPaymentMethods
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, AcceptedPaymentMethods acceptedPaymentMethods) {
    requestMap.putMap(INCLUDE_PAYMENT_METHOD_MC.convert(acceptedPaymentMethods.getIncludes()));
    requestMap.putMap(EXCLUDE_PAYMENT_METHOD_MC.convert(acceptedPaymentMethods.getExcludes()));
  }

  /**
   * Implementation of {@code AbstractPaymentMethodMapConverter}. Used to set key values
   *
   * @see AbstractPaymentMethodMapConverter
   */
  private static class IncludePaymentMethodV2MapConverter extends
      AbstractPaymentMethodMapConverter {

    @Override
    protected String getPaymentMethodGroupKey() {
      return "acceptPaymentMethodGroup";
    }

    @Override
    protected String getPaymentMethodNameKey() {
      return "acceptPaymentMethodName";
    }
  }

  /**
   * Implementation of {@code AbstractPaymentMethodMapConverter}. Used to set key values
   *
   * @see AbstractPaymentMethodMapConverter
   */
  private static class ExcludePaymentMethodV2MapConverter extends
      AbstractPaymentMethodMapConverter {

    @Override
    protected String getPaymentMethodGroupKey() {
      return "excludePaymentMethodGroup";
    }

    @Override
    protected String getPaymentMethodNameKey() {
      return "excludePaymentMethodName";
    }
  }
}
