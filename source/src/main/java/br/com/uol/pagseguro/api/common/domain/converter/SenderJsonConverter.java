package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Converter for sender.
 * Used to convert attributes of pre approval in request json.
 *
 * @author PagSeguro Internet Ltda.
 */
public class SenderJsonConverter extends AbstractJsonConverter<Sender> {
    private static final SenderPhoneJsonConverter SENDER_PHONE_JC = new SenderPhoneJsonConverter();
    private static final SenderAddressJsonConverter SENDER_ADDRESS_JC = new SenderAddressJsonConverter();
    private static final SenderDocumentJsonConverter SENDER_DOCUMENT_JC = new SenderDocumentJsonConverter();
    /**
     * Convert attributes of direct pre approval in request json
     *
     * @param requestJson  Request Json used to pass params to api
     * @param sender The interface of pre approval
     * @see RequestJson
     * @see Sender
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, Sender sender) {
        requestJson.putString("name", sender.getName());
        requestJson.putString("email", sender.getEmail());
        requestJson.putString("ip", sender.getIp());
        requestJson.putString("hash", sender.getHash());
        requestJson.putJson(SENDER_PHONE_JC.convert(sender.getPhone()), "phone");
        requestJson.putJson(SENDER_ADDRESS_JC.convert(sender.getAddress()), "address");
        requestJson.putJsonArray(SENDER_DOCUMENT_JC.convert(sender.getDocuments()), "documents");
    }

    /**
     * Implementation of {@code AbstractPhoneJsonConverter}. Used to set key values
     *
     * @see AbstractPhoneJsonConverter
     */
    private static class SenderPhoneJsonConverter extends AbstractPhoneJsonConverter {

        @Override
        protected String getAreaCodeKey() {
            return "areaCode";
        }

        @Override
        protected String getPhoneNumberKey() {
            return "number";
        }

    }

    /**
     * Implementation of {@code AbstractAddressJsonConverter}. Used to set key values
     *
     * @see AbstractAddressJsonConverter
     */
    private static class SenderAddressJsonConverter extends AbstractAddressJsonConverter {
        @Override
        protected String getStreetKey() {
            return "street";
        }

        @Override
        protected String getNumberKey() {
            return "number";
        }

        @Override
        protected String getComplementKey() {
            return "complement";
        }

        @Override
        protected String getDistrictKey() {
            return "district";
        }

        @Override
        protected String getPostalCodeKey() {
            return "postalCode";
        }

        @Override
        protected String getCityKey() {
            return "city";
        }

        @Override
        protected String getStateKey() {
            return "state";
        }

        @Override
        protected String getCountryKey() {
            return "country";
        }
    }

    /**
     * Implementation of {@code AbstractDocumentJsonConverter}. Used to set key values
     *
     * @see AbstractDocumentJsonConverter
     */
    private static class SenderDocumentJsonConverter extends AbstractDocumentJsonConverter {

        @Override
        protected String getTypeKey() {
            return "type";
        }

        @Override
        protected String getValueKey() {
            return "value";
        }

    }
}
