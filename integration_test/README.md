Testes de Integração automatizados para a Lib Java PagSeguro
========================================================================

Descrição
---------
O projeto de Teste de Integração automatizado da Lib Java foi desenvolvido em Cucumber e tem como objetivo realizar os testes de integração entre a Lib e o Sandbox do PagSeguro. A recomendação é que este projeto seja executado e evoluído a cada alteração ou nova funcionalidade implementada na Lib. Os testes automatizados cobrem as seguintes funcionalidades implementadas na Lib:
 - Criar [requisições de pagamentos]
 - Criar [requisições de assinaturas]
 - Cancelar [assinaturas]
 - Consultar [assinaturas]
 - Consultar [transações por código]
 - Consultar [transações por intervalo de datas]
 - Consultar [transações abandonadas]


 - Criar checkouts transparentes
 - Criar checkouts transparentes no modo split
 - Criar requisições de pagamentos
 - Criar requisição de pagamento com assinatura
 - Criar requisições de assinaturas
 - Criar requisição de cobrança de assinaturas
 - Criar autorização
 - Criar Sessão
 - Cancelamento de transações
 - Cancelar assinaturas
 - Estorno de Transação
 - Estorno parcial de transação
 - Consultar assinaturas
 - Consultar transações por código
 - Consultar transações por intervalo de datas
 - Consultar transações abandonadas
 - Consultar Autorizações
 - Consultar Autorizações por referência

Requisitos
----------
 - Java 1.8
 - Cucumber 1.2.4
 - PhantomJS 1.3.0
 - Selenium WebDriver 2.53.0
 - JUnit 4.12

Configuração
------------

Transactions (src/test/java/integration.Modulos/Transaction.JAVA)
----------

Informar as credenciais no passo "Dado que esteja autenticado api no pagseguro":

- APP_ID
- APP_KEY 
- SELLER_EMAIL
- SELLER_TOKEN
- EMAIL_SANDBOX 
- SENHA_SANDBOX
- COMPRADOR_USER
- COMPRADOR_SENHA

Informar Email (com extensão @sandbox.pagseguro.com.br) e HASH gerado nos metódos abaixo:
- crio_requisicao_pagamento_transparente_credito;
- requisicao_transparente_debito;
- requisicao_transparente_debito_invalido; (INFORMAR APENAS UM HASH VÁLIDO)
- requisicao_pagamento_transparente_boleto;
- requisicao_transparente_boleto_invalido;
- requisicao_transparente_cartao_internacional; (INFORMAR HASH COM CREDENCIAIS QUE GERARAM UM HASH DE CARTAO INTERNACIONAL)
- requisicao_transparente_internacional_invalido;
- crio_requisicao_boleto_split;
- requisicao_boleto_split_invalido;
- requisicao_cartao_credito_split;
- pagamento_credito_split_invalido;
- requisicao_debito_split;
- requisicao_pagamento_debito_split_invalido;
- requisicao_pagamento;
- requisicao_pagamento_invalida;
- requisicao_pagamento_assinatura;
- requisicao_pagamento_assinatura_invalida;

Informar TOKEN gerado nos metodos abaixo:
- requisicao_transparente_debito;
- requisicao_transparente_invalida;
- requisicao_transparente_cartao_internacional; (INFORMAR TOKEN DE CARTAO INTERNACIONAL GERADO PELO JAVASCRIPT)
- requisicao_transparente_internacional_invalido; (INFORMAR TOKEN DE CARTAO INTERNACIONAL GERADO PELO JAVASCRIPT)
- requisicao_cartao_credito_split;
- pagamento_credito_split_invalido

Gerar TOKEN e HASH:
- Na classe Session, informe os dados de aplicação válido. Rode apenas a aplicação da Session, informando no StepsRunner "tags = "@session" e no Session.feature, informando @session sob o "Cenario: Criar Sessão"
- Ao gerar será exibido um ID de Sessão, altere no arquivo javascript("getdatajs-default.html") o var SessionID com o ID que foi gerado.
- Suba a aplicação localmente e acesse via url.
- Entre no console do navegador e capture o HASH.
- No proprio console clique em object e capture o código token.

Gerar Token e HASH de Cartão Internacional:
- Na classe Session, informar os dados de aplicação que seja válido para gerar um cartão internacional.
- No Arquivo javascript("getdatajs-default.html") informar internationalMode como TRUE
- Fazer os passos para a geração de um ID de sessão e capturação de HASH e TOKEN.


