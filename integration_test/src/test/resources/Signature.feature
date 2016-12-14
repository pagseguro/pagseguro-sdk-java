#language: pt

Funcionalidade: Assinatura


@signature
Cenario:  Criar requisicoes de assinaturas
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de assinatura como vendedor
Entao e retornado a url de redirecionamento


Cenario:  Criar requisicoes de assinaturas invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de assinatura invalida como vendedor
Entao e retornado um erro de assinatura


Cenario: Criar requisicoes de cancelamento de assinaturas
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de cancelamento de assinatura
Entao e retornado o status da assinatura que foi cancelada


Cenario: Criar requisicoes de cancelamento de assinaturas invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de cancelamento de assinatura invalida
Entao e retornado um erro de assinatura de cancelamento


Cenario: Criar requisicoes de cobrança de assinaturas
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de cobranca de assinatura
Entao e retornado a url de cobranca de assinatura


Cenario: Criar requisicoes de cobranca de assinaturas invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de cobranca invalida
Entao e retornada um erro de assinatura de cobranca



Cenario: Consultar assinaturas por codigo
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura
Entao e retornado a assinatura consultada por codigo




Cenario: Consultar assinaturas por codigo invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma consulta invalida por codigo
Entao e retornado um erro de consulta de assinatura por codigo



Cenario: Consultar assinaturas por intervalo de datas
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura por intervalo de datas
Entao e retornado as assinaturas naquela data



Cenario: Consultar assinaturas por intervalo de datas invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura por intervalo de datas invalida
Entao e retornado um erro de consulta por data 



Cenario: Consultar assinaturas por intervalo de dias
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura por intervalo de dias
Entao e retornado as assinaturas naqueles dias



Cenario: Consultar assinaturas por intervalo de dias invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura por intervalo de dias invalida
Entao e retornado um erro de consulta por dias




Cenario: Consultar assinaturas por codigo de notificacao
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura por codigo de notificacoes
Entao e retornado as assinaturas com o codigo de notificacao




Cenario: Consultar assinaturas por codigo de notificacao invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma assinatura por codigo de notificacoes invalida
Entao e retornado um erro de consulta por notificacao
