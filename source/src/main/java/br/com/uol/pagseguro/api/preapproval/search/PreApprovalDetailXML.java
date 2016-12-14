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

package br.com.uol.pagseguro.api.preapproval.search;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.PreApprovalStatus;
import br.com.uol.pagseguro.api.common.domain.xml.SenderXML;

/**
 * Implementation of {@code PreApprovalDetail}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "preApproval")
public class PreApprovalDetailXML implements PreApprovalDetail {

  private String name;
  private String code;
  private Date date;
  private String tracker;
  private String statusId;
  private String reference;
  private Date lastEvent;
  private String charge;
  private SenderXML sender;

  PreApprovalDetailXML() {
  }

  @Override
  public String getName() {
    return name;
  }

  @XmlElement
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getCode() {
    return code;
  }

  @XmlElement
  public void setCode(String code) {
    this.code = code;
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
  public String getTracker() {
    return tracker;
  }

  @XmlElement
  public void setTracker(String tracker) {
    this.tracker = tracker;
  }

  @Override
  public PreApprovalStatus getStatus() {
    return new PreApprovalStatus(statusId);
  }

  public String getStatusId() {
    return statusId;
  }

  @XmlElement(name = "status")
  public void setStatusId(String statusId) {
    this.statusId = statusId;
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
  public Date getLastEvent() {
    return lastEvent;
  }

  @XmlElement(name = "lastEventDate")
  public void setLastEvent(Date lastEvent) {
    this.lastEvent = lastEvent;
  }

  @Override
  public String getCharge() {
    return charge;
  }

  @XmlElement
  public void setCharge(String charge) {
    this.charge = charge;
  }

  @Override
  public SenderXML getSender() {
    return sender;
  }

  @XmlElement
  public void setSender(SenderXML sender) {
    this.sender = sender;
  }

  @Override
  public String toString() {
    return "PreApprovalDetailXML{" +
        "name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", date=" + date +
        ", tracker='" + tracker + '\'' +
        ", statusId='" + statusId + '\'' +
        ", reference='" + reference + '\'' +
        ", lastEvent=" + lastEvent +
        ", charge='" + charge + '\'' +
        ", sender=" + sender +
        '}';
  }
}
