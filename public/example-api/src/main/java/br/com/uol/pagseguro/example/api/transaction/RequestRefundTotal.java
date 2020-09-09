package br.com.uol.pagseguro.example.api.transaction;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;

public class RequestRefundTotal {
    public static void main(String[] args){
        try {
            String sellerEmail = "your_seller_key";
            String sellerToken = "your_seller_token";

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(sellerEmail,
                    sellerToken), PagSeguroEnv.SANDBOX);

            pagSeguro.transactions().refundByCode("TRANSACATIONCODEHERE");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
