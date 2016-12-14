package br.com.uol.pagseguro.api.session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.exception.PagSeguroUnauthorizedException;
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
public class SessionResourceTest extends Resource4Test {

  private SessionResource sessionResource;

  private String responseAsString;

  @Before
  public void setUp() throws Exception {
    sessionResource = new SessionResource(pagSeguro, httpClient);

    responseAsString =
        "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>" +
            "<session>" +
            "<id>d8ebd56d32444161852eeb33a6112df6</id>" +
            "</session>";
  }

  @Test
  public void shouldCreateSellerSession() throws IOException {
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    CreatedSession createdSession = sessionResource.create();

    assertEquals(createdSession.getId(), "d8ebd56d32444161852eeb33a6112df6");

  }

  @Test
  public void shouldBeCreateApplicationSession() throws IOException {

    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    CreatedSession createdSession = sessionResource.create("Code");

    assertEquals(createdSession.getId(), "d8ebd56d32444161852eeb33a6112df6");
  }

  @Test(expected = PagSeguroUnauthorizedException.class)
  public void shouldThrowsUnauthorizedWhenCreateApplication() throws IOException {
    HttpResponse response = new HttpResponse(401, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(PagSeguroUnauthorizedException.class);

    sessionResource.create("Code");

  }

  @Test(expected = PagSeguroUnauthorizedException.class)
  public void shouldThrowsUnauthorizedWhenCreateSeller() throws IOException {
    HttpResponse response = new HttpResponse(401, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(PagSeguroUnauthorizedException.class);

    sessionResource.create();

  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());

    sessionResource.create();
  }



}
