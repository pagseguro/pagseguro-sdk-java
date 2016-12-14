package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class ShippingV3MapConverterTest {

  private ShippingV3MapConverter mapConverter;

  private Shipping shipping;

  @Before
  public void setUp() throws Exception {
    mapConverter = new ShippingV3MapConverter();

    shipping = new ShippingBuilder()
        .withAddress(new AddressBuilder()
            .withCountry("BRA")
            .withState("PA")
            .withPostalCode("99999999")
            .withCity("city")
            .withDistrict("district")
            .withStreet("street")
            .withNumber("999")
            .withComplement("complement"))
        .withType(ShippingType.Type.SEDEX)
        .withCost(new BigDecimal(99.99))
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("shipping.address.country", "BRA");
      put("shipping.address.state", "PA");
      put("shipping.address.city", "city");
      put("shipping.address.postalCode", "99999999");
      put("shipping.address.district", "district");
      put("shipping.address.street", "street");
      put("shipping.address.number", "999");
      put("shipping.address.complement", "complement");
      put("shipping.type", "2");
      put("shipping.cost", "99.99");
    }});

    RequestMap map = mapConverter.convert(shipping);

    assertEquals(expectedMap, map);
  }
}