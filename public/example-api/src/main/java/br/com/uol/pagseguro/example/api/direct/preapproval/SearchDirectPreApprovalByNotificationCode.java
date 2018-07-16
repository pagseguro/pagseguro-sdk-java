package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalSearchByNotificationCodeBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.SearchedDirectPreApprovalByNotificationCode;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class SearchDirectPreApprovalByNotificationCode {

    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{
            final PagSeguro pagSeguro = PagSeguro.instance(
                    new SimpleLoggerFactory(),
                    new JSEHttpClient(),
                    Credential.sellerCredential(sellerEmail, sellerToken),
                    PagSeguroEnv.SANDBOX
            );

            // Consulta pelo código de adesão (assinatura)
            SearchedDirectPreApprovalByNotificationCode searchedPreApproval = pagSeguro.directPreApprovals().searchByNotificationCode(
                    new DirectPreApprovalSearchByNotificationCodeBuilder()
                            .withCode("DF30A31C608E608ED570048F8FBD2BF01655")
            );

            System.out.println("Consulta pelo codigo de notificacao realizada!");
            System.out.println(searchedPreApproval);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
