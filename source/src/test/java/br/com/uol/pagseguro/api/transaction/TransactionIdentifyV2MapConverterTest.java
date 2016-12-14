package br.com.uol.pagseguro.api.transaction;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class TransactionIdentifyV2MapConverterTest {

  private TransactionIdentifyV2MapConverter mapConverter;

  private TransactionIdentify transactionIdentify;

  @Before
  public void setUp() throws Exception {
    mapConverter = new TransactionIdentifyV2MapConverter();

    transactionIdentify = new TransactionIdentifyBuilder()
        .withCode("code")
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("transactionCode", "code");
    }});

    RequestMap map = mapConverter.convert(transactionIdentify);

    assertEquals(expectedMap, map);
  }
}