package br.com.uol.pagseguro.api.preapproval.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.PreApprovalStatus;
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
public class PreApprovalSearchByCodeTest extends Resource4Test {

  private PreApprovalSearchByCode preApprovalSearchByCode;

  @Before
  public void setUp() throws Exception {
    preApprovalSearchByCode = new PreApprovalSearchByCode("code");
  }

  @Test
  public void shouldSearch() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                              "<preApproval>" +
                              "<name>name</name>" +
                              "<code>code</code>" +
                              "<date>2016-11-09T00:00:00.000-02:00</date>" +
                              "<tracker>tracker</tracker>" +
                              "<status>CANCELLED</status>" +
                              "<reference>reference</reference>" +
                              "<lastEventDate>2016-11-09T23:59:59.000-02:00</lastEventDate>" +
                              "<charge>auto</charge>" +
                              "<sender>" +
                              "<name>sender name</name>" +
                              "<email>email</email>" +
                              "<phone>" +
                              "<areaCode>16</areaCode>" +
                              "<number>123456789</number>" +
                              "</phone>" +
                              "<address>" +
                              "<street>street</street>" +
                              "<number>99</number>" +
                              "<complement>complement</complement>" +
                              "<district>district</district>" +
                              "<city>city</city>" +
                              "<state>SP</state>" +
                              "<country>country</country>" +
                              "<postalCode>12345678</postalCode>" +
                              "</address>" +
                              "</sender>" +
                              "</preApproval>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    PreApprovalDetail preApprovalDetail = preApprovalSearchByCode.execute(pagSeguro, httpClient);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    assertEquals("name", preApprovalDetail.getName());
    assertEquals("code", preApprovalDetail.getCode());
    assertEquals("tracker", preApprovalDetail.getTracker());
    assertEquals(PreApprovalStatus.Status.CANCELLED, preApprovalDetail.getStatus().getStatus());
    assertEquals("reference", preApprovalDetail.getReference());
    assertEquals(dateFormat.parse("2016-11-09 23:59:59"), preApprovalDetail.getLastEvent());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), preApprovalDetail.getDate());
    assertEquals("auto", preApprovalDetail.getCharge());
    assertEquals("sender name", preApprovalDetail.getSender().getName());
    assertEquals("email", preApprovalDetail.getSender().getEmail());
    assertEquals("16", preApprovalDetail.getSender().getPhone().getAreaCode());
    assertEquals("123456789", preApprovalDetail.getSender().getPhone().getNumber());
    assertEquals("street", preApprovalDetail.getSender().getAddress().getStreet());
    assertEquals("99", preApprovalDetail.getSender().getAddress().getNumber());
    assertEquals("complement", preApprovalDetail.getSender().getAddress().getComplement());
    assertEquals("district", preApprovalDetail.getSender().getAddress().getDistrict());
    assertEquals("city", preApprovalDetail.getSender().getAddress().getCity());
    assertEquals("SP", preApprovalDetail.getSender().getAddress().getState());
    assertEquals("country", preApprovalDetail.getSender().getAddress().getCountry());
    assertEquals("12345678", preApprovalDetail.getSender().getAddress().getPostalCode());
  }

  @Test
  public void shouldThrowsBadRequest() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>0001</code>" +
                                "<message>Pre approval code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      preApprovalSearchByCode.execute(pagSeguro, httpClient);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0001), error.getCode());
      assertEquals("Pre approval code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    preApprovalSearchByCode.execute(pagSeguro, httpClient);
  }
}