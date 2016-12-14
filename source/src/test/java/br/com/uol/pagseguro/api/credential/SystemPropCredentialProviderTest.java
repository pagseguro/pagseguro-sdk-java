package br.com.uol.pagseguro.api.credential;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.uol.pagseguro.api.Case4Test;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class SystemPropCredentialProviderTest extends Case4Test {

  private SystemPropCredentialProvider provider;

  @Test
  public void shouldGetSellerCredentials() throws Exception {
    provider = new SystemPropCredentialProvider("pagseguro.properties");
    Credential expectedCredential = Credential.sellerCredential("email", "token");

    Credential credential = provider.getCredential();

    assertEquals(expectedCredential, credential);
  }

  @Test
  public void shouldGetApplicationCredentials() throws Exception {
    provider = new SystemPropCredentialProvider("pagseguro2.properties");
    Credential expectedCredential = Credential.applicationCredential("appId", "appKey");

    Credential credential = provider.getCredential();

    assertEquals(expectedCredential, credential);
  }

}