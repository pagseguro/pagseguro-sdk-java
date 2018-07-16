package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalCancellationBuilder;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class CancelDirectPreApproval {

    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{
            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            // Cancelamento de adesão (assinatura)
            pagSeguro.directPreApprovals().cancel(
                    new DirectPreApprovalCancellationBuilder()
                            .withCode("1EA91B6DFFFFC60FF4030F807D2473EE")
            );

            System.out.println("Cancelamento de adesão realizado!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
