package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalByDateIntervalListBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalData;

import javax.xml.bind.DatatypeConverter;

public class SearchDirectPreApprovalByDateInterval {

    public static void main(String[] args) {
        try {
            String sellerEmail = "your_seller_email";
            String sellerToken = "your_seller_token";

            final PagSeguro pagSeguro = PagSeguro.instance(
                Credential.sellerCredential(sellerEmail, sellerToken),
                PagSeguroEnv.SANDBOX
            );

            // Listando as recorrências criadas dentro do intervalo de datas
            final DataList<? extends DirectPreApprovalData> directPreApprovalByDateInterval = pagSeguro.directPreApprovals()
                .listDirectPreApprovalByDateInterval(new DirectPreApprovalByDateIntervalListBuilder()
                    .withDateRange(
                        new DateRangeBuilder().between(
                            DatatypeConverter.parseDateTime("2018-01-01T00:00:00.000-03:00").getTime(),
                            DatatypeConverter.parseDateTime("2018-07-03T15:56:00.000-03:00").getTime()
                        )
                    )
                    //.withPage(1) //(opcional) página na qual se quer observar os resultados
                    //.withMaxPageResults(1) //(opcional) Número máximo de registros por página
                );

            System.out.println(directPreApprovalByDateInterval);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
