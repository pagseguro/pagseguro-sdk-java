package br.com.uol.pagseguro.api.application.authorization.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.Permission;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class AuthorizationSearchByCodeTest extends Resource4Test {

  private AuthorizationSearchByCode authorizationSearchByCode;

  @Before
  public void setUp() throws Exception {
    authorizationSearchByCode = new AuthorizationSearchByCode("code");
  }

  @Test
  public void shouldSearch() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                              "<authorization>\n" +
                              "<code>0F9DE39A0CF949538606EE7217620ADB</code>\n" +
                              "<authorizerEmail>" +
                              "v82066140592075093489@sandbox.pagseguro.com.br" +
                              "</authorizerEmail>\n" +
                              "<creationDate>2016-11-09T00:00:00.000-02:00</creationDate>\n" +
                              "<reference>AUTH_LIB_JAVA_0001</reference>\n" +
                              "<account>\n" +
                              "<publicKey>PUBD53899901C224B07B8719FD9CB4473E7</publicKey>\n" +
                              "</account>\n" +
                              "<permissions>\n" +
                              "<permission>\n" +
                              "<code>CREATE_CHECKOUTS</code>\n" +
                              "<status>APPROVED</status>\n" +
                              "<lastUpdate>2016-11-09T23:59:59.000-02:00</lastUpdate>\n" +
                              "</permission>\n" +
                              "</permissions>\n" +
                              "</authorization>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    AuthorizationDetail authorizationDetail = authorizationSearchByCode.execute(pagSeguro,
        httpClient);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    assertEquals("0F9DE39A0CF949538606EE7217620ADB", authorizationDetail.getCode());
    assertEquals("v82066140592075093489@sandbox.pagseguro.com.br",
        authorizationDetail.getAuthorizerEmail());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), authorizationDetail.getCreationDate());
    assertEquals("AUTH_LIB_JAVA_0001", authorizationDetail.getReference());
    assertEquals("PUBD53899901C224B07B8719FD9CB4473E7",
        authorizationDetail.getAccount().getPublicKey());
    Permission permission = authorizationDetail.getPermissions().iterator().next();
    assertEquals("CREATE_CHECKOUTS", permission.getCode().getCodeId());
    assertEquals("APPROVED", permission.getStatus());
    assertEquals(dateFormat.parse("2016-11-09 23:59:59"), permission.getLastUpdate());
  }

  @Test
  public void shouldThrowsBadRequest() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>0001</code>" +
                                "<message>Code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      authorizationSearchByCode.execute(pagSeguro, httpClient);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0001), error.getCode());
      assertEquals("Code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    authorizationSearchByCode.execute(pagSeguro, httpClient);
  }
}