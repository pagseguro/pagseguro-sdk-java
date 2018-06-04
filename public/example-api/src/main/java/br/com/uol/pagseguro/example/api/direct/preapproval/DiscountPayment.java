package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalRequestEditionBuilder;
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
                            .withCode("6481F9C82A2A8C7224E45F8994E95A49")
                            .withType(DiscountType.DISCOUNT_PERCENT)
                            .withValue("10.00")
            );

            System.out.println("Desconto realizado!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
