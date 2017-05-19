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

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for sender. Used in version 2 of the application
 * Used to convert attributes of sender in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class SenderV2MapConverter extends AbstractMapConverter<Sender> {

  private static final SenderPhoneV2MapConverter SENDER_PHONE_MC = new SenderPhoneV2MapConverter();

  private static final SenderAddressV2MapConverter SENDER_ADDRESS_MC =
      new SenderAddressV2MapConverter();

  /**
   * Convert attributes of sender in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param sender     The interface of sender
   * @see RequestMap
   * @see Sender
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Sender sender) {
    requestMap.putString("senderEmail", sender.getEmail());
    requestMap.putString("senderName", sender.getName());
    for (Document document : sender.getDocuments()) {
      if (document.getType().equals(DocumentType.CPF)) {
        requestMap.putString("senderCPF", document.getValue());
      } else {
        requestMap.putString("senderCNPJ", document.getValue());
      }
    }
    requestMap.putString("senderHash", sender.getHash());
    requestMap.putMap(SENDER_PHONE_MC.convert(sender.getPhone()));
    requestMap.putMap(SENDER_ADDRESS_MC.convert(sender.getAddress()));
  }

  /**
   * Implementation of {@code AbstractPhoneMapConverter}. Used to set key values
   *
   * @see AbstractPhoneMapConverter
   */
  private static class SenderPhoneV2MapConverter extends AbstractPhoneMapConverter {

    @Override
    protected String getAreaCodeKey() {
      return "senderAreaCode";
    }

    @Override
    protected String getPhoneNumberKey() {
      return "senderPhone";
    }

    @Override
    protected String getType() {
      return "senderType";
    }

  }

  /**
   * Implementation of {@code AbstractAddressMapConverter}. Used to set key values
   *
   * @see AbstractAddressMapConverter
   */
  private static class SenderAddressV2MapConverter extends AbstractAddressMapConverter {

    @Override
    protected String getCountryKey() {
      return "senderAddressCountry";
    }

    @Override
    protected String getStateKey() {
      return "senderAddressState";
    }

    @Override
    protected String getCityKey() {
      return "senderAddressCity";
    }

    @Override
    protected String getPostalCodeKey() {
      return "senderAddressPostalCode";
    }

    @Override
    protected String getDistrictKey() {
      return "senderAddressDistrict";
    }

    @Override
    protected String getStreetKey() {
      return "senderAddressStreet";
    }

    @Override
    protected String getNumberKey() {
      return "senderAddressNumber";
    }

    @Override
    protected String getComplementKey() {
      return "senderAddressComplement";
    }
  }

}
