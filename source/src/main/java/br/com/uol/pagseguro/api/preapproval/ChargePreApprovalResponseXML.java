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

package br.com.uol.pagseguro.api.preapproval;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

/**
 * Implementation of {@code ChargedPreApproval} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "result")
public class ChargePreApprovalResponseXML implements ChargedPreApproval, XMLUnmarshallListener {

  private PagSeguro pagSeguro;

  private String transactionCode;
  private Date date;

  ChargePreApprovalResponseXML() {
  }

  @Override
  public String getTransactionCode() {
    return transactionCode;
  }

  @XmlElement
  public void setTransactionCode(String transactionCode) {
    this.transactionCode = transactionCode;
  }

  public Date getDate() {
    return date;
  }

  @XmlElement
  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public TransactionDetail getTransactionDetail() {
    return pagSeguro.transactions().search().byCode(getTransactionCode());
  }

  @Override
  public void onUnmarshal(PagSeguro pagseguro, String rawData) {
    this.pagSeguro = pagseguro;
  }

  @Override
  public String toString() {
    return "ChargePreApprovalResponseXML{" +
        "pagSeguro=" + pagSeguro +
        ", transactionCode='" + transactionCode + '\'' +
        ", date=" + date +
        '}';
  }
}
