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
package br.com.uol.pagseguro.api.application.authorization.search;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.DataList;

/**
 * Implementation of {@code DataList<AuthorizationSummaryXML>}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "authorizationSearchResult")
public class AuthorizationSearchResponseXML implements DataList<AuthorizationSummaryXML> {

  private Date date;
  private Integer resultsInThisPage;
  private Integer currentPage;
  private Integer totalPages;
  private List<AuthorizationSummaryXML> authorizations;

  public Date getDate() {
    return date;
  }

  @XmlElement
  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getResultsInThisPage() {
    return resultsInThisPage;
  }

  @XmlElement
  public void setResultsInThisPage(Integer resultsInThisPage) {
    this.resultsInThisPage = resultsInThisPage;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  @XmlElement
  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  @Override
  public Integer getTotalPages() {
    return totalPages;
  }

  @XmlElement
  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public List<AuthorizationSummaryXML> getAuthorizations() {
    return authorizations;
  }

  @XmlElementWrapper(name = "authorizations")
  @XmlElement(name = "authorization")
  public void setAuthorizations(List<AuthorizationSummaryXML> authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public List<AuthorizationSummaryXML> getData() {
    return getAuthorizations() != null ? getAuthorizations() : Collections.<AuthorizationSummaryXML>emptyList();
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
  public Iterator<AuthorizationSummaryXML> iterator() {
    return getData().iterator();
  }

  @Override
  public String toString() {
    return "AuthorizationSearchResponseXML{" +
        "date=" + date +
        ", resultsInThisPage=" + resultsInThisPage +
        ", currentPage=" + currentPage +
        ", totalPages=" + totalPages +
        ", authorizations=" + authorizations +
        '}';
  }
}
