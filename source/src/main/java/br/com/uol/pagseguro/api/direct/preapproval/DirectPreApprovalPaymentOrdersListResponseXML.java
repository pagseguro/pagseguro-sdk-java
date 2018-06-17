package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.DirectPreApprovalDataList;

import java.util.Iterator;
import java.util.List;
//@TODO finish implementation
public class DirectPreApprovalPaymentOrdersListResponseXML implements DirectPreApprovalDataList<PaymentOrder> {
    @Override
    public List<PaymentOrder> getData() {
        return null;
    }

    @Override
    public Integer getTotalPages() {
        return null;
    }

    @Override
    public Integer getResultsInThisPage() {
        return null;
    }

    @Override
    public Integer getCurrentPage() {
        return null;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public Boolean isEmpty() {
        return null;
    }

    @Override
    public Iterator<PaymentOrder> iterator() {
        return null;
    }
}
