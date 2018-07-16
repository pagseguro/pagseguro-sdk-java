package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.DataList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Parse the XML response of Direct Pre Approval By Day Interval List - Implementation of {@code DirectPreApprovalDataXML}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "preApprovalSearchResult")
public class DirectPreApprovalByDayIntervalListResponseXML implements DataList<DirectPreApprovalDataXML> {

    private List<DirectPreApprovalDataXML> directPreApprovalByDayInterval;
    private Integer totalPages;
    private Integer resultsInThisPage;
    private Integer currentPage;
    private Date date;

    @XmlElement(name = "preApproval")
    @XmlElementWrapper(name = "preApprovals")
    public void setDirectPreApprovalByDayInterval(List<DirectPreApprovalDataXML> directPreApprovalByDayInterval) {
        this.directPreApprovalByDayInterval = directPreApprovalByDayInterval;
    }

    public List<DirectPreApprovalDataXML> getDirectPreApprovalByDayInterval() {
        return directPreApprovalByDayInterval;
    }

    @Override
    public List<DirectPreApprovalDataXML> getData() {
        return directPreApprovalByDayInterval != null ? directPreApprovalByDayInterval : Collections.<DirectPreApprovalDataXML>emptyList();
    }

    @Override
    public Integer getTotalPages() {
        return totalPages;
    }

    @XmlElement
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getResultsInThisPage() {
        return resultsInThisPage;
    }

    @XmlElement
    public void setResultsInThisPage(Integer resultsInThisPage) {
        this.resultsInThisPage = resultsInThisPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    @XmlElement
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @XmlElement
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Integer size() {
        return getResultsInThisPage();
    }

    @Override
    public Boolean isEmpty() {
        return getData().isEmpty();
    }

    @Override
    public Iterator<DirectPreApprovalDataXML> iterator() {
        return getData().iterator();
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return "DirectPreApprovalByDateIntervalListResponseXML{" +
            "resultsInThisPage=" + resultsInThisPage +
            ", currentPage=" + currentPage +
            ", totalPages=" + totalPages +
            ", date='" + sdf.format(date) + '\'' +
            ", preApprovalData=" + directPreApprovalByDayInterval +
            '}';
    }
}
