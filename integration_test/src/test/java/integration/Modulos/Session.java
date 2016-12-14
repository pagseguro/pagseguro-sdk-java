package integration.Modulos;

import cucumber.api.java.pt.Quando;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.session.CreatedSession;

public class Session {

//	Cenario: Criar sessao 
//	Dado que esteja autenticado na api do pagseguro
//	Quando crio uma sessao
//	Entao e retornado o codigo da sessao

	private static final String SELLER_EMAIL = "leonardo.lima@s2it.com.br";
	private static final String SELLER_TOKEN = "3A64F2083B124BD1986A128861C45794";
	
	private static final String SELLER_EMAIL_LUCIUS = "gabriel.pomin@s2it.com.br";
	private static final String SELLER_TOKEN_LUCIUS = "3C0E388C67134F90B21DD43DE4C77D9F";
	
@Quando("^crio uma sessao$")
	public void crio_uma_sessao() throws Throwable {
	
	final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL_LUCIUS,
            SELLER_TOKEN_LUCIUS), PagSeguroEnv.SANDBOX);
	
	CreatedSession createdSession = pagSeguro.sessions().createSeller();
	System.out.println(createdSession);
	

	
		
	
}
}
