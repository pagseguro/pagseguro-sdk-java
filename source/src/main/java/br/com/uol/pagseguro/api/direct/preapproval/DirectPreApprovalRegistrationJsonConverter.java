package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.*;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalRegistrationJsonConverter extends AbstractJsonConverter<DirectPreApprovalRegistration> {
    private final static PreApprovalJsonConverter PRE_APPROVAL_JC = new PreApprovalJsonConverter();
    private final static ReceiverJsonConverter RECEIVER_JC = new ReceiverJsonConverter();
    private final static ParameterJsonConverter PARAMETER_JC = new ParameterJsonConverter();

    DirectPreApprovalRegistrationJsonConverter() {
    }

    /**
     * Convert Interface for Pre Approval Registration in Request Map
     *
     * @param requestJson              Request Map used to pass params to api
     * @param directPreApprovalRegistration Interface for Pre Approval Registration
     * @see RequestJson
     * @see DirectPreApprovalRegistration
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalRegistration directPreApprovalRegistration) {
        requestJson.putString("reference", directPreApprovalRegistration.getReference());
        requestJson.putString("redirectURL", directPreApprovalRegistration.getRedirectURL());
        requestJson.putString("reviewURL", directPreApprovalRegistration.getReviewURL());
        requestJson.putJson(PRE_APPROVAL_JC.convert(directPreApprovalRegistration.getPreApproval()), "preApproval");
        requestJson.putJson(RECEIVER_JC.convert(directPreApprovalRegistration.getReceiver()), "receiver");
        requestJson.putJsonList(PARAMETER_JC.convert(directPreApprovalRegistration.getParameters()));
    }
}
