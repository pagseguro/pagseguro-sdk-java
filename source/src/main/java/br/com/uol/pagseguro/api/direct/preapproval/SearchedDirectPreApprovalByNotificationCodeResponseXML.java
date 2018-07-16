package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.xml.SenderXML;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Implementation of {@code SearchedDirectPreApprovalByNotificationCode} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "preApproval")
public class SearchedDirectPreApprovalByNotificationCodeResponseXML implements SearchedDirectPreApprovalByNotificationCode, XMLUnmarshallListener {

    private PagSeguro pagSeguro;
    private String name;
    private String code;
    private String tracker;
    private String status;
    private String lastEventDate;
    private String reference;
    private String charge;
    private SenderXML sender;
    private String date;

    SearchedDirectPreApprovalByNotificationCodeResponseXML() {
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    @XmlElement
    public void setCode(String code) {
        this.code = code;
    }

    public String getTracker() {
        return tracker;
    }

    @XmlElement
    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastEventDate() {
        return lastEventDate;
    }

    @XmlElement
    public void setLastEventDate(String lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    public String getReference() {
        return reference;
    }

    @XmlElement
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCharge() {
        return charge;
    }

    @XmlElement
    public void setCharge(String charge) {
        this.charge = charge;
    }

    public SenderXML getSender() { return sender; }

    @XmlElement
    public void setSender(SenderXML sender) { this.sender = sender; }

    public String getDate() {
        return date;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void onUnmarshal(PagSeguro pagseguro, String rawData) { this.pagSeguro = pagseguro; }

    @Override
    public String toString() {
        return "PreApprovalData{" +
                "pagSeguro=" + pagSeguro +
                ", name=" + name +
                ", code=" + code +
                ", tracker=" + tracker +
                ", status=" + status +
                ", lastEventDate=" + lastEventDate +
                ", reference=" + reference +
                ", charge=" + charge +
                ", sender=" + sender +
                ", date=" + date +
                '}';
    }
}
