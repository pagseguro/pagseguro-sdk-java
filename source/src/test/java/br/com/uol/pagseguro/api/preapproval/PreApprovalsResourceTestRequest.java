package br.com.uol.pagseguro.api.preapproval;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ParameterBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalRequestBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.preapproval.cancel.CancelledPreApproval;
import br.com.uol.pagseguro.api.preapproval.cancel.PreApprovalCancellation;
import br.com.uol.pagseguro.api.preapproval.cancel.PreApprovalCancellationBuilder;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class PreApprovalsResourceTestRequest extends Resource4Test {

  private PreApprovalsResource preApprovalsResource;

  private PreApprovalRegistration preApprovalRegistration;

  private PreApprovalCharging preApprovalCharging;

  private PreApprovalCancellation preApprovalCancellation;

  private DateFormat dateFormat;

  @Before
  public void setUp() throws Exception {
    preApprovalsResource = new PreApprovalsResource(pagSeguro, httpClient);
    dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    preApprovalRegistration = new PreApprovalRegistrationBuilder()
        .withRedirectURL("redirectUrl")
        .withNotificationURL("notificationUrl")
        .withCurrency(Currency.BRL)
        .withExtraAmount(new BigDecimal(9.99))
        .withReference("reference")
        .withShipping(new ShippingBuilder()
            .withAddress(new AddressBuilder()
                .withCountry("BRA")
                .withState("PA")
                .withPostalCode("99999999")
                .withCity("city")
                .withDistrict("district")
                .withStreet("street")
                .withNumber("999")
                .withComplement("complement"))
            .withType(ShippingType.Type.SEDEX)
            .withCost(new BigDecimal(99.99))
        )
        .withSender(new SenderBuilder()
            .withEmail("email@email.com")
            .withName("name")
            .withPhone(new PhoneBuilder()
                .withAreaCode("16")
                .withNumber("123456789")
            )
            .withAddress(new AddressBuilder()
                .withCountry("BRA")
                .withState("PA")
                .withPostalCode("99999999")
                .withCity("city")
                .withDistrict("district")
                .withStreet("street")
                .withNumber("999")
                .withComplement("complement")
            )
            .withCPF("99999999999")
            .withHash("hash")
        )
        .withPreApproval(new PreApprovalRequestBuilder()
            .withCharge("charge")
            .withName("name")
            .withDetails("details")
            .withAmountPerPayment(new BigDecimal(99.99))
            .withMaxAmountPerPayment(new BigDecimal(99.99))
            .withMaxTotalAmount(new BigDecimal(99.99))
            .withMaxAmountPerPeriod(new BigDecimal(99.99))
            .withMaxPaymentsPerPeriod(4)
            .withPeriod("period")
            .withDateRange(new DateRangeBuilder()
                .between(dateFormat.parse("2016/11/09 00:00:00"),
                    dateFormat.parse("2016/11/09 23:59:59"))
            )
        )
        .addParameter(new ParameterBuilder()
            .withName("param1")
            .withValue("value1")
        )
        .build();

    preApprovalCharging = new PreApprovalChargingBuilder()
        .withCode("code")
        .withReference("reference")
        .addItem(new PaymentItemBuilder()
            .withId("2")
            .withDescription("description2")
            .withAmount(new BigDecimal(99.99))
            .withQuantity(7)
            .withWeight(123)
            .withShippingCost(new BigDecimal(99.99))
        )
        .addParameter(new ParameterBuilder()
            .withName("param1")
            .withValue("value1")
        )
        .build();

    preApprovalCancellation = new PreApprovalCancellationBuilder()
        .withCode("code")
        .addParameter(new ParameterBuilder()
            .withName("param1")
            .withValue("value1")
        )
        .build();
  }

  @Test
  public void shouldRegister() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<preApprovalRequest>" +
                              "<code>code</code>" +
                              "<date>2016-11-09T00:00:00.000-03:00</date>" +
                              "</preApprovalRequest>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    RegisteredPreApproval registeredPreApproval = preApprovalsResource
        .register(preApprovalRegistration);

    assertEquals("code", registeredPreApproval.getPreApprovalCode());
    assertEquals("https://sandbox.pagseguro.uol.com.br/v2/pre-approvals/request.html?code=" +
                 "code", registeredPreApproval.getRedirectURL());

  }

  @Test
  public void shouldThrowsBadRequestOnRegister() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>00001</code>" +
                                "<message>Currency is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      preApprovalsResource.register(preApprovalRegistration);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(00001), error.getCode());
      assertEquals("Currency is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnRegister() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    preApprovalsResource.register(preApprovalRegistration);
  }

  @Test
  public void shouldCharge() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<result>" +
                              "<transactionCode>code</transactionCode>" +
                              "<date>2016-11-09T00:00:00.000-02:00</date>" +
                              "</result>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    ChargedPreApproval chargedPreApproval = preApprovalsResource.charge(preApprovalCharging);

    assertEquals("code", chargedPreApproval.getTransactionCode());

  }

  @Test
  public void shouldThrowsBadRequestOnCharge() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>00001</code>" +
                                "<message>Code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      preApprovalsResource.charge(preApprovalCharging);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(00001), error.getCode());
      assertEquals("Code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnCharge() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    preApprovalsResource.charge(preApprovalCharging);
  }

  @Test
  public void shouldCancel() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<result>" +
                              "<date>2016-11-09T00:00:00.000-02:00</date>" +
                              "<status>OK</status>" +
                              "</result>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    CancelledPreApproval cancelledPreApproval = preApprovalsResource
        .cancel(preApprovalCancellation);

    assertEquals("OK", cancelledPreApproval.getTransactionStatus());

  }

  @Test
  public void shouldThrowsBadRequestOnCancel() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>00001</code>" +
                                "<message>Code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      preApprovalsResource.cancel(preApprovalCancellation);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(00001), error.getCode());
      assertEquals("Code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnCancel() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    preApprovalsResource.cancel(preApprovalCancellation);
  }

}