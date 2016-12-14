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

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import br.com.uol.pagseguro.api.common.domain.Permission;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;

/**
 * Class used to parsed response of permission
 *
 * @author PagSeguro Internet Ltda.
 */
public class PermissionXML implements Permission {

  private String codeId;
  private String status;
  private Date lastUpdate;

  /**
   * Get code of permission
   *
   * @return Code
   */
  public String getCodeId() {
    return codeId;
  }

  /**
   * Set code of permission
   *
   * @param code Code
   */
  @XmlElement(name = "code")
  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }

  @Override
  public PermissionCode getCode() {
    return new PermissionCode(getCodeId());
  }

  /**
   * Get status of permission
   *
   * @return Status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Set status of permission
   *
   * @param status Status
   */
  @XmlElement
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Get last update of permission
   *
   * @return Last update permission
   */
  public Date getLastUpdate() {
    return lastUpdate;
  }

  /**
   * Get last update of permission
   *
   * @param lastUpdate Last update
   */
  @XmlElement
  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  @Override
  public String toString() {
    return "PermissionXML{" +
           "codeId='" + codeId + '\'' +
           ", status='" + status + '\'' +
           ", lastUpdate=" + lastUpdate +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PermissionXML)) return false;

    PermissionXML that = (PermissionXML) o;

    if (codeId != null ? !codeId.equals(that.codeId) : that.codeId != null) return false;
    if (status != null ? !status.equals(that.status) : that.status != null) return false;
    return lastUpdate != null ? lastUpdate.equals(that.lastUpdate) : that.lastUpdate == null;

  }

}
