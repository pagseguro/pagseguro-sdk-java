package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Converter for Json Direct Pre Approval Edition
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalEditionJsonConvert extends AbstractJsonConverter<DirectPreApprovalEdition> {

    /**
     * Convert Interface for Pre Approval Edition in Request Map
     *
     * @param requestJson              Request Map used to pass params to api
     * @param directPreApprovalEdition Interface for Pre Approval Edition
     * @see RequestJson
     * @see DirectPreApprovalEdition
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalEdition directPreApprovalEdition) {
        requestJson.putString("amountPerPayment", directPreApprovalEdition.getAmountPerPayment());
        requestJson.putBoolean("updateSubscriptions", directPreApprovalEdition.getUpdateSubscriptions());
    }
}
