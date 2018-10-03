package br.com.uol.pagseguro.api.installment;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class InstallmentRequestV2MapConverterTest {

  private InstallmentRequestV2MapConverter mapConverter;

  private InstallmentRequest installmentRequest;

  @Before
  public void setUp() throws Exception {
    mapConverter = new InstallmentRequestV2MapConverter();

    installmentRequest = new InstallmentListingBuilder()
        .withCardBrand("cardBrand")
        .withAmount(new BigDecimal(99.99))
        .withMaxInstallmentNoInterest(3)
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("cardBrand", "cardBrand");
      put("amount", "99.99");
      put("maxInstallmentNoInterest", "3");
    }});

    RequestMap map = mapConverter.convert(installmentRequest);

    assertEquals(expectedMap, map);
  }

}