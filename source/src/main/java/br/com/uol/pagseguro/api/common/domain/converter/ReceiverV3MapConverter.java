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

import br.com.uol.pagseguro.api.common.domain.Receiver;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for receivers. Used in version 3 of the application
 * Used to convert attributes of receivers in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class ReceiverV3MapConverter extends AbstractMapConverter<Iterable<? extends Receiver>> {

  /**
   * Convert attributes of receivers in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param receivers  The interface of receivers
   * @see RequestMap
   * @see Receiver
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Iterable<? extends Receiver> receivers) {
    int i = 1;
    for (Receiver receiver : receivers) {
      String receiverKey = String.format("receiver[%d].", i);
      requestMap.putString(receiverKey.concat("publicKey"), receiver.getPublicKey());
      if (receiver.getSplit() != null) {
        String splitKey = receiverKey.concat("split.");
        requestMap.putCurrency(splitKey.concat("amount"), receiver.getSplit().getAmount());
        requestMap.putCurrency(splitKey.concat("ratePercent"), receiver.getSplit()
            .getRatePercent());
        requestMap.putCurrency(splitKey.concat("feePercent"), receiver.getSplit().getFeePercent());
      }
      i++;
    }

  }

}
