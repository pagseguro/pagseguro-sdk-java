#language: pt

Funcionalidade: Requisicoes

Cenario: Checkout transparente credito
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente credito
Entao e retornado o codigo da transacao transparente credito



Cenario: Checkout transparente credito invalido
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente credito invalida
Entao e retornado um erro de transacao transparente credito



Cenario: Checkout transparente debito
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente debito
Entao e retornada url de transacao transparente debito



Cenario: Checkout transparente debito invalido
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente debito invalido
Entao e retornado o codigo da transacao transparente debito invalido



Cenario: Checkout transparente boleto
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente boleto
Entao e retornado o codigo da transacao transparente boleto



Cenario: Checkout transparente boleto invalido
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente boleto invalido
Entao e retornado o codigo da transacao transparente boleto invalido



Cenario: Checkout transparente cartao internacional
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente cartao internacional
Entao e retornado o codigo da transacao transparente cartao internacional



Cenario: Checkout transparente cartao internacional invalido
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento transparente cartao internacional invalido
Entao e retornado o codigo da transacao transparente cartao internacional invalido



Cenario: Requisicao de Pagamento
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento
Quando finalizo o pagamento pelo sandbox
Entao e comparado o resultado na finalizacao do checkout



Cenario: Requisicao de Pagamento invalida
Dado que esteja autenticado na api do pagseguro
Quando cria uma requisicao de pagamento invalida
Entao e retornado um erro de transacao



Cenario: Requisicao de Pagamento com assinatura
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento com assinatura
Entao e retornado o codigo da transacao com assinatura



Cenario: Requisicao de Pagamento com assinatura invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento com assinatura invalida
Entao e retornado um erro de transacao com assinatura



Cenario: Requisicao de cancelamento de transacao
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de cancelamento de transacao
Entao e retornado o codigo da transacao de cancelamento



Cenario: Requisicao de cancelamento de transacao invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de cancelamento invalida
Entao e retornado um erro na transacao de cancelamento




Cenario: Requisicao de estorno de pagamento
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de estorno de pagamento
Entao e retornado a resposta do servidor



Cenario: Requisicao de estorno de pagamento invalida
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de estorno de pagamento invalida
Entao e retornado um erro no estorno



Cenario: Requisicao de estorno parcial
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de estorno parcial
Entao e retornado a resposta do servidor estorno parcial




Cenario: Requisicao de estorno parcial invalido
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de estorno invalido
Entao e retornado a resposta do servidor estorno parcial invalido




Cenario: Consultar transacoes por codigo
Dado que esteja autenticado na api do pagseguro
Quando crio uma requisicao de pagamento
E pesquiso a transacao pelo codigo
Entao e retornado a transacao por codigo




Cenario: Consultar transacoes por codigo invalida
Dado que esteja autenticado na api do pagseguro
E pesquiso a transacao pelo codigo invalido
Entao e retornado um erro de consulta por codigo



Cenario: Consultar transacoes por intervalo de datas
Dado que esteja autenticado na api do pagseguro
E pesquiso a transacao por um intervalo de datas
Entao e retornado as transacoes por datas



Cenario: Consultar transacoes por intervalo de datas invalida 
Dado que esteja autenticado na api do pagseguro
E pesquiso a transacao por um intervalo de datas invalida
Entao e retornado um erro de consulta de transacoes por data



Cenario: Consultar transacoes abandonadas
Dado que esteja autenticado na api do pagseguro
Quando pesquiso uma transacao abandonada no pagseguro
Entao e retornado a transacao abandonada



Cenario: Consultar transacoes abandonadas invalida
Dado que esteja autenticado na api do pagseguro
Quando pesquiso uma transacao abandonada invalida
Entao e retornado um erro de consulta de transacoes abandonada



Cenario: Consultar transacoes por codigo de referencia
Dado que esteja autenticado na api do pagseguro
Quando pesquiso uma transacao por codigo de referencia
Entao e retornada a transacao pela referencia



Cenario: Consultar transacoes por codigo de referencia invalida
Dado que esteja autenticado na api do pagseguro
Quando pesquiso uma transacao por codigo de referencia invalida
Entao e retornada um erro de consulta de transacao pela referencia




