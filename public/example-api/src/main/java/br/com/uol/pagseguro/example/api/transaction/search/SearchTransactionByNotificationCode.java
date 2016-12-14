package br.com.uol.pagseguro.example.api.transaction.search;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;

public class SearchTransactionByNotificationCode {

  public static void main(String[] args){
    try {
      String sellerEmail = "your_seller_key";
      String sellerToken = "your_seller_token";

      final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(sellerEmail,
          sellerToken), PagSeguroEnv.SANDBOX);

      TransactionDetail transaction = pagSeguro.transactions().search().byNotificationCode("91922B2602C102C19DEAA4413F9EA8011698");
      System.out.println(transaction);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
