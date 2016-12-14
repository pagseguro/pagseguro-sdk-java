package br.com.uol.pagseguro.api.preapproval;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalChargingV2MapConverterTest {

  private PreApprovalChargingV2MapConverter mapConverter;

  private PreApprovalCharging preApprovalCharging;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PreApprovalChargingV2MapConverter();

    preApprovalCharging = new PreApprovalChargingBuilder()
        .withReference("reference")
        .withCode("code")
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("reference", "reference");
      put("preApprovalCode", "code");
    }});

    RequestMap map = mapConverter.convert(preApprovalCharging);

    assertEquals(expectedMap, map);
  }
}