package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

import java.text.SimpleDateFormat;

/**
 * Converter for Direct Pre Approval By Date Interval List
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalByDateIntervalListMapConvert extends AbstractMapConverter<DirectPreApprovalByDateIntervalList> {

    DirectPreApprovalByDateIntervalListMapConvert() {
    }

    /**
     * Convert Interface for Listing Direct Pre Approval By Date Interval in Request Map
     *
     * @param requestMap Request Map used to pass params to api
     * @param directPreApprovalByDateIntervalList Interface for Direct Pre Approval By Date Interval List
     * @see RequestMap
     * @see DirectPreApprovalByDateIntervalList
     * @see AbstractMapConverter#convert(Object)
     */
    @Override
    protected void convert(RequestMap requestMap, DirectPreApprovalByDateIntervalList directPreApprovalByDateIntervalList) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        requestMap.putInteger("page", directPreApprovalByDateIntervalList.getPage());
        requestMap.putInteger("maxPageResults", directPreApprovalByDateIntervalList.getMaxPageResults());
        requestMap.putDate("initialDate", directPreApprovalByDateIntervalList.getDateRange().getFrom(), sdf);
        requestMap.putDate("finalDate", directPreApprovalByDateIntervalList.getDateRange().getTo(), sdf);
    }
}