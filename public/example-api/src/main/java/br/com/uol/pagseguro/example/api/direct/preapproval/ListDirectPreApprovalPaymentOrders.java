package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentOrderStatus;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalPaymentOrdersListBuilder;
import br.com.uol.pagseguro.api.direct.preapproval.PaymentOrder;

public class ListDirectPreApprovalPaymentOrders {
    public static void main(String[] args) {
        try {
            String sellerEmail = "your_seller_email";
            String sellerToken = "your_seller_token";

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(sellerEmail,
                    sellerToken), PagSeguroEnv.SANDBOX);

            // Listando as ordens de pagamento da assinatura de código 09CA1D3141415C74441C6FA8E0B3ED14
            final DataList<? extends PaymentOrder> paymentOrders = pagSeguro.directPreApprovals()
                    .listPaymentOrders(new DirectPreApprovalPaymentOrdersListBuilder()
                        .withCode("09CA1D3141415C74441C6FA8E0B3ED14") // código da assinatura
                        //.withStatus(PaymentOrderStatus.PROCESSANDO) //(opcional) status em que se encontra a ordem de pagamento.
                        //.withPage(1) //(opcional) página na qual se quer observar os resultados
                        //.withMaxPageResults(3) //(opcional) Número máximo de registros por página
                    );

            System.out.println(paymentOrders);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
