package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class ChargeDirectPreApprovalResponseXML implements ChargedDirectPreApproval, XMLUnmarshallListener {

    private PagSeguro pagSeguro;
    private String date;
    private String transactionCode;


    @Override
    public String getDate() { return date; }

    @XmlElement
    public void setDate(String date) { this.date = date; }

    @Override
    public String getTransactionCode() { return transactionCode; }

    @XmlElement
    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    @Override
    public void onUnmarshal(PagSeguro pagseguro, String rawData) { this.pagSeguro = pagseguro; }

    @Override
    public String toString() {
        return "RegisterPreApprovalResponseXML{" +
                //"pagSeguro=" + pagSeguro +
                //", transactionCode='" + transactionCode + '\'' +
                //", date=" + date +
                '}';
    }
}
