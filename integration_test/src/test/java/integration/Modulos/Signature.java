package integration.Modulos;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroInternalServerException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.preapproval.ChargedPreApproval;
import br.com.uol.pagseguro.api.preapproval.PreApprovalChargingBuilder;
import br.com.uol.pagseguro.api.preapproval.PreApprovalRegistrationBuilder;
import br.com.uol.pagseguro.api.preapproval.RegisteredPreApproval;
import br.com.uol.pagseguro.api.preapproval.cancel.CancelledPreApproval;
import br.com.uol.pagseguro.api.preapproval.cancel.PreApprovalCancellationBuilder;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalDetail;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalSummary;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class Signature {
	
	
	private static final String SELLER_EMAIL = "INFORMAR E-MAIL SELLER";
	private static final String SELLER_TOKEN = "INFORMAR SENHA SELLER";	
	private static final String EMAIL_SANDBOX = "INFORMAR E-MAIL SANDBOX";
	private static final String SENHA_SANDBOX = "INFORMAR SENHA SANDBOX";
	private static final String COMPRADOR_USER = "INFORMAR E-MAIL SANDBOX";
	private static final String COMPRADOR_SENHA = "INFORMAR SENHA SANDBOX";
	
	String url = null;
	String codigo = null;
	String codigoAssinatura = null;
	String error = null;
	
	WebDriver driver;
	
//	Cenario:  Criar requisicoes de assinaturas
//	Dado que esteja autenticado na api do pagseguro
//	Quando crio uma requisicao de assinatura como vendedor
//	Entao e retornado a url de redirecionamento	
	
	@Quando("^crio uma requisicao de assinatura como vendedor$")
		public void requisicao_assinatura_seller() throws Throwable {
		
		try {
		
	    	final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
	            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
	    
	    	RegisteredPreApproval registeredPreApproval = pagSeguro.preApprovals().register(
	                new PreApprovalRegistrationBuilder()
	                	.withCurrency(Currency.BRL)
	                	.withExtraAmount(BigDecimal.ONE)
	                	.withReference("REF_002")
	                	.withNotificationURL("www.testestes.com.br/notification/")
	                	.withRedirectURL("www.testestes.com.br/redirect")
	                	.withSender(new SenderBuilder()
	                		.withEmail("teste@teste.com")
	                		.withName("Comprador Teste")
	                		.withPhone(new PhoneBuilder()
	                				.withAreaCode("12")
	                				.withNumber("997398218")
	                				)
	                			)
	                	.withShipping(new ShippingBuilder()
	                		.withType(ShippingType.Type.SEDEX)
	                		.withCost(BigDecimal.TEN)
	                	.withAddress(new AddressBuilder()
                          	.withPostalCode("22299000")
                          	.withState(State.SP)
                          	.withCity("São Paulo")
                      
                          	.withDistrict("Pinheiros")
                          	.withStreet("Av Faria Lima")
	                		)
	                	)
	                	.withPreApproval(new PreApprovalBuilder()
	                		.withCharge("manual")
	                		.withName("Seguro contra roubo do Notebook Prata")
	                		.withDetails("Todo dia 28 será cobrado o valor de R$100,00 referente ao seguro contra roubo do Notebook Prata")
	                		.withAmountPerPayment(BigDecimal.TEN)
	                		.withMaxTotalAmount(new BigDecimal(100.00))
	                		.withMaxAmountPerPeriod(BigDecimal.TEN)
	                		.withMaxPaymentsPerPeriod(2)
	                		.withPeriod("monthly")
	   
	                		.withDateRange(new DateRangeBuilder()
	                				.between(
	                						new Date(),
                                     DatatypeConverter.parseDateTime("2017-09-27T23:59:59.000-03:00").getTime())
                      )
	                )
	              );
	    	
	    	 url = registeredPreApproval.getRedirectURL();
	    	 codigoAssinatura = registeredPreApproval.getPreApprovalCode();
	    	 System.out.println(url);
		}catch(Exception e) {
			e.printStackTrace();
			
		} 
	}
@Entao("^e retornado a url de redirecionamento$")
	public String retorno_codigo_assinatura_aprovada() throws Throwable {
	//DRIVER CHROME
	
//				System.setProperty(
//				"webdriver.chrome.driver"
//				, "src/test/resources/driver/win32/chromedriver.exe");
//				WebDriver driver = new ChromeDriver();
	
	// INFORMAR CAMINHO ONDE ESTÁ O ARQUIVO PHANTOMJS.EXE
	
	File file = new File("C:\\Users\\llima\\workspace\\teste\\src\\test\\resources\\driver\\win32\\phantomjs.exe");				
    System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
    WebDriver driver = new PhantomJSDriver();
     
	driver.manage().window().maximize();
	driver.get(url);
	
	//INFORMAR USUARIO DE COMPRADOR DE TESTE
	driver.findElement(By.id("user")).sendKeys(COMPRADOR_USER);
	driver.findElement(By.id("senderPassword")).sendKeys(COMPRADOR_SENHA);
	driver.findElement(By.className("mainActionButton")).click();
	Thread.sleep(5000);
	driver.findElement(By.id("walletChange")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("creditCardNumber")).sendKeys("4111111111111111");
	Thread.sleep(5000);
	driver.findElement(By.id("creditCardDueDate_Month")).sendKeys("12");
	driver.findElement(By.id("creditCardDueDate_Year")).sendKeys("30");	
	Thread.sleep(3000);		
	driver.findElement(By.id("creditCardHolderName")).sendKeys("Comprador de Teste");
	driver.findElement(By.id("creditCardCVV")).sendKeys("123");
	driver.findElement(By.id("holderCPF")).sendKeys("84815525269");
	driver.findElement(By.id("holderAreaCode")).sendKeys("16");
	Thread.sleep(3000);
	driver.findElement(By.id("holderPhone")).sendKeys("997398444");
	driver.findElement(By.id("holderBornDate")).sendKeys("25031991");
	driver.findElement(By.id("continueToPayment")).click();
	System.out.println("Assinatura Criada com Sucesso.");
	Thread.sleep(30000);
	
	 String codigoSignature = driver.findElement(By.id("transactionCode")).getText();
	 
	 System.out.println(codigoSignature);
	 
	 codigoSignature = codigoSignature.replace("-", "");
	 
	 driver.quit(); 
	 return codigoSignature;
}

//Cenario:  Criar requisicoes de assinaturas invalida
//Dado que esteja autenticado na api do pagseguro
//Quando crio uma requisicao de assinatura invalida como vendedor
//Entao e retornado um erro de assinatura
	
@Quando("^crio uma requisicao de assinatura invalida como vendedor$")
		public void requisicao_assinatura_invalida_vendedor() throws Throwable {
	try {
		
    	final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
    
    	RegisteredPreApproval registeredPreApproval = pagSeguro.preApprovals().register(
                new PreApprovalRegistrationBuilder()
                	.withCurrency(Currency.BRL)
                	.withExtraAmount(BigDecimal.ONE)
                	.withReference("REF_002")
                	.withNotificationURL("www.testeste.com.br/notification")
                	.withRedirectURL("www.testestes.com.br/redirect")
                	.withSender(new SenderBuilder()
                		.withEmail("teste@teste.com.br")
                		.withName("Comprador Teste")
                		.withPhone(new PhoneBuilder()
                				.withAreaCode("12")
                				.withNumber("997398922")
                				)
                			)
                	.withShipping(new ShippingBuilder()
                		.withType(ShippingType.Type.SEDEX)
                		.withCost(BigDecimal.TEN)
                	.withAddress(new AddressBuilder()
                      	.withPostalCode("22299000")
                      	.withState(State.SP)
                      	.withCity("São Paulo")
                  
                      	.withDistrict("Pinheiros")
                      	.withStreet("Av Faria Lima")
                		)
                	)
                	.withPreApproval(new PreApprovalBuilder()
                		.withCharge("manual")
                		.withDetails("Todo dia 28 será cobrado o valor de R$100,00 referente ao seguro contra roubo do Notebook Prata")
                		.withAmountPerPayment(BigDecimal.TEN)
                		.withMaxTotalAmount(new BigDecimal(100.00))
                		.withMaxAmountPerPeriod(BigDecimal.TEN)
                		.withMaxPaymentsPerPeriod(2)
                		.withPeriod("monthly")
   
                		.withDateRange(new DateRangeBuilder()
                				.between(
                						new Date(),
                                 DatatypeConverter.parseDateTime("2017-09-27T23:59:59.000-03:00").getTime())
                  )
                )
              );
    	
    	 String urlPreApproval = registeredPreApproval.getRedirectURL();
    		System.out.println(urlPreApproval);
	}catch(PagSeguroBadRequestException e){
		System.out.println(e.getErrors());
    	
    	ServerErrors serverErros = e.getErrors();
		ServerError serverError = serverErros.getErrors().iterator().next();
		
		assertEquals("preApprovalName is required", serverError.getMessage());
		assertEquals(new Integer(11088), serverError.getCode());;
		
	} 
}
	
@Entao("^e retornado um erro de assinatura$")
	public void retorno_erro_assinatura() throws Throwable {
	
}
	
//Cenario: Criar requisicoes de cancelamento de assinaturas
//Dado que esteja autenticado na api do pagseguro
//Quando crio uma requisicao de cancelamento de assinatura
//Entao e retornado o status da assinatura que foi cancelada
	
 @Quando("^crio uma requisicao de cancelamento de assinatura$")
  	public void _requisicao_cancelamento_assinatura() throws Throwable {  
	 requisicao_assinatura_seller();
	 retorno_codigo_assinatura_aprovada();
	 	  
	  try{	  
	  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
	            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
	  
	 CancelledPreApproval cancelPreApproval = pagSeguro.preApprovals().cancel(new PreApprovalCancellationBuilder()
	 																		.withCode(capturar_codigo_assinatura_e_ativar()) //informar um codigo de uma assinatura que esteja PAGAcapturar_codigo_assinatura()
			 );
	 
	 	codigo = cancelPreApproval.getTransactionStatus();
	  }catch(PagSeguroBadRequestException e){
			System.out.println(e.getErrors());			
		}	  
  }	  
 	public String capturar_codigo_assinatura_e_ativar() throws InterruptedException, IOException {		
 		//CHROME
 		
//		System.setProperty(
//		"webdriver.chrome.driver"
//		, "src/test/resources/driver/win32/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
 		
 		
// 		
 		File file = new File("..\\bin\\phantomjs.exe");	 //informar caminho do arquivo phantomJS			
 	     System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
 	     WebDriver driver = new PhantomJSDriver();
 	     
		driver.get("https://sandbox.pagseguro.uol.com.br/");
 		driver.manage().window().maximize();
 		Thread.sleep(3000);
		driver.findElement(By.id("email-login")).clear();
		driver.findElement(By.id("email-login")).sendKeys(EMAIL_SANDBOX);
		driver.findElement(By.id("pass-login")).clear();
		driver.findElement(By.id("pass-login")).sendKeys(SENHA_SANDBOX);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(6000);
		driver.findElement(By.linkText("Assinaturas")).click();
		Thread.sleep(10000);
		int index = 1;
		WebElement baseTable = driver.findElement(By.id("preapproval-list-table"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		tableRows.get(index).click();
	
		Thread.sleep(3000);
		
		String codigoSignature =driver.findElement(By.xpath("//*[@id='preapproval-details-summary']/div[1]/dl[2]/dd")).getText();
		
		driver.findElement(By.id("change-status-link")).click();
		Thread.sleep(3000);
		new Select(driver.findElement(By.cssSelector("#cboxLoadedContent > div.modal-content > form.change-status-form > #status"))).selectByVisibleText("Ativa");
		
		driver.findElement(By.cssSelector("#cboxLoadedContent > div > form > div.button-wrapper.center > button.submit-modal.pagseguro-button")).click();
		Thread.sleep(3000);
		
		driver.quit();
		
		System.out.println(codigoSignature);
	// TODO Auto-generated method stub
 		
 		return codigoSignature;
	
}
	@Entao("^e retornado o status da assinatura que foi cancelada$")
	  	public void status_cancelamento_assinatura () throws Throwable {
		  System.out.println(codigo);	  
	  }
	  
//	  Cenario: Criar requisicoes de cancelamento de assinaturas invalida
//	  Dado que esteja autenticado na api do pagseguro
//	  Quando crio uma requisicao de cancelamento de assinatura invalida
//	  Entao e retornado um erro de assinatura de cancelamento
	  
	  @Quando("^crio uma requisicao de cancelamento de assinatura invalida$")
	  	public void requisicao_cancelamento_invalida() throws Throwable {
		  
		  try{
			  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			  
			  	
			 CancelledPreApproval cancelPreApproval = pagSeguro.preApprovals().cancel(new PreApprovalCancellationBuilder()
			 																		.withCode("")
					 );
			 
			 	cancelPreApproval.getTransactionStatus();
			 	
			  }catch(Exception e ){
					assertEquals(true, e instanceof PagSeguroInternalServerException);	  	
			  }	 		  	
	  }
	  	  
@Entao("^e retornado um erro de assinatura de cancelamento$")

	public void retorno_erro_assinatura_cancelamento() throws Throwable {	
}
	  
//Cenario: Criar requisicoes de cobrança de assinaturas
//Dado que esteja autenticado na api do pagseguro
//Quando crio uma requisicao de cobranca de assinatura
//Entao e retornado a url de cobranca de assinatura
	  
@Quando("^crio uma requisicao de cobranca de assinatura$")
	  	public void autenticado_requisicao_cobranca_assinatura() throws Throwable {
				  
	requisicao_assinatura_seller();
	 retorno_codigo_assinatura_aprovada();	
		  try {
				
		    	final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
		            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
		    	    	
		    	ChargedPreApproval preApprovalCobranca = pagSeguro.preApprovals().charge(new PreApprovalChargingBuilder().addItem(new PaymentItemBuilder()
		    												.withId("001")
		    												.withDescription("Seguro contra quebra e roubo")
		    												.withAmount(new BigDecimal(5.00))
		    												.withQuantity(2)
		    												
		    												).withReference("REF-1111")
		    												 .withCode(capturar_codigo_assinatura_e_ativar()) //ASSINATURA TEM QUE ESTAR PAGA E SÓ PODE SER FEITO UMA VEZ											 
		    			);
		    	codigo = preApprovalCobranca.getTransactionCode();
		    	
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	
 }	
		  @Entao("^e retornado a url de cobranca de assinatura$")
		  	public void retorno_cobranca_de_assinatura() throws Throwable {
			  	System.out.println(codigo);

	  }	
		  
//		  Cenario: Criar requisicoes de cobranca de assinaturas invalida
//		  Dado que esteja autenticado na api do pagseguro
//		  Quando crio uma requisicao de cobranca invalida
//		  Entao e retornada um erro de assinatura de cobranca
		  
@Quando("^crio uma requisicao de cobranca invalida$")
		public void requisicao_cobranca_assinatura_invalida() throws Throwable {
			  try {
					
			    	final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			    		    	
			    	ChargedPreApproval preApprovalCobranca = pagSeguro.preApprovals().charge(new PreApprovalChargingBuilder().addItem(new PaymentItemBuilder()
			    												
			    												.withDescription("Seguro contra quebra e roubo")
			    												.withAmount(new BigDecimal(10.00))
			    												.withQuantity(1)
			    												
			    												).withReference("REF-1111")
			    												 .withCode("")								 
			    			);
			    	preApprovalCobranca.getTransactionCode();
			  
			  }		  
			  
			  catch(PagSeguroBadRequestException e){
					System.out.println(e.getErrors());
					
			    	ServerErrors serverErros = e.getErrors();
					ServerError serverError = serverErros.getErrors().iterator().next();
					
					assertEquals("pre-approval code is required.", serverError.getMessage());
					assertEquals(new Integer(17001), serverError.getCode());;
				}	
		  }
		  
@Entao("^e retornada um erro de assinatura de cobranca$")
	public void retorno_erro_assinatura_cobranca() throws Throwable {
	
}
// Cenario: Consultar assinaturas por codigo
// Dado que esteja autenticado na api do pagseguro
// Quando consulto uma assinatura
// Entao e retornado a assinatura consultada por codigo

@Quando("^consulto uma assinatura$")  
		public void consultar_assinatura_codigo() throws Throwable {

	requisicao_assinatura_seller();
	retorno_codigo_assinatura_aprovada();
			  try {
				  
				  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
				            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
				  
			PreApprovalDetail preApprovaldetail = pagSeguro.preApprovals().search().byCode(busca_codigo_assinatura());
			
				System.out.println(preApprovaldetail.getName());
				  	    	
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	
		  }
private String busca_codigo_assinatura() throws Throwable {
//	System.setProperty(
//	"webdriver.chrome.driver"
//	, "src/test/resources/driver/win32/chromedriver.exe");
//	WebDriver driver = new ChromeDriver();

		File file = new File("..\bin\\phantomjs.exe");	 //informar caminho do arquivo phantomJS			
	     System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
	     WebDriver driver = new PhantomJSDriver();	   
	     
	driver.get("https://sandbox.pagseguro.uol.com.br/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	driver.findElement(By.id("email-login")).clear();
	driver.findElement(By.id("email-login")).sendKeys(EMAIL_SANDBOX);
	driver.findElement(By.id("pass-login")).clear();
	driver.findElement(By.id("pass-login")).sendKeys(SENHA_SANDBOX);
	driver.findElement(By.id("login-button")).click();
	Thread.sleep(6000);
	driver.findElement(By.linkText("Assinaturas")).click();
	Thread.sleep(10000);
	int index = 1;
	WebElement baseTable = driver.findElement(By.id("preapproval-list-table"));
	List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
	tableRows.get(index).click();

	Thread.sleep(3000);
	
	String codigoSignatureBusca =driver.findElement(By.xpath("//*[@id='preapproval-details-summary']/div[1]/dl[2]/dd")).getText();

	driver.quit();
	
	return codigoSignatureBusca;
}
@Entao("^e retornado a assinatura consultada por codigo$")
	public void retorno_assinatura_codigo() throws Throwable {
		System.out.println(codigo);
}
	
//Cenario: Consultar assinaturas por codigo invalida
//Dado que esteja autenticado na api do pagseguro
//Quando crio uma consulta invalida por codigo
//Entao e retornado um erro de consulta de assinatura por codigo

@Quando("^crio uma consulta invalida por codigo$")
	public void consultar_uma_assinatura_codigo_invalida()throws Throwable {	
		 try {
				
		    	final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
		            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
		    
		    	PreApprovalDetail preApprovaldetail = pagSeguro.preApprovals().search().byCode("21221");
		 	
		    	preApprovaldetail.getStatus();
		    	
		 }catch(Exception e ){
				assertEquals(true, e instanceof PagSeguroInternalServerException);	  	
		  }	 		
	}
@Entao("^e retornado um erro de consulta de assinatura por codigo$")
	public void erro_consulta_por_codigo_assinatura() throws Throwable{
	
}
		 	  
//		Cenario: Consultar assinaturas por intervalo de datas
//		Dado que esteja autenticado na api do pagseguro
//		Quando consulto uma assinatura por intervalo de datas
//		Entao e retornado as assinaturas naquela data
		  
		  @Quando("^consulto uma assinatura por intervalo de datas$")
		  	public void autenticado_consulta_intervalo_datas() throws Throwable {
			  
			  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			  
			  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  
	            Date date = dateFormat.parse("28/10/2016");
	            Date date2 = dateFormat.parse("01/11/2016");
	            final DataList<? extends PreApprovalSummary> preApprovalDate =
	                    pagSeguro.preApprovals().search().byDateRange(
	                            new DateRangeBuilder().between(date, date2),
	                            1,
	                            10);
			  
	           codigo = preApprovalDate.toString();
			  
		  }
	
		  @Entao("^e retornado as assinaturas naquela data$")
		  	public void retornado_assinatura_data() throws Throwable {
			  	System.out.println(codigo);
			  
		  }
		  
//		  Cenario: Consultar assinaturas por intervalo de datas invalida
//		  Dado que esteja autenticado na api do pagseguro
//		  Quando consulto uma assinatura por intervalo de datas invalida
//		  Entao e retornado um erro de consulta por data 
		  
		  @Quando("^consulto uma assinatura por intervalo de datas invalida$")
		  	public void autenticado_consulta_intervalo_datas_invalida() throws Throwable {
			  
		try {	  
			  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			  
			  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  
	            Date date = dateFormat.parse("03/11/2016");
	            Date date2 = dateFormat.parse("02/11/2016");
	            final DataList<? extends PreApprovalSummary> preApprovalDate =
	                    pagSeguro.preApprovals().search().byDateRange(
	                            new DateRangeBuilder().between(date, date2),
	                            1,
	                            10);
			  
	            System.out.println(preApprovalDate);
			  
		  }catch(PagSeguroBadRequestException e){
				System.out.println(e.getErrors());
		    	
		    	ServerErrors serverErros = e.getErrors();
				ServerError serverError = serverErros.getErrors().iterator().next();
				
				assertEquals("initialDate must be lower than or equal finalDate.", serverError.getMessage());
				assertEquals(new Integer(13007), serverError.getCode());;
				
			}	
		  }
		
@Entao("^e retornado um erro de consulta por data$")
		  	public void retornado_erro_assinatura() throws Throwable {
			  
		  } 
//Cenario: Consultar assinaturas por intervalo de dias
//Dado que esteja autenticado na api do pagseguro
//Quando consulto uma assinatura por intervalo de dias
//Entao e retornado as assinaturas naqueles dias
		  
@Quando("^consulto uma assinatura por intervalo de dias$")
		public void consulta_intervalo_dias () throws Throwable {
			  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			  
	            final DataList<? extends PreApprovalSummary> preApprovalDay =
	                    pagSeguro.preApprovals().search().byInterval(30);
			  
	            codigo = preApprovalDay.toString();
		  }
		  
		  @Entao("^e retornado as assinaturas naqueles dias$")
		  	public void retornado_assinaturas_naqueles_dias() throws Throwable{
			  	System.out.println(codigo);
			  
		  }
		  
//		  Cenario: Consultar assinaturas por intervalo de dias invalida
//		  Dado que esteja autenticado na api do pagseguro
//		  Quando consulto uma assinatura por intervalo de dias invalida
//		  Entao e retornado um erro de consulta por dias
		  		  
@Quando("^consulto uma assinatura por intervalo de dias invalida$")
		 public void consulta_intervalo_dias_invalida() throws Throwable {
			  
			  try{
					  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
					            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
					  

			            final DataList<? extends PreApprovalSummary> preApprovalDay =
			                    pagSeguro.preApprovals().search().byInterval(200);
					  
			            System.out.println(preApprovalDay);
					  
				  }catch(PagSeguroBadRequestException e){
						System.out.println(e.getErrors());
				    	
				    	ServerErrors serverErros = e.getErrors();
						ServerError serverError = serverErros.getErrors().iterator().next();
						
						assertEquals("interval must be between 1 and 30.", serverError.getMessage());
						assertEquals(new Integer(13018), serverError.getCode());;						
					}		  
		  }		  
@Entao("^e retornado um erro de consulta por dias$")
	public void retorno_erro_por_dia() throws Throwable {
	
}
		    
//  Cenario: Consultar assinaturas por codigo de notificacao
//  Dado que esteja autenticado na api do pagseguro
//  Quando consulto uma assinatura por codigo de notificacoes
//  Entao e retornado as assinaturas com o codigo de notificacao
		  
@Quando("^consulto uma assinatura por codigo de notificacoes$")
		public void consulta_notificacao_assinatura() throws Throwable { 
		
 requisicao_assinatura_seller();
 retorno_codigo_assinatura_aprovada();

 
			  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			  
			  PreApprovalDetail preApprovaldetail = pagSeguro.preApprovals().search().byNotificationCode(buscar_codigo_notificacao());
			  
			  codigo = preApprovaldetail.getCode();
		  }
		  
public String buscar_codigo_notificacao() throws InterruptedException {
	
//	System.setProperty(
//	"webdriver.chrome.driver"
//	, "src/test/resources/driver/win32/chromedriver.exe");

//driver = new ChromeDriver();
	
	
	File file = new File("bin\\phantomjs.exe");	 //informar caminho do arquivo phantomJS			
    System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
    WebDriver driver = new PhantomJSDriver();
	
	

	driver.get("https://sandbox.pagseguro.uol.com.br/");
	driver.manage().window().maximize();
	Thread.sleep(3000);
	driver.findElement(By.id("email-login")).clear();
	driver.findElement(By.id("email-login")).sendKeys(EMAIL_SANDBOX);
	driver.findElement(By.id("pass-login")).clear();
	driver.findElement(By.id("pass-login")).sendKeys(SENHA_SANDBOX);
	driver.findElement(By.id("login-button")).click();
	Thread.sleep(6000);
	driver.findElement(By.linkText("Assinaturas")).click();
	Thread.sleep(10000);
	int index = 1;
	
	WebElement baseTable = driver.findElement(By.id("preapproval-list-table"));
	List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
	tableRows.get(index).click();
	Thread.sleep(3000);
	int notify = 0;
	WebElement baseTablenotify = driver.findElement(By.className("pagseguro-table"));
	List<WebElement> tableRowsNotify = baseTablenotify.findElements(By.cssSelector("#nas-data > div.nas-data-area > table > tbody > tr > td.col-code"));
	String codigoNotification = tableRowsNotify.get(notify).findElement(By.className("nas-code")).getText();
	
	System.out.println(codigoNotification);

	return codigoNotification;
}
@Entao("^e retornado as assinaturas com o codigo de notificacao$")
	public void retorno_codigo_notificacao() throws Throwable {
		System.out.println(codigo);
}

//		  Cenario: Consultar assinaturas por código de notificacao invalida
//		  Dado que esteja autenticado e consultar uma assinatura por codigo de notificacoes invalida
//		  Entao e retornado um erro de consulta por notificacao
		  
@Quando("^consulto uma assinatura por codigo de notificacoes invalida$")
		public void consulta_notificacao_assinatura_invalida() throws Throwable { 
			
			 try { 
			  final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL,
			            SELLER_TOKEN), PagSeguroEnv.SANDBOX);
			  
			  PreApprovalDetail preApprovaldetail = pagSeguro.preApprovals().search().byNotificationCode("566B9CAD4B044B04DA77742F5FA653E1AB24");
			  
			  preApprovaldetail.getCode();
			  
		  }catch(Exception e) {
			  assertEquals(true, e instanceof PagSeguroInternalServerException);	 
				
			}	
		  
		  }
@Entao("^e retornado um erro de consulta por notificacao$")
	public void retorno_erro_consulta_notificacao() throws Throwable {
		
}
}