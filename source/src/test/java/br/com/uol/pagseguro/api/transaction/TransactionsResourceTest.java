package br.com.uol.pagseguro.api.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.builder.ParameterBuilder;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class TransactionsResourceTest extends Resource4Test {

  private TransactionsResource transactionsResource;

  private TransactionIdentify transactionIdentify;

  @Before
  public void setUp() throws Exception {
    transactionsResource = new TransactionsResource(pagSeguro, httpClient);

    transactionIdentify = new TransactionIdentifyBuilder()
        .withCode("code")
        .addParameter(new ParameterBuilder()
            .withName("param1")
            .withValue("value1")
        )
        .build();
  }

  @Test
  public void shouldCancel() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<result>OK</result>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    transactionsResource.cancel(transactionIdentify);

    verify(httpClient, times(1)).execute(any(HttpMethod.class), anyString(),
        anyMap(), any(HttpRequestBody.class));

  }

  @Test
  public void shouldThrowsBadRequestOnCancel() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>00001</code>" +
                                "<message>Transaction code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      transactionsResource.cancel(transactionIdentify);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(00001), error.getCode());
      assertEquals("Transaction code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnCancel() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    transactionsResource.cancel(transactionIdentify);
  }

  @Test
  public void shouldRefund() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<result>OK</result>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    transactionsResource.refund(transactionIdentify, new BigDecimal(99.99));

    verify(httpClient, times(1)).execute(any(HttpMethod.class), anyString(),
        anyMap(), any(HttpRequestBody.class));
  }

  @Test
  public void shouldThrowsBadRequestOnRefund() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>00001</code>" +
                                "<message>Transaction code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      transactionsResource.refund(transactionIdentify, new BigDecimal(99.99));
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(00001), error.getCode());
      assertEquals("Transaction code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnRefund() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    transactionsResource.refund(transactionIdentify, new BigDecimal(99.99));
  }
}