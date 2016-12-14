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
@PrepareForTest({System.class, JVMEnvVariableCredentialProvider.class})
public class JVMEnvVariableCredentialProviderTest extends Case4Test {

  private JVMEnvVariableCredentialProvider provider;

  @Before
  public void setUp() throws Exception {
    provider = new JVMEnvVariableCredentialProvider();
    mockStatic(System.class);
  }

  @Test
  public void shouldGetSellerCredentials() throws Exception {
    when(System.getProperty("pagseguro.email")).thenReturn("email");
    when(System.getProperty("pagseguro.token")).thenReturn("token");
    Credential expectedCredential = Credential.sellerCredential("email", "token");

    Credential credential = provider.getCredential();

    assertEquals(expectedCredential, credential);
  }

  @Test
  public void shouldGetApplicationCredentials() throws Exception {
    when(System.getProperty("pagseguro.appId")).thenReturn("appId");
    when(System.getProperty("pagseguro.appKey")).thenReturn("appKey");
    Credential expectedCredential = Credential.applicationCredential("appId", "appKey");

    Credential credential = provider.getCredential();

    assertEquals(expectedCredential, credential);
  }

}