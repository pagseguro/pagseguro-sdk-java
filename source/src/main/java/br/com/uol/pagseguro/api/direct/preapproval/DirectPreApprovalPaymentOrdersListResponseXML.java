package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DirectPreApprovalDataList;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;
//@TODO finish implementation
@XmlRootElement(name = "paymentOrdersResult")
public class DirectPreApprovalPaymentOrdersListResponseXML implements DirectPreApprovalDataList<PaymentOrderXML>, XMLUnmarshallListener {
    private List<PaymentOrderXML> paymentOrders;
    private Integer totalPages;
    private Integer resultsInThisPage;
    private Integer currentPage;
    private String date;
    private boolean isEmpty;
    private PagSeguro pagseguro;


    @XmlElement(name = "paymentOrder")
    @XmlElementWrapper(name = "paymentOrders")
    public void setData(List<PaymentOrderXML> paymentOrders) {
        this.paymentOrders = paymentOrders;
    }

    @Override
    public List<PaymentOrderXML> getData() {
        return paymentOrders;
    }

    @Override
    public Integer getTotalPages() {
        return null;
    }

    @XmlElement
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public Integer getResultsInThisPage() {
        return null;
    }

    @XmlElement
    public void setResultsInThisPage(Integer resultsInThisPage) {
        this.resultsInThisPage = resultsInThisPage;
    }

    @Override
    public Integer getCurrentPage() {
        return null;
    }


    @XmlElement
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String getDate() {
        return null;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Boolean isEmpty() {
        return null;
    }

//    public void setEmpty(boolean empty) {
//        isEmpty = empty;
//    }

    @Override
    public Iterator<PaymentOrderXML> iterator() {
        return null;
    }

    @Override
    public void onUnmarshal(PagSeguro pagseguro, String rawData) {
        this.pagseguro = pagseguro;
    }

    @Override
    public String toString() {
        return "PaymentOrdersListResponseXML{" +
                "totalPages=" + totalPages +
                ", resultsInThisPage=" + resultsInThisPage +
                ", currentPage=" + currentPage +
                ", date='" + date + '\'' +
                ", paymentOrders=" + paymentOrders +
                ", pagseguro=" + pagseguro +
                '}';
    }
}
