package br.com.uol.pagseguro.example.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.direct.preapproval.ChargedDirectPreApproval;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalChargeBuilder;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

import java.math.BigDecimal;

public class ChargeDirectPreApproval {
    public static void main(String[] args){
        String sellerEmail = "your_seller_email";
        String sellerToken = "your_seller_token";

        try{

            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            // Efetuando cobrança de plano personalizado (manual)
            ChargedDirectPreApproval directPreApprovalRequestCharge = pagSeguro.directPreApprovals().charge(
                    new DirectPreApprovalChargeBuilder()
                            .withCode("1EA91B6DFFFFC60FF4030F807D2473EE")
                            .withReference("xxxx")
                            .withSenderHash("hash")
                            .withSenderIp("1.1.1.1")
                            .addItem(
                                    new PaymentItemBuilder()
                                            .withId("0001")
                                            .withDescription("Produto PagSeguroI") //
                                            .withAmount(new BigDecimal(10.00))//
                                            .withQuantity(1)
                                            .withWeight(1)
                                            .withShippingCost(new BigDecimal(1.00))
                            )

            );

            System.out.println(directPreApprovalRequestCharge.getDate());
            System.out.println(directPreApprovalRequestCharge.getTransactionCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
