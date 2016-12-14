package br.com.uol.pagseguro.api.installment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.DataList;
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
public class InstallmentsListResourceTest extends Resource4Test{

  private InstallmentsListResource installmentsListResource;

  private InstallmentRequest installmentRequest;

  @Before
  public void setUp() throws Exception {
    installmentsListResource = new InstallmentsListResource(pagSeguro, httpClient);

    installmentRequest = new InstallmentRequestBuilder()
        .withCardBrand("visa")
        .withAmount(new BigDecimal(100))
        .withMaxInstallmentNoInterest(2)
        .build();
  }

  @Test
  public void shouldList() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>" +
                              "<installments>" +
                              "<installment>" +
                              "<cardBrand>visa</cardBrand>" +
                              "<quantity>1</quantity>" +
                              "<amount>100.00</amount>" +
                              "<totalAmount>100.00</totalAmount>" +
                              "<interestFree>true</interestFree>" +
                              "</installment>" +
                              "<installment>" +
                              "<cardBrand>visa</cardBrand>" +
                              "<quantity>2</quantity>" +
                              "<amount>50.00</amount>" +
                              "<totalAmount>100.00</totalAmount>" +
                              "<interestFree>true</interestFree>" +
                              "</installment>" +
                              "<installment>" +
                              "<cardBrand>visa</cardBrand>" +
                              "<quantity>3</quantity>" +
                              "<amount>34.33</amount>" +
                              "<totalAmount>102.99</totalAmount>" +
                              "<interestFree>false</interestFree>" +
                              "</installment>" +
                              "</installments>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    DataList<? extends InstallmentDetail> data = installmentsListResource.list(installmentRequest);

    Iterator<InstallmentDetail> iterator = (Iterator<InstallmentDetail>) data.iterator();

    InstallmentDetail installment = iterator.next();
    assertEquals("visa", installment.getCardBrand());
    assertEquals(new Integer(1), installment.getQuantity());
    assertEquals(new BigDecimal(100.00).setScale(2), installment.getAmount());
    assertEquals(new BigDecimal(100.00).setScale(2), installment.getTotalAmount());
    assertEquals(true, installment.getInterestFree());

    installment = iterator.next();
    assertEquals("visa", installment.getCardBrand());
    assertEquals(new Integer(2), installment.getQuantity());
    assertEquals(new BigDecimal(50.00).setScale(2), installment.getAmount());
    assertEquals(new BigDecimal(100.00).setScale(2), installment.getTotalAmount());
    assertEquals(true, installment.getInterestFree());

    installment = iterator.next();
    assertEquals("visa", installment.getCardBrand());
    assertEquals(new Integer(3), installment.getQuantity());
    assertEquals(new BigDecimal(34.33).setScale(2, RoundingMode.HALF_EVEN),
        installment.getAmount());
    assertEquals(new BigDecimal(102.99).setScale(2, RoundingMode.HALF_EVEN),
        installment.getTotalAmount());
    assertEquals(false, installment.getInterestFree());
  }

  @Test
  public void shouldThrowsBadRequestOnList() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>0001</code>" +
                                "<message>Card brand is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      installmentsListResource.list(installmentRequest);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0001), error.getCode());
      assertEquals("Card brand is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnList() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    installmentsListResource.list(installmentRequest);
  }

}