package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalSearchByAccessionCodeBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.SearchedDirectPreApprovalByAccessionCode;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class SearchDirectPreApprovalByAccessionCode {

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
            SearchedDirectPreApprovalByAccessionCode searchedPreApproval = pagSeguro.directPreApprovals().searchByAccessionCode(
                    new DirectPreApprovalSearchByAccessionCodeBuilder()
                            .withCode("0213D5537C7CE858840B8FBF0854CAA3")
            );

            System.out.println("Consulta pelo codigo de adesão realizada!");
            System.out.println(searchedPreApproval);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
