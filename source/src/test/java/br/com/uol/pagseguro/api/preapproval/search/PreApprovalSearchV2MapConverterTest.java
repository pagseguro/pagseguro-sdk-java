package br.com.uol.pagseguro.api.preapproval.search;

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
public class PreApprovalSearchV2MapConverterTest {

  private PreApprovalSearchV2MapConverter mapConverter;

  private PreApprovalSearch preApprovalSearch;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PreApprovalSearchV2MapConverter();

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    preApprovalSearch = new PreApprovalSearchBuilder()
        .withDateRange(new DateRangeBuilder()
            .between(dateFormat.parse("2016/11/09 00:00:00"),
                dateFormat.parse("2016/11/09 23:59:59"))
        )
        .withReference("reference")
        .withPage(3)
        .withMaxResults(5)
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("initialDate", "2016-11-09T00:00");
      put("finalDate", "2016-11-09T23:59");
      put("reference", "reference");
      put("page", "3");
      put("maxPageResults", "5");
    }});

    RequestMap map = mapConverter.convert(preApprovalSearch);

    assertEquals(expectedMap, map);
  }

}