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

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.DataList;

/**
 * Implementation of {@code PreApprovalSummaryXML}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "preApprovalSearchResult")
public class PreApprovalSearchResponseXML implements DataList<PreApprovalSummaryXML> {

  private List<PreApprovalSummaryXML> preApprovals;

  private Integer totalPages;

  private Integer resultsInThisPage;

  private Date date;

  PreApprovalSearchResponseXML() {
  }

  public List<PreApprovalSummaryXML> getPreApprovals() {
    return preApprovals;
  }

  @XmlElement(name = "preApproval")
  @XmlElementWrapper(name = "preApprovals")
  public void setPreApprovals(List<PreApprovalSummaryXML> preApprovals) {
    this.preApprovals = preApprovals;
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

  public Date getDate() {
    return date;
  }

  @XmlElement
  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public List<PreApprovalSummaryXML> getData() {
    return preApprovals != null ? preApprovals : Collections.<PreApprovalSummaryXML>emptyList();
  }

  @Override
  public Integer size() {
    return getData().size();
  }

  @Override
  public Boolean isEmpty() {
    return getData().isEmpty();
  }

  @Override
  public Iterator<PreApprovalSummaryXML> iterator() {
    return getData().iterator();
  }

  @Override
  public String toString() {
    return "PreApprovalSearchResponseXML{" +
        "preApprovals=" + preApprovals +
        ", totalPages=" + totalPages +
        ", resultsInThisPage=" + resultsInThisPage +
        ", date=" + date +
        '}';
  }
}
