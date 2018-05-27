package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.*;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalRegistrationJsonConverter extends AbstractJsonConverter<DirectPreApprovalRegistration> {
    private final static SenderJsonConverter SENDER_JC = new SenderJsonConverter();
    private final static PreApprovalPaymentMethodJsonConverter PAYMENT_METHOD_JC = new PreApprovalPaymentMethodJsonConverter();
    private final static ParameterJsonConverter PARAMETER_JC = new ParameterJsonConverter();

    DirectPreApprovalRegistrationJsonConverter() {
    }

    /**
     * Convert Interface for Pre Approval Registration in Request Json
     *
     * @param requestJson Request Json used to pass params to api
     * @param directPreApprovalRegistration Interface for Direct Pre Approval Accession Registration
     * @see RequestJson
     * @see DirectPreApprovalRegistration
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalRegistration directPreApprovalRegistration) {
        requestJson.putString("plan", directPreApprovalRegistration.getPlan());
        requestJson.putString("reference", directPreApprovalRegistration.getReference());
        requestJson.putJson(SENDER_JC.convert(directPreApprovalRegistration.getSender()), "sender");
        requestJson.putJson(PAYMENT_METHOD_JC.convert(directPreApprovalRegistration.getPaymentMethod()), "paymentMethod");
        requestJson.putJsonList(PARAMETER_JC.convert(directPreApprovalRegistration.getParameters()));
    }
}
