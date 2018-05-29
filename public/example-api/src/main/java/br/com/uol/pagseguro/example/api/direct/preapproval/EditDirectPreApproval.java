package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class EditDirectPreApproval {
    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{
            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Edição de Valor em Planos
            pagSeguro.directPreApprovalsRequest().edit(
                    new DirectPreApprovalEditionBuilder()
                            .withCode("6CC1DFE28585C07BB469FFB3F8593AFA")
                            .withAmountPerPayment("19.99")
                            .withUpdateSubscriptions(false)
            );

            System.out.println("Edicao do valor do plano realizado!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
