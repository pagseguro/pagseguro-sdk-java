package integration.Modulos;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.DatatypeConverter;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.ShippingType.Type;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.BankBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.CreditCardBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DocumentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.HolderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.InstallmentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ReceiverBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SplitBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.transaction.CancelledTransaction;
import br.com.uol.pagseguro.api.transaction.RefundedTransaction;
import br.com.uol.pagseguro.api.transaction.TransactionIdentifyBuilder;
import br.com.uol.pagseguro.api.transaction.TransactionRefundingBuilder;
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder;
import br.com.uol.pagseguro.api.transaction.register.SplitPaymentRegistrationBuilder;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;



import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import driver.Driver;

public class Transactions {

	String SELLER_EMAIL = "leonardo.lima@s2it.com.br";
	String SELLER_TOKEN = "3A64F2083B124BD1986A128861C45794";
	private static final String APP_ID = "app6426009352";
	private static final String APP_KEY = "1F0EFAF6C5C5D0299451EFAEA226B087";
	private static final String APP_ID_LUCIUS = "app6426009352";
	private static final String APP_KEY_LUCIUS = "1F0EFAF6C5C5D0299451EFAEA226B087";
	String EMAIL_SANDBOX = null;
	String SENHA_SANDBOX = null;

	String codigo = null;
	String codigoRequest = null;
	String url = null;
	String pagSeguro = null;

	WebDriver driver;

	//
	@Before
	public void setUp() {
		driver = Driver.getInstance();
	}

	//
	// Cenario: Checkout transparente credito
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente credito
	// Entao e retornado o codigo da transacao transparente credito
	// //
	@Dado("^que esteja autenticado na api do pagseguro$")
	public void esteja_autenticado_pagseguro() throws Throwable {

		SELLER_EMAIL = "leonardo.lima@s2it.com.br";
		SELLER_TOKEN = "3A64F2083B124BD1986A128861C45794";
		EMAIL_SANDBOX = "leonardo.lima@s2it.com.br";
		SENHA_SANDBOX = "Leiteninho12";
	}

