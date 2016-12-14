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
package br.com.uol.pagseguro.example.api.instantiation;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

/**
 * @author PagSeguro Internet Ltda.
 *
 * Maneiras de instanciar a lib
 */
public class InstanciateLib {


  public static void main(String[] args){

  }

  public void instanciateLib1(){
    // Dessa maneira, a lib tenta obter os valores das credenciais e do ambiente atraves das configuracoes
    // que seguem a seguinte prioridade: variaveis de ambiente da JVM (-Dpagseguro.email="email@email.com.br"
    // -Dpagseguro.token="token" -Dpagseguro.environment="sandbox") > atraves do arquivo "pagseguro.properties"
    // contido dentro da pasta resources com as propriedades (email, token, appId, appKey, environment) > atraves
    // das variaveis de ambiente do sistema operacional (PSL_EMAIL, PSL_TOKEN, PSL_APP_ID, PSL_APP_KEY,
    // PSL_ENVIRONMENT). Caso a lib nao encontre as informacoes necessarias para ser instanciada eh lancada uma
    // excecao
    //
    // A lib utiliza o HttpClient default, e os log default da lib que usa a implementacao java.util.logging

    final PagSeguro pagSeguro = PagSeguro.instance();
  }

  public void instanciateLib2(){
    // Dessa maneira, o usuario da lib pode implementar o Http Client e passar para instanciar a lib, a lib tentara
    // obter os valores das credencias e do ambiente atraves das configuracoes. Utilizara o log default

    final PagSeguro pagSeguro = PagSeguro.instance(new JSEHttpClient());
  }

  public void instanciateLib3(){

    // Dessa maneira, o usuario pode instanciar a lib passando suas credenciais e o ambiente que ele deseja usar
    // manualmente

    String sellerEmail = "your_seller_email";
    String sellerToken = "your_seller_token";

    final PagSeguro pagSeguro = PagSeguro
        .instance(Credential.sellerCredential(sellerEmail, sellerToken),
          PagSeguroEnv.SANDBOX);

  }

  public void instanciateLib4(){
    // Dessa maneira o usuario pode instanciar a lib passando todas as opcoes manualmente
    // Caso o usuario queria utilizar algum log ja configurado na aplicacao ele deve passar algumas das seguintes
    // fabricas: SimpleLoggerFactory (prove a implementacao do log do PagSeguro utilizando a implementacao do java
    // java.util.logging, que escreve o log da lib no arquivo pagseguro.log na raiz da aplicacao),
    // Slf4jLoggerFactory (prove a implementacao do log do PagSeguro utilizando alguma das implementacoes do SLF4J
    // que o usuario ja utiliza), CommonsLoggerFactory (prove a implementacao do log do PagSeguro utilizando alguma
    // das implementacoes do Apache Commons Logging que o usuario ja utiliza)
    //
    // Caso o usuario queria usar uma implementacao do SLF4J ou Apache Commons Logging e a lib nao encontrar,
    // ocasionara uma excecao

    String sellerEmail = "your_seller_email";
    String sellerToken = "your_seller_token";

    final PagSeguro pagSeguro = PagSeguro
        .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);
  }

}
