package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.builder.ExpirationBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalRequestBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Charge;
import br.com.uol.pagseguro.api.common.domain.enums.Period;
import br.com.uol.pagseguro.api.common.domain.enums.Unit;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalRequestRegistrationBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.RegisteredDirectPreApprovalRequest;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class CreateDirectPreApprovalAutomaticPlan {
    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{

            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Criação do plano AUTOMATICO de pagamento recorrente transparente
            //Plano Automático com cobranças mensais de R$ 100,00 por 1 ano
            RegisteredDirectPreApprovalRequest registeredPreApproval = pagSeguro.directPreApprovalsRequest().register(
                    new DirectPreApprovalRequestRegistrationBuilder()
                            .withRedirectURL("http://www.seusite.com.br/assinatura-concluida")
                            .withReference("XXXXXX")
                            .withReviewURL("http://lojamodelo.com.br/revisar")
                            .withMaxUses(500)

                            .withPreApproval(new PreApprovalRequestBuilder()
                                            .withName("Assinatura da Revista Fictícia")
                                            .withCharge(Charge.AUTO)
                                            .withPeriod(Period.MONTHLY)
                                            .withDetails("Assinatura mensal de Revista")
                                            .withAmountPerPayment(new BigDecimal(100.00))
                                            .withExpiration(new ExpirationBuilder()
                                                    .withValue(1)
                                                    .withUnit(Unit.YEARS))
                            )

            );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            System.out.println(sdf.format(registeredPreApproval.getPreApprovalDate()));
            System.out.println(registeredPreApproval.getPreApprovalCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



