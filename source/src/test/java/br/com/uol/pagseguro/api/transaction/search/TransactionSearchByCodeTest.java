package br.com.uol.pagseguro.api.transaction.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.TransactionPaymentMethod;
import br.com.uol.pagseguro.api.common.domain.TransactionStatus;
import br.com.uol.pagseguro.api.common.domain.TransactionType;
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
public class TransactionSearchByCodeTest extends Resource4Test {

  private TransactionSearchByCode transactionSearchByCode;

  @Before
  public void setUp() throws Exception {
    transactionSearchByCode = new TransactionSearchByCode("code");
  }

  @Test
  public void shouldSearch() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                              "<transaction>\n" +
                              "  <reference>reference</reference>\n" +
                              "  <date>2016-11-09T00:00:00.000-02:00</date>\n" +
                              "  <items>\n" +
                              "    <item>\n" +
                              "      <id>0001</id>\n" +
                              "      <description>description</description>\n" +
                              "      <quantity>1</quantity>\n" +
                              "      <amount>9.99</amount>\n" +
                              "    </item>\n" +
                              "  </items>\n" +
                              "  <status>INITIATED</status>\n" +
                              "  <type>CHECKOUT</type>\n" +
                              "  <code>code</code>\n" +
                              "  <itemCount>1</itemCount>\n" +
                              "  <installmentCount>1</installmentCount>\n" +
                              "  <sender>\n" +
                              "    <name>sender name</name>\n" +
                              "    <email>email</email>\n" +
                              "    <phone>\n" +
                              "      <areaCode>99</areaCode>\n" +
                              "      <number>123456789</number>\n" +
                              "    </phone>\n" +
                              "  </sender>\n" +
                              "  <shipping>\n" +
                              "    <address>\n" +
                              "      <street>street</street>\n" +
                              "      <number>9</number>\n" +
                              "      <complement>complement</complement>\n" +
                              "      <district>district</district>\n" +
                              "      <city>city</city>\n" +
                              "      <state>SP</state>\n" +
                              "      <country>country</country>\n" +
                              "      <postalCode>12345678</postalCode>\n" +
                              "    </address>\n" +
                              "    <type>1</type>\n" +
                              "    <cost>9.99</cost>\n" +
                              "  </shipping>\n" +
                              "  <lastEventDate>2016-11-09T23:59:59.000-02:00</lastEventDate>\n" +
                              "  <paymentMethod>\n" +
                              "    <type>1</type>\n" +
                              "    <code>101</code>\n" +
                              "  </paymentMethod>\n" +
                              "  <grossAmount>9.99</grossAmount>\n" +
                              "  <discountAmount>9.99</discountAmount>\n" +
                              "  <feeAmount>9.99</feeAmount>\n" +
                              "  <netAmount>9.99</netAmount>\n" +
                              "  <extraAmount>9.99</extraAmount>\n" +
                              "  <escrowEndDate>2016-11-09T01:01:01.000-02:00</escrowEndDate>\n" +
                              "</transaction>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    TransactionDetail transactionDetail = transactionSearchByCode.execute(pagSeguro, httpClient);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    assertEquals("reference", transactionDetail.getReference());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), transactionDetail.getDate());

    PaymentItem item = transactionDetail.getItems().iterator().next();
    assertEquals("0001", item.getId());
    assertEquals("description", item.getDescription());
    assertEquals(new Integer(1), item.getQuantity());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN), item.getAmount());

    assertEquals(TransactionStatus.Status.UNRECOGNIZED, transactionDetail.getStatus().getStatus());
    assertEquals(TransactionType.Type.UNRECOGNIZED, transactionDetail.getType().getType());
    assertEquals("code", transactionDetail.getCode());
    assertEquals("sender name", transactionDetail.getSender().getName());
    assertEquals("email", transactionDetail.getSender().getEmail());
    assertEquals("99", transactionDetail.getSender().getPhone().getAreaCode());
    assertEquals("123456789", transactionDetail.getSender().getPhone().getNumber());
    assertEquals("street", transactionDetail.getShipping().getAddress().getStreet());
    assertEquals("9", transactionDetail.getShipping().getAddress().getNumber());
    assertEquals("complement", transactionDetail.getShipping().getAddress().getComplement());
    assertEquals("district", transactionDetail.getShipping().getAddress().getDistrict());
    assertEquals("city", transactionDetail.getShipping().getAddress().getCity());
    assertEquals("SP", transactionDetail.getShipping().getAddress().getState());
    assertEquals("country", transactionDetail.getShipping().getAddress().getCountry());
    assertEquals("12345678", transactionDetail.getShipping().getAddress().getPostalCode());
    assertEquals(ShippingType.Type.PAC,
        transactionDetail.getShipping().getShippingType().getType());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        transactionDetail.getShipping().getCost());
    assertEquals(dateFormat.parse("2016-11-09 23:59:59"), transactionDetail.getLastEvent());
    assertEquals(TransactionPaymentMethod.Type.CREDIT_CARD,
        transactionDetail.getPaymentMethod().getType());
    assertEquals(TransactionPaymentMethod.Code.VISA,
        transactionDetail.getPaymentMethod().getCode());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        transactionDetail.getGrossAmount());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        transactionDetail.getDiscountAmount());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        transactionDetail.getFeeAmount());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        transactionDetail.getNetAmount());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        transactionDetail.getExtraAmount());
    assertEquals(dateFormat.parse("2016-11-09 01:01:01"), transactionDetail.getEscrowEndDate());
  }

  @Test
  public void shouldThrowsBadRequest() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>0001</code>" +
                                "<message>Transaction code is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      transactionSearchByCode.execute(pagSeguro, httpClient);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0001), error.getCode());
      assertEquals("Transaction code is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    transactionSearchByCode.execute(pagSeguro, httpClient);
  }

}