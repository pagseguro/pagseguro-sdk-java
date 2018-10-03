package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Converter for Json Direct Pre Approval Changing Status
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalChangingStatusJsonConvert extends AbstractJsonConverter<DirectPreApprovalChangingStatus> {

    /**
     * Convert Interface for Pre Approval Changing Status in Request Map
     *
     * @param requestJson                     Request Map used to pass params to api
     * @param directPreApprovalChangingStatus Interface for Pre Approval Changing Status
     * @see RequestJson
     * @see DirectPreApprovalChangingStatus
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalChangingStatus directPreApprovalChangingStatus) {
        requestJson.putString("status", directPreApprovalChangingStatus.getStatus());
    }
}
