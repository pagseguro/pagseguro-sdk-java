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
package br.com.uol.pagseguro.example.api.preapproval;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.*;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.preapproval.PreApprovalRegistrationBuilder;
import br.com.uol.pagseguro.api.preapproval.RegisteredPreApproval;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalRegister {

  public static void main(String[] args){

    String sellerEmail = "your_seller_email";
    String sellerToken = "your_seller_token";

    try{

      final PagSeguro pagSeguro = PagSeguro
          .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);


      //Assinatura
      RegisteredPreApproval registeredPreApproval = pagSeguro.preApprovals().register(
          new PreApprovalRegistrationBuilder()
              .withCurrency(Currency.BRL)
              .withExtraAmount(BigDecimal.ONE)
              .withReference("XXXXXX")
              .withSender(new SenderBuilder()//
                  .withEmail("comprador@uol.com.br")//
                  .withName("Jose Comprador")
                  .withCPF("99999999999")
                  .withPhone(new PhoneBuilder()//
                      .withAreaCode("99") //
                      .withNumber("99999999"))) //
              .withShipping(new ShippingBuilder()//
                  .withType(ShippingType.Type.SEDEX) //
                  .withCost(BigDecimal.TEN)//
                  .withAddress(new AddressBuilder() //
                      .withPostalCode("99999999")
                      .withCountry("BRA")
                      .withState(State.SP)//
                      .withCity("Cidade Exemplo")
                      .withComplement("99o andar")
                      .withDistrict("Jardim Internet")
                      .withNumber("9999")
                      .withStreet("Av. PagSeguro")))
              .withPreApproval(new PreApprovalRequestBuilder()
                  .withCharge("auto")
                  .withName("Seguro contra roubo do Notebook Rosa")
                  .withDetails("Cada dia 28 será cobrado o valor de R$100,00 referente ao seguro " +
                      "contra roubo do Notebook Prata")
                  .withAmountPerPayment(BigDecimal.TEN)
                  .withMaxTotalAmount(new BigDecimal(200))
                  .withMaxAmountPerPeriod(BigDecimal.TEN)
                  .withMaxPaymentsPerPeriod(2)
                  .withPeriod("monthly")
                  .withDateRange(new DateRangeBuilder()
                      .between(
                          new Date(),
                          DatatypeConverter.parseDateTime("2018-09-27T23:59:59.000-03:00")
                              .getTime())
                  )
              )
              .withRedirectURL("http://loja.teste.com/redirect")
              .withNotificationURL("http://loja.teste.com/notification")

      );
      System.out.println(registeredPreApproval.getRedirectURL());
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
