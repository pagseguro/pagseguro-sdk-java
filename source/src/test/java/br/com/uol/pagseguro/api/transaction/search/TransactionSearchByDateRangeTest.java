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
import java.util.Iterator;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.TransactionPaymentMethod;
import br.com.uol.pagseguro.api.common.domain.TransactionStatus;
import br.com.uol.pagseguro.api.common.domain.TransactionType;
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
public class TransactionSearchByDateRangeTest extends Resource4Test {

  private TransactionSearchByDateRange transactionSearchByDateRange;

  private TransactionSearch transactionSearch;

  @Before
  public void setUp() throws Exception {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    transactionSearch = new TransactionSearchBuilder()
        .withDateRange(new DateRangeBuilder()
            .between(dateFormat.parse("2016-11-09 00:00:00"),
                dateFormat.parse("2016-11-09 23:59:59"))
        )
        .withReference("reference")
        .withPage(2)
        .withMaxResults(5)
        .build();

    transactionSearchByDateRange = new TransactionSearchByDateRange(transactionSearch);
  }

  @Test
  public void shouldSearch() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                              "<transactionSearchResult>" +
                              "<date>2016-09-11T00:00:00.000-02:00</date>" +
                              "<currentPage>1</currentPage>" +
                              "<resultsInThisPage>2</resultsInThisPage>" +
                              "<totalPages>1</totalPages>" +
                              "<transactions>" +
                              "<transaction>" +
                              "<date>2016-11-09T01:01:01.000-02:00</date>" +
                              "<lastEventDate>2016-11-09T02:02:02.000-02:00</lastEventDate>" +
                              "<code>code3</code>" +
                              "<reference>reference</reference>" +
                              "<type>1</type>" +
                              "<status>3</status>" +
                              "<paymentMethod>" +
                              "<type>1</type>" +
                              "</paymentMethod>" +
                              "<grossAmount>9.99</grossAmount>" +
                              "<discountAmount>0.00</discountAmount>" +
                              "<feeAmount>3.33</feeAmount>" +
                              "<netAmount>2.22</netAmount>" +
                              "<extraAmount>1.11</extraAmount>" +
                              "</transaction>" +
                              "<transaction>" +
                              "<date>2016-11-09T01:01:01.000-02:00</date>" +
                              "<lastEventDate>2016-11-09T02:02:02.000-02:00</lastEventDate>" +
                              "<code>code4</code>" +
                              "<reference>reference</reference>" +
                              "<type>1</type>" +
                              "<status>3</status>" +
                              "<paymentMethod>" +
                              "<type>1</type>" +
                              "</paymentMethod>" +
                              "<grossAmount>9.99</grossAmount>" +
                              "<discountAmount>0.00</discountAmount>" +
                              "<feeAmount>3.33</feeAmount>" +
                              "<netAmount>2.22</netAmount>" +
                              "<extraAmount>1.11</extraAmount>" +
                              "</transaction>" +
                              "</transactions>" +
                              "</transactionSearchResult>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    DataList<? extends TransactionSummary> data = transactionSearchByDateRange.execute(pagSeguro,
        httpClient);

    assertEquals(new Integer(1), data.getTotalPages());
    assertEquals(false, data.isEmpty());

    Iterator<TransactionSummary> authorizationSummaryIterator =
        (Iterator<TransactionSummary>) data.getData().iterator();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    TransactionSummary preApproval = authorizationSummaryIterator.next();
    assertEquals(dateFormat.parse("2016-11-09 01:01:01"), preApproval.getDate());
    assertEquals(dateFormat.parse("2016-11-09 02:02:02"), preApproval.getLastEvent());
    assertEquals("code3", preApproval.getCode());
    assertEquals("reference", preApproval.getReference());
    assertEquals(TransactionType.Type.CHECKOUT, preApproval.getType().getType());
    assertEquals(TransactionStatus.Status.IN_REVIEW, preApproval.getStatus().getStatus());
    assertEquals(TransactionPaymentMethod.Type.CREDIT_CARD,
        preApproval.getPaymentMethod().getType());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getGrossAmount());
    assertEquals(new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getDiscountAmount());
    assertEquals(new BigDecimal(3.33).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getFeeAmount());
    assertEquals(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getNetAmount());
    assertEquals(new BigDecimal(1.11).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getExtraAmount());

    preApproval = authorizationSummaryIterator.next();
    assertEquals(dateFormat.parse("2016-11-09 01:01:01"), preApproval.getDate());
    assertEquals(dateFormat.parse("2016-11-09 02:02:02"), preApproval.getLastEvent());
    assertEquals("code4", preApproval.getCode());
    assertEquals("reference", preApproval.getReference());
    assertEquals(TransactionType.Type.CHECKOUT, preApproval.getType().getType());
    assertEquals(TransactionStatus.Status.IN_REVIEW, preApproval.getStatus().getStatus());
    assertEquals(TransactionPaymentMethod.Type.CREDIT_CARD,
        preApproval.getPaymentMethod().getType());
    assertEquals(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getGrossAmount());
    assertEquals(new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getDiscountAmount());
    assertEquals(new BigDecimal(3.33).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getFeeAmount());
    assertEquals(new BigDecimal(2.22).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getNetAmount());
    assertEquals(new BigDecimal(1.11).setScale(2, RoundingMode.HALF_EVEN),
        preApproval.getExtraAmount());
  }

  @Test
  public void shouldThrowsBadRequest() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>0001</code>" +
                                "<message>Transaction date range is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      transactionSearchByDateRange.execute(pagSeguro, httpClient);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0001), error.getCode());
      assertEquals("Transaction date range is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    transactionSearchByDateRange.execute(pagSeguro, httpClient);
  }
}