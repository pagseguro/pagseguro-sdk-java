package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.credential.Credential;

public class ListDirectPreApprovalPaymentOrders {
    public static void main(String[] args) {
        try {
            String sellerEmail = "gabriel.pomin@s2it.com.br";
            String sellerToken = "3C0E388C67134F90B21DD43DE4C77D9F";

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(sellerEmail,
                    sellerToken), PagSeguroEnv.SANDBOX);

            final DataList<? extends PaymentOrder> paymentOrders = pagSeguro.directPreApprovals()
                    .listPaymentOrders(new DirectPreApprovalPaymentOrdersListBuilder()
                        .withCode("09CA1D3141415C74441C6FA8E0B3ED13")
                        //.withStatus(PaymentOrderStatus.PROCESSANDO) //optional
//                    .withPage(1)//optional
//                    .withMaxPageResults(3) //optional
                    );

            System.out.println(paymentOrders);
            System.out.println(paymentOrders.getData());
            System.out.println(paymentOrders.getDate());
            System.out.println(paymentOrders.size());
            System.out.println(paymentOrders.getCurrentPage());
            System.out.println(paymentOrders.getTotalPages());
            System.out.println(paymentOrders.getData().isEmpty());
            System.out.println(paymentOrders.getData().iterator().next().getTransactions().iterator().next().getCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
