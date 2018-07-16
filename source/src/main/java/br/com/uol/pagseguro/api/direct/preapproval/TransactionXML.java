package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.TransactionStatus;

import javax.xml.bind.annotation.XmlElement;

public class TransactionXML implements  Transaction {
    private String code;
    private Integer statusId;
    private String date;

    //@Override
    public String getCode() {
        return code;
    }

    @XmlElement
    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatusId() {
        return statusId;
    }

    @XmlElement(name = "status")
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public TransactionStatus getStatus() {
        return new TransactionStatus(getStatusId());
    }

    @Override
    public String getDate() { return date; }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionXML{" +
                "code=" + code +
                ", statusId=" + statusId +
                ", date=" + date +
                '}';
    }
}
