package br.com.uol.pagseguro.api.common.domain.converter;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import br.com.uol.pagseguro.api.common.domain.CreditCard;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.CreditCardBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DocumentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.HolderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.utils.RequestMap;

import static org.junit.Assert.assertEquals;

/**
 * @author PagSeguro Internet Ltda.
 */
public class CreditCardV2MapConverterTest {

  private CreditCardV2MapConverter mapConverter;

  private CreditCard creditCard;

  @Before
  public void setUp() throws Exception {
    mapConverter = new CreditCardV2MapConverter();

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    creditCard = new CreditCardBuilder()
        .withToken("token")
        .withHolder(new HolderBuilder()
            .withName("name")
            .addDocument(new DocumentBuilder()
                .withType(DocumentType.CPF)
                .withValue("cpf")
            )
            .addDocument(new DocumentBuilder()
                .withType(DocumentType.CNPJ)
                .withValue("cnpj")
            )
            .withBithDate(dateFormat.parse("2016/11/09 00:00:00"))
            .withPhone(new PhoneBuilder()
                .withAreaCode("16")
                .withNumber("123456789")
            )
        )
        .withBillingAddress(new AddressBuilder()
            .withCountry("BRA")
            .withState("PA")
            .withPostalCode("99999999")
            .withCity("city")
            .withDistrict("district")
            .withStreet("street")
            .withNumber("999")
            .withComplement("complement")
        )
        .build();
  }

  @Test
  public void shouldConvert() throws Exception {
    RequestMap expectedMap = new RequestMap();
    expectedMap.putMap(new HashMap<String, String>(){{
      put("creditCardToken", "token");
      put("creditCardHolderName", "name");
      put("creditCardHolderCPF", "cpf");
      put("creditCardHolderCNPJ", "cnpj");
      put("creditCardHolderBirthDate", "09/11/2016");
      put("creditCardHolderAreaCode", "16");
      put("creditCardHolderPhone", "123456789");
      put("billingAddressCountry", "BRA");
      put("billingAddressState", "PA");
      put("billingAddressCity", "city");
      put("billingAddressPostalCode", "99999999");
      put("billingAddressDistrict", "district");
      put("billingAddressStreet", "street");
      put("billingAddressNumber", "999");
      put("billingAddressComplement", "complement");
    }});

    RequestMap map = mapConverter.convert(creditCard);

    assertEquals(expectedMap, map);
  }

}