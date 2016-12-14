package br.com.uol.pagseguro.api.transaction.register;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.common.domain.CreditCard;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.BankBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.CreditCardBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DocumentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.HolderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.InstallmentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ParameterBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ReceiverBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SplitBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class SplitPaymentRegisterResourceTest extends Resource4Test {

  private SplitPaymentRegisterResource splitPaymentRegisterResource;

  private SplitPaymentRegistration splitPaymentRegistration;

  private String responseAsString;

  private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Before
  public void setUp() {

    splitPaymentRegistration = new SplitPaymentRegistrationBuilder()
        .withPaymentMode("default")
        .withCurrency(Currency.BRL)
        .withNotificationURL("www.sualoja.com.br/notification")
        .withReference("LIBJAVA_DIRECT_PAYMENT")
        .withExtraAmount(new BigDecimal(100.00))
        .addParameter(new ParameterBuilder()
            .withName("parameter")
            .withValue("value"))
        .addItem(new PaymentItemBuilder()//
            .withId("0001")//
            .withDescription("Produto PagSeguroI") //
            .withAmount(new BigDecimal(99999.99))//
            .withQuantity(1)
            .withWeight(1000))
        .withSender(new SenderBuilder()//
            .withEmail("comprador@uol.com.br")//
            .withName("Jose Comprador")
            .withCPF("99999999999")
            .withPhone(new PhoneBuilder()//
                .withAreaCode("99") //
                .withNumber("99999999"))) //
        .withShipping(new ShippingBuilder()//
            .withType(ShippingType.Type.SEDEX) //
            .withCost(BigDecimal.TEN)//
            .withAddress(new AddressBuilder() //
                .withPostalCode("99999999")
                .withCountry("BRA")
                .withState(State.XX)//
                .withCity("Cidade Exemplo")
                .withComplement("99o andar")
                .withDistrict("Jardim Internet")
                .withNumber("9999")
                .withStreet("Av. PagSeguro"))
        )
        .withPrimaryReceiver(new ReceiverBuilder()
            .withPublicKey("publickey1")
            .withSplit(new SplitBuilder()
                .withAmount(new BigDecimal(50))
                .withRatePercent(new BigDecimal(50))
                .withFeePercent(new BigDecimal(50))
            )
        )
        .addReceiver(new ReceiverBuilder()
            .withPublicKey("publickey1")
            .withSplit(new SplitBuilder()
                .withAmount(new BigDecimal(50))
                .withRatePercent(new BigDecimal(50))
                .withFeePercent(new BigDecimal(50))
            )
        ).build();

    splitPaymentRegisterResource = new SplitPaymentRegisterResource(pagSeguro, httpClient, splitPaymentRegistration);

    responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<transaction>\n" +
        "   <date>2016-11-10T10:25:52.000-02:00</date>\n" +
        "   <code>33FF1EE6-D99E-4BEB-9128-070C6B244688</code>\n" +
        "   <reference>LIBJAVA_DIRECT_PAYMENT</reference>\n" +
        "   <type>1</type>\n" +
        "   <status>3</status>\n" +
        "   <lastEventDate>2016-11-10T10:25:55.000-02:00</lastEventDate>\n" +
        "   <paymentMethod>\n" +
        "      <type>2</type>\n" +
        "      <code>202</code>\n" +
        "   </paymentMethod>\n" +
        "   <paymentLink>https://sandbox.pagseguro.uol.com.br/checkout/payment/booklet/print.jhtml?c=acd4738c97f8b820e520ecf89a91fcdaf60c854f561a578e283eb2ec83d636b354bccc7cb28b2554</paymentLink>\n" +
        "   <grossAmount>270.00</grossAmount>\n" +
        "   <discountAmount>0.00</discountAmount>\n" +
        "   <feeAmount>11.17</feeAmount>\n" +
        "   <netAmount>258.83</netAmount>\n" +
        "   <extraAmount>100.00</extraAmount>\n" +
        "   <escrowEndDate>2016-12-10T10:25:55.000-02:00</escrowEndDate>\n" +
        "   <installmentCount>1</installmentCount>\n" +
        "   <itemCount>1</itemCount>\n" +
        "   <items>\n" +
        "      <item>\n" +
        "         <id>0001</id>\n" +
        "         <description>Produto PagSeguroI</description>\n" +
        "         <quantity>1</quantity>\n" +
        "         <amount>1000.00</amount>\n" +
        "      </item>\n" +
        "   </items>\n" +
        "   <sender>\n" +
        "      <name>Jose Comprador</name>\n" +
        "      <email>comprador@uol.com.br</email>\n" +
        "      <phone>\n" +
        "         <areaCode>99</areaCode>\n" +
        "         <number>99999999</number>\n" +
        "      </phone>\n" +
        "   </sender>\n" +
        "   <shipping>\n" +
        "      <address>\n" +
        "         <street>Av. PagSeguro</street>\n" +
        "         <number>9999</number>\n" +
        "         <complement>99o andar</complement>\n" +
        "         <district>Jardim Internet</district>\n" +
        "         <city>Cidade Exemplo</city>\n" +
        "         <state>XX</state>\n" +
        "         <country>BRA</country>\n" +
        "         <postalCode>99999999</postalCode>\n" +
        "      </address>\n" +
        "      <type>2</type>\n" +
        "      <cost>20.00</cost>\n" +
        "   </shipping>\n" +
        "   <creditorFees>\n" +
        "      <intermediationRateAmount>0.40</intermediationRateAmount>\n" +
        "      <intermediationFeeAmount>0.80</intermediationFeeAmount>\n" +
        "   </creditorFees>" +
        "</transaction>";
  }

  @Test
  public void shouldBankSlipRegister() throws IOException, ParseException {
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    TransactionDetail transactionDetail = splitPaymentRegisterResource.withBankSlip();
    assertTransaction(transactionDetail);
  }

  @Test
  public void shouldCreditCardRegister() throws IOException, ParseException {
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    CreditCard creditCard = getCreditCard();

    TransactionDetail transactionDetail = splitPaymentRegisterResource.withCreditCard(creditCard);
    assertTransaction(transactionDetail);
  }

  @Test
  public void shouldOnlineDebitRegister() throws IOException, ParseException {
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    TransactionDetail transactionDetail = splitPaymentRegisterResource.withOnlineDebit(
        new BankBuilder()
            .withName(BankName.Name.BANCO_DO_BRASIL));
    assertTransaction(transactionDetail);
  }

  @Test
  public void shouldThrowsBadRequestOnRegister() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
          "<errors>" +
          "<error>" +
          "<code>53070</code>" +
          "<message>item id is required.</message>" +
          "</error>" +
          "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      splitPaymentRegisterResource.withBankSlip();

    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(53070), error.getCode());
      assertEquals("item id is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnRegister() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    splitPaymentRegisterResource.withBankSlip();
  }


  private CreditCard getCreditCard() throws ParseException {
    return new CreditCardBuilder()
        .withHolder(new HolderBuilder()
            .addDocument(new DocumentBuilder()
                .withType(DocumentType.CPF)
                .withValue("99999999999")
            )
            .withName("Jose Comprador")
            .withBithDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900"))
            .withPhone(new PhoneBuilder()
                .withAreaCode("99")
                .withNumber("99999999")
            )
        )
        .withBillingAddress(new AddressBuilder() //
            .withPostalCode("99999999")
            .withCountry("BRA")
            .withState(State.XX)//
            .withCity("Cidade Exemplo")
            .withComplement("99o andar")
            .withDistrict("Jardim Internet")
            .withNumber("9999")
            .withStreet("Av. PagSeguro")
        )
        .withInstallment(new InstallmentBuilder()
            .withQuantity(2)
            .withValue(new BigDecimal(135.50))
        ).build();
  }

  private void assertTransaction(TransactionDetail transactionDetail) throws ParseException {
    assertEquals("33FF1EE6-D99E-4BEB-9128-070C6B244688", transactionDetail.getCode());
    assertEquals("LIBJAVA_DIRECT_PAYMENT", transactionDetail.getReference());
    assertEquals(1, transactionDetail.getType().getTypeId());
    assertEquals(3, transactionDetail.getStatus().getStatusId());
    assertEquals(dateFormat.parse("2016-11-10 10:25:55"), transactionDetail.getLastEvent());
    assertEquals(new Integer(2), transactionDetail.getPaymentMethod().getTypeId());
    assertEquals(new Integer(202), transactionDetail.getPaymentMethod().getCodeId());
    assertEquals("https://sandbox.pagseguro.uol.com.br/checkout/payment/booklet/print.jhtml?c=acd4738c97f8b820e520ecf89a91fcdaf60c854f561a578e283eb2ec83d636b354bccc7cb28b2554",
        transactionDetail.getPaymentLink());
    assertEquals(new BigDecimal(270).setScale(2), transactionDetail.getGrossAmount());
    assertEquals(BigDecimal.ZERO.setScale(2), transactionDetail.getDiscountAmount());
    assertEquals(new BigDecimal(11.17).setScale(2, RoundingMode.CEILING), transactionDetail.getFeeAmount());
    assertEquals(new BigDecimal(258.83).setScale(2, RoundingMode.CEILING), transactionDetail.getNetAmount());
    assertEquals(new BigDecimal(100.00).setScale(2, RoundingMode.CEILING), transactionDetail.getExtraAmount());
    assertEquals(dateFormat.parse("2016-12-10 10:25:55"), transactionDetail.getEscrowEndDate());

    PaymentItem item = transactionDetail.getItems().iterator().next();
    assertEquals("0001", item.getId());
    assertEquals("Produto PagSeguroI", item.getDescription());
    assertEquals(new Integer(1), item.getQuantity());
    assertEquals(new BigDecimal(1000).setScale(2, RoundingMode.CEILING), item.getAmount());

    assertEquals("Jose Comprador", transactionDetail.getSender().getName());
    assertEquals("comprador@uol.com.br", transactionDetail.getSender().getEmail());
    assertEquals("99", transactionDetail.getSender().getPhone().getAreaCode());
    assertEquals("99999999", transactionDetail.getSender().getPhone().getNumber());
    assertEquals(new BigDecimal(0.40).setScale(2, RoundingMode.HALF_EVEN), transactionDetail.getCreditorFees().getIntermediationRateAmount());
    assertEquals(new BigDecimal(0.80).setScale(2, RoundingMode.HALF_EVEN), transactionDetail.getCreditorFees().getIntermediationFeeAmount());

  }

}
