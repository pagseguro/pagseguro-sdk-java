package br.com.uol.pagseguro.api.transaction.register;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.builder.ReceiverBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

public class SplitPaymentRegistrationV3MapConverterTest {

  private SplitPaymentRegistrationV3MapConverter mapConverter;

  private SplitPaymentRegistration splitPaymentRegistration;

  @Before
  public void setUp() {
    mapConverter = new SplitPaymentRegistrationV3MapConverter();

    splitPaymentRegistration =
        new SplitPaymentRegistrationBuilder()
            .withPrimaryReceiver(
                new ReceiverBuilder().withPublicKey("public")).build();
  }

  @Test
  public void shouldConvert() {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("primaryReceiver.publicKey", "public");
      put("currency", "BRL");
    }});

    RequestMap map = mapConverter.convert(splitPaymentRegistration);

    assertEquals(expectedMap, map);
  }
}
