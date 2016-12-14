package br.com.uol.pagseguro.api.credential;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class ApplicationCredentialTest {

  private ApplicationCredential applicationCredential;

  @Before
  public void setUp() throws Exception {
    applicationCredential = (ApplicationCredential) Credential.applicationCredential("appId", "appKey");
  }

  @Test
  public void shouldConvertToMap() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("appId", "appId");
      put("appKey", "appKey");
    }});

    RequestMap map = applicationCredential.asMap();

    assertEquals(expectedMap, map);
  }

}