package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PreApprovalJsonConverter;
import br.com.uol.pagseguro.api.common.domain.converter.SenderV2MapConverter;
import br.com.uol.pagseguro.api.common.domain.converter.ShippingV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;
import br.com.uol.pagseguro.api.utils.RequestMap;
//@TODO change for ...RegistrationJsonConverter
public class DirectPreApprovalRegistrationMapConverter extends AbstractJsonConverter<DirectPreApprovalRegistration> {
    private final static PreApprovalJsonConverter PRE_APPROVAL_JC = new PreApprovalJsonConverter();
    private final static ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

    DirectPreApprovalRegistrationMapConverter() {
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
//        requestJson.jsonBody();
        //@TODO add parameter, same as pre approval objetc
//        requestMap.putMap(PARAMETER_MC.convert(preApprovalRegistration.getParameters()));
        //@TODO add receiver, same as pre approval objetc
    }
}
