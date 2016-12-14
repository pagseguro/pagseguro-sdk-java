package br.com.uol.pagseguro.api.application.authorization;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PermissionV2MapConverterTest {

  private PermissionV2MapConverter mapConverter;

  private List<PermissionCode.Code> permissions;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PermissionV2MapConverter();

    permissions = new ArrayList<PermissionCode.Code>() {{
      add(PermissionCode.Code.CREATE_CHECKOUTS);
      add(PermissionCode.Code.DIRECT_PAYMENT);
    }};
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("permissions", "CREATE_CHECKOUTS,DIRECT_PAYMENT");
    }});

    RequestMap map = mapConverter.convert(permissions);

    assertEquals(expectedMap, map);
  }
}