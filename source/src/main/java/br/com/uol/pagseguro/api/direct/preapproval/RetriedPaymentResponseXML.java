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
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of {@code RetriedPayment} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "paymentResult")
public class RetriedPaymentResponseXML implements RetriedPayment, XMLUnmarshallListener {
    private PagSeguro pagSeguro;
    private String transactionCode;
    private Date date;

    public RetriedPaymentResponseXML() {
    }

    @Override
    public String getTransactionCode() {
        return transactionCode;
    }

    @XmlElement
    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @XmlElement
    public  void setDate(Date date) {
        this.date = date;
    }

    /**
     * Callback always called after unmarshal serves to when you need to keep within
     * XML a reference to the PagSeguro APIs.
     *
     * @param pagseguro PagSeguro
     * @param rawData      String
     */
    @Override
    public void onUnmarshal(PagSeguro pagseguro, String rawData) {
        this.pagSeguro = pagseguro;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return "RetriedPaymentResponseXML{" +
            "pagSeguro=" + pagSeguro +
            ", transactionCode='" + transactionCode + '\'' +
            ", date='" + sdf.format(date) + '\'' +
            '}';
    }
}
