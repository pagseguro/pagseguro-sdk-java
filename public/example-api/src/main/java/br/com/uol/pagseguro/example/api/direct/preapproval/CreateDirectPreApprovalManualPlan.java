package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.builder.ExpirationBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalRequestBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ReceiverBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Charge;
import br.com.uol.pagseguro.api.common.domain.enums.DayOfWeek;
import br.com.uol.pagseguro.api.common.domain.enums.Period;
import br.com.uol.pagseguro.api.common.domain.enums.Unit;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalRequestRegistrationBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.RegisteredDirectPreApprovalRequest;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

import java.math.BigDecimal;

/**
 * @author PagSeguro Internet Ltda.
 */
public class CreateDirectPreApprovalManualPlan {

    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{

            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Criação do plano MANUAL de pagamento recorrente
            //Plano pós-pago (manual) com cobranças semanais de R$ 10,00 a serem efetuadas toda Segunda-feira, com duração ilimitada e taxa de adesão de R$ 50,00
            RegisteredDirectPreApprovalRequest registeredPreApproval = pagSeguro.directPreApprovalsRequest().register(
                    new DirectPreApprovalRequestRegistrationBuilder()
                            .withRedirectURL("http://www.seusite.com.br/assinatura-concluida")
                            .withReference("XXXXXX")
                            .withReviewURL("http://lojamodelo.com.br/revisar")
                            .withReceiver(new ReceiverBuilder()
                                    .withEmail("receiveremail@sandbox.pagseguro.com.br"))
                            .withMaxUses(500)

                            .withPreApproval(new PreApprovalRequestBuilder()
                                            .withName("Acesso ao Site Fictício")
                                            .withCharge(Charge.MANUAL)
                                            .withPeriod(Period.WEEKLY)
                                            .withDetails("Plano de acesso ao Site Ficticio")
                                            .withAmountPerPayment(new BigDecimal(10.00))
                                            .withMembershipFee(new BigDecimal(50.00))
                                            .withExpiration(new ExpirationBuilder()
                                                    .withValue(1)
                                                    .withUnit(Unit.YEARS))
                                            .withMaxAmountPerPeriod(new BigDecimal(50.00))
                                            .withMaxPaymentsPerPeriod(5)
                                            .withDayOfWeek(DayOfWeek.MONDAY)
                            )

            );
            System.out.println(registeredPreApproval.getPreApprovalCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
