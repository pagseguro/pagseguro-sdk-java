package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.PaymentItemsJsonConverter;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalRequestChargeJsonConvert extends  AbstractJsonConverter<DirectPreApprovalRequestCharge>{

    public static final PaymentItemsJsonConverter PAYMENT_ITEMS_JC = new PaymentItemsJsonConverter();
    /**
     * Convert Interface for Pre Approval Edition in Request Map
     *
     * @param requestJson              Request Map used to pass params to api
     * @param directPreApprovalRequestCharge Interface for Pre Approval Edition
     * @see RequestJson
     * @see DirectPreApprovalRequestEdition
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalRequestCharge directPreApprovalRequestCharge) {
        requestJson.putString("preApprovalCode", directPreApprovalRequestCharge.getPreApprovalCode());
        requestJson.putString("reference", directPreApprovalRequestCharge.getReference());
        requestJson.putString("senderHash", directPreApprovalRequestCharge.getSenderHash());
        requestJson.putString("senderIp", directPreApprovalRequestCharge.getSenderIp());
        requestJson.putJsonArray(PAYMENT_ITEMS_JC.convert(directPreApprovalRequestCharge.getItems()), "items");
    }
}
