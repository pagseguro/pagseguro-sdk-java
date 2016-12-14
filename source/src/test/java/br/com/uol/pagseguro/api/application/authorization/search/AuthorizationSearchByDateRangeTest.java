package br.com.uol.pagseguro.api.application.authorization.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.Permission;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class AuthorizationSearchByDateRangeTest extends Resource4Test {

  private AuthorizationSearchByDateRange authorizationSearchByDateRange;

  private AuthorizationSearch authorizationSearch;

  @Before
  public void setUp() throws Exception {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    authorizationSearch = new AuthorizationSearchBuilder()
        .withDateRange(new DateRangeBuilder()
            .between(dateFormat.parse("2016-11-09 00:00:00"),
                dateFormat.parse("2016-11-09 23:59:59"))
        )
        .withReference("reference")
        .withPage(2)
        .withMaxResults(27)
        .build();

    authorizationSearchByDateRange = new AuthorizationSearchByDateRange(authorizationSearch);
  }

  @Test
  public void shouldSearch() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                              "<authorizationSearchResult>" +
                              "<date>2016-09-02T00:00:00.000-03:00</date>" +
                              "<authorizations>" +
                              "<authorization>" +
                              "<code>0F9DE39A0CF949538606EE7217620ADB</code>" +
                              "<authorizerEmail>v82066140592075093489@sandbox.pagseguro.com.br</authorizerEmail>" +
                              "<creationDate>2016-11-09T00:00:00.000-02:00</creationDate>" +
                              "<reference>AUTH_LIB_JAVA_0001</reference>" +
                              "<account>" +
                              "<publicKey>PUBD53899901C224B07B8719FD9CB4473E7</publicKey>" +
                              "</account>" +
                              "<permissions>" +
                              "<permission>" +
                              "<code>CREATE_CHECKOUTS</code>" +
                              "<status>APPROVED</status>" +
                              "<lastUpdate>2016-11-09T23:59:59.000-02:00</lastUpdate>" +
                              "</permission>" +
                              "</permissions>" +
                              "</authorization>" +
                              "<authorization>" +
                              "<code>code</code>" +
                              "<authorizerEmail>teste@sandbox.pagseguro.com.br</authorizerEmail>" +
                              "<creationDate>2016-11-09T00:00:00.000-02:00</creationDate>" +
                              "<reference>AUTH_LIB_JAVA_0001</reference>" +
                              "<account>" +
                              "<publicKey>publicKey</publicKey>" +
                              "</account>" +
                              "<permissions>" +
                              "<permission>" +
                              "<code>CREATE_CHECKOUTS</code>" +
                              "<status>APPROVED</status>" +
                              "<lastUpdate>2016-11-09T23:59:59.000-02:00</lastUpdate>" +
                              "</permission>" +
                              "</permissions>" +
                              "</authorization>" +
                              "</authorizations>" +
                              "<resultsInThisPage>1</resultsInThisPage>" +
                              "<currentPage>1</currentPage>" +
                              "<totalPages>1</totalPages>" +
                              "</authorizationSearchResult>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    DataList<? extends AuthorizationSummary> data =
        authorizationSearchByDateRange.execute(pagSeguro, httpClient);

    assertEquals(new Integer(1), data.getTotalPages());
    assertEquals(false, data.isEmpty());

    Iterator<AuthorizationSummary> authorizationSummaryIterator =
        (Iterator<AuthorizationSummary>) data.getData().iterator();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    AuthorizationSummary authorization = authorizationSummaryIterator.next();
    assertEquals("0F9DE39A0CF949538606EE7217620ADB", authorization.getCode());
    assertEquals("v82066140592075093489@sandbox.pagseguro.com.br",
        authorization.getAuthorizerEmail());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), authorization.getCreationDate());
    assertEquals("AUTH_LIB_JAVA_0001", authorization.getReference());
    assertEquals("PUBD53899901C224B07B8719FD9CB4473E7", authorization.getAccount().getPublicKey());
    Permission permission = authorization.getPermissions().iterator().next();
    assertEquals("CREATE_CHECKOUTS", permission.getCode().getCodeId());
    assertEquals("APPROVED", permission.getStatus());
    assertEquals(dateFormat.parse("2016-11-09 23:59:59"), permission.getLastUpdate());

    authorization = authorizationSummaryIterator.next();
    assertEquals("code", authorization.getCode());
    assertEquals("teste@sandbox.pagseguro.com.br", authorization.getAuthorizerEmail());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), authorization.getCreationDate());
    assertEquals("AUTH_LIB_JAVA_0001", authorization.getReference());
    assertEquals("publicKey", authorization.getAccount().getPublicKey());
    permission = authorization.getPermissions().iterator().next();
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
                                "<code>0002</code>" +
                                "<message>Date range is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      authorizationSearchByDateRange.execute(pagSeguro, httpClient);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0002), error.getCode());
      assertEquals("Date range is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    authorizationSearchByDateRange.execute(pagSeguro, httpClient);
  }

}