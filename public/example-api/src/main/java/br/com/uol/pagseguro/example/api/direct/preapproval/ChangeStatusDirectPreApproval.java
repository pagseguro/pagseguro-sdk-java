package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.enums.PreApprovalStatus;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalChangingStatusBuilder;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class ChangeStatusDirectPreApproval {

    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{
            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            // Alteração do status de adesão (assinatura) [ suspenção: "SUSPENDED" | reativação: "ACTIVE" ]
            pagSeguro.directPreApprovals().changeStatus(
                    new DirectPreApprovalChangingStatusBuilder()
                            .withCode("0213D5537C7CE858840B8FBF0854CAA3")
                            .withStatus(PreApprovalStatus.ACTIVE)
            );

            System.out.println("Alteracao de status de adesao realizado!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
