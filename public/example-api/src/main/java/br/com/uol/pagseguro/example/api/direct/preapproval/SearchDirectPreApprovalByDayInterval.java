package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalByDayIntervalListBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalData;

public class SearchDirectPreApprovalByDayInterval {

    public static void main(String[] args) {
        try {
            String sellerEmail = "your_seller_email";
            String sellerToken = "your_seller_token";

            final PagSeguro pagSeguro = PagSeguro.instance(
                Credential.sellerCredential(sellerEmail, sellerToken),
                PagSeguroEnv.SANDBOX
            );

            // Listando as recorrências  que tiveram algum tipo de notificação dentro de um intervalo de dias
            final DataList<? extends DirectPreApprovalData> directPreApprovalByDayInterval = pagSeguro.directPreApprovals()
                .listDirectPreApprovalByDayInterval(new DirectPreApprovalByDayIntervalListBuilder()
                    .withInterval(20) // (obrigatório) quantidade de dias de intervalo (máx: 30)
                    //.withPage(1) //(opcional) página na qual se quer observar os resultados
                    //.withMaxPageResults(1) //(opcional) Número máximo de registros por página
                );

            System.out.println(directPreApprovalByDayInterval);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
