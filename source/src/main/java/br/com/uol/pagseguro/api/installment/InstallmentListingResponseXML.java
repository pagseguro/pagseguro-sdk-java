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
package br.com.uol.pagseguro.api.installment;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uol.pagseguro.api.common.domain.DataList;

/**
 * Implementation of {@code DataList<InstallmentDetailXML>}
 *
 * @author PagSeguro Internet Ltda.
 */
@XmlRootElement(name = "installments")
public class InstallmentListingResponseXML implements DataList<InstallmentDetailXML> {

  @XmlElement(name = "installment")
  private List<InstallmentDetailXML> installments;

  InstallmentListingResponseXML() {
  }

  @Override
  public List<InstallmentDetailXML> getData() {
    return installments != null ? installments : Collections.<InstallmentDetailXML>emptyList();
  }

  @Override
  public Integer getTotalPages() {
    return 1;
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
  public Integer getCurrentPage() {
    return null;
  }

  @Override
  public Date getDate() {
    return null;
  }

  @Override
  public Iterator<InstallmentDetailXML> iterator() {
    return getData().iterator();
  }

  @Override
  public String toString() {
    return "InstallmentListingResponseXML{" +
        "installments=" + installments +
        '}';
  }
}
