package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class SenderV3MapConverterTest {

  private SenderV3MapConverter mapConverter;

  private Sender sender;

  @Before
  public void setUp() throws Exception {
    mapConverter = new SenderV3MapConverter();

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
      put("sender.email", "email@email.com");
      put("sender.name", "name");
      put("sender.areaCode", "16");
      put("sender.phone", "123456789");
      put("sender.address.country", "BRA");
      put("sender.address.state", "PA");
      put("sender.address.city", "city");
      put("sender.address.postalCode", "99999999");
      put("sender.address.district", "district");
      put("sender.address.street", "street");
      put("sender.address.number", "999");
      put("sender.address.complement", "complement");
      put("sender.CPF", "99999999999");
      put("sender.hash", "hash");
    }});

    RequestMap map = mapConverter.convert(sender);

    assertEquals(expectedMap, map);
  }

}