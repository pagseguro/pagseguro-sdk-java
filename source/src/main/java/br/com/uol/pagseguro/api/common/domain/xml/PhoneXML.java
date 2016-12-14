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

import br.com.uol.pagseguro.api.common.domain.Phone;

/**
 * Implementation of {@code Phone}
 *
 * @author PagSeguro Internet Ltda.
 */
public class PhoneXML implements Phone {

  private String areaCode;

  private String phoneNumber;

  PhoneXML() {
  }

  @Override
  public String getNumber() {
    return phoneNumber;
  }

  @XmlElement(name = "number")
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String getAreaCode() {
    return areaCode;
  }

  @XmlElement
  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  @Override
  public String toString() {
    return "PhoneXML{" +
        "areaCode='" + areaCode + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
