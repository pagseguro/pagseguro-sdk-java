`DEPRECATED` #Biblioteca de integração PagSeguro para Java


> **_NOTE:_** **Esse SDK foi descontinuado** <br> Estamos trabalhando em soluções e facilidades para evoluirmos a Plataforma de API’s do PagSeguro. Conheça nossa Plataforma de API’s acessando https://dev.pagseguro.uol.com.br/reference/pagseguro-reference-intro

## Descrição

A biblioteca PagSeguro em Java é um conjunto de classes de domínio que facilitam, para o desenvolvedor Java, a utilização das funcionalidades que o PagSeguro oferece na forma de APIs. Com a biblioteca instalada e configurada, você pode facilmente integrar funcionalidades como:

 - [Criar Requisições de Pagamentos]
 - [Criar Requisições de Pagamento Recorrente] (assinatura transparente)
 - [Criar Requisições de assinaturas]
 - [Cancelar Assinaturas]
 - [Cancelar Transações por Código]
 - [Estornar Transações por Código]
 - [Consultar Assinaturas]
 - [Consultar Transações por Código]
 - [Consultar Transações por Intervalo de Datas]
 - [Receber Notificações]
 - [Solicitar Autorização]


## Requisitos Mínimos

 - [Java] 1.6+
 - [Gradle] (somente para editar o código-fonte deste SDK)


## Utilizando este SDK

