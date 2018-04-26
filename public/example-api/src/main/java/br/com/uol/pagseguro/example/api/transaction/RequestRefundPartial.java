package br.com.uol.pagseguro.example.api.transaction;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import java.math.BigDecimal;

public class RequestRefundPartial {
    public static void main(String[] args){
        try {
            String sellerEmail = "your_seller_key";
            String sellerToken = "your_seller_token";

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(sellerEmail,
                    sellerToken), PagSeguroEnv.SANDBOX);

            /* refunds the value of R$ 10.90 from the transaction */
            pagSeguro.transactions().refundByCode("TRANSACATIONCODEHERE", new BigDecimal(10.90));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
