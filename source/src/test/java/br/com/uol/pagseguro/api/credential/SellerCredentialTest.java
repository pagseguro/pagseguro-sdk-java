package br.com.uol.pagseguro.api.credential;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class SellerCredentialTest {

  private SellerCredential sellerCredential;

  @Before
  public void setUp() {
    sellerCredential = (SellerCredential) Credential.sellerCredential("email", "token");
  }

  @Test
  public void shouldConvertToMap() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("email", "email");
      put("token", "token");
    }});

    RequestMap map = sellerCredential.asMap();

    assertEquals(expectedMap, map);
  }

}