package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.PaymentMethodConfig;
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PaymentMethodConfigsV2MapConverterTest {

  private PaymentMethodConfigsV2MapConverter mapConverter;

  private List<PaymentMethodConfig> paymentMethodConfigs;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PaymentMethodConfigsV2MapConverter();

    paymentMethodConfigs = new ArrayList<PaymentMethodConfig>() {{
      add(new PaymentMethodConfigBuilder()
          .withConfig(new ConfigBuilder()
              .withKey(ConfigKey.DISCOUNT_PERCENT)
              .withValue(new BigDecimal(10))
          )
          .withPaymentMethod(new PaymentMethodBuilder()
              .withGroup(PaymentMethodGroup.BALANCE)
          )
          .build()
      );
      add(new PaymentMethodConfigBuilder()
          .withConfig(new ConfigBuilder()
              .withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
              .withValue(new BigDecimal(2))
          )
          .withPaymentMethod(new PaymentMethodBuilder()
              .withGroup(PaymentMethodGroup.CREDIT_CARD)
          )
          .build()
      );
    }};
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("paymentMethodGroup1", "BALANCE");
      put("paymentMethodConfigKey1_1", "DISCOUNT_PERCENT");
      put("paymentMethodConfigValue1_1", "10.00");
      put("paymentMethodGroup2", "CREDIT_CARD");
      put("paymentMethodConfigKey2_1", "MAX_INSTALLMENTS_NO_INTEREST");
      put("paymentMethodConfigValue2_1", "2");
    }});

    RequestMap map = mapConverter.convert(paymentMethodConfigs);

    assertEquals(expectedMap, map);
  }

}