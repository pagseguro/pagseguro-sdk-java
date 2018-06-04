package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.enums.DiscountType;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalRequestDiscountBuilder;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class DiscountPayment {
    public static void main(String[] args){

        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{
            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Desconto na próxima cobrança
            pagSeguro.directPreApprovalsRequest().discount(
                    new DirectPreApprovalRequestDiscountBuilder()
                            .withCode("4F05C7F7B9B9A34CC48CDFBEC21D9047") //código da assinatura
                            .withType(DiscountType.DISCOUNT_PERCENT)
                            .withValue("10.00")
            );

            System.out.println("Desconto realizado!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
