package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.PreApproval;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalV2MapConverterTest {

  private PreApprovalV2MapConverter mapConverter;

  private PreApproval preApproval;

  @Before
  public void setUp() throws Exception {
    mapConverter = new PreApprovalV2MapConverter();

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    preApproval = new PreApprovalBuilder()
        .withCharge("charge")
        .withName("name")
        .withDetails("details")
        .withAmountPerPayment(new BigDecimal(99.99))
        .withMaxAmountPerPayment(new BigDecimal(99.99))
        .withMaxTotalAmount(new BigDecimal(99.99))
        .withMaxAmountPerPeriod(new BigDecimal(99.99))
        .withMaxPaymentsPerPeriod(4)
        .withPeriod("period")
        .withDateRange(new DateRangeBuilder()
            .between(dateFormat.parse("2016/11/09 00:00:00"),
                dateFormat.parse("2016/11/09 23:59:59"))
        )
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("preApprovalCharge", "charge");
      put("preApprovalName", "name");
      put("preApprovalDetails", "details");
      put("preApprovalAmountPerPayment", "99.99");
      put("preApprovalMaxAmountPerPayment", "99.99");
      put("preApprovalMaxTotalAmount", "99.99");
      put("preApprovalMaxPaymentsPerPeriod", "4");
      put("preApprovalMaxAmountPerPeriod", "99.99");
      put("preApprovalPeriod", "period");
      put("preApprovalInitialDate", "2016-11-09T00:00:000");
      put("preApprovalFinalDate", "2016-11-09T23:59:059");
    }});

    RequestMap map = mapConverter.convert(preApproval);

    assertEquals(expectedMap, map);
  }

}