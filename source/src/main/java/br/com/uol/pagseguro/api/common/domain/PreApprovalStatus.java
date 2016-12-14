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

package br.com.uol.pagseguro.api.common.domain;

/**
 * Class for pre approval status
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalStatus {

  private final String statusId;

  /**
   * Constructor
   *
   * @param statusId Status id
   */
  public PreApprovalStatus(String statusId) {
    this.statusId = statusId;
  }

  /**
   * Get status id
   *
   * @return Status id
   */
  public String getStatusId() {
    return statusId;
  }

  /**
   * Get status by status id
   *
   * @return Status
   * @see Status
   */
  public Status getStatus() {
    return Status.fromStatusId(statusId);
  }

  /**
   * Status enum
   */
  public enum Status {

    /**
     * INITIATED
     */
    INITIATED("INITIATED"),

    /**
     * PENDING
     */
    PENDING("PENDING"),

    /**
     * ACTIVE
     */
    ACTIVE("ACTIVE"),

    /**
     * CANCELLED
     */
    CANCELLED("CANCELLED"),

    /**
     * CANCELLED BY RECEIVER
     */
    CANCELLED_BY_RECEIVER("CANCELLED_BY_RECEIVER"),

    /**
     * CANCELLED BY SENDER
     */
    CANCELLED_BY_SENDER("CANCELLED_BY_SENDER"),

    /**
     * EXPIRED
     */
    EXPIRED("EXPIRED"),

    /**
     * UNRECOGNIZED
     */
    UNRECOGNIZED(null);

    private String statusId;

    /**
     * Constructor
     *
     * @param statusId Status id
     */
    Status(String statusId) {
      this.statusId = statusId;
    }

    /**
     * Get status by id
     *
     * @param statusId Status id
     * @return Status enum
     */
    public static Status fromStatusId(String statusId) {
      for (Status status : Status.values()) {
        if (status.statusId != null && status.statusId.equalsIgnoreCase(statusId)) {
          return status;
        }
      }
      return UNRECOGNIZED;
    }
  }
}
