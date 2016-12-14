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

import br.com.uol.pagseguro.api.common.domain.CreditCard;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for credit card. Used in version 2 of the application
 * Used to convert attributes of credit card in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class CreditCardV2MapConverter extends AbstractMapConverter<CreditCard> {

  private static final CreditCardHolderV2MapConverter CREDIT_CARD_HOLDER_MC =
      new CreditCardHolderV2MapConverter();

  private static final InstallmentV2MapConverter INSTALLMENT_MC = new InstallmentV2MapConverter();

  private static final CreditCardBillingAddressV2MapConverter CREDIT_CARD_BILLING_ADDRESS_MC =
      new CreditCardBillingAddressV2MapConverter();

  /**
   * Convert attributes of credit card in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param creditCard The interface of credit card
   * @see RequestMap
   * @see CreditCard
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, CreditCard creditCard) {
    requestMap.putString("creditCardToken", creditCard.getToken());
    requestMap.putMap(CREDIT_CARD_HOLDER_MC.convert(creditCard.getHolder()));
    requestMap.putMap(INSTALLMENT_MC.convert(creditCard.getInstallment()));
    requestMap.putMap(CREDIT_CARD_BILLING_ADDRESS_MC.convert(creditCard.getBillingAddress()));
  }

  /**
   * Implementation of {@code AbstractHolderMapConverter}. Used to set key values
   *
   * @see AbstractHolderMapConverter
   */
  private static class CreditCardHolderV2MapConverter extends AbstractHolderMapConverter {

    private static final HolderPhoneV2MapConverter HOLDER_PHONE_MC =
        new HolderPhoneV2MapConverter();

    @Override
    protected String getNameKey() {
      return "creditCardHolderName";
    }

    @Override
    protected String getCpfKey() {
      return "creditCardHolderCPF";
    }

    @Override
    protected String getCnpjKey() {
      return "creditCardHolderCNPJ";
    }

    @Override
    protected String getBirthDateKey() {
      return "creditCardHolderBirthDate";
    }

    @Override
    protected AbstractPhoneMapConverter getPhoneMapConverter() {
      return HOLDER_PHONE_MC;
    }

    private static class HolderPhoneV2MapConverter extends AbstractPhoneMapConverter {

      @Override
      protected String getAreaCodeKey() {
        return "creditCardHolderAreaCode";
      }

      @Override
      protected String getPhoneNumberKey() {
        return "creditCardHolderPhone";
      }

    }

  }

  /**
   * Implementation of {@code AbstractAddressMapConverter}. Used to set key values
   *
   * @see AbstractAddressMapConverter
   */
  private static class CreditCardBillingAddressV2MapConverter extends AbstractAddressMapConverter {

    @Override
    protected String getCountryKey() {
      return "billingAddressCountry";
    }

    @Override
    protected String getStateKey() {
      return "billingAddressState";
    }

    @Override
    protected String getCityKey() {
      return "billingAddressCity";
    }

    @Override
    protected String getPostalCodeKey() {
      return "billingAddressPostalCode";
    }

    @Override
    protected String getDistrictKey() {
      return "billingAddressDistrict";
    }

    @Override
    protected String getStreetKey() {
      return "billingAddressStreet";
    }

    @Override
    protected String getNumberKey() {
      return "billingAddressNumber";
    }

    @Override
    protected String getComplementKey() {
      return "billingAddressComplement";
    }

  }

}
