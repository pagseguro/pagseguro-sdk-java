package integration.Modulos;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.ShippingType.Type;
import br.com.uol.pagseguro.api.common.domain.builder.*;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class Transactions {

    String SELLER_EMAIL = null;
    String SELLER_TOKEN = null;
    String APP_ID = null;
    String APP_KEY = null;
    String EMAIL_SANDBOX = null;
    String SENHA_SANDBOX = null;
    String COMPRADOR_USER = null;
    String COMPRADOR_SENHA = null;
    String codigo = null;
    String codigoRequest = null;
    String url = null;
    String pagSeguro = null;
    String urlComparacao;
    String TOKEN;
    String HASH;
    String EMAIL;
    String CHAVEPUBLICA1;
    String CHAVEPUBLICA2;

    WebDriver driver;

    //
    //	@Before
    //	public void setUp() {
    //		driver = Driver.getInstance();
    //	}

    //
    // Cenario: Checkout transparente credito
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de pagamento transparente credito
    // Entao e retornado o codigo da transacao transparente credito
    // //
    @Dado("^que esteja autenticado na api do pagseguro$")
    public void esteja_autenticado_pagseguro() throws Throwable {

        APP_ID = "INFORMAR O USER DE APLICA��O";
        APP_KEY = "INFORMAR A SENHA DE APLICA��O";
        SELLER_EMAIL = "INFORMAR USUARIO SELLER";
        SELLER_TOKEN = "INFORMAR SENHA SELLER";
        EMAIL_SANDBOX = "INFORMAR E-MAIL SANDBOX";
        SENHA_SANDBOX = "INFORMAR SENHA DO SANDBOX";
        COMPRADOR_USER = "INFORMAR USUARIO COMPRADOR";
        COMPRADOR_SENHA = "INFORMAR SENHA COMPRADOR";
        TOKEN = "INFORMAR TOKEN GERADO";
        HASH = "INFORMAR HASH GERADO";
        EMAIL = "INFORMAR EMAIL GERADO";
        CHAVEPUBLICA1 = "INFORMAR CHAVE PUBLICA DE VENDEDOR 1";
        CHAVEPUBLICA2 = "INFORMAR CHAVE PUBLICA DE VENDEDOR 2";
    }

    //
    @Quando("^crio uma requisicao de pagamento transparente credito$")
    public void crio_requisicao_pagamento_transparente_credito() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            TransactionDetail transactionCard = pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder().withPaymentMode("default").withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("001").withDescription("Notebook Preto").withQuantity(1).withAmount(new BigDecimal(100.00))).withNotificationURL("www.lojateste.com.br/notification").withReference("DIRECT_PAYMENT").withSender(new SenderBuilder().withName("Comprador Teste").withCPF("84815525269").withPhone(new PhoneBuilder().withAreaCode("16").withNumber("981284174")

                    ).withEmail(EMAIL).withHash(HASH)// alterar
                    // conforme
                    // a
                    // session
            ).withShipping(new ShippingBuilder().withAddress(new AddressBuilder().withStreet("Rua teste").withNumber("1233").withComplement("complemento").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withType(ShippingType.Type.SEDEX).withCost(new BigDecimal(20.00)))).withCreditCard(new CreditCardBuilder().withBillingAddress(new AddressBuilder().withStreet("Rua Armandao").withNumber("1233").withComplement("teste").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withInstallment(new InstallmentBuilder().withQuantity(2).withValue(new BigDecimal(60.00)).withNoInterestInstallmentQuantity(2)).withHolder(new HolderBuilder().addDocument(new DocumentBuilder().withType(DocumentType.CPF).withValue("04570568351")).withName("Leonardo Camargo").withBithDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/03/1995")).withPhone(new PhoneBuilder().withAreaCode("16").withNumber("997334968"))).withToken(TOKEN));

            codigo = transactionCard.getPaymentLink();
            System.out.println(codigo);
        } catch (PagSeguroBadRequestException e) {

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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            TransactionDetail transactionCard = pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder().withPaymentMode("default").withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("001").withDescription("Notebook Preto").withQuantity(1).withAmount(new BigDecimal(100.00))).withNotificationURL("www.lojateste.com.br/notification").withReference("DIRECT_PAYMENT").withSender(new SenderBuilder().withName("Comprador Teste").withCPF("84815525269").withPhone(new PhoneBuilder().withAreaCode("16").withNumber("981284174")

            ).withEmail("teste@sandbox.pagseguro.com.br")).withShipping(new ShippingBuilder().withAddress(new AddressBuilder().withStreet("Rua teste").withNumber("1233").withComplement("complemento").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withType(ShippingType.Type.SEDEX).withCost(new BigDecimal(20.00)))).withCreditCard(new CreditCardBuilder().withBillingAddress(new AddressBuilder().withStreet("Rua Armando").withNumber("1233").withComplement("teste").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withInstallment(new InstallmentBuilder().withQuantity(2).withValue(new BigDecimal(60.00)).withNoInterestInstallmentQuantity(2)).withHolder(new HolderBuilder().addDocument(new DocumentBuilder().withType(DocumentType.CPF).withValue("04570568351")).withName("Comprador Teste").withBithDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/03/1995")).withPhone(new PhoneBuilder().withAreaCode("16").withNumber("992678969"))).withToken(TOKEN));

            System.out.println(transactionCard);
        } catch (PagSeguroBadRequestException e) {

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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            TransactionDetail transactionDebit = pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder().withPaymentMode("default").withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("001").withDescription("Notebook Preto").withQuantity(1).withAmount(new BigDecimal(100.00))).withNotificationURL("www.lojateste.com.br/notification").withReference("DIRECT_PAYMENT").withSender(new SenderBuilder().withName("Comprador Teste").withCPF("84815525269").withPhone(new PhoneBuilder().withAreaCode("16").withNumber("981284174")

            ).withEmail(EMAIL).withHash(HASH)).withShipping(new ShippingBuilder().withAddress(new AddressBuilder().withStreet("Rua teste").withNumber("1233").withComplement("complemento").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withType(ShippingType.Type.SEDEX).withCost(new BigDecimal(20.00)))).withOnlineDebit(new BankBuilder().withName(BankName.Name.BRADESCO));

            url = transactionDebit.getPaymentLink();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());
        }
    }

    @Entao("^e retornada url de transacao transparente debito$")
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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            // System.out.println(pagSeguro.installments().list("visa", new
            // BigDecimal(120), 2));

            TransactionDetail transactionDebitInvalid = pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder().withPaymentMode("default").withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("001").withDescription("Notebook Preto").withQuantity(1).withAmount(new BigDecimal(100.00))).withNotificationURL("www.lojateste.com.br/notification").withReference("DIRECT_PAYMENT").withSender(new SenderBuilder().withName("Comprador Teste").withCPF("84815525269").withPhone(new PhoneBuilder().withAreaCode("16").withNumber("981284174")

            )

                    .withHash(HASH)).withShipping(new ShippingBuilder().withAddress(new AddressBuilder().withStreet("Rua teste").withNumber("1233").withComplement("complemento").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withType(ShippingType.Type.SEDEX).withCost(new BigDecimal(20.00)))).withOnlineDebit(new BankBuilder().withName(BankName.Name.BRADESCO));
            transactionDebitInvalid.getCode();
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
        System.out.println("Debito Invalido Feito com Sucesso.");
    }

    // Cenario: Checkout transparente boleto
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de pagamento transparente boleto
    // Entao e retornado o codigo da transacao transparente boleto

    @Quando("^crio uma requisicao de pagamento transparente boleto$")
    public void requisicao_pagamento_transparente_boleto() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            TransactionDetail transactionBoleto = pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder().withPaymentMode("default").withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("001").withDescription("Notebook Preto").withQuantity(1).withAmount(new BigDecimal(100.00))).withNotificationURL("www.lojateste.com.br/notification").withReference("DIRECT_PAYMENT").withSender(new SenderBuilder().withName("Comprador Teste").withCPF("84815525269").withPhone(new PhoneBuilder().withAreaCode("16").withNumber("981284174")

            ).withEmail(EMAIL).withHash(HASH)).withShipping(new ShippingBuilder().withAddress(new AddressBuilder().withStreet("Rua teste").withNumber("1233").withComplement("complemento").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withType(ShippingType.Type.SEDEX).withCost(new BigDecimal(20.00)))).withBankSlip();

            codigo = transactionBoleto.getPaymentLink();
            System.out.println("Transa��o boleto finalizada com sucesso.");
        } catch (PagSeguroBadRequestException e) {

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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            TransactionDetail transactionBoleto = pagSeguro.transactions().register(new DirectPaymentRegistrationBuilder().withPaymentMode("default").withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withDescription("Notebook Preto").withQuantity(1).withAmount(new BigDecimal(100.00))).withNotificationURL("www.lojateste.com.br/notification").withReference("DIRECT_PAYMENT").withSender(new SenderBuilder().withName("Comprador Teste").withCPF("84815525269").withPhone(new PhoneBuilder().withAreaCode("16").withNumber("981284174")

            ).withEmail(EMAIL).withHash(HASH)).withShipping(new ShippingBuilder().withAddress(new AddressBuilder().withStreet("Rua teste").withNumber("1233").withComplement("complemento").withDistrict("Centro").withPostalCode("14800360").withCity("Araraquara").withState(State.SP).withCountry("BRA")).withType(ShippingType.Type.SEDEX).withCost(new BigDecimal(20.00)))).withBankSlip();

            transactionBoleto.getCancellationSource();
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
        System.out.println("Transa�ao Boleto Invalido com sucesso.");
    }

    // Cenario: Requisicao de Pagamento
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de pagamento
    // Entao e retornado o codigo da transacao

    @Quando("^crio uma requisicao de pagamento$")
    public void requisicao_pagamento() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(new CheckoutRegistrationBuilder().withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("49099").withAmount(new BigDecimal(10.00)).withDescription("Item 1").withQuantity(10).withShippingCost(new BigDecimal(20)).withWeight(10))

                    .withSender(new SenderBuilder()

                            .withEmail(EMAIL).withHash(HASH).withCPF("29182659427").withName("Comprador Teste").withPhone(new PhoneBuilder().withAreaCode("11").withNumber("965671300"))).withShipping(new ShippingBuilder().withType(Type.SEDEX).withCost(BigDecimal.TEN).withAddress(new AddressBuilder().withNumber("10").withPostalCode("22299000").withState(State.SP).withCity("S�o Paulo").withDistrict("Pinheiros").withStreet("Av Faria Lima")))

            );

            codigoRequest = registeredCheckout.getRedirectURL();

            System.out.println(codigoRequest);
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());
        }
    }

    @Quando("^finalizo o pagamento pelo sandbox$")
    public String finalizar_pagamento_sandbox() throws Throwable {

        //DRIVER DO CHROME
        //		System.setProperty(
        //				"webdriver.chrome.driver", "src/test/resources/driver/win32/chromedriver.exe");
        //		WebDriver driver = new ChromeDriver();

        File file = new File("..\bin\\phantomjs.exe"); //INFORMAR CAMINHO DO PHANTOM
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        WebDriver driver = new PhantomJSDriver();

        driver.manage().window().maximize();
        driver.get(codigoRequest);
        driver.findElement(By.id("signOut")).click();
        //INFORMAR DADOS DE COMPRADOR DE TESTE

        driver.findElement(By.id("user")).sendKeys(COMPRADOR_USER);
        driver.findElement(By.id("senderPassword")).sendKeys(COMPRADOR_SENHA);
        driver.findElement(By.className("mainActionButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("walletChange")).click();
        //		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //PRINT DA TELA
        //		 FileUtils.copyFile(srcFile, new File("C:\\Dell\\screenshot_.png"));
        //		srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.cssSelector("#bookletOption > label")).click(); //BOLETO
        Thread.sleep(3000);
        driver.findElement(By.id("senderCPF")).sendKeys("35395512551");
        System.out.println("Fluxo Boleto Finalizado.");
        driver.findElement(By.id("continueToPayment")).click();
        Thread.sleep(10000);
        String codigoTransacao = driver.findElement(By.id("transactionCode")).getText();
        codigoTransacao = codigoTransacao.replace("-", "");
        System.out.println(codigoTransacao);
        urlComparacao = driver.getCurrentUrl();

        this.driver = driver;

        return codigoTransacao;
    }

    @Entao("^e comparado o resultado na finalizacao do checkout$")
    public void retorno_resultado_finalizacao_checkout() {

        if (driver.getPageSource().contains("O pagamento est� em processamento.")) {
            System.out.println("Checkout finalizado com sucesso.");
        } else {
            System.out.println("N�o foi poss�vel finalizar e comparar o resultado na finaliza��o do checkout.");
        }

        driver.quit();
    }
    // Cenario: Requisicao de Pagamento invalida
    // Dado que esteja autenticado na api do pagseguro
    // Quando cria uma requisicao de pagamento invalida
    // Entao e retornado um erro de transacao

    @Quando("^cria uma requisicao de pagamento invalida$")
    public void requisicao_pagamento_invalida() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            RegisteredCheckout registeredCheckoutInvalid = pagSeguro.checkouts().register(new CheckoutRegistrationBuilder().withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withAmount(new BigDecimal(10.00)).withDescription("Item 1").withQuantity(10).withShippingCost(new BigDecimal(20)).withWeight(10))

                    .withSender(new SenderBuilder().withEmail(EMAIL).withHash(HASH).withCPF("29182659427")

                            .withName("Comprador Teste").withPhone(new PhoneBuilder().withAreaCode("11").withNumber("965671301"))).withShipping(new ShippingBuilder().withType(Type.SEDEX).withCost(BigDecimal.TEN).withAddress(new AddressBuilder().withNumber("10").withPostalCode("22299000").withState(State.SP).withCity("S�o Paulo").withDistrict("Pinheiros").withStreet("Av Faria Lima"))));
            registeredCheckoutInvalid.getCheckoutCode();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            // O que vem da API do Pagseguro
            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("Item Id is required.", serverError.getMessage());
            assertEquals(new Integer(11025), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de transacao$")
    public void retorno_erro_normal() throws Throwable {
        System.out.println("Erro transa��o.");
    }

    // Cenario: Requisicao de Pagamento com assinatura
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de pagamento com assinatura
    // Entao e retornado o codigo da transacao

    @Quando("^crio uma requisicao de pagamento com assinatura$")
    public void requisicao_pagamento_assinatura() throws Throwable {

        final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

        RegisteredCheckout registeredCheckoutApproval = pagSeguro.checkouts().register(new CheckoutRegistrationBuilder().withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("49099").withAmount(new BigDecimal(100.00)).withDescription("Item 1").withQuantity(10).withShippingCost(new BigDecimal(20)).withWeight(10))

                .withSender(new SenderBuilder().withEmail(EMAIL).withHash(HASH).withCPF("29182659427")

                        .withName("Comprador Teste").withPhone(new PhoneBuilder().withAreaCode("11").withNumber("965671310"))).withShipping(new ShippingBuilder().withType(Type.SEDEX).withCost(BigDecimal.TEN).withAddress(new AddressBuilder().withNumber("10").withPostalCode("14800360").withState(State.SP).withCity("Araraquara").withDistrict("Centro").withStreet("Rua Padre Duarte"))).withPreApproval(new PreApprovalRequestBuilder().withName("Leonardo").withDetails("Todo dia 28 ser� cobrado o valor de 100,00 referente ao seguro contra roubo do notebook").withAmountPerPayment(new BigDecimal(100.00)).withPeriod("Monthly").withMaxPaymentsPerPeriod(2)

                        .withMaxAmountPerPeriod(new BigDecimal(200.00)).withCharge("manual").withDateRange(new DateRangeBuilder().between(

                                DatatypeConverter.parseDateTime("2016-12-20T23:59:00.000-03:00").getTime(),// inicial
                                // ALTERAR
                                // CONFORME
                                // FOR
                                // ALTERANDO
                                // A
                                // DATA
                                DatatypeConverter.parseDateTime("2016-12-30T23:59:00.000-03:00").getTime()// final
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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            RegisteredCheckout registeredCheckoutApproval = pagSeguro.checkouts().register(new CheckoutRegistrationBuilder().withCurrency(Currency.BRL).addItem(new PaymentItemBuilder().withId("49099").withAmount(new BigDecimal(100.00)).withQuantity(10).withShippingCost(new BigDecimal(20)).withWeight(10))

                    .withSender(new SenderBuilder().withEmail(EMAIL).withHash(HASH).withCPF("29182659427")

                            .withName("Comprador Teste").withPhone(new PhoneBuilder().withAreaCode("11").withNumber("965671310"))).withShipping(new ShippingBuilder().withType(Type.SEDEX).withCost(BigDecimal.TEN).withAddress(new AddressBuilder().withNumber("10").withPostalCode("14800360").withState(State.SP).withCity("Araraquara").withDistrict("Centro").withStreet("Rua Padre Duarte"))).withPreApproval(new PreApprovalRequestBuilder().withName("Comprador Teste").withDetails("Todo dia 28 ser� cobrado o valor de 100,00 referente ao seguro contra roubo do notebook").withAmountPerPayment(new BigDecimal(100.00)).withPeriod("Monthly").withMaxPaymentsPerPeriod(2)

                            .withMaxAmountPerPeriod(new BigDecimal(200.00)).withCharge("manual").withDateRange(new DateRangeBuilder().between(

                                    DatatypeConverter.parseDateTime("2016-10-23T23:59:00.000-03:00").getTime(),// inicial
                                    DatatypeConverter.parseDateTime("2016-11-02T23:59:00.000-03:00").getTime()// final
                            ))

                    )

            );

            registeredCheckoutApproval.getCheckoutCode();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("Item description is required.", serverError.getMessage());
            assertEquals(new Integer(11033), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de transacao com assinatura$")
    public void retorno_erro_transacao_assinatura() throws Throwable {
        System.out.println("Erro transa��o com assinatura");
    }

    // Cenario: Requisicao de cancelamento de transacao
    // Dado que esteja autenticado na api do pagseguro
    // Quando cria uma requisicao de cancelamento de transacao
    // Entao e retornado o codigo da transacao de cancelamento

    //    @Quando("^crio uma requisicao de cancelamento de transacao$")
    //    public void crio_requisicao_cancelamento_transacao() throws Throwable {
    //        try {
    //            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);
    //            requisicao_pagamento();
    //            CancelledTransaction transactionCancel = pagSeguro.transactions().cancel(new TransactionIdentifyBuilder().withCode(finalizar_pagamento_sandbox()));
    //            System.out.println(transactionCancel.getResultTransactionCancel());
    //        } catch (PagSeguroBadRequestException e) {
    //            System.out.println(e.getErrors());
    //
    //            ServerErrors serverErros = e.getErrors();
    //            ServerError serverError = serverErros.getErrors().iterator().next();
    //
    //            assertEquals("invalid transaction status to cancel.", serverError.getMessage());
    //            assertEquals(new Integer(56002), serverError.getCode());
    //        }
    //    }

    @Entao("^e retornado o codigo da transacao de cancelamento$")
    public void retorno_codigo_transacao_cancelamento() throws Throwable {
        System.out.println(codigo);
    }

    // Cenario: Requisicao de cancelamento de transacao invalida
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de cancelamento de transacao invalida
    // Entao e retornado um erro na transacao de cancelamento

    //    @Quando("^crio uma requisicao de cancelamento invalida$")
    //    public void requisicao_cancelamento_invalida() throws Throwable {
    //        try {
    //            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);
    //            CancelledTransaction transactionCancel = pagSeguro.transactions().cancel(new TransactionIdentifyBuilder().withCode("61D83EDA24A24F8987905068150479GF"));
    //            transactionCancel.getResultTransactionCancel();
    //        } catch (PagSeguroBadRequestException e) {
    //            System.out.println(e.getErrors());
    //
    //            ServerErrors serverErros = e.getErrors();
    //            ServerError serverError = serverErros.getErrors().iterator().next();
    //
    //            assertEquals("transaction is not found.", serverError.getMessage());
    //            assertEquals(new Integer(14008), serverError.getCode());
    //        }
    //    }

    @Entao("^e retornado um erro na transacao de cancelamento$")
    public void retorno_erro_transacao_cancelamento() throws Throwable {
        System.out.println("Transa��o de Cancelamento com Erro.");
    }

    // Cenario: Requisi��o de estorno de pagamento
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de estorno de pagamento
    // Entao e retornado a resposta do servidor

//    @Quando("^crio uma requisicao de estorno de pagamento$")
//    public void requisicao_estorno_() throws Throwable {
//        try {
//            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);
//            requisicao_pagamento();
//            String codigoTransacao = finalizar_pagamento_sandbox();
//            alterarStatusRequisicaoParaPago(codigoTransacao);
//            RefundedTransaction transactionRefunded = pagSeguro.transactions().refund(new TransactionRefundingBuilder().withCode(codigoTransacao));
//            codigo = transactionRefunded.getResultTransactionRefund();
//        } catch (Exception e) {
//            System.err.println(e);
//            e.printStackTrace();
//        }
//    }

    private void alterarStatusRequisicaoParaPago(String codigoTransacao) throws Throwable {

        //CHROME

        //		System.setProperty(
        //		"webdriver.chrome.driver"
        //		, "src/test/resources/driver/win32/chromedriver");
        //		driver = new ChromeDriver();

        File file = new File("bin\\phantomjs.exe");        //INFORMAR CAMINHO DO PHANTOM
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        WebDriver driver = new PhantomJSDriver();

        driver.manage().window().maximize();
        driver.get("https://sandbox.pagseguro.uol.com.br/");
        driver.findElement(By.id("email-login")).clear();
        driver.findElement(By.id("email-login")).sendKeys(EMAIL_SANDBOX);
        driver.findElement(By.id("pass-login")).clear();
        driver.findElement(By.id("pass-login")).sendKeys(SENHA_SANDBOX);
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("//*[text()='" + codigoTransacao + "']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("change-status-link")).click();
        Thread.sleep(2000);
        new Select(driver.findElement(By.cssSelector("#cboxLoadedContent > div.modal-content > form.change-status-form > #change-status-list"))).selectByVisibleText("Paga");
        driver.findElement(By.cssSelector("#cboxLoadedContent > div > form > div.button-wrapper.center > button.submit-modal.pagseguro-button")).click();

        driver.quit();
    }

    @Entao("^e retornado a resposta do servidor$")
    public void retorno_resposta_servidor_estorno() throws Throwable {
        System.out.println(codigo);
    }

    // Cenario: Requisicao de estorno de pagamento invalida
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de estorno de pagamento
    // Entao e retornado um erro no estorno

//    @Quando("^crio uma requisicao de estorno de pagamento invalida")
//    public void requisicao_estorno_pagamento_invalida() throws Throwable {
//        try {
//            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);
//            RefundedTransaction transactionRefunded = pagSeguro.transactions().refund(new TransactionRefundingBuilder().withCode("61D83EDA24A24F8987905068159488F1"));
//            transactionRefunded.getResultTransactionRefund();
//        } catch (PagSeguroBadRequestException e) {
//            System.out.println(e.getErrors());
//
//            ServerErrors serverErros = e.getErrors();
//            ServerError serverError = serverErros.getErrors().iterator().next();
//
//            assertEquals("transaction is not found.", serverError.getMessage());
//            assertEquals(new Integer(14008), serverError.getCode());
//        }
//    }

    @Entao("^e retornado um erro no estorno$")
    public void retorno_erro_estorno() throws Throwable {
        System.out.println("Requisi��o Estorno de Pagamento inv�lido.");
    }
    // Cenario: Requisicao de estorno parcial
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de estorno parcial
    // Entao e retornado a resposta do servidor

//    @Quando("^crio uma requisicao de estorno parcial$")
//    public void requisicao_estorno_parcial() throws Throwable {
//        try {
//            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);
//            requisicao_pagamento();
//            String codigoTransacao = finalizar_pagamento_sandbox();
//            alterarStatusRequisicaoParaPago(codigoTransacao);
//            RefundedTransaction transactionParcialRefunded = pagSeguro.transactions().refund(new TransactionRefundingBuilder().withCode(codigoTransacao).withValue(new BigDecimal(10.00)));
//            codigo = transactionParcialRefunded.getResultTransactionRefund();
//        } catch (PagSeguroBadRequestException e) {
//            System.out.println(e.getErrors());
//            e.printStackTrace();
//        }
//    }

    @Entao("^e retornado a resposta do servidor estorno parcial$")
    public void retorno_resposta_servidor_estorno_parcial() throws Throwable {
        System.out.println(codigo);
    }
    // Cenario: Requisicao de estorno parcial invalido
    // Dado que esteja autenticado na api do pagseguro
    // Quando crio uma requisicao de estorno invalido
    // Entao e retornado a resposta do servidor

//    @Quando("^crio uma requisicao de estorno invalido$")
//    public void requisicao_estorno_invalido_() throws Throwable {
//        try {
//            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);
//            RefundedTransaction transactionParcialRefunded = pagSeguro.transactions().refund(new TransactionRefundingBuilder().withCode("361D83EDA24A24F8987905068159488F").withValue(new BigDecimal(10.00)));
//            System.out.println(transactionParcialRefunded.getResultTransactionRefund());
//        } catch (PagSeguroBadRequestException e) {
//            System.out.println(e.getErrors());
//            ServerErrors serverErros = e.getErrors();
//            ServerError serverError = serverErros.getErrors().iterator().next();
//            assertEquals("transaction is not found.", serverError.getMessage());
//            assertEquals(new Integer(14008), serverError.getCode());
//        }
//    }

    @Entao("^e retornado a resposta do servidor estorno parcial invalido$")
    public void estorno_parcial_resposta_servidor_invalido() throws Throwable {

        System.out.println("Erro estorno parcial.");
    }

    //	Dado que esteja autenticado na api do pagseguro
    //	E pesquiso a transacao pelo codigo invalido
    //	Entao e retornado um erro de consulta por codigo

    @Quando("^pesquiso a transacao pelo codigo$")
    public void pesquiso_transacao_codigo() throws Throwable {
        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            requisicao_pagamento();
            TransactionDetail transactionDetail = pagSeguro.transactions().search().byCode(finalizar_pagamento_sandbox());

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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            TransactionDetail transactionDetail = pagSeguro.transactions().search().byCode("212212121");

            codigo = transactionDetail.getCode();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("invalid transactionCode value: 212212121", serverError.getMessage());
            assertEquals(new Integer(13003), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de consulta por codigo$")
    public void retorno_erro_consulta_codigo() throws Throwable {
        System.out.println("Pesquiso transa��o codigo inv�lido com sucesso.");
    }

    // Cenario: Consultar transacoes por intervalo de datas
    // Dado que esteja autenticado na api do pagseguro
    // E pesquiso a transacao por um intervalo de datas
    // Entao e retornado as transacoes

    @Quando("^pesquiso a transacao por um intervalo de datas$")
    public void transacao_intervalo_datas() throws Throwable {

        final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse("24/10/2016");
        final DataList<? extends TransactionSummary> transactionDate = pagSeguro.transactions().search().byDateRange(new DateRangeBuilder().between(date, new Date()), 1, 10);
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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = dateFormat.parse("0/0/0");
            Date date2 = dateFormat.parse("0/0/0");

            final DataList<? extends TransactionSummary> transactionSearch = pagSeguro.transactions().search().byDateRange(new DateRangeBuilder().between(date, date2), 90000, 300);

            if (transactionSearch.isEmpty()) {
                return;
            }
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();

            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("initialDate must not be older than 6 months.", serverError.getMessage());
            assertEquals(new Integer(11046), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de consulta de transacoes por data$")
    public void retorno_erro_consulta_transacao_data() throws Throwable {
        System.out.println("Transa��o intervalo inv�lido.");
    }

    // Cenario: Consultar transacoes abandonadas
    // Dado que esteja autenticado na api do pagseguro
    // Quando pesquiso uma transacao abandonada
    // Entao e retornada a transacao abandonada

    @Quando("^pesquiso uma transacao abandonada no pagseguro$")
    public void pesquiso_transacao_abandoada() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = dateFormat.parse("10/10/2016");
            Date date2 = dateFormat.parse("10/11/2016");
            final DataList<? extends TransactionSummary> transactionDate = pagSeguro.transactions().search().abandoned(new DateRangeBuilder().between(date, date2), 1, 10);
            if (transactionDate.isEmpty()) {
                return;
            }

            codigo = transactionDate.toString();
        } catch (PagSeguroBadRequestException e) {

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("initialDate must be lower than allowed limit.", serverError.getMessage());
            assertEquals(new Integer(13005), serverError.getCode());
        }
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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = dateFormat.parse("23/10/2016");
            Date date2 = dateFormat.parse("24/10/2016");
            final DataList<? extends TransactionSummary> transactionDate = pagSeguro.transactions().search().abandoned(new DateRangeBuilder().between(date, date2), 1, 10);
            if (transactionDate.isEmpty()) {
                return;
            }
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("invalid transaction status to refund.", serverError.getMessage());
            assertEquals(new Integer(14007), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de consulta de transacoes abandonada$")
    public void erro_consulta_transacao_abandonadas() throws Throwable {
        System.out.println("Transa��o abandonada inv�lida.");
    }

    // Cenario: Consultar transacoes por codigo de referencia
    // Dado que esteja autenticado na api do pagseuro
    // Quando pesquiso uma transacao por codigo de referencia
    // Entao e retornada a transacao pela referencia

    @Quando("^pesquiso uma transacao por codigo de referencia$")
    public void pesquisa_codigo_referencia() throws Throwable {
        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            final DataList<? extends TransactionSummary> transactionDate = pagSeguro.transactions().search().byReference("LIBJAVA0000001");

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
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(SELLER_EMAIL, SELLER_TOKEN), PagSeguroEnv.SANDBOX);

            final DataList<? extends TransactionSummary> transactionDate = pagSeguro.transactions().search().byReference("");

            transactionDate.size();
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
        System.out.println("Erro Consulta Transa��o por refer�ncia.");
    }
}