package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.AcceptedPaymentMethods;
import br.com.uol.pagseguro.api.common.domain.builder.AcceptedPaymentMethodsBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodName;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class AcceptedPaymentMethodsV2MapConverterTest {

  private AcceptedPaymentMethodsV2MapConverter mapConverter;

  private AcceptedPaymentMethods acceptedPaymentMethods;

  @Before
  public void setUp() throws Exception {
    mapConverter = new AcceptedPaymentMethodsV2MapConverter();

    acceptedPaymentMethods = new AcceptedPaymentMethodsBuilder()
        .addInclude(new PaymentMethodBuilder()
            .withGroup(PaymentMethodGroup.CREDIT_CARD)
        )
        .addInclude(new PaymentMethodBuilder()
            .withName(PaymentMethodName.BRASILCARD)
        )
        .addExclude(new PaymentMethodBuilder()
            .withGroup(PaymentMethodGroup.BANK_SLIP)
        )
        .addExclude(new PaymentMethodBuilder()
            .withName(PaymentMethodName.DEBIT_BRADESCO)
        )
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>(){{
      put("acceptPaymentMethodGroup", "CREDIT_CARD");
      put("acceptPaymentMethodName", "BRASILCARD");
      put("excludePaymentMethodGroup", "BOLETO");
      put("excludePaymentMethodName", "DEBITO_BRADESCO");
    }});

    RequestMap map = mapConverter.convert(acceptedPaymentMethods);

    assertEquals(expectedMap, map);
  }

}