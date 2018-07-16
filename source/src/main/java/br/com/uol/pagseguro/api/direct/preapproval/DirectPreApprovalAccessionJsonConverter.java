package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.*;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalAccessionJsonConverter extends AbstractJsonConverter<DirectPreApprovalAccession> {
    private final static SenderJsonConverter SENDER_JC = new SenderJsonConverter();
    private final static PreApprovalPaymentMethodJsonConverter PAYMENT_METHOD_JC = new PreApprovalPaymentMethodJsonConverter();
    private final static ParameterJsonConverter PARAMETER_JC = new ParameterJsonConverter();

    DirectPreApprovalAccessionJsonConverter() {
    }

    /**
     * Convert Interface for Pre Approval Registration in Request Json
     *
     * @param requestJson Request Json used to pass params to api
     * @param directPreApprovalAccession Interface for Direct Pre Approval Accession Registration
     * @see RequestJson
     * @see DirectPreApprovalAccession
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalAccession directPreApprovalAccession) {
        requestJson.putString("plan", directPreApprovalAccession.getPlan());
        requestJson.putString("reference", directPreApprovalAccession.getReference());
        requestJson.putJson(SENDER_JC.convert(directPreApprovalAccession.getSender()), "sender");
        requestJson.putJson(PAYMENT_METHOD_JC.convert(directPreApprovalAccession.getPaymentMethod()), "paymentMethod");
        requestJson.putJsonList(PARAMETER_JC.convert(directPreApprovalAccession.getParameters()));
    }
}
