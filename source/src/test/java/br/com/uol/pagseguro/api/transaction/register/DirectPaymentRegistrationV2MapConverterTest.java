package br.com.uol.pagseguro.api.transaction.register;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

public class DirectPaymentRegistrationV2MapConverterTest {

  private DirectPaymentRegistrationV2MapConverter mapConverter;

  private DirectPaymentRegistration directPaymentRegistration;


  @Before
  public void setUp() {
    mapConverter = new DirectPaymentRegistrationV2MapConverter();

    directPaymentRegistration = new DirectPaymentRegistrationBuilder()
        .withExtraAmount(new BigDecimal(100))
        .withReceiverEmail("teste@teste.com.br")
        .withCurrency(Currency.BRL).build();

  }

  @Test
  public void shouldConvert() {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("extraAmount", "100.00");
      put("receiverEmail", "teste@teste.com.br");
      put("currency", "BRL");
    }});

    RequestMap map = mapConverter.convert(directPaymentRegistration);

    assertEquals(expectedMap, map);
  }
}