Este repositório contém 2 projetos: o código-fonte e um projeto com exemplos de uso das funcionalidades do SDK.

 - Para utilizar o SDK como dependência do seu projeto siga as orientações da seção [Utilização via Grade](#utilização-via-gradle)
 - Para modificar o código-fonte deste SDK siga as orientações da seção [Instalação Manual](#instalação-manual)
 - Para apenas executar os exemplos de uso deste SDK, abra o projeto do diretório *public/example-api* como um projeto do Gradle


## Utilização via Gradle

 Adicionar no arquivo *build.gradle* do seu projeto, na seção de dependências, o seguinte trecho de código:

 ```groovy
 compile (group: 'br.com.uol.pagseguro', name: 'pagseguro-api', version: 'X.Y.Z')
 ```


## Instalação Manual

 - Baixe o repositório como arquivo zip ou faça um clone
 - Descompacte os arquivos em seu computador
 - Navega até a pasta *source*
 - Execute o comando ```gradle build```
 - Navegue até a pasta *build/libs*
 - Importe o arquivo ```pagseguro-api-*.*.*.jar``` para seu projeto
 - O diretório *public* contém exemplos de chamadas utilizando a API e o diretório *source* contém a biblioteca propriamente dita.


## Configuração

Para fazer uso real da biblioteca, é preciso fazer as configurações de credenciais e de ambiente.
As duas configurações seguem a ordem de precedência:

 - Manual
 - Propriedades do sistema
 - Arquivo (*pagseguro.properties*)
 - Variáveis do sistema


### Credenciais

É necessário configurar as credenciais do vendedor ou da aplicação. Segue abaixo como configurar de acordo com cada método.

#### Manual
```PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential("appId", "appKey"), PagSeguroEnv.SANDBOX);```
#### Propriedades do sistema
É necessário passar as opções para a JVM:
- Vendedor: ```-Dpagseguro.email="email" -Dpagseguro.token="token"```
- Aplicação: ```-Dpagseguro.appId="appId" -Dpagseguro.appKey="appKey"```

```PagSeguro pagSeguro = PagSeguro.instance();```
#### Arquivo
É necessário criar o arquivo *pagseguro.properties* dentro da pasta *resources* da sua aplicação e criar as propriedades:
- Vendedor:
```
    email=email
    token=token
```
- Aplicação:
```
    appId=appId
    appKey=appKey
```

```PagSeguro pagSeguro = PagSeguro.instance();```
#### Variáveis do sistema
É necessário criar as seguintes variáveis de ambiente:
- Vendedor: ```PSL_EMAIL``` e ```PSL_TOKEN```
- Aplicação: ```PSL_APP_ID``` e ```PSL_APP_KEY```

```PagSeguro pagSeguro = PagSeguro.instance();```


### Ambiente

É necessário configurar o ambiente do pagseguro que deseja utilizar. Segue abaixo como configurar de acordo com cada método.

#### Manual
- Sandbox:
```
Credential credential = Credential.sellerCredential("email", "token");
PagSeguroEnv environment = PagSeguroEnv.SANDBOX;
PagSeguro pagSeguro = PagSeguro.instance(credential, environment);
```
- Produção:
```
Credential credential = Credential.sellerCredential("email", "token");
PagSeguroEnv environment = PagSeguroEnv.PRODUCTION;
PagSeguro pagSeguro = PagSeguro.instance(credential, environment);
```


```  
PagSeguro pagSeguro = PagSeguro.instance(credential, environment);
```
#### Propriedades do sistema
É necessário passar as opções para a JVM:
- Sandbox: ```-Dpagseguro.environment="sandbox"```
- Produção: ```-Dpagseguro.environment="production"```

```PagSeguro pagSeguro = PagSeguro.instance();```
#### Arquivo
É necessário criar o arquivo *pagseguro.properties* dentro da pasta *resources* da sua aplicação e criar as propriedades:
- Sandbox: ```environment=sandbox```
- Produção: ```environment=production```

```PagSeguro pagSeguro = PagSeguro.instance();```
#### Variáveis do sistema
É necessário criar as seguintes variáveis de ambiente:
- Sandbox: ```PSL_PSL_ENVIRONMENT``` com valor: ```sandbox```
- Produção: ```PSL_PSL_ENVIRONMENT```  com valor: ```production```

```PagSeguro pagSeguro = PagSeguro.instance();```


## Dúvidas?

Caso tenha dúvidas ou precise de suporte, abra um chamado conosco [link].


## Changelog

Para consultar o log de alterações acesse o arquivo [CHANGELOG.md](CHANGELOG.md).

## Licença

Copyright 2016 PagSeguro Internet LTDA.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


## Notas

 - O PagSeguro somente aceita pagamento utilizando a moeda Real brasileiro (BRL).
 - Certifique-se que o email e o token informados estejam relacionados a uma conta que possua o perfil de vendedor ou empresarial.
 - Certifique-se que tenha definido corretamente o charset de acordo com a codificação (ISO-8859-1 ou UTF-8) do seu sistema. Isso irá prevenir que as transações gerem possíveis erros ou quebras ou ainda que caracteres especiais possam ser apresentados de maneira diferente do habitual.
 - Para que ocorra normalmente a geração de logs, certifique-se que o diretório e o arquivo de log tenham permissões de leitura e escrita.
 - Para a utilizar o checkout transparente, é necessária a solicitação de ativação junto com a equipe do PagSeguro, maiores informações podem ser encontradas em [Como receber pagamentos pelo PagSeguro].


## Contribuições

Achou e corrigiu um bug ou tem alguma feature em mente e deseja contribuir?

* Faça um fork
* Adicione sua feature ou correção de bug (git checkout -b my-new-feature)
* Commit suas mudanças (git commit -am 'Added some feature')
* Rode um push para o branch (git push origin my-new-feature)
* Envie um Pull Request
* Obs.: Adicione exemplos para sua nova feature. Se seu Pull Request for relacionado a uma versão específica, o Pull Request não deve ser enviado para o branch master e sim para o branch correspondente a versão.
* Obs2: Não serão aceitos PR's na branch master. Utilizar a branch de desenvolvimento.

  [Criar Requisições de Pagamentos]: https://devs.pagseguro.uol.com.br/docs/checkout-web
  [Criar Requisições de Pagamento Recorrente]: https://devs.pagseguro.uol.com.br/docs/pagamento-recorrente
  [Criar Requisições de assinaturas]: https://devs.pagseguro.uol.com.br/docs/arquivo-documentacoes-depreciadas
  [Cancelar Assinaturas]: https://devs.pagseguro.uol.com.br/docs/arquivo-documentacoes-depreciadas
  [Cancelar Transações por Código]: https://devs.pagseguro.uol.com.br/docs/pagamento-recorrente-cancelamento-de-adesao
  [Estornar Transações por Código]: https://devs.pagseguro.uol.com.br/docs/checkout-web-cancelamento-e-estorno
  [Consultar Assinaturas]: https://devs.pagseguro.uol.com.br/docs/arquivo-documentacoes-depreciadas
  [Consultar Transações por Código]: https://devs.pagseguro.uol.com.br/docs/pagamento-recorrente-consulta-pelo-codigo-de-adesao
  [Consultar Transações por Intervalo de Datas]: https://devs.pagseguro.uol.com.br/docs/pagamento-recorrente-consulta-por-intervalo-de-datas
  [Receber Notificações]: https://devs.pagseguro.uol.com.br/docs/checkout-web-notificacoes
  [Solicitar Autorização]: https://devs.pagseguro.uol.com.br/docs/modelo-de-aplicacoes-solicitando-autorizacao
  [gradle]: https://gradle.org/
  [java]: https://www.java.com
  [link]: https://app.pipefy.com/public/form/k8aKYyJE/?_ga=2.175732066.1759255508.1544013668-532205691.1540442951
  [Como receber pagamentos pelo PagSeguro]: https://pagseguro.uol.com.br/receba-pagamentos.jhtml#checkout-transparent
