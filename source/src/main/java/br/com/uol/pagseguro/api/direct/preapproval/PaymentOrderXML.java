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

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of {@code PaymentOrder} and {@code XMLUnmarshallListener}. Responsible for parse the XML response of
 * Payment Order
 *
 * @author PagSeguro Internet Ltda.
 */
public class PaymentOrderXML implements PaymentOrder {
    private String code;
    private Integer status;
    private BigDecimal amount;
    private BigDecimal grossAmount;
    private String lastEventDate;
    private String schedulingDate;
    private List<TransactionXML> transactions;
    private DiscountXML discount;

    PaymentOrderXML() {
    }

    //@Override
    public String getCode() {
        return code;
    }

    @XmlElement
    public void setCode(String code) {
        this.code = code;
    }

    //@Override
    public Integer getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(Integer status) {
        this.status = status;
    }

    //@Override
    public BigDecimal getAmount() {
        return amount;
    }

    @XmlElement
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    //@Override
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    @XmlElement
    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getLastEventDate() {
        return lastEventDate;
    }

    @XmlElement
    public void setLastEventDate(String lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    //@Override
    public String getSchedulingDate() {
        return schedulingDate;
    }

    @XmlElement
    public void setSchedulingDate(String schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    @Override
    public List<TransactionXML> getTransactions() {
        return transactions;
        //return new ArrayList<Transaction>();
    }

    //@XmlElement(name = "transaction")
    //@XmlElementWrapper(name = "transactions")
    public void setTransactions(List<TransactionXML> transactions) {
        this.transactions = transactions;
    }

    //@Override
    public DiscountXML getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountXML discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "PaymentOrderXML{" +
                "code=" + code +
                ", status=" + status +
                //", code='" + code + '\'' +
                ", amount=" + amount +
                ", grossAmount=" + grossAmount +
                ", lastEventDate=" + lastEventDate +
                ", discount=" + discount +
                ", schedulingDate=" + schedulingDate +
                ", transactions=" + transactions +
                '}';
    }
}