	//
	@Quando("^crio uma requisicao de pagamento transparente credito$")
	public void crio_requisicao_pagamento_transparente_credito()
			throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			TransactionDetail transactionCard = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo3@sandbox.pagseguro.com.br")
													.withHash(
															"023f5c806494371168f89181f1ea32b990235be5a8cc1b690d8f748396d4261c")// alterar
																																// conforme
																																// a
																																// session
									)
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00))))
					.withCreditCard(
							new CreditCardBuilder()
									.withBillingAddress(
											new AddressBuilder()
													.withStreet("Rua Armandao")
													.withNumber("1233")
													.withComplement("teste")
													.withDistrict("Centro")
													.withPostalCode("14800360")
													.withCity("Araraquara")
													.withState(State.SP)
													.withCountry("BRA"))
									.withInstallment(
											new InstallmentBuilder()
													.withQuantity(2)
													.withValue(
															new BigDecimal(
																	60.00))
													.withNoInterestInstallmentQuantity(
															2))
									.withHolder(
											new HolderBuilder()
													.addDocument(
															new DocumentBuilder()
																	.withType(
																			DocumentType.CPF)
																	.withValue(
																			"04570568351"))
													.withName(
															"Leonardo Camargo")
													.withBithDate(
															new SimpleDateFormat(
																	"dd/MM/yyyy")
																	.parse("25/03/1996"))
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968")))
									.withToken(
											"88f7fde7b4214e62bf9150aa0c2a06db"));

			codigo = transactionCard.getCode();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
		}

	}

	@Entao("^e retornado o codigo da transacao transparente credito$")
	public void retorno_codigo_transparente() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Checkout transparente invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente invalida
	// Entao e retornado um erro de transacao transparente

	@Quando("^crio uma requisicao de pagamento transparente credito invalida$")
	public void requisicao_transparente_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			// System.out.println(pagSeguro.installments().list("visa", new
			// BigDecimal(120), 2));

			TransactionDetail transactionCard = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo3@sandbox.pagseguro.com.br")

									)
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00))))
					.withCreditCard(
							new CreditCardBuilder()
									.withBillingAddress(
											new AddressBuilder()
													.withStreet("Rua Armandao")
													.withNumber("1233")
													.withComplement("teste")
													.withDistrict("Centro")
													.withPostalCode("14800360")
													.withCity("Araraquara")
													.withState(State.SP)
													.withCountry("BRA"))
									.withInstallment(
											new InstallmentBuilder()
													.withQuantity(2)
													.withValue(
															new BigDecimal(
																	60.00))
													.withNoInterestInstallmentQuantity(
															2))
									.withHolder(
											new HolderBuilder()
													.addDocument(
															new DocumentBuilder()
																	.withType(
																			DocumentType.CPF)
																	.withValue(
																			"04570568351"))
													.withName(
															"Leonardo Camargo")
													.withBithDate(
															new SimpleDateFormat(
																	"dd/MM/yyyy")
																	.parse("25/03/1996"))
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968")))
									.withToken(
											"88f7fde7b4214e62bf9150aa0c2a06db"));

			System.out.println(transactionCard);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("sender hash is required.", serverError.getMessage());
			assertEquals(new Integer(53150), serverError.getCode());

		}
	}

	@Entao("^e retornado um erro de transacao transparente credito$")
	public void erro_transacao_transparente() throws Throwable {

	}

	// Cenario: Checkout transparente debito
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente debito
	// Entao e retornado o codigo da transacao transparente debito

	@Quando("^crio uma requisicao de pagamento transparente debito$")
	public void requisicao_transparente_debito() throws Throwable {
		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			// System.out.println(pagSeguro.installments().list("visa", new
			// BigDecimal(120), 2));

			TransactionDetail transactionDebit = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo3@sandbox.pagseguro.com.br")
													.withHash(
															"cecf34f5687492fd58c23e0e86f16f99b5a7769a4b975281afd52ca1f6c1d070"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00))))
					.withOnlineDebit(
							new BankBuilder().withName(BankName.Name.BRADESCO));

			url = transactionDebit.getPaymentLink();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
			
		}

	}

	@Entao("^e retornado o codigo da transacao transparente debito$")
	public void retorno_codigo_debito_transparente() throws Throwable {

		System.out.println(url);

	}

	// Cenario: Checkout transparente debito invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando
	// Entao e retornado o codigo da transacao transparente debito invalido

	@Quando("^crio uma requisicao de pagamento transparente debito invalido$")
	public void requisicao_transparente_debito_invalido() throws Throwable {
		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			// System.out.println(pagSeguro.installments().list("visa", new
			// BigDecimal(120), 2));

			TransactionDetail transactionDebitInvalid = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)

													.withHash(
															"d3ff88e019e9a1b064bda1df6774b270567d9e1db060b9fe624c5ab41024419d"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00))))
					.withOnlineDebit(
							new BankBuilder().withName(BankName.Name.BRADESCO));

			System.out.println(transactionDebitInvalid);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("sender email is required.", serverError.getMessage());
			assertEquals(new Integer(53010), serverError.getCode());
		}

	}

	@Entao("^e retornado o codigo da transacao transparente debito invalido$")
	public void retorno_codigo_transparente_debito_invalido() throws Throwable {

	}

	// Cenario: Checkout transparente boleto
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente boleto
	// Entao e retornado o codigo da transacao transparente boleto

	@Quando("^crio uma requisicao de pagamento transparente boleto$")
	public void requisicao_pagamento_transparente_boleto() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			// System.out.println(pagSeguro.installments().list("visa", new
			// BigDecimal(120), 2));

			TransactionDetail transactionBoleto = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo36@sandbox.pagseguro.com.br")
													.withHash(
															"c88695ae96bc78d8f41f7051074c21c058fe588cd2cef644769be00b7c5fdb94"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00))))
					.withBankSlip();

			codigo = transactionBoleto.getPaymentLink();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
		}

	}

	@Entao("^e retornado o codigo da transacao transparente boleto$")
	public void retorno_transacao_transparente_boleto() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Checkout transparente boleto invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente boleto invalido
	// Entao e retornado o codigo da transacao transparente boleto invalido

	@Quando("^crio uma requisicao de pagamento transparente boleto invalido$")
	public void requisicao_transparente_boleto_invalido() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			TransactionDetail transactionBoleto = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo36@sandbox.pagseguro.com.br")
													.withHash(
															"d3ff88e019e9a1b064bda1df6774b270567d9e1db060b9fe624c5ab41024419d"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00))))
					.withBankSlip();

			System.out.println(transactionBoleto.getCode());

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("item id is required.", serverError.getMessage());
			assertEquals(new Integer(53070), serverError.getCode());
		}

	}

	@Entao("^e retornado o codigo da transacao transparente boleto invalido$")
	public void codigo_transacao_boleto_invalido() throws Throwable {

	}

	// Cenario: Checkout transparente cartao internacional
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente cartao internacional
	// Entao e retornado o codigo da transacao transparente cartao internacional

	@Quando("^crio uma requisicao de pagamento transparente cartao internacional$")
	public void requisicao_transparente_cartao_internacional() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			TransactionDetail transactionInternational = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00))

									)
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")

									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")

													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo3@sandbox.pagseguro.com.br")
													.withHash(
															"1a6e64342e9daaa5c461638c67a6e5e0c6bf3660f0a846742767b58014fb4116"))

									.withShipping(
											new ShippingBuilder()

													.withAddress(
															new AddressBuilder()

																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)

													.withCost(
															new BigDecimal(
																	20.00))))
					.withCreditCard(
							new CreditCardBuilder()
									.withBillingAddress(
											new AddressBuilder()
													.withStreet("Rua Armandao")
													.withNumber("1233")
													.withComplement("teste")
													.withDistrict("Centro")
													.withPostalCode("14800360")
													.withCity("Araraquara")
													.withState(State.SP)
													.withCountry("BRA"))
									.withInstallment(
											new InstallmentBuilder()
													.withQuantity(2)
													.withValue(
															new BigDecimal(
																	60.00))
													.withNoInterestInstallmentQuantity(
															2))
									.withHolder(
											new HolderBuilder()
													.addDocument(
															new DocumentBuilder()
																	.withType(
																			DocumentType.CPF)
																	.withValue(
																			"04570568351"))
													.withName(
															"Leonardo Camargo")
													.withBithDate(
															new SimpleDateFormat(
																	"dd/MM/yyyy")
																	.parse("25/03/1996"))
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968")))
									.withToken(
											"b3aba845b22b43a4812f498f1686b68e"));

			codigo = transactionInternational.getPaymentLink();
			System.out.println(transactionInternational);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

		}
	}

	@Entao("^e retornado o codigo da transacao transparente cartao internacional$")
	public void retorno_codigo_transparente_cartao_internacional()
			throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Checkout transparente cartao internacional invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento transparente cartao internacional
	// invalido
	// Entao e retornado o codigo da transacao transparente cartao internacional
	// invalido

	@Quando("^crio uma requisicao de pagamento transparente cartao internacional invalido$")
	public void requisicao_transparente_internacional_invalido()
			throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			TransactionDetail transactionInternational = pagSeguro
					.transactions()
					.register(
							new DirectPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Notebook Preto")
													.withAmount(
															new BigDecimal(
																	100.00))

									)
									.withNotificationURL(
											"www.lojateste.com.br/notification")
									.withReference("DIRECT_PAYMENT")

									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")

													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"981284174")

													)
													.withEmail(
															"lcamargo3@sandbox.pagseguro.com.br")
													.withHash(
															"b51c1a7f3f155bd287e9aae1b800fb8a1583cea134cd2f2724e3f862efa83170"))

									.withShipping(
											new ShippingBuilder()

													.withAddress(
															new AddressBuilder()

																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)

													.withCost(
															new BigDecimal(
																	20.00))))
					.withCreditCard(
							new CreditCardBuilder()
									.withBillingAddress(
											new AddressBuilder()
													.withStreet("Rua Armandao")
													.withNumber("1233")
													.withComplement("teste")
													.withDistrict("Centro")
													.withPostalCode("14800360")
													.withCity("Araraquara")
													.withState(State.SP)
													.withCountry("BRA"))
									.withInstallment(
											new InstallmentBuilder()
													.withQuantity(2)
													.withValue(
															new BigDecimal(
																	60.00))
													.withNoInterestInstallmentQuantity(
															2))
									.withHolder(
											new HolderBuilder()
													.addDocument(
															new DocumentBuilder()
																	.withType(
																			DocumentType.CPF)
																	.withValue(
																			"04570568351"))
													.withName(
															"Leonardo Camargo")
													.withBithDate(
															new SimpleDateFormat(
																	"dd/MM/yyyy")
																	.parse("25/03/1996"))
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968")))
									.withToken(
											"bf711d2f449d4b308526dd963227f5f3"));

			codigo = transactionInternational.getPaymentLink();
			System.out.println(transactionInternational);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("item quantity is required.", serverError.getMessage());
			assertEquals(new Integer(53074), serverError.getCode());
		}

	}

	@Entao("^e retornado o codigo da transacao transparente cartao internacional invalido$")
	public void erro_codigo_cartao_internacional_invalido() throws Throwable {

	}

	// Cenario: Checkout transparente split boleto
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento boleto split
	// Entao e retornado o codigo do boleto split

	@Quando("^crio uma requisicao de pagamento boleto split$")
	public void crio_requisicao_boleto_split() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(APP_ID, APP_KEY),
					PagSeguroEnv.SANDBOX);

			TransactionDetail bankSlipSplitTransaction = pagSeguro
					.transactions()
					.register(
							new SplitPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription("Notebook")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	150.00)))
									.withNotificationURL(
											"www.teste.com.br/notification")
									.withReference("LIBJAVA_DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968"))
													.withEmail(
															"leonardo@sandbox.pagseguro.com.br")
													.withHash(
															"a431a7cb564d8284b085ec28f599641f8603aaf925e123b6dba11d99840eeb3d"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"teste")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00)))
									.addReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB12FEDF9EBD994931A287FCF983EEC240")
													.withSplit(
															new SplitBuilder()
																	.withAmount(
																			new BigDecimal(
																					20))
																	.withFeePercent(
																			new BigDecimal(
																					50))))
									.addReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB93E1B187F5164BF0A4AE4A992F9738A9")
													.withSplit(
															new SplitBuilder()
																	.withAmount(
																			new BigDecimal(
																					20))
																	.withFeePercent(
																			new BigDecimal(
																					50)))))
					.withBankSlip();

			codigo = bankSlipSplitTransaction.getPaymentLink();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
		}

	}

	@Entao("^e retornado o codigo do boleto split$")
	public void retorno_codigo_boleto_split() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Checkout transparente split boleto invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento boleto split invalido
	// Entao e retornado o erro do boleto split invalido

	@Quando("^crio uma requisicao de pagamento boleto split invalido$")
	public void requisicao_boleto_split_invalido() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(APP_ID, APP_KEY),
					PagSeguroEnv.SANDBOX);

			TransactionDetail bankSlipSplitTransaction = pagSeguro
					.transactions()
					.register(
							new SplitPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription("Notebook")
													.withAmount(
															new BigDecimal(
																	150.00)))
									.withNotificationURL(
											"www.teste.com.br/notification")
									.withReference("LIBJAVA_DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968"))
													.withEmail(
															"leonardo@sandbox.pagseguro.com.br")
													.withHash(
															"07d27488ad064660cd7a639a1dfb875df1d07312d9bdf5eaafb31c6104c36d76"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"teste")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	20.00)))
									.withPrimaryReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB12FEDF9EBD994931A287FCF983EEC240")
													.withSplit(
															new SplitBuilder()
																	.withAmount(
																			new BigDecimal(
																					(20)))
																	.withFeePercent(
																			new BigDecimal(
																					50))))
									.addReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB93E1B187F5164BF0A4AE4A992F9738A9")
													.withSplit(
															new SplitBuilder()
																	.withAmount(
																			new BigDecimal(
																					(20)))
																	.withFeePercent(
																			new BigDecimal(
																					50)))))
					.withBankSlip();

			System.out.println(bankSlipSplitTransaction);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("item quantity is required.", serverError.getMessage());
			assertEquals(new Integer(53074), serverError.getCode());
			// assertEquals ("qualquer coisa" ,"teste");
		}

	}

	@Entao("^e retornado o erro do boleto split invalido$")
	public void retorno_erro_boleto_split_invalido() throws Throwable {

	}

	// Cenario: Checkout transparente split cartao de credito
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento cartao de credito split
	// Entao e retornado o codigo da transacao do cartao de credito split

	@Quando("^crio uma requisicao de pagamento cartao de credito split$")
	public void requisicao_cartao_credito_split() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(APP_ID, APP_KEY),
					PagSeguroEnv.SANDBOX);

			TransactionDetail creditCardSplitTransaction = pagSeguro
					.transactions()
					.register(
							new SplitPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription("Notebook")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.teste.com.br/notification")
									.withReference("LIBJAVA_DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968"))
													.withEmail(
															"leonardo@sandbox.pagseguro.com.br")
													.withHash(
															"6f3fc5e20b8869b30271c61f5711ad38c10d483c702f131fc41e65d5ed64a9f8"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"casa 2")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	10.00)))
									.withPrimaryReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB12FEDF9EBD994931A287FCF983EEC240")
													.withSplit(
															new SplitBuilder()
																	.withFeePercent(
																			new BigDecimal(
																					50))
																	.withAmount(
																			new BigDecimal(
																					20)))))
					.withCreditCard(
							new CreditCardBuilder()
									.withBillingAddress(
											new AddressBuilder()
													.withStreet("Rua teste")
													.withNumber("1233")
													.withComplement(
															"complemento")
													.withDistrict("Centro")
													.withPostalCode("14800140")
													.withCity("Araraquara")
													.withState(State.PA)
													.withCountry("BRA"))
									.withInstallment(
											new InstallmentBuilder()
													.withQuantity(2)
													.withValue(
															new BigDecimal(
																	55.00))
													.withNoInterestInstallmentQuantity(
															(2)))
									.withHolder(
											new HolderBuilder()
													.addDocument(
															new DocumentBuilder()
																	.withType(
																			DocumentType.CPF)
																	.withValue(
																			"43176359845"))
													.withName(
															"Leonardo Camargo de Lima")
													.withBithDate(
															new SimpleDateFormat(
																	"dd/MM/yyyy")
																	.parse("25/03/1996"))
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"94")
																	.withNumber(
																			"981284174")))

									.withToken(
											"f769efa8714d4082bf22c62f147e8992"));

			codigo = creditCardSplitTransaction.getCode();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
		}

	}

	@Entao("^e retornado o codigo da transacao do cartao de credito split$")
	public void retorno_codigo_cartao_credito_split() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Checkout transparente split cartao de credito invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento cartao de credito split invalido
	// Entao e retornado o erro da transacao do cartao de credito split invalido

	@Quando("^crio uma requisicao de pagamento cartao de credito split invalido$")
	public void pagamento_credito_split_invalido() throws Throwable {
		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(APP_ID, APP_KEY),
					PagSeguroEnv.SANDBOX);

			TransactionDetail creditCardSplitTransaction = pagSeguro
					.transactions()
					.register(
							new SplitPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withDescription("Notebook")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	100.00)))
									.withNotificationURL(
											"www.teste.com.br/notification")
									.withReference("LIBJAVA_DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968"))
													.withEmail(
															"leonardo@sandbox.pagseguro.com.br")
													.withHash(
															"PUB12FEDF9EBD994931A287FCF983EEC240"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"casa 2")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800360")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	10.00)))
									.withPrimaryReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB12FEDF9EBD994931A287FCF983EEC240")
													.withSplit(
															new SplitBuilder()
																	.withFeePercent(
																			new BigDecimal(
																					50))
																	.withAmount(
																			new BigDecimal(
																					20)))))
					.withCreditCard(
							new CreditCardBuilder()
									.withBillingAddress(
											new AddressBuilder()
													.withStreet("Rua teste")
													.withNumber("1233")
													.withComplement(
															"complemento")
													.withDistrict("Centro")
													.withPostalCode("14800140")
													.withCity("Araraquara")
													.withState(State.PA)
													.withCountry("BRA"))
									.withInstallment(
											new InstallmentBuilder()
													.withQuantity(2)
													.withValue(
															new BigDecimal(
																	55.00))
													.withNoInterestInstallmentQuantity(
															(2)))
									.withHolder(
											new HolderBuilder()
													.addDocument(
															new DocumentBuilder()
																	.withType(
																			DocumentType.CPF)
																	.withValue(
																			"43176359845"))
													.withName("Leonardo")
													.withBithDate(
															new SimpleDateFormat(
																	"dd/MM/yyyy")
																	.parse("25/03/1996"))
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"94")
																	.withNumber(
																			"981284174")))
									.withToken(
											"f2fb8164b5ad4af2a949a87309733272"));

			System.out.println(creditCardSplitTransaction);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("item id is required.", serverError.getMessage());
			assertEquals(new Integer(53070), serverError.getCode());
			// assertEquals ("qualquer coisa" ,"teste");
		}
	}

	@Entao("^e retornado o erro da transacao do cartao de credito split invalido$")
	public void retorno_erro_cartao_credito_split_invalido() throws Throwable {

	}

	// Cenario: Checkout transparente split debito
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento debito split
	// Entao e retornado o codigo da transacao debito split

	@Quando("^crio uma requisicao de pagamento debito split$")
	public void requisicao_debito_split() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(APP_ID, APP_KEY),
					PagSeguroEnv.SANDBOX);

			TransactionDetail onlineDebitSplirr =

			pagSeguro
					.transactions()
					.register(
							new SplitPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("001")
													.withDescription(
															"Leonardo camargo")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	150.00)))
									.withNotificationURL(
											"www.teste.com.br/notification")
									.withReference("LIBJAVA_DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968"))
													.withEmail(
															"leonardo@sandbox.pagseguro.com.br")
													.withHash(
															"1835e9585b8f177db712873db9c09ae7477e4e96bc0941f3b007e3d8a6c2f353"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800140")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	10.00)))
									.withPrimaryReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB12FEDF9EBD994931A287FCF983EEC240")
													.withSplit(
															new SplitBuilder()
																	.withFeePercent(
																			new BigDecimal(
																					50))
																	.withAmount(
																			new BigDecimal(
																					20))))

					)
					.withOnlineDebit(
							new BankBuilder()
									.withName(BankName.Name.BANCO_DO_BRASIL));

			codigo = onlineDebitSplirr.getPaymentLink();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
		}
	}

	@Entao("^e retornado o codigo da transacao debito split$")
	public void retorno_codigo_transacao_debito_split() throws Throwable {
		System.out.println(codigo);
	}

	// Cenario: Checkout transparente split debito invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento debito split invalido
	// Entao e retornadebito split invalidodo o erro da transacao

	@Quando("^crio uma requisicao de pagamento debito split invalido$")
	public void requisicao_pagamento_debito_split_invalido() throws Throwable {
		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(APP_ID, APP_KEY),
					PagSeguroEnv.SANDBOX);

			TransactionDetail onlineDebitSplitTransaction =

			pagSeguro
					.transactions()
					.register(
							new SplitPaymentRegistrationBuilder()
									.withPaymentMode("default")
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withDescription(
															"Leonardo camargo")
													.withQuantity(1)
													.withAmount(
															new BigDecimal(
																	150.00)))
									.withNotificationURL(
											"www.teste.com.br/notification")
									.withReference("LIBJAVA_DIRECT_PAYMENT")
									.withSender(
											new SenderBuilder()
													.withName(
															"Leonardo Camargo")
													.withCPF("43176359845")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"16")
																	.withNumber(
																			"997398968"))
													.withEmail(
															"leonardo@sandbox.pagseguro.com.br")
													.withHash(
															"07d27488ad064660cd7a639a1dfb875df1d07312d9bdf5eaafb31c6104c36d76"))
									.withShipping(
											new ShippingBuilder()
													.withAddress(
															new AddressBuilder()
																	.withStreet(
																			"Rua teste")
																	.withNumber(
																			"1233")
																	.withComplement(
																			"complemento")
																	.withDistrict(
																			"Centro")
																	.withPostalCode(
																			"14800140")
																	.withCity(
																			"Araraquara")
																	.withState(
																			State.SP)
																	.withCountry(
																			"BRA"))
													.withType(
															ShippingType.Type.SEDEX)
													.withCost(
															new BigDecimal(
																	10.00)))
									.withPrimaryReceiver(
											new ReceiverBuilder()
													.withPublicKey(
															"PUB12FEDF9EBD994931A287FCF983EEC240")
													.withSplit(
															new SplitBuilder()
																	.withFeePercent(
																			new BigDecimal(
																					50))
																	.withAmount(
																			new BigDecimal(
																					20))))

					)
					.withOnlineDebit(
							new BankBuilder()
									.withName(BankName.Name.BANCO_DO_BRASIL));
			System.out.println(onlineDebitSplitTransaction);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("item id is required.", serverError.getMessage());
			assertEquals(new Integer(53070), serverError.getCode());
			// assertEquals ("qualquer coisa" ,"teste");
		}
	}

	@Entao("^e retornado o erro da transacao debito split invalido$")
	public void retorno_debito_split_invalido() throws Throwable {

	}

	// Cenario: Requisicao de Pagamento
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento
	// Entao e retornado o codigo da transacao

	// @Dado("^que esteja autenticado na api do pagseguro$")
	// public void autenticado_pagseguro() throws Throwable{
	//
	// }

	@Quando("^crio uma requisicao de pagamento$")
	public void requisicao_pagamento() throws Throwable {

		final PagSeguro pagSeguro = PagSeguro.instance(
				Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
				PagSeguroEnv.SANDBOX);

		RegisteredCheckout registeredCheckout = pagSeguro
				.checkouts()
				.register(
						new CheckoutRegistrationBuilder()
								.withCurrency(Currency.BRL)
								.addItem(
										new PaymentItemBuilder()
												.withId("49099")
												.withAmount(
														new BigDecimal(10.00))
												.withDescription("Item 1")
												.withQuantity(10)
												.withShippingCost(
														new BigDecimal(20))
												.withWeight(10))

								.withSender(
										new SenderBuilder()
												.withHash(
														"bc947e397943de0d32325f669b64c039d67d738196b3aa0ea37787fc86a74e0c")
												.withCPF("29182659427")
												.withEmail(
														"c88018633641030893019@sandbox.pagseguro.com.br")
												.withName("Luiz Roos")
												.withPhone(
														new PhoneBuilder()
																.withAreaCode(
																		"11")
																.withNumber(
																		"965671300")))
								.withShipping(
										new ShippingBuilder()
												.withType(Type.SEDEX)
												.withCost(BigDecimal.TEN)
												.withAddress(
														new AddressBuilder()
																.withNumber(
																		"10")
																.withPostalCode(
																		"22299000")
																.withState(
																		State.SP)
																.withCity(
																		"São Paulo")
																.withDistrict(
																		"Pinheiros")
																.withStreet(
																		"Av Faria Lima")))

				);

		codigoRequest = registeredCheckout.getRedirectURL();
		System.out.println(codigoRequest);

	}

	@Quando("^finalizo o pagamento pelo sandbox$")
	public String retornado_codigo_transacao() throws Throwable {
//		 File file = new File("C:\\Users\\llima\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");				
//	     System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
//	     WebDriver driver = new PhantomJSDriver();
		
		
		System.setProperty(
				"webdriver.chrome.driver"
				, "src/test/resources/drivers/win32/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get(codigoRequest);
		driver.findElement(By.id("signOut")).click();
		driver.findElement(By.id("user")).sendKeys(
				"c88018633641030893019@sandbox.pagseguro.com.br");
		driver.findElement(By.id("senderPassword"))
				.sendKeys("68pn1853bp761611");
		driver.findElement(By.className("mainActionButton")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("walletChange")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.findElement(By.id("holderCPF")).sendKeys("43176359845");
		
		driver.findElement(By.id("holderPhone")).sendKeys("997398968");
		driver.findElement(By.id("holderAreaCode")).sendKeys("16");
		wait = new WebDriverWait(driver, 5);
		driver.findElement(By.id("holderBornDate")).sendKeys("25031996");
		driver.findElement(By.id("creditCardNumber")).sendKeys(
				"4111111111111111");
		 wait = new WebDriverWait(driver, 10);
		 driver.findElement(By.id("creditCardDueDate_Month")).sendKeys("12");
		driver.findElement(By.id("creditCardDueDate_Year")).sendKeys("30");
		
		driver.findElement(By.id("creditCardHolderName")).sendKeys(
				"Leonardo Camargo");
		
		driver.findElement(By.id("creditCardCVV")).sendKeys("123");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Select selectBox = new Select(driver.findElement(By
				.id("cardInstallmentQuantity")));
		selectBox.selectByVisibleText("2 x R$ 57,48 = R$ 114,96");
		driver.findElement(By.id("continueToPayment")).click();
		System.out.println("To aqui");
		 File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         System.out.println("File:" + srcFile);
         FileUtils.copyFile(srcFile, new File("C:\\Dell\\screenshot_.png"));
         System.out.println("Done");
//		wait = new WebDriverWait(driver, 30);
		
		wait = new WebDriverWait(driver, 30);
		
		 wait.until(ExpectedConditions.urlContains("checkout/nc/nl/conclusion"));
		String codigoTransacao = driver.findElement(By.id("transactionCode"))
				.getText();
		System.out.println("capturei");
		codigoTransacao = codigoTransacao.replace("-", "");

		System.out.println(codigoTransacao);

		return codigoTransacao;
	}

	@Entao("^e comparado o resultado$")
	public void comparar_resultado() throws Throwable {
		driver.getPageSource()
				.contains(
						"Seu pagamento está em fase de análise. Você poderá receber um contato da nossa equipe para confirmar sua transação.");

	}

	// Cenario: Requisicao de Pagamento invalida

	// Dado que esteja autenticado na api do pagseguro
	// Quando cria uma requisicao de pagamento invalida
	// Entao e retornado um erro de transacao

	@Quando("^cria uma requisicao de pagamento invalida$")
	public void requisicao_pagamento_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			RegisteredCheckout registeredCheckoutInvalid = pagSeguro
					.checkouts()
					.register(
							new CheckoutRegistrationBuilder()
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withAmount(
															new BigDecimal(
																	10.00))
													.withDescription("Item 1")
													.withQuantity(10)
													.withShippingCost(
															new BigDecimal(20))
													.withWeight(10))

									.withSender(
											new SenderBuilder()
													.withHash(
															"bc947e397943de0d32325f669b64c039d67d738196b3aa0ea37787fc86a74e0c")
													.withCPF("29182659427")
													.withEmail(
															"v82066140592075093489@sandbox.pagseguro.com.br")
													.withName("Luiz Roos")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"11")
																	.withNumber(
																			"965671300")))
									.withShipping(
											new ShippingBuilder()
													.withType(Type.SEDEX)
													.withCost(BigDecimal.TEN)
													.withAddress(
															new AddressBuilder()
																	.withNumber(
																			"10")
																	.withPostalCode(
																			"22299000")
																	.withState(
																			State.SP)
																	.withCity(
																			"São Paulo")
																	.withDistrict(
																			"Pinheiros")
																	.withStreet(
																			"Av Faria Lima")))

					);

			System.out.println(registeredCheckoutInvalid.getCheckoutCode());

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			// O que vem da API do Pagseguro
			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("Item Id is required.", serverError.getMessage());
			assertEquals(new Integer(11025), serverError.getCode());
			// assertEquals ("qualquer coisa" ,"teste");
		}

	}

	@Entao("^e retornado um erro de transacao$")
	public void retorno_erro_normal() throws Throwable {

	}

	// Cenario: Requisicao de Pagamento com assinatura
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento com assinatura
	// Entao e retornado o codigo da transacao

	@Quando("^crio uma requisicao de pagamento com assinatura$")
	public void requisicao_pagamento_assinatura() throws Throwable {

		final PagSeguro pagSeguro = PagSeguro.instance(
				Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
				PagSeguroEnv.SANDBOX);

		RegisteredCheckout registeredCheckoutApproval = pagSeguro
				.checkouts()
				.register(
						new CheckoutRegistrationBuilder()
								.withCurrency(Currency.BRL)
								.addItem(
										new PaymentItemBuilder()
												.withId("49099")
												.withAmount(
														new BigDecimal(100.00))
												.withDescription("Item 1")
												.withQuantity(10)
												.withShippingCost(
														new BigDecimal(20))
												.withWeight(10))

								.withSender(
										new SenderBuilder()
												.withHash(
														"bc947e397943de0d32325f669b64c039d67d738196b3aa0ea37787fc86a74e0c")
												.withCPF("29182659427")
												.withEmail(
														"v82066140592075093489@sandbox.pagseguro.com.br")
												.withName("Luiz Roos")
												.withPhone(
														new PhoneBuilder()
																.withAreaCode(
																		"11")
																.withNumber(
																		"965671300")))
								.withShipping(
										new ShippingBuilder()
												.withType(Type.SEDEX)
												.withCost(BigDecimal.TEN)
												.withAddress(
														new AddressBuilder()
																.withNumber(
																		"10")
																.withPostalCode(
																		"14800360")
																.withState(
																		State.SP)
																.withCity(
																		"Araraquara")
																.withDistrict(
																		"Centro")
																.withStreet(
																		"Rua Padre Duarte")))
								.withPreApproval(
										new PreApprovalBuilder()
												.withName("Leonardo")
												.withDetails(
														"Todo dia 28 será cobrado o valor de 100,00 referente ao seguro contra roubo do notebook")
												.withAmountPerPayment(
														new BigDecimal(100.00))
												// .withMaxAmountPerPayment(new
												// BigDecimal(200.00))
												.withPeriod("Monthly")
												.withMaxPaymentsPerPeriod(2)

												.withMaxAmountPerPeriod(
														new BigDecimal(200.00))
												.withCharge("manual")
												.withDateRange(
														new DateRangeBuilder()
																.between(

																		DatatypeConverter
																				.parseDateTime(
																						"2016-12-20T23:59:00.000-03:00")
																				.getTime(),// inicial
																							// ALTERAR
																							// CONFORME
																							// FOR
																							// ALTERANDO
																							// A
																							// DATA
																		DatatypeConverter
																				.parseDateTime(
																						"2016-12-30T23:59:00.000-03:00")
																				.getTime()// final
																))

								));

		codigo = registeredCheckoutApproval.getRedirectURL();

	}

	@Entao("^e retornado o codigo da transacao com assinatura$")
	public void retorno_codigo_transacao_assinatura() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Requisicao de Pagamento com assinatura invalida
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento com assinatura invalida
	// Entao e retornado um erro de transacao com assinatura

	@Quando("^crio uma requisicao de pagamento com assinatura invalida$")
	public void requisicao_pagamento_assinatura_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			RegisteredCheckout registeredCheckoutApproval = pagSeguro
					.checkouts()
					.register(
							new CheckoutRegistrationBuilder()
									.withCurrency(Currency.BRL)
									.addItem(
											new PaymentItemBuilder()
													.withId("49099")
													.withAmount(
															new BigDecimal(
																	100.00))
													.withQuantity(10)
													.withShippingCost(
															new BigDecimal(20))
													.withWeight(10))

									.withSender(
											new SenderBuilder()
													.withHash(
															"bc947e397943de0d32325f669b64c039d67d738196b3aa0ea37787fc86a74e0c")
													.withCPF("29182659427")
													.withEmail(
															"v82066140592075093489@sandbox.pagseguro.com.br")
													.withName("Luiz Roos")
													.withPhone(
															new PhoneBuilder()
																	.withAreaCode(
																			"11")
																	.withNumber(
																			"965671300")))
									.withShipping(
											new ShippingBuilder()
													.withType(Type.SEDEX)
													.withCost(BigDecimal.TEN)
													.withAddress(
															new AddressBuilder()
																	.withNumber(
																			"10")
																	.withPostalCode(
																			"14800360")
																	.withState(
																			State.SP)
																	.withCity(
																			"Araraquara")
																	.withDistrict(
																			"Centro")
																	.withStreet(
																			"Rua Padre Duarte")))
									.withPreApproval(
											new PreApprovalBuilder()
													.withName("Leonardo")
													.withDetails(
															"Todo dia 28 será cobrado o valor de 100,00 referente ao seguro contra roubo do notebook")
													.withAmountPerPayment(
															new BigDecimal(
																	100.00))
													// .withMaxAmountPerPayment(new
													// BigDecimal(200.00))
													.withPeriod("Monthly")
													.withMaxPaymentsPerPeriod(2)

													.withMaxAmountPerPeriod(
															new BigDecimal(
																	200.00))
													.withCharge("manual")
													.withDateRange(
															new DateRangeBuilder()
																	.between(

																			DatatypeConverter
																					.parseDateTime(
																							"2016-10-23T23:59:00.000-03:00")
																					.getTime(),// inicial
																			DatatypeConverter
																					.parseDateTime(
																							"2016-11-02T23:59:00.000-03:00")
																					.getTime()// final
																	))

									)

					);

			System.out.println(registeredCheckoutApproval);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("Item description is required.",
					serverError.getMessage());
			assertEquals(new Integer(11033), serverError.getCode());

		}

	}

	@Entao("^e retornado um erro de transacao com assinatura$")
	public void retorno_erro_transacao_assinatura() throws Throwable {

	}

	// Cenario: Requisicao de cancelamento de transacao
	// Dado que esteja autenticado na api do pagseguro
	// Quando cria uma requisicao de cancelamento de transacao
	// Entao e retornado o codigo da transacao de cancelamento

	@Quando("^crio uma requisicao de cancelamento de transacao$")
	public void crio_requisicao_cancelamento_transacao() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			requisicao_pagamento();
			CancelledTransaction transactionCancel = pagSeguro.transactions()
					.cancel(new TransactionIdentifyBuilder()
							.withCode(retornado_codigo_transacao())

					);

			System.out.println(transactionCancel.getResultTransactionCancel());

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("invalid transaction status to cancel.",
					serverError.getMessage());
			assertEquals(new Integer(56002), serverError.getCode());

		}

	}

	@Entao("^e retornado o codigo da transacao de cancelamento$")
	public void retorno_codigo_transacao_cancelamento() throws Throwable {
		System.out.println(codigo);
	}

	// Cenario: Requisicao de cancelamento de transacao invalida
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de cancelamento de transacao invalida
	// Entao e retornado um erro na transacao de cancelamento

	@Quando("^crio uma requisicao de cancelamento invalida$")
	public void requisicao_cancelamento_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			CancelledTransaction transactionCancel = pagSeguro.transactions()
					.cancel(new TransactionIdentifyBuilder()
							.withCode("61D83EDA24A24F8987905068159488FF")

					);

			System.out.println(transactionCancel.getResultTransactionCancel());

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("invalid transaction status to cancel.",
					serverError.getMessage());
			assertEquals(new Integer(56002), serverError.getCode());

		}

	}

	@Entao("^e retornado um erro na transacao de cancelamento$")
	public void retorno_erro_transacao_cancelamento() throws Throwable {

	}

	// Cenario: Requisição de estorno de pagamento
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de estorno de pagamento
	// Entao e retornado a resposta do servidor

	@Quando("^crio uma requisicao de estorno de pagamento$")
	public void requisicao_estorno_() throws Throwable {

		try {

			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			requisicao_pagamento();

			String codigoTransacao = retornado_codigo_transacao();

			alterarStatusRequisicaoParaPago(codigoTransacao);

			RefundedTransaction transactionRefunded = pagSeguro.transactions()
					.refund(new TransactionRefundingBuilder()
							.withCode(codigoTransacao));

			codigo = transactionRefunded.getResultTransactionRefund();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}

	private void alterarStatusRequisicaoParaPago(String codigoTransacao) {
//		File file = new File("C:\\Users\\llima\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");				
//	     System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
//	     WebDriver driver = new PhantomJSDriver();
		System.setProperty(
				"webdriver.chrome.driver"
				, "src/test/resources/drivers/win32/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sandbox.pagseguro.uol.com.br/");
		driver.findElement(By.id("email-login")).clear();
		driver.findElement(By.id("email-login")).sendKeys(EMAIL_SANDBOX);
		driver.findElement(By.id("pass-login")).clear();
		driver.findElement(By.id("pass-login")).sendKeys(SENHA_SANDBOX);
		driver.findElement(By.id("login-button")).click();
		driver.findElement(By.xpath("//*[text()='" + codigoTransacao + "']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.findElement(By.id("change-status-link")).click();
		wait = new WebDriverWait(driver, 100);
	    new Select(driver.findElement(By.cssSelector("#cboxLoadedContent > div.modal-content > form.change-status-form > #change-status-list"))).selectByVisibleText("Paga");
		driver.findElement(By.cssSelector("#cboxLoadedContent > div > form > div.button-wrapper.center > button.submit-modal.pagseguro-button")).click();
		
		
		
		
	}

	@Entao("^e retornado a resposta do servidor$")
	public void retorno_resposta_servidor_estorno() throws Throwable {
		System.out.println(codigo);
	}

	// Cenario: Requisicao de estorno de pagamento invalida
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de estorno de pagamento
	// Entao e retornado um erro no estorno

	@Quando("^crio uma requisicao de estorno de pagamento invalida")
	public void requisicao_estorno_pagamento_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			RefundedTransaction transactionRefunded = pagSeguro.transactions()
					.refund(new TransactionRefundingBuilder()
							.withCode("61D83EDA24A24F8987905068159488F1"));

			transactionRefunded.getResultTransactionRefund();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("transaction is not found.", serverError.getMessage());
			assertEquals(new Integer(14008), serverError.getCode());
		}

	}

	@Entao("^e retornado um erro no estorno$")
	public void retorno_erro_estorno() throws Throwable {

	}

	// Cenario: Requisicao de estorno parcial
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de estorno parcial
	// Entao e retornado a resposta do servidor

	@Quando("^crio uma requisicao de estorno parcial$")
	public void requisicao_estorno_parcial() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			requisicao_pagamento();

			RefundedTransaction transactionParcialRefunded = pagSeguro
					.transactions().refund(
							new TransactionRefundingBuilder().withCode(
									"3CF6E38C4C2C4B5D83AE2097CB07F2FC")
									.withValue(new BigDecimal(10.00)));

			codigo = transactionParcialRefunded.getResultTransactionRefund();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());
			e.printStackTrace();
		}

	}

	@Entao("^e retornado a resposta do servidor estorno parcial$")
	public void retorno_resposta_servidor_estorno_parcial() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Requisicao de estorno parcial invalido
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de estorno invalido
	// Entao e retornado a resposta do servidor

	@Quando("^crio uma requisicao de estorno invalido$")
	public void requisicao_estorno_invalido_() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			RefundedTransaction transactionParcialRefunded = pagSeguro
					.transactions().refund(
							new TransactionRefundingBuilder().withCode(
									"3CF6E38C4C2C4B5D83AE2097CB07F2FC")
									.withValue(new BigDecimal(10.00)));

			System.out.println(transactionParcialRefunded
					.getResultTransactionRefund());
		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("invalid transaction status to refund.",
					serverError.getMessage());
			assertEquals(new Integer(14007), serverError.getCode());

		}

	}

	@Entao("^e retornado a resposta do servidor estorno parcial invalido$")
	public void estorno_parcial_resposta_servidor_invalido() throws Throwable {

	}

