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
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.TransactionMethod;
import br.com.uol.pagseguro.api.common.domain.TransactionStatus;
import br.com.uol.pagseguro.api.common.domain.TransactionType;
import br.com.uol.pagseguro.api.common.domain.xml.CreditorFeeXML;
import br.com.uol.pagseguro.api.common.domain.xml.PaymentItemXML;
import br.com.uol.pagseguro.api.common.domain.xml.SenderXML;
import br.com.uol.pagseguro.api.common.domain.xml.ShippingXML;
import br.com.uol.pagseguro.api.common.domain.xml.TransactionPaymentMethodXML;

/**
 * Implementation of {@code TransactionDetail}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "transaction")
public class TransactionDetailXML implements TransactionDetail {

  private String code;

  private String reference;

  private Date date;

  private Date lastEvent;

  private Integer typeId;

  private Integer statusId;

  private SenderXML sender;

  private ShippingXML shipping;

  private List<PaymentItemXML> items;

  private TransactionPaymentMethodXML paymentMethod;

  private BigDecimal grossAmount;

  private BigDecimal discountAmount;

  private BigDecimal netAmount;

  private BigDecimal extraAmount;

  private String paymentLink;

  private BigDecimal feeAmount;

  private String mode;

  private String methodDescription;

  private Date escrowEndDate;

  private String cancellationSource;

  private CreditorFeeXML creditorFees;

  TransactionDetailXML() {
  }

  public String getCode() {
    return code;
  }

  @Override
  public List<? extends Parameter> getParameters() {
    return null;
  }

  @XmlElement
  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getReference() {
    return reference;
  }

  @XmlElement
  public void setReference(String reference) {
    this.reference = reference;
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
  public Date getLastEvent() {
    return lastEvent;
  }

  @XmlElement(name = "lastEventDate")
  public void setLastEvent(Date lastEvent) {
    this.lastEvent = lastEvent;
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

  public Integer getTypeId() {
    return typeId;
  }

  @XmlElement(name = "type")
  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  @Override
  public TransactionType getType() {
    return new TransactionType(getTypeId());
  }

  @XmlElement
  public void setSender(SenderXML sender) {
    this.sender = sender;
  }

  @Override
  public SenderXML getSender() {
    return sender;
  }

  @XmlElement(name = "item")
  @XmlElementWrapper(name = "items")
  public void setItems(List<PaymentItemXML> items) {
    this.items = items;
  }

  @Override
  public List<PaymentItemXML> getItems() {
    return items;
  }

  @XmlElement
  public void setShipping(ShippingXML shipping) {
    this.shipping = shipping;
  }

  @Override
  public ShippingXML getShipping() {
    return shipping;
  }

  @XmlElement
  public void setPaymentMethod(TransactionPaymentMethodXML paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  @Override
  public TransactionPaymentMethodXML getPaymentMethod() {
    return paymentMethod;
  }

  @XmlElement
  public void setExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
  }

  @Override
  public BigDecimal getExtraAmount() {
    return extraAmount;
  }

  @XmlElement
  public void setNetAmount(BigDecimal netAmount) {
    this.netAmount = netAmount;
  }

  @Override
  public BigDecimal getNetAmount() {
    return netAmount;
  }

  @XmlElement
  public void setGrossAmount(BigDecimal grossAmount) {
    this.grossAmount = grossAmount;
  }

  @Override
  public BigDecimal getGrossAmount() {
    return grossAmount;
  }

  @XmlElement
  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  @Override
  public BigDecimal getDiscountAmount() {
    return discountAmount;
  }

  @XmlElement
  public void setPaymentLink(String paymentLink) {
    this.paymentLink = paymentLink;
  }

  @Override
  public String getPaymentLink() {
    return paymentLink;
  }

  @Override
  public BigDecimal getFeeAmount() {
    return feeAmount;
  }

  @XmlElement
  public void setFeeAmount(BigDecimal feeAmount) {
    this.feeAmount = feeAmount;
  }

  @Override
  public String getMode() {
    return mode;
  }

  @XmlElement
  public void setMode(String mode) {
    this.mode = mode;
  }

  @Override
  public TransactionMethod getMethod() {
    return methodDescription != null ? new TransactionMethod(methodDescription) : null;
  }

  public String getMethodDescription() {
    return methodDescription;
  }

  @XmlElement(name = "method")
  public void setMethodDescription(String methodDescription) {
    this.methodDescription = methodDescription;
  }

  @Override
  public Date getEscrowEndDate() {
    return escrowEndDate;
  }

  @XmlElement
  public void setEscrowEndDate(Date escrowEndDate) {
    this.escrowEndDate = escrowEndDate;
  }

  @Override
  public String getCancellationSource() {
    return cancellationSource;
  }

  @XmlElement
  public void setCancellationSource(String cancellationSource) {
    this.cancellationSource = cancellationSource;
  }

  public CreditorFeeXML getCreditorFees() {
    return creditorFees;
  }

  @XmlElement
  public void setCreditorFees(CreditorFeeXML creditorFees) {
    this.creditorFees = creditorFees;
  }

  @Override
  public String toString() {
    return "TransactionDetailXML{" +
        "code='" + code + '\'' +
        ", reference='" + reference + '\'' +
        ", date=" + date +
        ", lastEvent=" + lastEvent +
        ", typeId=" + typeId +
        ", statusId=" + statusId +
        ", sender=" + sender +
        ", shipping=" + shipping +
        ", items=" + items +
        ", paymentMethod=" + paymentMethod +
        ", grossAmount=" + grossAmount +
        ", discountAmount=" + discountAmount +
        ", netAmount=" + netAmount +
        ", extraAmount=" + extraAmount +
        ", paymentLink='" + paymentLink + '\'' +
        ", feeAmount=" + feeAmount +
        ", mode='" + mode + '\'' +
        ", methodDescription='" + methodDescription + '\'' +
        ", escrowEndDate=" + escrowEndDate +
        ", cancellationSource='" + cancellationSource + '\'' +
        ", creditorFeeXML=" + creditorFees +
        '}';
  }
}
