package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ReceiverBuilder;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

import java.math.BigDecimal;

public class DirectPreApprovalRegister {
    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{

            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);
//              Credential.applicationCredential(appId, appKey), PagSeguroEnv.SANDBOX);

            //Criação do plano de pagamento recorrente
            RegisteredDirectPreApproval registeredPreApproval = pagSeguro.directPreApprovals().register(
                    new DirectPreApprovalRegistrationBuilder()
                            .withRedirectURL("http://www.seusite.com.br/assinatura-concluida")
                            .withReference("XXXXXX")
                            .withReviewURL("http://lojamodelo.com.br/revisar")
                            .withMaxUses(500)
                            .withReceiver(new ReceiverBuilder()
                                    .withEmail("suporte@lojamodelo.com.br"))

                            .withPreApproval(new PreApprovalBuilder()
                                            .withName("Seguro contra roubo do Notebook Rosa")
                                            .withCharge("AUTO")//@TODO create enum for charge types
                                            .withPeriod("MONTHLY")//@TODO create enum for period types
                                            .withDetails("Cada dia 28 será cobrado o valor de R$100,00 referente ao seguro " +
                                                    "contra roubo do Notebook Prata")
                                            .withAmountPerPayment(BigDecimal.TEN) //200

                                            //.withMembershipFee(BigDecimal.TEN) //150

                                            //.withTrialPeriodDuration(28)

                                            .withExpiration(new ExpirationBuilder()
                                                    .withValue(10)
                                                    .withUnit("MONTHS")) //@TODO create enum for unit types

                                            .withDetails("Plan description")
//                                .withMaxAmountPerPeriod(BigDecimal.TEN) //100
//                                .withMaxAmountPerPayment(BigDecimal.TEN) //100
//                                .withMaxTotalAmount(new BigDecimal(100))
//                                .withMaxPaymentsPerPeriod(3)
//                                .withDateRange(new DateRangeBuilder()
//                                  .between(
//                                      new Date(), //2018-05-19
//                                      DatatypeConverter.parseDateTime("2018-09-27T23:59:59.000-03:00") //2018-05-19
//                                          .getTime()))

//                                .withDayOfYear("03-27")//MM-dd //@TODO convert to date class
//                                    .withDayOfMonth(1)
//                                    .withDayOfWeek("MONDAY") //@TODO create enum for week days
//                                    .withCancelURL("http://seusite.com.br/cancelamento")

                            )


//                    .addParameter(new ParameterBuilder()
//                            .withName("authorizationCode")
//                            .withValue("AUTHORIZATIONCODEHERE")
//                    )

            );
            System.out.println(registeredPreApproval.getPreApprovalCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



