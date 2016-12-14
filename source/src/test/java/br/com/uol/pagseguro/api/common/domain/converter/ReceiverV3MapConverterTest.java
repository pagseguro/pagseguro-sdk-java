package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Receiver;
import br.com.uol.pagseguro.api.common.domain.builder.ReceiverBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SplitBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class ReceiverV3MapConverterTest {

  private ReceiverV3MapConverter mapConverter;

  private List<Receiver> receivers;

  @Before
  public void setUp() throws Exception {
    mapConverter = new ReceiverV3MapConverter();

    receivers = new ArrayList<Receiver>() {{
      add(new ReceiverBuilder()
          .withPublicKey("publicKey1")
          .withSplit(new SplitBuilder()
              .withAmount(new BigDecimal(9.99))
              .withRatePercent(new BigDecimal(9.99))
              .withFeePercent(new BigDecimal(9.99))
          )
          .build()
      );
      add(new ReceiverBuilder()
          .withPublicKey("publicKey2")
          .withSplit(new SplitBuilder()
              .withAmount(new BigDecimal(9.99))
              .withRatePercent(new BigDecimal(9.99))
              .withFeePercent(new BigDecimal(9.99))
          )
          .build()
      );
    }};
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>() {{
      put("receiver[1].publicKey", "publicKey1");
      put("receiver[1].split.amount", "9.99");
      put("receiver[1].split.ratePercent", "9.99");
      put("receiver[1].split.feePercent", "9.99");
      put("receiver[2].publicKey", "publicKey1");
      put("receiver[2].split.amount", "9.99");
      put("receiver[2].split.ratePercent", "9.99");
      put("receiver[2].split.feePercent", "9.99");
    }});
  }

}