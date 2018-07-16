package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for Direct Pre Approval By Day Interval List
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalByDayIntervalListMapConvert extends AbstractMapConverter<DirectPreApprovalByDayIntervalList> {

    DirectPreApprovalByDayIntervalListMapConvert() {
    }

    /**
     * Convert Interface for Listing Direct Pre Approval By Day Interval in Request Map
     *
     * @param requestMap Request Map used to pass params to api
     * @param directPreApprovalByDayIntervalList Interface for Direct Pre Approval By Day Interval List
     * @see RequestMap
     * @see DirectPreApprovalByDayIntervalList
     * @see AbstractMapConverter#convert(Object)
     */
    @Override
    protected void convert(RequestMap requestMap, DirectPreApprovalByDayIntervalList directPreApprovalByDayIntervalList) {
        requestMap.putInteger("page", directPreApprovalByDayIntervalList.getPage());
        requestMap.putInteger("maxPageResults", directPreApprovalByDayIntervalList.getMaxPageResults());
        requestMap.putInteger("interval", directPreApprovalByDayIntervalList.getInterval());
    }
}
