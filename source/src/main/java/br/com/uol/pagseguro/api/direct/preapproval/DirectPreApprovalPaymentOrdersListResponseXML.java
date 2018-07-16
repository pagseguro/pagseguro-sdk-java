/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */


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
 * Parse the XML response of Direct Pre Approval Payment Orders List - Implementation of
 * {@code PaymentOrderXML} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "paymentOrdersResult")
public class DirectPreApprovalPaymentOrdersListResponseXML implements DataList<PaymentOrderXML> {
    private List<PaymentOrderXML> paymentOrders;
    private Integer totalPages;
    private Integer resultsInThisPage;
    private Integer currentPage;
    private Date date;

    @XmlElement(name = "paymentOrder")
    @XmlElementWrapper(name = "paymentOrders")
    public void setPaymentOrders(List<PaymentOrderXML> paymentOrders) {
        this.paymentOrders = paymentOrders;
    }

    public List<PaymentOrderXML> getPaymentOrders() {
        return paymentOrders;
    }

    @Override
    public List<PaymentOrderXML> getData() {
        return paymentOrders != null ? paymentOrders : Collections.<PaymentOrderXML>emptyList();
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
    public Iterator<PaymentOrderXML> iterator() {
        return getData().iterator();
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return "PaymentOrdersListResponseXML{" +
                "totalPages=" + totalPages +
                ", resultsInThisPage=" + resultsInThisPage +
                ", currentPage=" + currentPage +
                ", date='" + sdf.format(date) + '\'' +
                ", paymentOrders=" + paymentOrders +
                '}';
    }
}