Informar PUBLICKEY nos metodos abaixo:
- crio_requisicao_boleto_split;
- requisicao_boleto_split_invalido;
- requisicao_cartao_credito_split;
- pagamento_credito_split_invalido;
- requisicao_debito_split;
- requisicao_pagamento_debito_split_invalido;



Signature (src/test/java/integration.Modulos/Signature.JAVA)
------------

Informar os dados SELLER e USUARIO sandbox no Signature.JAVA:
- SELLER_EMAIL;
- SELLER_SENHA;
- EMAIL_SANDBOX;
- SENHA_SANDBOX;
- COMPRADOR_USER;
- COMPRADOR_SENHA;

Autorization (src/test/java/integration.Modulos/Autorization.JAVA)
------------

Informar os dados de Aplicação no Autorization.JAVA:
- APP_ID;
- APP_KEY;
- EMAIL_SANDBOX; 
- SENHA_SANDBOX;
- VENDEDOR_USER;
- VENDEDOR_SENHA;


Session (src/test/java/integration.Modulos/Session.JAVA)
------------

Informar os dados de Aplicação no Session.JAVA:
- SELLER_EMAIL_SESSION
- SELLER_TOKEN_SESSION 


INFORMAR CAMINHO DO PHANTOMJS
--------------

src/test/java/integration.Modulos/Transaction.JAVA:
- finalizar_pagamento_sandbox;
- alterarStatusRequisicaoParaPago;

src/test/java/integration.Modulos/Signature.JAVA:
- retorno_codigo_assinatura_aprovada;
- capturar_codigo_assinatura_e_ativar;
- buscar_codigo_notificacao;

src/test/java/integration.Modulos/Autorization.JAVA:
- finalizacao_autorizacao;
- buscar_codigo_autorizacao;
- buscar_codigo_notificacao_autorizacao;




Changelog
---------
1.0.0
 - Primeira versão do projeto com testes das funcionalidades:
  - Criar requisições de pagamentos
 - Criar requisições de pagamentos com assinaturas
 - Criar requisições de cancelamento de transações
 - Criar requisições de estorno de transações
 - Consultar transações por código
 - Consultar transações por intervalo de datas
 - Consultar transações abandonadas
 - Consultar transações por código de referência
 - Criar requisições de autorizações
 - Consultar autorizações por código
 - Consultar autorizações por intervalo de datas
 - Consultar autorizações por código de notificação
 - Consultar autorizações por código de referência 
 - Criar requisições de assinaturas
 - Criar requisições de cancelamento de assinaturas
 - Criar requisições de cobrança de assinaturas
 - Consultar assinaturas por código
 - Consultar assinaturas por intervalo de datas
 - Consultar assinaturas por intervalo de dias
 - Consultar assinaturas por código de notificação
 - Receber notificações de autorizações
 - Receber notificações de assinaturas
 - Receber notificações de transações
 - Criar requisições de checkout transparente utilizando boleto
 - Criar requisições de checkout transparente utilizando debito online
 - Criar requisições de checkout transparente utilizando cartão de crédito
 - Criar requisições de checkout transparente utilizando cartão de crédito internacional
 - Criar requisições de checkout transparente utilizando boleto com split payment
 - Criar requisições de checkout transparente utilizando debito online com split payment
 - Criar requisições de checkout transparente utilizando cartão de crédito com split payment
 - Criar requisições de checkout transparente utilizando cartão de crédito internacional com split paymentdatas
 - Consultar transações abandonadas
 - Consultar Autorizações
 - Consultar Autorizações por referência
 

[requisições de assinaturas]: http://download.uol.com.br/pagseguro/docs/pagseguro-assinatura-automatica.pdf
  [assinaturas]: http://download.uol.com.br/pagseguro/docs/pagseguro-assinatura-automatica.pdf
  [requisições de pagamentos]: https://pagseguro.uol.com.br/v2/guia-de-integracao/api-de-pagamentos.html
  [notificações]: https://pagseguro.uol.com.br/v3/guia-de-integracao/api-de-notificacoes.html
  [Checkout Transparente]: https://pagseguro.uol.com.br/receba-pagamentos.jhtml#checkout-transparent
  [transações por código]: https://pagseguro.uol.com.br/v3/guia-de-integracao/consulta-de-transacoes-por-codigo.html
  [transações por intervalo de datas]: https://pagseguro.uol.com.br/v2/guia-de-integracao/consulta-de-transacoes-por-intervalo-de-datas.html
  [transações abandonadas]: https://pagseguro.uol.com.br/v2/guia-de-integracao/consulta-de-transacoes-abandonadas.html