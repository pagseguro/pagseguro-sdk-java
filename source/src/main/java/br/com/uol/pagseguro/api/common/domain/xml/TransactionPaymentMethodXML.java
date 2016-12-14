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
package br.com.uol.pagseguro.api.common.domain.xml;

import javax.xml.bind.annotation.XmlElement;

import br.com.uol.pagseguro.api.common.domain.TransactionPaymentMethod;

/**
 * Implementation of {@code TransactionPaymentMethod}
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionPaymentMethodXML implements TransactionPaymentMethod {

  private Integer typeId;

  private Integer codeId;

  TransactionPaymentMethodXML() {
  }

  @Override
  public Integer getCodeId() {
    return codeId;
  }

  @XmlElement(name = "code")
  public void setCodeId(Integer codeId) {
    this.codeId = codeId;
  }

  @XmlElement(name = "type")
  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  @Override
  public Integer getTypeId() {
    return typeId;
  }

  @Override
  public Code getCode() {
    return codeId == null ? TransactionPaymentMethod.Code.UNKNOW : Code.fromCodeId(getCodeId());
  }

  @Override
  public Type getType() {
    return Type.fromTypeId(typeId);
  }

  @Override
  public String toString() {
    return "TransactionPaymentMethodXML{" +
        "typeId=" + typeId +
        ", codeId=" + codeId +
        '}';
  }
}
