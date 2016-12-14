package br.com.uol.pagseguro.api.http;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, AuthenticatedHttpClient.class})
public class AuthenticatedHttpClientTest {

  @Mock
  private Log logger;

  private AuthenticatedHttpClient httpClient;

  private UUID correlationId;

  @Before
  public void setUp() {
    PowerMockito.mockStatic(LoggerFactory.class);
    Mockito.when(LoggerFactory.getLogger(anyString())).thenReturn(logger);
    Mockito.when(LoggerFactory.getLogger(any(Class.class))).thenReturn(logger);

    Credential credential = Credential.applicationCredential("appId", "appKey");
    httpClient = new AuthenticatedHttpClient(new JSEHttpClient(), credential);
    correlationId = UUID.randomUUID();
    mockStatic(UUID.class);
    when(UUID.randomUUID()).thenReturn(correlationId);
  }

  @Test
  public void shouldAppendCorrelationId() throws Exception {
    String expectedUri = "http://localhost?teste=teste&correlationId=" + correlationId.toString();
    String uri = httpClient.appendCorrelationId("http://localhost?teste=teste");

    assertEquals(expectedUri, uri);
  }

  @Test
  public void shouldAppendCredential() throws Exception {
    String expectedUri = "http://localhost?teste=teste&appId=appId&appKey=appKey";
    String uri = httpClient.appendCredential("http://localhost?teste=teste");

    assertEquals(expectedUri, uri);
  }
}