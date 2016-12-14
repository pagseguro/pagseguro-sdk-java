package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class SenderV2MapConverterTest {

  private SenderV2MapConverter mapConverter;

  private Sender sender;

  @Before
  public void setUp() throws Exception {
    mapConverter = new SenderV2MapConverter();

    sender = new SenderBuilder()
        .withEmail("email@email.com")
        .withName("name")
        .withPhone(new PhoneBuilder()
            .withAreaCode("16")
            .withNumber("123456789")
        )
        .withAddress(new AddressBuilder()
            .withCountry("BRA")
            .withState("PA")
            .withPostalCode("99999999")
            .withCity("city")
            .withDistrict("district")
            .withStreet("street")
            .withNumber("999")
            .withComplement("complement")
        )
        .withCPF("99999999999")
        .withHash("hash")
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("senderEmail", "email@email.com");
      put("senderName", "name");
      put("senderAreaCode", "16");
      put("senderPhone", "123456789");
      put("senderAddressCountry", "BRA");
      put("senderAddressState", "PA");
      put("senderAddressCity", "city");
      put("senderAddressPostalCode", "99999999");
      put("senderAddressDistrict", "district");
      put("senderAddressStreet", "street");
      put("senderAddressNumber", "999");
      put("senderAddressComplement", "complement");
      put("senderCPF", "99999999999");
      put("senderHash", "hash");
    }});

    RequestMap map = mapConverter.convert(sender);

    assertEquals(expectedMap, map);
  }

}