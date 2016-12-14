package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PaymentItemsV3MapConverterTest {

  private PaymentItemsV3MapConverter mapConverter;

  private List<PaymentItem> items;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PaymentItemsV3MapConverter();

    items = new ArrayList<PaymentItem>() {{
      add(new PaymentItemBuilder()
          .withId("1")
          .withDescription("description1")
          .withAmount(new BigDecimal(99.99))
          .withQuantity(7)
          .withWeight(123)
          .withShippingCost(new BigDecimal(99.99))
          .build()
      );
      add(new PaymentItemBuilder()
          .withId("2")
          .withDescription("description2")
          .withAmount(new BigDecimal(99.99))
          .withQuantity(7)
          .withWeight(123)
          .withShippingCost(new BigDecimal(99.99))
          .build()
      );
    }};
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("item[1].id", "1");
      put("item[1].description", "description1");
      put("item[1].amount", "99.99");
      put("item[1].quantity", "7");
      put("item[1].weight", "123");
      put("item[1].shippingCost", "99.99");
      put("item[2].id", "2");
      put("item[2].description", "description2");
      put("item[2].amount", "99.99");
      put("item[2].quantity", "7");
      put("item[2].weight", "123");
      put("item[2].shippingCost", "99.99");
    }});

    RequestMap map = mapConverter.convert(items);

    assertEquals(expectedMap, map);
  }

}