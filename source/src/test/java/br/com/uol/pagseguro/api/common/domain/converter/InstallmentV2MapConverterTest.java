package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.Installment;
import br.com.uol.pagseguro.api.common.domain.builder.InstallmentBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class InstallmentV2MapConverterTest {

  private InstallmentV2MapConverter mapConverter;

  private Installment installment;

  @Before
  public void setUp() throws Exception {
    mapConverter = new InstallmentV2MapConverter();

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
      put("installmentQuantity", "1");
      put("installmentValue", "99.99");
      put("noInterestInstallmentQuantity", "6");
    }});

    RequestMap map = mapConverter.convert(installment);

    assertEquals(expectedMap, map);
  }

}