package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.PreApprovalCreditCard;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Abstract converter for pre approval payment method credit card.
 * Used to convert attributes of pre approval payment method credit card in request json.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractPreApprovalCreditCardJsonConverter extends AbstractJsonConverter<PreApprovalCreditCard> {

    private static final PreApprovalHolderJsonConverter PRE_APPROVAL_HOLDER_JC = new PreApprovalHolderJsonConverter();
    /**
     * Convert attributes of Pre Approval Credit Card in request json
     *
     * @param requestJson Request Json used to pass params to api
     * @param preApprovalCreditCard the Interface of PreApprovalCreditCard
     * @see RequestJson
     * @see PreApprovalCreditCard
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, PreApprovalCreditCard preApprovalCreditCard) {
        requestJson.putString(getTokenKey(), preApprovalCreditCard.getToken());
        requestJson.putJson(PRE_APPROVAL_HOLDER_JC.convert(preApprovalCreditCard.getHolder()), getPreApprovalHolderKey());
    }

    /**
     * Get token key
     *
     * @return The key to the token is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getTokenKey();

    /**
     * Get Pre Approval Holder key
     *
     * @return The key to the pre approval holder is a key dynamic inserted in the implementation of this class.
     */
    protected abstract String getPreApprovalHolderKey();

    private static class PreApprovalHolderJsonConverter extends AbstractPreApprovalHolderJsonConverter {
        protected String getNameKey() { return "name"; }
        protected String getBirthDateKey() { return "birthDate"; }
        protected String getDocumentsKey() { return "documents"; }
        protected String getPhoneKey() { return "phone"; }
        protected String getBillingAddressKey() { return "billingAddress"; }
    }
}
