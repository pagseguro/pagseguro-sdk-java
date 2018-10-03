package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalEditionBuilder;
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
            pagSeguro.directPreApprovals().edit(
                    new DirectPreApprovalEditionBuilder()
                            .withCode("6418F4C21A1A5F9774A1CFB341E0D0B7")
                            .withAmountPerPayment("19.99")
                            .withUpdateSubscriptions(false)
            );

            System.out.println("Edição do valor do plano realizado!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
