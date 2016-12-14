package br.com.uol.pagseguro.api.http;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroForbiddenException;
import br.com.uol.pagseguro.api.exception.PagSeguroInternalServerException;
import br.com.uol.pagseguro.api.exception.PagSeguroServiceUnavailableException;
import br.com.uol.pagseguro.api.exception.PagSeguroUnauthorizedException;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({})
public class HttpResponseTest {

  @Mock
  private PagSeguro pagSeguro;

  @Rule
  private ExpectedException requestException = ExpectedException.none();

  private HttpResponse response;

  private String responseAsString;

  private Integer status;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void shouldParseSuccessful() throws Exception {
    status = 200;
    responseAsString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                       "<response>" +
                       "one response" +
                       "</response>";
    response = new HttpResponse(status, responseAsString);

    SimpleResponseXML4Test expectedParsedResponse = new SimpleResponseXML4Test("one response");
    SimpleResponseXML4Test parsedResponse = response.parseXMLContent(pagSeguro,
        SimpleResponseXML4Test.class);

    assertEquals(expectedParsedResponse, parsedResponse);
  }

  @Test
  public void shouldParseBadRequest() throws Exception {
    requestException.expect(PagSeguroBadRequestException.class);

    status = 400;
    responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                       "<errors>" +
                       "<error>" +
                       "<code>00001</code>" +
                       "<message>Message.</message>" +
                       "</error>" +
                       "<error>" +
                       "<code>00002</code>" +
                       "<message>Message two.</message>" +
                       "</error>" +
                       "</errors>";
    response = new HttpResponse(status, responseAsString);

    response.parseXMLContent(pagSeguro, SimpleResponseXML4Test.class);
  }

  @Test
  public void shouldParseUnauthorized() throws Exception {
    requestException.expect(PagSeguroUnauthorizedException.class);

    status = 401;
    responseAsString = "";
    response = new HttpResponse(status, responseAsString);

    response.parseXMLContent(pagSeguro, SimpleResponseXML4Test.class);
  }

  @Test
  public void shouldParseForbidden() throws Exception {
    requestException.expect(PagSeguroForbiddenException.class);

    status = 403;
    responseAsString = "";
    response = new HttpResponse(status, responseAsString);

    response.parseXMLContent(pagSeguro, SimpleResponseXML4Test.class);
  }

  @Test
  public void shouldParseServiceUnavailable() throws Exception {
    requestException.expect(PagSeguroServiceUnavailableException.class);

    status = 503;
    responseAsString = "";
    response = new HttpResponse(status, responseAsString);

    response.parseXMLContent(pagSeguro, SimpleResponseXML4Test.class);
  }

  @Test
  public void shouldParseInternalServer() throws Exception {
    requestException.expect(PagSeguroInternalServerException.class);

    status = 300;
    responseAsString = "";
    response = new HttpResponse(status, responseAsString);

    response.parseXMLContent(pagSeguro, SimpleResponseXML4Test.class);
  }

  @XmlRootElement(name = "response")
  private static class SimpleResponseXML4Test {

    private String response;

    public SimpleResponseXML4Test() {
    }

    public SimpleResponseXML4Test(String response) {
      this.response = response;
    }

    public String getResponse() {
      return response;
    }

    @XmlValue
    public void setResponse(String response) {
      this.response = response;
    }

    @Override
    public String toString() {
      return "SimpleResponseXML{" +
             "response='" + response + '\'' +
             '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SimpleResponseXML4Test)) return false;

      SimpleResponseXML4Test that = (SimpleResponseXML4Test) o;

      return response != null ? response.equals(that.response) : that.response == null;

    }
  }
}