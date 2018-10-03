package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.converter.PaymentItemsJsonConverter;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalChargeJsonConvert extends  AbstractJsonConverter<DirectPreApprovalCharge>{

    public static final PaymentItemsJsonConverter PAYMENT_ITEMS_JC = new PaymentItemsJsonConverter();
    /**
     * Convert Interface for Pre Approval Edition in Request Map
     *
     * @param requestJson              Request Map used to pass params to api
     * @param directPreApprovalCharge Interface for Pre Approval Edition
     * @see RequestJson
     * @see DirectPreApprovalEdition
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalCharge directPreApprovalCharge) {
        requestJson.putString("preApprovalCode", directPreApprovalCharge.getPreApprovalCode());
        requestJson.putString("reference", directPreApprovalCharge.getReference());
        requestJson.putString("senderHash", directPreApprovalCharge.getSenderHash());
        requestJson.putString("senderIp", directPreApprovalCharge.getSenderIp());
        requestJson.putJsonArray(PAYMENT_ITEMS_JC.convert(directPreApprovalCharge.getItems()), "items");
    }
}
