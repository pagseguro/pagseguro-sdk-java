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

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PaymentItemsV2MapConverterTest {

  private PaymentItemsV2MapConverter mapConverter;

  private List<PaymentItem> items;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PaymentItemsV2MapConverter();

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
      put("itemId1", "1");
      put("itemDescription1", "description1");
      put("itemAmount1", "99.99");
      put("itemQuantity1", "7");
      put("itemWeight1", "123");
      put("itemShippingCost1", "99.99");
      put("itemId2", "2");
      put("itemDescription2", "description2");
      put("itemAmount2", "99.99");
      put("itemQuantity2", "7");
      put("itemWeight2", "123");
      put("itemShippingCost2", "99.99");
    }});

    RequestMap map = mapConverter.convert(items);

    assertEquals(expectedMap, map);
  }

}