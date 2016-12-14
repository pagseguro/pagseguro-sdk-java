package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.Installment;
import br.com.uol.pagseguro.api.common.domain.builder.InstallmentBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class InstallmentV3MapConverterTest {

  private InstallmentV3MapConverter mapConverter;

  private Installment installment;

  @Before
  public void setUp() throws Exception {
    mapConverter = new InstallmentV3MapConverter();

    installment = new InstallmentBuilder()
        .withQuantity(1)
        .withValue(new BigDecimal(99.99))
        .withNoInterestInstallmentQuantity(6)
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>(){{
      put("installment.quantity", "1");
      put("installment.value", "99.99");
      put("installment.noInterestInstallmentQuantity", "6");
    }});

    RequestMap map = mapConverter.convert(installment);

    assertEquals(expectedMap, map);
  }

}