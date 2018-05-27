package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.PreApprovalHolder;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

import java.text.SimpleDateFormat;

/**
 * Abstract converter for pre approval holder, used in Payment Method Credit Card.
 * Used to convert attributes of pre approval holder in request json.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractPreApprovalHolderJsonConverter extends AbstractJsonConverter<PreApprovalHolder> {

    private static final PreApprovalHolderPhoneJsonConverter PRE_APPROVAL_HOLDER_PHONE_JC = new PreApprovalHolderPhoneJsonConverter();
    private static final PreApprovalHolderAddressJsonConverter PRE_APPROVAL_HOLDER_ADDRESS_JC = new PreApprovalHolderAddressJsonConverter();
    private static final PreApprovalHolderDocumentJsonConverter PRE_APPROVAL_HOLDER_DOCUMENT_JC = new PreApprovalHolderDocumentJsonConverter();

    /**
     * Convert attributes of Pre Approval Holder in request json
     *
     * @param requestJson Request Json used to pass params to api
     * @param preApprovalHolder Object to be converted
     * @see RequestJson
     * @see PreApprovalHolder
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, PreApprovalHolder preApprovalHolder) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        requestJson.putString(getNameKey(), preApprovalHolder.getName());
        requestJson.putDate(getBirthDateKey(), preApprovalHolder.getBirthDate(), sdf);
        requestJson.putJsonArray(PRE_APPROVAL_HOLDER_DOCUMENT_JC.convert(preApprovalHolder.getDocuments()), getDocumentsKey());
        requestJson.putJson(PRE_APPROVAL_HOLDER_PHONE_JC.convert(preApprovalHolder.getPhone()), getPhoneKey());
        requestJson.putJson(PRE_APPROVAL_HOLDER_ADDRESS_JC.convert(preApprovalHolder.getBillingAddress()), getBillingAddressKey());
    }

    /**
     * Get name key
     *
     * @return The key to the name is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getNameKey();

    /**
     * Get birthdate key
     *
     * @return The key to the birthdate is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getBirthDateKey();

    /**
     * Get document key
     *
     * @return The key to the document is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getDocumentsKey();

    /**
     * Get phone key
     *
     * @return The key to the phone is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getPhoneKey();

    /**
     * Get billing address key
     *
     * @return The key to the billing address is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getBillingAddressKey();

    /**
     * Implementation of {@code AbstractPhoneJsonConverter}. Used to set key values
     *
     * @see AbstractPhoneJsonConverter
     */
    private static class PreApprovalHolderPhoneJsonConverter extends AbstractPhoneJsonConverter {

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
    private static class PreApprovalHolderAddressJsonConverter extends AbstractAddressJsonConverter {
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
    private static class PreApprovalHolderDocumentJsonConverter extends AbstractDocumentJsonConverter {

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
