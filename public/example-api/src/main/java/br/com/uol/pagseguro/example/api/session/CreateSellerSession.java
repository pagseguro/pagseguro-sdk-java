/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */
package br.com.uol.pagseguro.example.api.session;


import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.session.CreatedSession;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

/**
 * @author PagSeguro Internet Ltda.
 */
public class CreateSellerSession {

  public static void main(String[] args){
    String sellerEmail = "your_seller_email";
    String sellerToken = "your_seller_token";

    final PagSeguro pagSeguro = PagSeguro
        .instance(new SimpleLoggerFactory(), new JSEHttpClient(), Credential.sellerCredential(sellerEmail,
            sellerToken), PagSeguroEnv.SANDBOX);

    try {

      // Criacao de sessao de seller
      CreatedSession createdSession = pagSeguro.sessions().createSeller();
      System.out.println(createdSession.getId());
    }catch (Exception e){
      e.printStackTrace();
    }

  }

}
