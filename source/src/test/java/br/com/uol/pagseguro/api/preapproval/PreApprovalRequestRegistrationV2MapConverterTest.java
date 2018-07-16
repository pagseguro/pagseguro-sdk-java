package br.com.uol.pagseguro.api.preapproval;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalRequestRegistrationV2MapConverterTest {

  private PreApprovalRegistrationV2MapConverter mapConverter;

  private PreApprovalRegistration preApprovalRegistration;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PreApprovalRegistrationV2MapConverter();

    preApprovalRegistration = new PreApprovalRegistrationBuilder()
        .withReference("reference")
        .withRedirectURL("redirectUrl")
        .withNotificationURL("notificationUrl")
        .withExtraAmount(new BigDecimal(99.99))
        .withCurrency(Currency.BRL)
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("reference", "reference");
      put("redirectURL", "redirectUrl");
      put("notificationURL", "notificationUrl");
      put("extraAmount", "99.99");
      put("currency", "BRL");
    }});

    RequestMap map = mapConverter.convert(preApprovalRegistration);

    assertEquals(expectedMap, map);
  }

}