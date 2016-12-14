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
 * Converter for credit card. Used in version 3 of the application
 * Used to convert attributes of credit card in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class CreditCardV3MapConverter extends AbstractMapConverter<CreditCard> {

  private static final CreditCardHolderV3MapConverter CREDIT_CARD_HOLDER_MC =
      new CreditCardHolderV3MapConverter();

  private static final InstallmentV3MapConverter INSTALLMENT_MC = new InstallmentV3MapConverter();

  private static final CreditCardBillingAddressV3MapConverter CREDIT_CARD_BILLING_ADDRESS_MC =
      new CreditCardBillingAddressV3MapConverter();

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
    requestMap.putString("creditCard.token", creditCard.getToken());
    requestMap.putMap(CREDIT_CARD_HOLDER_MC.convert(creditCard.getHolder()));
    requestMap.putMap(INSTALLMENT_MC.convert(creditCard.getInstallment()));
    requestMap.putMap(CREDIT_CARD_BILLING_ADDRESS_MC.convert(creditCard.getBillingAddress()));
  }

  /**
   * Implementation of {@code AbstractHolderMapConverter}. Used to set key values
   *
   * @see AbstractHolderMapConverter
   */
  private static class CreditCardHolderV3MapConverter extends AbstractHolderMapConverter {

    private static final HolderPhoneV3MapConverter HOLDER_PHONE_MC =
        new HolderPhoneV3MapConverter();

    @Override
    protected String getNameKey() {
      return "creditCard.holder.name";
    }

    @Override
    protected String getCpfKey() {
      return "creditCard.holder.CPF";
    }

    @Override
    protected String getCnpjKey() {
      return "creditCard.holder.CNPJ";
    }

    @Override
    protected String getBirthDateKey() {
      return "creditCard.holder.birthDate";
    }

    @Override
    protected AbstractPhoneMapConverter getPhoneMapConverter() {
      return HOLDER_PHONE_MC;
    }

    private static class HolderPhoneV3MapConverter extends AbstractPhoneMapConverter {

      @Override
      protected String getAreaCodeKey() {
        return "creditCard.holder.areaCode";
      }

      @Override
      protected String getPhoneNumberKey() {
        return "creditCard.holder.phone";
      }

    }

  }

  /**
   * Implementation of {@code AbstractAddressMapConverter}. Used to set key values
   *
   * @see AbstractAddressMapConverter
   */
  private static class CreditCardBillingAddressV3MapConverter extends AbstractAddressMapConverter {

    @Override
    protected String getCountryKey() {
      return "billingAddress.country";
    }

    @Override
    protected String getStateKey() {
      return "billingAddress.state";
    }

    @Override
    protected String getCityKey() {
      return "billingAddress.city";
    }

    @Override
    protected String getPostalCodeKey() {
      return "billingAddress.postalCode";
    }

    @Override
    protected String getDistrictKey() {
      return "billingAddress.district";
    }

    @Override
    protected String getStreetKey() {
      return "billingAddress.street";
    }

    @Override
    protected String getNumberKey() {
      return "billingAddress.number";
    }

    @Override
    protected String getComplementKey() {
      return "billingAddress.complement";
    }

  }

}
