package br.com.uol.pagseguro.api.checkout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class CheckoutRegistrationV2MapConverterTest {

  private CheckoutRegistrationV2MapConverter mapConverter;

  private CheckoutRegistration checkoutRegistration;

  @Before
  public void setUp() throws Exception {
    mapConverter = new CheckoutRegistrationV2MapConverter();
    checkoutRegistration = new CheckoutRegistrationBuilder()
        .withCurrency(Currency.BRL)
        .withExtraAmount(new BigDecimal(9.99))
        .withReference("reference")
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>(){{
      put("currency", "BRL");
      put("extraAmount", "9.99");
      put("reference", "reference");
    }});

    RequestMap map = mapConverter.convert(checkoutRegistration);

    Assert.assertEquals(expectedMap, map);
  }
}