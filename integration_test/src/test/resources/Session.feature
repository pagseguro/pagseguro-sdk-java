#language: pt

Funcionalidade: Sessao

@session
Cenario: Criar sessao 
Dado que esteja autenticado na api do pagseguro
Quando crio uma sessao
Entao e retornado o codigo da sessao

