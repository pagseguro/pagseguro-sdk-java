package br.com.uol.pagseguro.api.utils;

import org.junit.Before;
import org.junit.Test;

import br.com.uol.pagseguro.api.http.HttpRequestBody;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class RequestMapTest {

  private RequestMap requestMap;

  @Before
  public void setUp() throws Exception {
    requestMap = new RequestMap();
    requestMap.putString("key1", "value1");
    requestMap.putString("key2", "value2");
  }

  @Test
  public void shouldConvertToUrlEncode() throws Exception {
    String expectedUrlEncoded = "key1=value1&key2=value2";
    String urlEncoded = requestMap.toUrlEncode("UTF-8");

    assertEquals(expectedUrlEncoded, urlEncoded);
  }

  @Test
  public void shouldConvertToHttpRequestBody() throws Exception {
    HttpRequestBody expectedHttpRequestBody =
        new HttpRequestBody("application/x-www-form-urlencoded; charset=UTF-8",
            "key1=value1&key2=value2", "UTF-8");
    HttpRequestBody httpRequestBody = requestMap.toHttpRequestBody("UTF-8");

    assertEquals(expectedHttpRequestBody, httpRequestBody);
  }
}