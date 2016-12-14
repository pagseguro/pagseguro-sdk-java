package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.builder.ParameterBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class ParameterV2MapConverterTest {

  private ParameterV2MapConverter mapConverter;

  private List<Parameter> parameters;

  @Before
  public void setUp() throws Exception {
    mapConverter = new ParameterV2MapConverter();

    parameters = new ArrayList<Parameter>() {{
      add(new ParameterBuilder()
          .withName("param1")
          .withValue("value1")
          .build()
      );
      add(new ParameterBuilder()
          .withName("param2")
          .withValue("value2")
          .build()
      );
    }};
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("param1", "value1");
      put("param2", "value2");
    }});

    RequestMap map = mapConverter.convert(parameters);

    assertEquals(expectedMap, map);
  }

}