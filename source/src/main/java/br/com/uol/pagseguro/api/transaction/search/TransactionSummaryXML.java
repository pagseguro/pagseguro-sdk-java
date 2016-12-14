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

package br.com.uol.pagseguro.api.transaction.search;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.TransactionStatus;
import br.com.uol.pagseguro.api.common.domain.TransactionType;
import br.com.uol.pagseguro.api.common.domain.xml.TransactionPaymentMethodXML;
import br.com.uol.pagseguro.api.utils.XMLUnmarshallListener;

/**
 * Implementation of {@code TransactionSummary} and {@code XMLUnmarshallListener}
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionSummaryXML implements TransactionSummary, XMLUnmarshallListener {

  private Date date;

  private String reference;

  private String code;

  private Integer typeId;

  private Integer statusId;

  private TransactionPaymentMethodXML paymentMethod;

  private BigDecimal grossAmount;

  private BigDecimal discountAmount;

  private BigDecimal feeAmount;

  private BigDecimal netAmount;

  private BigDecimal extraAmount;

  private Date lastEvent;

  private PagSeguro pagseguro;

  TransactionSummaryXML() {
  }

  public Date getDate() {
    return date;
  }

  @XmlElement
  public void setDate(Date date) {
    this.date = date;
  }

  public String getReference() {
    return reference;
  }

  @XmlElement
  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getCode() {
    return code;
  }

  @Override
  public List<? extends Parameter> getParameters() {
    return new ArrayList<Parameter>();
  }

  @XmlElement
  public void setCode(String code) {
    this.code = code;
  }

  public Integer getTypeId() {
    return typeId;
  }

  @XmlElement(name = "type")
  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getStatusId() {
    return statusId;
  }

  @XmlElement
  public void setPaymentMethod(TransactionPaymentMethodXML paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  @Override
  public TransactionPaymentMethodXML getPaymentMethod() {
    return paymentMethod;
  }

  @Override
  public TransactionType getType() {
    return new TransactionType(getTypeId());
  }

  @XmlElement(name = "status")
  public void setStatusId(Integer statusId) {
    this.statusId = statusId;
  }

  @Override
  public TransactionStatus getStatus() {
    return new TransactionStatus(getStatusId());
  }

  public BigDecimal getGrossAmount() {
    return grossAmount;
  }

  @XmlElement
  public void setGrossAmount(BigDecimal grossAmount) {
    this.grossAmount = grossAmount;
  }

  public BigDecimal getDiscountAmount() {
    return discountAmount;
  }

  @XmlElement
  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  public BigDecimal getFeeAmount() {
    return feeAmount;
  }

  @XmlElement
  public void setFeeAmount(BigDecimal feeAmount) {
    this.feeAmount = feeAmount;
  }

  public BigDecimal getNetAmount() {
    return netAmount;
  }

  @XmlElement
  public void setNetAmount(BigDecimal netAmount) {
    this.netAmount = netAmount;
  }

  public BigDecimal getExtraAmount() {
    return extraAmount;
  }

  @XmlElement
  public void setExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
  }

  public Date getLastEvent() {
    return lastEvent;
  }

  @XmlElement(name = "lastEventDate")
  public void setLastEvent(Date lastEvent) {
    this.lastEvent = lastEvent;
  }

  @Override
  public TransactionDetail getDetail() {
    return pagseguro.transactions().search().byCode(getCode());
  }

  @Override
  public void onUnmarshal(PagSeguro pagseguro, String rawData) {
    this.pagseguro = pagseguro;
  }

  @Override
  public String toString() {
    return "TransactionSummaryXML{" +
        "date=" + date +
        ", reference='" + reference + '\'' +
        ", code='" + code + '\'' +
        ", typeId=" + typeId +
        ", statusId=" + statusId +
        ", paymentMethod=" + paymentMethod +
        ", grossAmount=" + grossAmount +
        ", discountAmount=" + discountAmount +
        ", feeAmount=" + feeAmount +
        ", netAmount=" + netAmount +
        ", extraAmount=" + extraAmount +
        ", lastEvent=" + lastEvent +
        ", pagseguro=" + pagseguro +
        '}';
  }
}
