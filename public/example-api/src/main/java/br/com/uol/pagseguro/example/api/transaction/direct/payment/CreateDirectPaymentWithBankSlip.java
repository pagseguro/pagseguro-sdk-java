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
package br.com.uol.pagseguro.example.api.transaction.direct.payment;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

/**
 * @author PagSeguro Internet Ltda.
 */
public class CreateDirectPaymentWithBankSlip {

  public static void main(String[] args){

    String sellerEmail = "your_seller_email";
    String sellerToken = "your_seller_token";

    final PagSeguro pagSeguro = PagSeguro
        .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);
    
    try{
      // Checkout transparente (pagamento direto) com boleto
      TransactionDetail bankSlipTransaction =
          pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder()
              .withPaymentMode("default")
              .withCurrency(Currency.BRL)
              .withExtraAmount(new BigDecimal(100.00))
              .addItem(new PaymentItemBuilder()//
                  .withId("0001")//
                  .withDescription("Produto PagSeguroI") //
                  .withAmount(new BigDecimal(99999.99))//
                  .withQuantity(1)
                  .withWeight(1000))

              .addItem(new PaymentItemBuilder()//
                  .withId("0002")//
                  .withDescription("Produto PagSeguroII") //
                  .withAmount(new BigDecimal(99999.98))//
                  .withQuantity(2)
                  .withWeight(750)
              )
              .withNotificationURL("www.sualoja.com.br/notification")
              .withReference("LIBJAVA_DIRECT_PAYMENT")
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
                      .withState(State.XX)//
                      .withCity("Cidade Exemplo")
                      .withComplement("99o andar")
                      .withDistrict("Jardim Internet")
                      .withNumber("9999")
                      .withStreet("Av. PagSeguro")))
          ).withBankSlip();
      System.out.println(bankSlipTransaction);
      
      
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
