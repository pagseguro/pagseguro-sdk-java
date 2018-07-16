package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.builder.*;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.common.domain.enums.PreApprovalPaymentMethodType;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalChangingPaymentMethodBuilder;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

import java.text.SimpleDateFormat;

public class ChangePaymentMethodDirectPreApproval {

    public static void main(String[] args) {

        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try {
            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Permite a alteração do meio de pagamento (cartão de crédito) atrelado ao pagamento do plano para as próximas cobranças
            pagSeguro.directPreApprovals().changePaymentMethod(
                    new DirectPreApprovalChangingPaymentMethodBuilder()
                            .withPreApprovalCode("9902A8593131EE5FF4331F85FA630007") // código da assinatura
                            .withType(PreApprovalPaymentMethodType.CREDITCARD)
                            .withSenderRiskData(new PreApprovalSenderRiskDataBuilder()
                                    .withIp("1.1.1.1")
                                    .withHash("HASHCODE")
                            )
                            .withCreditCard(new PreApprovalCreditCardBuilder()
                                    .withToken("621593e9f7cd4955b3452b4400e4893e")
                                    .withHolder(new PreApprovalHolderBuilder()
                                            .withName("JOSÉ COMPRADOR")
                                            .withBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/12/1990"))
                                            .addDocument(new DocumentBuilder()
                                                    .withType(DocumentType.CPF)
                                                    .withValue("99999999999")
                                            )
                                            .withPhone(new PhoneBuilder()
                                                    .withAreaCode("99")
                                                    .withNumber("99999999")
                                            )
                                            .withBillingAddress(new AddressBuilder()
                                                    .withStreet("Av. PagSeguro")
                                                    .withNumber("9999")
                                                    .withComplement("99o andar")
                                                    .withDistrict("Jardim Internet")
                                                    .withCity("Cidade Exemplo")
                                                    .withState(State.SP)
                                                    .withCountry("BRA")
                                                    .withPostalCode("99999999")
                                            )
                                    )
                            )
            );
            System.out.println("Meio de pagamento alterado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
