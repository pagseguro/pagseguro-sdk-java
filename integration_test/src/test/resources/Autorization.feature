#language: pt

Funcionalidade: Autorizacoes


Cenario: Criar uma autorizacao
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de autorizacao
Entao e retornado a url de autorizacao


Cenario: Criar uma autorizacao invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de autorizacao invalida
Entao e retornado um erro de autorizacao


Cenario: Consultar autorizacao por codigo
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao pelo codigo
Entao e retornada a autorizacao



Cenario: Consultar autorizacao por codigo invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao pelo codigo invalida
Entao e retornado um erro de consulta de autorizacao por codigo



Cenario: Consultar autorizacao por intervalo de datas
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao por intervalo de datas
Entao e retornada autorizacoes por intervalo de data



Cenario: Consultar autorizacao por intervalo de datas invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao por intervalo de datas invalida
Entao e retornado um erro de consulta de autorizacao por data



Cenario: Consultar autorizacao por codigo de notificacao
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao por codigo de notificacao
Entao e retornada autorizacao por codigo de notificacao


Cenario: Consultar autorizacao por codigo de notificacao invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao por codigo de notificacao invalida
Entao e retornado um erro de consulta de autorizacao por notificacao



Cenario: Consultar autorizacao por codigo de referencia
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao por referencia
Entao e retornada as autorizacoes por referencia



Cenario: Consultar autorizacao por codigo de referencia invalida
Dado que esteja autenticado na api do pagseguro
Quando consulto uma autorizacao por referencia invalida
Entao e retornado um erro de consulta de autorizacao por codigo de referencia
