package br.com.uol.pagseguro.api.credential;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.uol.pagseguro.api.Case4Test;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, SystemEnvVariableCredentialProvider.class})
public class SystemEnvVariableCredentialProviderTest extends Case4Test {

  private SystemEnvVariableCredentialProvider provider;

  @Before
  public void setUp() throws Exception {
    provider = new SystemEnvVariableCredentialProvider();
    mockStatic(System.class);
  }

  @Test
  public void shouldGetSellerCredentials() throws Exception {
    when(System.getenv("PSL_EMAIL")).thenReturn("email");
    when(System.getenv("PSL_TOKEN")).thenReturn("token");
    Credential expectedCredential = Credential.sellerCredential("email", "token");

    Credential credential = provider.getCredential();

    assertEquals(expectedCredential, credential);
  }

  @Test
  public void shouldGetApplicationCredentials() throws Exception {
    when(System.getenv("PSL_APP_ID")).thenReturn("appId");
    when(System.getenv("PSL_APP_KEY")).thenReturn("appKey");
    Credential expectedCredential = Credential.applicationCredential("appId", "appKey");

    Credential credential = provider.getCredential();

    assertEquals(expectedCredential, credential);
  }
}