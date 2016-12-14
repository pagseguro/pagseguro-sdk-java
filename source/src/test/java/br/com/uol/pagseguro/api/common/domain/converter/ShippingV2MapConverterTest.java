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
public class ShippingV2MapConverterTest {

  private ShippingV2MapConverter mapConverter;

  private Shipping shipping;

  @Before
  public void setUp() throws Exception {
    mapConverter = new ShippingV2MapConverter();

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
      put("shippingAddressCountry", "BRA");
      put("shippingAddressState", "PA");
      put("shippingAddressCity", "city");
      put("shippingAddressPostalCode", "99999999");
      put("shippingAddressDistrict", "district");
      put("shippingAddressStreet", "street");
      put("shippingAddressNumber", "999");
      put("shippingAddressComplement", "complement");
      put("shippingType", "2");
      put("shippingCost", "99.99");
    }});

    RequestMap map = mapConverter.convert(shipping);

    assertEquals(expectedMap, map);
  }

}