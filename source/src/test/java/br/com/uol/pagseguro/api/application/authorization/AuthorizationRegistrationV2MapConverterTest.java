package br.com.uol.pagseguro.api.application.authorization;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationRegistrationV2MapConverterTest {

  private AuthorizationRegistrationV2MapConverter mapConverter;

  private AuthorizationRegistration authorizationRegistration;

  @Before
  public void setUp() throws Exception {
    mapConverter = new AuthorizationRegistrationV2MapConverter();

    authorizationRegistration = new AuthorizationRegistrationBuilder()
        .withReference("reference")
        .withNotificationUrl("notificationUrl")
        .withRedirectURL("redirectUrl")
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("reference", "reference");
      put("notificationURL", "notificationUrl");
      put("redirectURL", "redirectUrl");
    }});

    RequestMap map = mapConverter.convert(authorizationRegistration);

    assertEquals(expectedMap, map);
  }

}