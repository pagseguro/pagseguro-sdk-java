package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

public class DirectPreApprovalRequestDiscountJsonConvert extends AbstractJsonConverter<DirectPreApprovalRequestDiscount> {

    /**
     * Convert Interface for Pre Approval discount in Request Map
     *
     * @param requestJson              Request Map used to pass params to api
     * @param directPreApprovalDiscount Interface for Pre Approval Discount
     * @see RequestJson
     * @see DirectPreApprovalRequestDiscount
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalRequestDiscount directPreApprovalDiscount) {
        requestJson.putString("type", directPreApprovalDiscount.getType());
        requestJson.putString("value", directPreApprovalDiscount.getValue());
    }
}
