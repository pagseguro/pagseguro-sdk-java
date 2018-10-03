package integration.Modulos;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistration;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistrationBuilder;
import br.com.uol.pagseguro.api.application.authorization.RegisteredAuthorization;
import br.com.uol.pagseguro.api.application.authorization.search.AuthorizationDetail;
import br.com.uol.pagseguro.api.application.authorization.search.AuthorizationSummary;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.PermissionCode.Code;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroInternalServerException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Autorization {

    private static final String APP_ID = "INFORMAR USER APLICA��O";
    private static final String APP_KEY = "INFORMAR SENHA APLICA��O";
    private static final String EMAIL_SANDBOX = "INFORMAR EMAIL SANDBOX";
    private static final String SENHA_SANDBOX = "INFORMAR SENHA SANDBOX";
    private static final String VENDEDOR_USER = "INFORMAR USUARIO VENDEDOR";
    private static final String VENDEDOR_SENHA = "INFORMAR SENHA VENDEDOR";

    String codigo = null;
    Integer codigoRef = null;
    String url = null;

    //	Cenario: Criar uma autorizacao
    //	Dado que esteja autenticado na api do pagseguro
    //	Quando crio uma requisicao de autorizacao
    //	Entao e retornado a url de autorizacao

    @Quando("^crio uma requisicao de autorizacao$")
    public void crio_uma_autorizacao() throws Throwable {

        try {

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            AuthorizationRegistration authorizationRegistration = new AuthorizationRegistrationBuilder().withNotificationUrl("http://www.sualoja.com.br/retornopagamento.php").withRedirectURL("http://www.sualoja.com.br/retornopagamento.php").withReference("REF_001").addPermission(Code.CREATE_CHECKOUTS).build();

            RegisteredAuthorization registeredAuthorization = pagSeguro.authorizations().register(authorizationRegistration);

            url = registeredAuthorization.getRedirectURL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Entao("^e retornado a url de autorizacao")
    public void finalizacao_autorizacao() throws Throwable {
        File file = new File("\\BIN\\phantomjs.exe");     //informar caminho do arquivo phantomJS
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        WebDriver driver = new PhantomJSDriver();
        //
        //	 	    System.setProperty(
        //					"webdriver.chrome.driver"
        //					, "src/test/resources/driver/win32/chromedriver.exe");
        //	 	    WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.id("link-login")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email-input")).sendKeys(VENDEDOR_USER);
        driver.findElement(By.id("password-input")).sendKeys(VENDEDOR_SENHA);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(12000);
        driver.findElement(By.className("pagseguro-button")).click();
        Thread.sleep(5000);
    }

    //		Cenario: Criar uma autorizacao invalida
    //		Dado que esteja autenticado na api do pagseguro
    //		Quando crio uma requisicao de autorizacao invalida
    //		Entao e retornado um erro de autorizacao

    @Quando("^crio uma requisicao de autorizacao invalida$")
    public void crio_uma_autorizacao_invalida() throws Throwable {

        try {

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            AuthorizationRegistration authorizationRegistration = new AuthorizationRegistrationBuilder().withNotificationUrl("http://www.sualoja.com.br/retornopagamento.php").withRedirectURL("http://www.sualoja.com.br/retornopagamento.php").withReference("REF_001").build();

            RegisteredAuthorization registeredAuthorization = pagSeguro.authorizations().register(authorizationRegistration);
            System.out.println(registeredAuthorization.getRedirectURL());
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("permissions is required.", serverError.getMessage());
            assertEquals(new Integer(12003), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de autorizacao$")
    public void retorno_erro_autorizacao() throws Throwable {

    }

    //		Cenario: Consultar autorizacao por codigo
    //		Dado que esteja autenticado na api do pagseguro
    //		Quando consulto uma autorizacao pelo codigo
    //		Entao e retornada a autorizacao

    @Quando("^consulto uma autorizacao pelo codigo$")
    public void consultar_autorizacao_por_codigo() throws Throwable {

        crio_uma_autorizacao();

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            AuthorizationDetail authorizationDetail = pagSeguro.authorizations().search().byCode(buscar_codigo_autorizacao()); //INFORMAR UMA AUTORIZA��O V�LIDA QUE ESTEJA LINKADA AO USUARIO

            codigo = authorizationDetail.toString();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());
        }
    }

    private String buscar_codigo_autorizacao() throws Throwable {
        //			 System.setProperty(
        //						"webdriver.chrome.driver"
        //						, "src/test/resources/driver/win32/chromedriver.exe");
        //
        //				 WebDriver driver = new ChromeDriver();

        File file = new File("bin\\phantomjs.exe");     //informar caminho do arquivo phantomJS
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
        driver.findElement(By.linkText("Autoriza��es")).click();
        Thread.sleep(10000);
        int index = 1;
        WebElement baseTable = driver.findElement(By.id("authorization-list-table"));
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        tableRows.get(index).click();
        Thread.sleep(3000);
        String codigoAutorizacao = driver.findElement(By.id("authorization-code")).getText();

        System.out.println(codigoAutorizacao);

        return codigoAutorizacao;
    }

    @Entao("^e retornada a autorizacao$")
    public void retornada_a_autorizacao() throws Throwable {
        System.out.println(codigo);
    }

    //		Cenario: Consultar autorizacao por codigo invalida
    //		Dado que esteja autenticado na api do pagseguro
    //		Quando consulto uma autorizacao pelo codigo invalida
    //		Entao e retornado um erro de consulta de autorizacao por codigo

    @Quando("^consulto uma autorizacao pelo codigo invalida$")
    public void consultar_autorizacao_codigo_invalida() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            AuthorizationDetail authorizationDetail = pagSeguro.authorizations().search().byCode("454545");

            System.out.print(authorizationDetail);
        } catch (Exception e) {

            assertEquals(true, e instanceof PagSeguroInternalServerException);
        }
    }

    @Entao("^e retornado um erro de consulta de autorizacao por codigo$")
    public void retorno_erro_consulta_autorizacao_codigo() throws Throwable {

    }

    //		Cenario: Consultar autorizacao por intervalo de datas
    //		Dado que esteja autenticado na api do pagseguro
    //		Quando consulto uma autorizacao por intervalo de datas
    //		Entao e retornada autorizacoes por intervalo de data

    @Quando("^consulto uma autorizacao por intervalo de datas$")
    public void autenticado_consulta_por_datas() throws Throwable {

        crio_uma_autorizacao();
        finalizacao_autorizacao();

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //INFORMAR A DATA ATUAL

            Date date = dateFormat.parse("26/10/2016");
            Date date2 = dateFormat.parse("28/10/2016");
            final DataList<? extends AuthorizationSummary> authorizationsDate = pagSeguro.authorizations().search().byDateRange(new DateRangeBuilder().between(date, date2), 1, 10);

            codigo = authorizationsDate.toString();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());
        }
    }

    @Entao("^e retornada autorizacoes por intervalo de data$")
    public void retorno_autorizacoes_data() throws Throwable {
        System.out.println(codigo);
    }

    //Cenario: Consultar autorizacao por intervalo de datas invalida
    //Dado que esteja autenticado na api do pagseguro
    //Quando consulto uma autorizacao por intervalo de datas invalida
    //Entao e retornado um erro de consulta de autorizacao por data

    @Dado("^consulto uma autorizacao por intervalo de datas invalida$")
    public void autorizacao_intervalo_data_invalida() throws Throwable {

        try {

            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = dateFormat.parse("26/10/2016");
            Date date2 = dateFormat.parse("25/10/2016");
            final DataList<? extends AuthorizationSummary> authorizationsDate = pagSeguro.authorizations().search().byDateRange(new DateRangeBuilder().between(date, date2), 1, 10);

            System.out.print(authorizationsDate);
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("initialDate must be lower than or equal finalDate.", serverError.getMessage());
            assertEquals(new Integer(13007), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de consulta de autorizacao por data$")
    public void retorno_erro_consulta_autorizacao_data() throws Throwable {

    }

    //Cenario: Consultar autorizacao por codigo de notificacao
    //Dado que esteja autenticado na api do pagseguro
    //Quando consulto uma autorizacao por codigo de notificacao
    //Entao e retornada autorizacao por codigo de notificacao

    @Quando("^consulto uma autorizacao por codigo de notificacao$")
    public void consultar_autorizacao_por_notificacao() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            AuthorizationDetail authorizationCode = pagSeguro.authorizations().search().byNotificationCode(buscar_codigo_notificacao_autorizacao());

            codigo = authorizationCode.getCode();
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());
        }
    }

    public String buscar_codigo_notificacao_autorizacao() throws Throwable {

        //	    System.setProperty(
        //			"webdriver.chrome.driver"
        //			, "src/test/resources/driver/win32/chromedriver.exe");

        File file = new File("bin\\phantomjs.exe");     //informar caminho do arquivo phantomJS
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
        driver.findElement(By.linkText("Autoriza��es")).click();
        Thread.sleep(10000);
        int index = 1;
        WebElement baseTable = driver.findElement(By.id("authorization-list-table"));
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        tableRows.get(index).click();
        Thread.sleep(3000);
        int notify = 0;
        WebElement baseTablenotify = driver.findElement(By.cssSelector("#nas-data > div.nas-data-area > table"));
        List<WebElement> tableRowsNotify = baseTablenotify.findElements(By.cssSelector("#nas-data > div.nas-data-area > table > tbody > tr > td.col-code"));
        String codigoNotification = tableRowsNotify.get(notify).findElement(By.className("nas-code")).getText();
        System.out.println(codigoNotification);

        return codigoNotification;

        // TODO Auto-generated method stub
    }

    @Entao("^e retornada autorizacao por codigo de notificacao$")
    public void retorno_autorizacao_codigo_notificacao() throws Throwable {
        System.out.println(codigo);
    }

    //Cenario: Consultar autorizacao por codigo de notificacao invalida
    //Dado que esteja autenticado na api do pagseguro
    //Quando consulte uma autorizacao por codigo de notificacao invalida
    //Entao e retornado um erro de consulta de autorizacao por notificacao

    @Quando("^consulto uma autorizacao por codigo de notificacao invalida$")
    public void consulte_autorizacao_notificacao_invalida() throws Throwable {
        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            AuthorizationDetail authorizationCode = pagSeguro.authorizations().search().byNotificationCode("9898989898");

            System.out.println(authorizationCode);
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("invalid notification code value: 9898989898", serverError.getMessage());
            assertEquals(new Integer(13001), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de consulta de autorizacao por notificacao$")
    public void retorno_erro_autorizacao_notificacao() throws Throwable {

    }

    //Cenario: Consultar autorizacao por codigo de referencia
    //Dado que esteja autenticado na api do pagseguro
    //Quando consulto uma autorizacao por referencia
    //Entao e retornada as autorizacoes por referencia

    @Quando("^consulto uma autorizacao por referencia$")
    public void consulte_autorizacao_referencia() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            final DataList<? extends AuthorizationSummary> authorizationRef = pagSeguro.authorizations().search().byReference("REF_001");

            codigoRef = authorizationRef.hashCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Entao("^e retornada as autorizacoes por referencia$")
    public void retorno_autorizacao_referencia() throws Throwable {
        System.out.println(codigoRef);
    }

    //		Cenario: Consultar autorizacao por codigo de referencia invalida
    //		Dado que esteja autenticado na api do pagseguro
    //		Quando consulte uma autorizacao por referencia invalida
    //		Entao e retornado um erro de consulta de autorizacao por codigo de referencia

    @Quando("^consulto uma autorizacao por referencia invalida$")
    public void consulte_autorizacao_referencia_invalida() throws Throwable {

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(APP_ID, APP_KEY), PagSeguroEnv.SANDBOX);

            final DataList<? extends AuthorizationSummary> authorizationRef = pagSeguro.authorizations().search().byReference("");

            System.out.print(authorizationRef);
        } catch (PagSeguroBadRequestException e) {
            System.out.println(e.getErrors());

            ServerErrors serverErros = e.getErrors();
            ServerError serverError = serverErros.getErrors().iterator().next();

            assertEquals("permissions is required.", serverError.getMessage());
            assertEquals(new Integer(12003), serverError.getCode());
        }
    }

    @Entao("^e retornado um erro de consulta de autorizacao por codigo de referencia$")
    public void retorno_consulta_autorizacao_referencia() throws Throwable {

    }
}