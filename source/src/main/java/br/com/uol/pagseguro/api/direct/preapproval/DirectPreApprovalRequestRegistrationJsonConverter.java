package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.*;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalRequestRegistrationJsonConverter extends AbstractJsonConverter<DirectPreApprovalRequestRegistration> {
    private final static PreApprovalRequestJsonConverter PRE_APPROVAL_JC = new PreApprovalRequestJsonConverter();
    private final static ReceiverJsonConverter RECEIVER_JC = new ReceiverJsonConverter();
    private final static ParameterJsonConverter PARAMETER_JC = new ParameterJsonConverter();

    DirectPreApprovalRequestRegistrationJsonConverter() {
    }

    /**
     * Convert Interface for Pre Approval Registration in Request Map
     *
     * @param requestJson              Request Map used to pass params to api
     * @param directPreApprovalRequestRegistration Interface for Pre Approval Registration
     * @see RequestJson
     * @see DirectPreApprovalRequestRegistration
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalRequestRegistration directPreApprovalRequestRegistration) {
        requestJson.putString("reference", directPreApprovalRequestRegistration.getReference());
        requestJson.putString("redirectURL", directPreApprovalRequestRegistration.getRedirectURL());
        requestJson.putString("reviewURL", directPreApprovalRequestRegistration.getReviewURL());
        requestJson.putJson(PRE_APPROVAL_JC.convert(directPreApprovalRequestRegistration.getPreApproval()), "preApproval");
        requestJson.putJson(RECEIVER_JC.convert(directPreApprovalRequestRegistration.getReceiver()), "receiver");
        requestJson.putJsonList(PARAMETER_JC.convert(directPreApprovalRequestRegistration.getParameters()));
    }
}