//	Dado que esteja autenticado na api do pagseguro
//	E pesquiso a transacao pelo codigo invalido
//	Entao e retornado um erro de consulta por codigo

	@Quando("^pesquiso a transacao pelo codigo$")
	public void pesquiso_transacao_codigo() throws Throwable {
		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			requisicao_pagamento();
			TransactionDetail transactionDetail = pagSeguro.transactions()
					.search().byCode(retornado_codigo_transacao());

			codigo = transactionDetail.getCode();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

		}
	}

	@Entao("^e retornado a transacao por codigo$")
	public void retorno_transacao_codigo() throws Throwable {
		System.out.println(codigo);
	}

	// Cenario: Consultar transacoes por codigo invailda
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento
	// E pesquiso a transacao pelo codigo invalido
	// Entao e retornado um erro de consulta por codigo

	@Quando("^pesquiso a transacao pelo codigo invalido$")
	public void pesquiso_transacao_codigo_invalido() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			
			TransactionDetail transactionDetail = pagSeguro.transactions()
					.search().byCode("212212121");

			codigo = transactionDetail.getCode();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("invalid transactionCode value: 212212121",
					serverError.getMessage());
			assertEquals(new Integer(13003), serverError.getCode());

		}

	}

	@Entao("^e retornado um erro de consulta por codigo$")
	public void retorno_erro_consulta_codigo() throws Throwable {

	}

	// Cenario: Consultar transacoes por intervalo de datas
	// Dado que esteja autenticado na api do pagseguro
	// Quando crio uma requisicao de pagamento
	// E pesquiso a transacao por um intervalo de datas
	// Entao e retornado as transacoes

	@Quando("^pesquiso a transacao por um intervalo de datas$")
	public void transacao_intervalo_datas() throws Throwable {

		final PagSeguro pagSeguro = PagSeguro.instance(
				Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
				PagSeguroEnv.SANDBOX);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date date = dateFormat.parse("24/10/2016");
		final DataList<? extends TransactionSummary> transactionDate = pagSeguro
				.transactions()
				.search()
				.byDateRange(new DateRangeBuilder().between(date, new Date()),
						1, 10);
		if (transactionDate.isEmpty()) {
			return;
		}
		codigo = transactionDate.toString();

	}

	@Entao("^e retornado as transacoes por datas$")
	public void retornado_transacoes_data() throws Throwable {
		System.out.println(codigo);

	}

	// Cenario: Consultar transacoes por intervalo de datas invalida
	// Dado que esteja autenticado na api do pagseguro
	// E pesquiso a transacao por um intervalo de datas invalida
	// Entao e retornado um erro de consulta de transacoes por data

	@Quando("^pesquiso a transacao por um intervalo de datas invalida$")
	public void transacao_intervalo_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date date = dateFormat.parse("10/39/2016");
			final DataList<? extends TransactionSummary> transactionDate = pagSeguro
					.transactions()
					.search()
					.byDateRange(
							new DateRangeBuilder().between(date, new Date()),
							1, 10);
			if (transactionDate.isEmpty()) {
				return;
			}
			System.out.print(transactionDate);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("initialDate must not be older than 6 months.",
					serverError.getMessage());
			assertEquals(new Integer(11046), serverError.getCode());

		}

	}

	@Entao("^e retornado um erro de consulta de transacoes por data$")
	public void retorno_erro_consulta_transacao_data() throws Throwable {

	}

	// Cenario: Consultar transacoes abandonadas
	// Dado que esteja autenticado na api do pagseguro
	// Quando pesquiso uma transacao abandonada
	// Entao e retornada a transacao abandonada

	@Quando("^pesquiso uma transacao abandonada no pagseguro$")
	public void pesquiso_transacao_abandoada() throws Throwable {

		final PagSeguro pagSeguro = PagSeguro.instance(
				Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
				PagSeguroEnv.SANDBOX);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date date = dateFormat.parse("23/10/2016");
		Date date2 = dateFormat.parse("11/11/2016");
		final DataList<? extends TransactionSummary> transactionDate = pagSeguro
				.transactions()
				.search()
				.byDateRange(new DateRangeBuilder().between(date, date2), 1, 10);
		if (transactionDate.isEmpty()) {
			return;
		}

		codigo = transactionDate.toString();

	}

	@Entao("^e retornado a transacao abandonada$")
	public void retornado_transacoes_abandonada() throws Throwable {
		System.out.println(codigo);
	}

	// Cenario: Consultar transacoes abandonadas invalida
	// Dado que esteja autenticado na api do pagseguro
	// Quando pesquiso uma transacao abandonada invalida
	// Entao e retornada a transacao abandonada invalida

	@Quando("^pesquiso uma transacao abandonada invalida$")
	public void transacao_abandonada_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date date = dateFormat.parse("23/10/2016");
			Date date2 = dateFormat.parse("24/10/2016");
			final DataList<? extends TransactionSummary> transactionDate = pagSeguro
					.transactions()
					.search()
					.byDateRange(new DateRangeBuilder().between(date, date2),
							1, 10);
			if (transactionDate.isEmpty()) {
				return;
			}

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("invalid transaction status to refund.",
					serverError.getMessage());
			assertEquals(new Integer(14007), serverError.getCode());

		}

	}

	@Entao("^e retornado um erro de consulta de transacoes abandonada$")
	public void erro_consulta_transacao_abandonadas() throws Throwable {

	}

	// Cenario: Consultar transacoes por codigo de referencia
	// Dado que esteja autenticado na api do pagseuro
	// Quando pesquiso uma transacao por codigo de referencia
	// Entao e retornada a transacao pela referencia

	@Quando("^pesquiso uma transacao por codigo de referencia$")
	public void pesquisa_codigo_referencia() throws Throwable {
		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			final DataList<? extends TransactionSummary> transactionDate = pagSeguro
					.transactions().search().byReference("LIBJAVA0000001");

			codigo = transactionDate.toString();

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

		}
	}

	@Entao("^e retornada a transacao pela referencia$")
	public void retorno_transacao_referencia() throws Throwable {
		System.out.println(codigo);
	}

	// Cenario: Consultar transacoes por codigo de referencia invalida
	// Dado que esteja autenticado na api do pagseuro
	// Quando pesquiso uma transacao por codigo de referencia invalida
	// Entao e retornada um erro de consulta de transacao pela referencia

	@Quando("^pesquiso uma transacao por codigo de referencia invalida$")
	public void transacao_referencia_invalida() throws Throwable {

		try {
			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN),
					PagSeguroEnv.SANDBOX);

			final DataList<? extends TransactionSummary> transactionDate = pagSeguro
					.transactions().search().byReference("");

			System.out.print(transactionDate);

		} catch (PagSeguroBadRequestException e) {
			System.out.println(e.getErrors());

			ServerErrors serverErros = e.getErrors();
			ServerError serverError = serverErros.getErrors().iterator().next();

			assertEquals("initialDate is required.", serverError.getMessage());
			assertEquals(new Integer(13004), serverError.getCode());
		}

	}

	@Entao("^e retornada um erro de consulta de transacao pela referencia$")
	public void retorno_erro_referencia() throws Throwable {

	}

	public void alterar_status_transacao() throws Throwable {

		driver.get("sandbox.pagseguro.uol.com.br");

	}

}
