package br.com.uol.pagseguro.api.preapproval.search;

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
import br.com.uol.pagseguro.api.common.domain.PreApprovalStatus;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
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
public class PreApprovalRequestSearchByDateRangeTest extends Resource4Test {

  private PreApprovalSearchByDateRange preApprovalSearchByDateRange;

  private PreApprovalSearch preApprovalSearch;

  @Before
  public void setUp() throws Exception {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    preApprovalSearch = new PreApprovalSearchBuilder()
        .withDateRange(new DateRangeBuilder()
            .between(dateFormat.parse("2016-11-09 00:00:00"),
                dateFormat.parse("2016-11-09 23:59:59"))
        )
        .withReference("reference")
        .withPage(2)
        .withMaxResults(5)
        .build();

    preApprovalSearchByDateRange = new PreApprovalSearchByDateRange(preApprovalSearch);
  }

  @Test
  public void shouldSearch() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                              "<preApprovalSearchResult>" +
                              "<resultsInThisPage>2</resultsInThisPage>" +
                              "<currentPage>1</currentPage>" +
                              "<totalPages>1</totalPages>" +
                              "<date>2016-11-09T00:00:00.000-02:00</date>" +
                              "<preApprovals>" +
                              "<preApproval>" +
                              "<name>Pre Approval 1</name>" +
                              "<code>code 1</code>" +
                              "<date>2016-11-09T00:00:00.000-02:00</date>" +
                              "<tracker>tracker</tracker>" +
                              "<status>INITIATED</status>" +
                              "<reference>reference</reference>" +
                              "<lastEventDate>2016-11-09T23:59:59.000-02:00</lastEventDate>" +
                              "<charge>auto</charge>" +
                              "</preApproval>" +
                              "<preApproval>" +
                              "<name>Pre Approval 2</name>" +
                              "<code>code 2</code>" +
                              "<date>2016-11-09T00:00:00.000-02:00</date>" +
                              "<tracker>tracker</tracker>" +
                              "<status>INITIATED</status>" +
                              "<reference>reference</reference>" +
                              "<lastEventDate>2016-11-09T23:59:59.000-02:00</lastEventDate>" +
                              "<charge>auto</charge>" +
                              "</preApproval>" +
                              "</preApprovals>" +
                              "</preApprovalSearchResult>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    DataList<? extends PreApprovalSummary> data = preApprovalSearchByDateRange.execute(pagSeguro,
        httpClient);

    assertEquals(new Integer(1), data.getTotalPages());
    assertEquals(false, data.isEmpty());

    Iterator<PreApprovalSummary> authorizationSummaryIterator =
        (Iterator<PreApprovalSummary>) data.getData().iterator();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    PreApprovalSummary preApproval = authorizationSummaryIterator.next();
    assertEquals("code 1", preApproval.getCode());
    assertEquals("Pre Approval 1", preApproval.getName());
    assertEquals("tracker", preApproval.getTracker());
    assertEquals(PreApprovalStatus.Status.INITIATED, preApproval.getStatus().getStatus());
    assertEquals("reference", preApproval.getReference());
    assertEquals(dateFormat.parse("2016-11-09 23:59:59"), preApproval.getLastEvent());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), preApproval.getDate());
    assertEquals("auto", preApproval.getCharge());

    preApproval = authorizationSummaryIterator.next();
    assertEquals("code 2", preApproval.getCode());
    assertEquals("Pre Approval 2", preApproval.getName());
    assertEquals("tracker", preApproval.getTracker());
    assertEquals(PreApprovalStatus.Status.INITIATED, preApproval.getStatus().getStatus());
    assertEquals("reference", preApproval.getReference());
    assertEquals(dateFormat.parse("2016-11-09 23:59:59"), preApproval.getLastEvent());
    assertEquals(dateFormat.parse("2016-11-09 00:00:00"), preApproval.getDate());
    assertEquals("auto", preApproval.getCharge());
  }

  @Test
  public void shouldThrowsBadRequest() {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>0001</code>" +
                                "<message>Date range is required.</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      preApprovalSearchByDateRange.execute(pagSeguro, httpClient);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(0001), error.getCode());
      assertEquals("Date range is required.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    preApprovalSearchByDateRange.execute(pagSeguro, httpClient);
  }

}