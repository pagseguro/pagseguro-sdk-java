package br.com.uol.pagseguro.api.checkout;

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
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ParameterBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
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
public class CheckoutsResourceTest extends Resource4Test {

  private CheckoutsResource checkoutsResource;

  private CheckoutRegistration checkoutRegistration;

  @Before
  public void setUp() throws Exception {
    checkoutsResource = new CheckoutsResource(pagSeguro, httpClient);
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    checkoutRegistration = new CheckoutRegistrationBuilder()
        .withCurrency(Currency.BRL)
        .withExtraAmount(new BigDecimal(9.99))
        .withReference("reference")
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
        .addItem(new PaymentItemBuilder()
            .withId("1")
            .withDescription("description1")
            .withAmount(new BigDecimal(99.99))
            .withQuantity(7)
            .withWeight(123)
            .withShippingCost(new BigDecimal(99.99))
        )
        .addItem(new PaymentItemBuilder()
            .withId("2")
            .withDescription("description2")
            .withAmount(new BigDecimal(99.99))
            .withQuantity(7)
            .withWeight(123)
            .withShippingCost(new BigDecimal(99.99))
        )
        .withPreApproval(new PreApprovalBuilder()
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
        .addParameter(new ParameterBuilder()
            .withName("param2")
            .withValue("value2")
        )
        .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
            .withConfig(new ConfigBuilder()
                .withKey(ConfigKey.DISCOUNT_PERCENT)
                .withValue(new BigDecimal(10))
            )
            .withPaymentMethod(new PaymentMethodBuilder()
                .withGroup(PaymentMethodGroup.BALANCE)
            )
        )
        .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
            .withConfig(new ConfigBuilder()
                .withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
                .withValue(new BigDecimal(2))
            )
            .withPaymentMethod(new PaymentMethodBuilder()
                .withGroup(PaymentMethodGroup.CREDIT_CARD)
            )
        )
        .build();
  }

  @Test
  public void shouldRegister() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<checkout>" +
                              "<code>8CF4BE7DCECEF0F004A6DFA0A8243412</code>" +
                              "<date>2016-11-09T00:00:00.000-03:00</date>" +
                              "</checkout>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    RegisteredCheckout registeredCheckout = checkoutsResource.register(checkoutRegistration);

    assertEquals("8CF4BE7DCECEF0F004A6DFA0A8243412", registeredCheckout.getCheckoutCode());
    assertEquals("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=" +
                 "8CF4BE7DCECEF0F004A6DFA0A8243412", registeredCheckout.getRedirectURL());
  }

  @Test
  public void shouldThrowsBadRequestOnRegister() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>11004</code>" +
                                "<message>Currency is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      checkoutsResource.register(checkoutRegistration);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(11004), error.getCode());
      assertEquals("Currency is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnRegister() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    checkoutsResource.register(checkoutRegistration);
  }

}