package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.Bank;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.common.domain.builder.BankBuilder;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.*;

/**
 * @author PagSeguro Internet Ltda.
 */
public class BankV3MapConverterTest {

  private BankV3MapConverter mapConverter;

  private Bank bank;

  @Before
  public void setUp() throws Exception {
    mapConverter = new BankV3MapConverter();

    bank = new BankBuilder()
        .withName(BankName.Name.BANCO_DO_BRASIL)
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>(){{
      put("bank.name", "bancodobrasil");
    }});

    RequestMap map = mapConverter.convert(bank);

    assertEquals(expectedMap, map);
  }

}