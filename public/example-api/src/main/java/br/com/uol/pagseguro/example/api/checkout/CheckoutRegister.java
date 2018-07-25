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
package br.com.uol.pagseguro.example.api.checkout;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AcceptedPaymentMethodsBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

/**
 * @author PagSeguro Internet Ltda.
 */
public class CheckoutRegister {

    public static void main(String[] args){

        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try {

            final PagSeguro pagSeguro = PagSeguro
                .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                    Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Criando um checkout
            RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(
                new CheckoutRegistrationBuilder()
                    .withCurrency(Currency.BRL)
                    .withExtraAmount(BigDecimal.ONE)
                    .withReference("XXXXXX")
                    .withSender(new SenderBuilder()
                        .withEmail("comprador@uol.com.br")
                        .withName("Jose Comprador")
                        .withCPF("99999999999")
                        .withHash("abc123")
                        .withPhone(new PhoneBuilder()
                            .withAreaCode("99")
                            .withNumber("99999999")))
                    .withShipping(new ShippingBuilder()
                        .withType(ShippingType.Type.SEDEX)
                        .withCost(BigDecimal.TEN)
                        .withAddress(new AddressBuilder()
                            .withPostalCode("99999999")
                            .withCountry("BRA")
                            .withState(State.SP)
                            .withCity("Cidade Exemplo")
                            .withComplement("99o andar")
                            .withDistrict("Jardim Internet")
                            .withNumber("9999")
                            .withStreet("Av. PagSeguro")))

                    .addItem(new PaymentItemBuilder()
                        .withId("0001")
                        .withDescription("Produto PagSeguroI")
                        .withAmount(new BigDecimal(99999.99))
                        .withQuantity(1)
                        .withWeight(1000))

                    .addItem(new PaymentItemBuilder()
                        .withId("0002")
                        .withDescription("Produto PagSeguroII")
                        .withAmount(new BigDecimal(99999.98))
                        .withQuantity(2)
                        .withWeight(750)
                    )

                    //Para definir o a inclusão ou exclusão de um meio você deverá utilizar três parâmetros: o parâmetro que define a configuração do grupo,
                    // o grupo de meios de pagamento e o nome do meio de pagamento.
                    // No parâmetro que define a configuração do grupo você informará se o grupo ou o meio de pagamento será incluído ou excluído.
                    // Já no grupo você informará qual o grupo de meio de pagamento que receberá a configuração definida anteriormente.
                    // Você poderá incluir e excluir os grupos de meios de pagamento Boleto, Débito, Cartão de Crédito, Depósito Bancário e Saldo PagSeguro.
                    // Já no parâmetro nome você informará qual o meio de pagamento que receberá a configuração definida anteriormente. Os meios são as bandeiras e bancos disponíveis.
                    //Atenção:  - Não é possível incluir e excluir o mesmo grupo de meio de pagamento (Ex.: incluir e excluir o grupo CREDIT_CARD).
                    // - Não é possível incluir um grupo e um meio do mesmo grupo (Ex.: incluir grupo cartão e bandeira visa na mesma chamada);
                    // - Não é possível excluir um grupo e um meio do mesmo grupo (Ex.: excluir grupo cartão e bandeira visa na mesma chamada);
                    // - Não é possível incluir e excluir o mesmo meio de pagamento (Ex.: incluir e excluir a bandeira VISA).

                    .withAcceptedPaymentMethods(new AcceptedPaymentMethodsBuilder()
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.BALANCE)
                        )
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.BANK_SLIP)
                        )
                    )

                    //Para definir o percentual de desconto para um meio de pagamento você deverá utilizar três parâmetros: grupo de meios de pagamento, chave configuração de desconto e o percentual de desconto. No parâmetro de grupo você deve informar um dos meios de pagamento disponibilizados pelo PagSeguro. Após definir o grupo é necessário definir os a configuração dos campos chave e valor.
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.DISCOUNT_PERCENT)
                            .withValue(new BigDecimal(10.00))
                        )
                    )
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.BANK_SLIP)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.DISCOUNT_PERCENT)
                            .withValue(new BigDecimal(10.00))
                        )
                    )

                    //Para definir o parcelamento você deverá utilizar três parâmetros: grupo, chave e valor.
                    // No parâmetro grupo você informará qual o meio pagamento que receberá as configurações.
                    // Para limitar o parcelamento você deve informar o meio de pagamento Cartão de crédito (CREDIT_CARD).
                    //Após definir o grupo você deverá definir as configurações nos campos chave e valor.
                    // Estes devem ser definidos com a chave MAX_INSTALLMENTS_LIMIT que define a configuração de limite de parcelamento e como valor o número de parcelas que você deseja apresentar ao cliente (de 2 a 18 parcelas).

                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.MAX_INSTALLMENTS_LIMIT)
                            .withValue(new BigDecimal(10))
                        )
                    )
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
                            .withValue(new BigDecimal(5))
                        )
                    )
            );
            System.out.println(registeredCheckout.getRedirectURL());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
