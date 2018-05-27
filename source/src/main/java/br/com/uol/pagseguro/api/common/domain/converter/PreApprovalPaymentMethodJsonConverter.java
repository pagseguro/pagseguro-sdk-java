package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.PreApprovalPaymentMethod;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Converter for sender.
 * Used to convert attributes of pre approval in request json.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalPaymentMethodJsonConverter extends AbstractJsonConverter<PreApprovalPaymentMethod> {
    private static final PreApprovalPaymentMethodCreditCardJsonConverter PRE_APPROVAL_PAYMENT_METHOD_CREDITCARD_JC = new PreApprovalPaymentMethodCreditCardJsonConverter();

    /**
     * Convert attributes of direct pre approval in request json
     *
     * @param requestJson  Request Json used to pass params to api
     * @param preApprovalPaymentMethod The interface of pre approval
     * @see RequestJson
     * @see Sender
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, PreApprovalPaymentMethod preApprovalPaymentMethod) {
        requestJson.putString("type", preApprovalPaymentMethod.getType());
        requestJson.putJson(PRE_APPROVAL_PAYMENT_METHOD_CREDITCARD_JC.convert(preApprovalPaymentMethod.getPreApprovalCreditCard()), "creditCard");
    }

    /**
     * Implementation of {@code AbstractPreApprovalCreditCardJsonConverter}. Used to set key values
     *
     * @see AbstractPreApprovalCreditCardJsonConverter
     */
    private static class PreApprovalPaymentMethodCreditCardJsonConverter extends AbstractPreApprovalCreditCardJsonConverter {
        protected String getTokenKey() { return "token"; }
        protected String getPreApprovalHolderKey() { return "holder"; }
    }
}
