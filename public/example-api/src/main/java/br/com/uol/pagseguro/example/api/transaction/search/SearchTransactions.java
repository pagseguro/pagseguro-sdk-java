package br.com.uol.pagseguro.example.api.transaction.search;

import java.util.Date;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;

public class SearchTransactions {

  public static void main(String[] args){
    try {
      String sellerEmail = "your_seller_key";
      String sellerToken = "your_seller_token";

      final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(sellerEmail,
          sellerToken), PagSeguroEnv.SANDBOX);

      final DataList<? extends TransactionSummary> transactions =
          pagSeguro.transactions().search().byDateRange(new DateRangeBuilder().between(new Date(), new Date()), 1, 10);
      System.out.println(transactions.size());
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
