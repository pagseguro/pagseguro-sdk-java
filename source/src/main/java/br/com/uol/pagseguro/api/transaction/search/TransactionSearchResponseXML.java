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

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.DataList;

/**
 * Implementation of {@code TransactionSummaryXML}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "transactionSearchResult")
public class TransactionSearchResponseXML implements DataList<TransactionSummaryXML> {

  private List<TransactionSummaryXML> transactions;

  private int totalPages;

  private int resultsInThisPage;

  TransactionSearchResponseXML() {
  }

  @XmlElement(name = "transaction")
  @XmlElementWrapper(name = "transactions")
  public void setTransactions(List<TransactionSummaryXML> transactions) {
    this.transactions = transactions;
  }

  public List<TransactionSummaryXML> getTransactions() {
    return transactions;
  }

  @XmlElement
  public void setResultsInThisPage(int resultsInThisPage) {
    this.resultsInThisPage = resultsInThisPage;
  }

  public int getResultsInThisPage() {
    return resultsInThisPage;
  }

  @XmlElement
  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  @Override
  public List<TransactionSummaryXML> getData() {
    return transactions != null ? transactions : Collections.<TransactionSummaryXML>emptyList();
  }

  @Override
  public Iterator<TransactionSummaryXML> iterator() {
    return getData().iterator();
  }

  @Override
  public Integer size() {
    return getResultsInThisPage();
  }

  @Override
  public Boolean isEmpty() {
    return getData().isEmpty();
  }

  @Override
  public Integer getCurrentPage() {
    return null;
  }

  @Override
  public Date getDate() {
    return null;
  }

  @Override
  public String toString() {
    return "TransactionSearchResponseXML{" +
        "transactions=" + transactions +
        ", totalPages=" + totalPages +
        ", resultsInThisPage=" + resultsInThisPage +
        '}';
  }
}
