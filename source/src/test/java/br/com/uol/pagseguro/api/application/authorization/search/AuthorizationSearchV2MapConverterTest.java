package br.com.uol.pagseguro.api.application.authorization.search;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class AuthorizationSearchV2MapConverterTest {

  private AuthorizationSearchV2MapConverter mapConverter;

  private AuthorizationSearch authorizationSearch;

  @Before
  public void setUp() throws Exception {
    mapConverter = new AuthorizationSearchV2MapConverter();

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    authorizationSearch = new AuthorizationSearchBuilder()
        .withPage(7)
        .withMaxResults(23)
        .withDateRange(new DateRangeBuilder()
            .between(dateFormat.parse("2016/11/09 00:00:00"),
                dateFormat.parse("2016/11/09 23:59:59"))
        )
        .withReference("reference")
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("page", "7");
      put("maxPageResults", "23");
      put("initialDate", "2016-11-09T00:00");
      put("finalDate", "2016-11-09T23:59");
      put("reference", "reference");
    }});

    RequestMap map = mapConverter.convert(authorizationSearch);

    assertEquals(expectedMap, map);
  }

}