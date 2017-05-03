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
 * Class for transaction status
 *
 * @author PagSeguro Internet Ltda.
 */
public class TransactionStatus {

  private final Integer statusId;

  /**
   * Constructor
   *
   * @param statusId Status id
   */
  public TransactionStatus(Integer statusId) {
    this.statusId = statusId;
  }

  /**
   * Get status id
   *
   * @return Status id
   */
  public int getStatusId() {
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
     * WAITING PAYMENT
     */
    WAITING_PAYMENT(1),

    /**
     * IN REVIEW
     */
    IN_REVIEW(2),

    /**
     * APPROVED
     */
    APPROVED(3),

    /**
     * AVAILABLE
     */
    AVAILABLE(4),

    /**
     * IN DISPUTE
     */
    IN_DISPUTE(5),

    /**
     * RETURNED
     */
    RETURNED(6),

    /**
     * CANCELLED
     */
    CANCELLED(7),

    /**
     * UNRECOGNIZED
     */
    UNRECOGNIZED(null);

    private Integer statusId;

    /**
     * Constructor
     *
     * @param statusId Status id
     */
    Status(Integer statusId) {
      this.statusId = statusId;
    }

    /**
     * Get status by status id
     *
     * @param statusId Status id
     * @return Status
     */
    public static Status fromStatusId(Integer statusId) {
      for (Status status : Status.values()) {
        if (status.statusId != null && status.statusId == statusId) {
          return status;
        }
      }
      return UNRECOGNIZED;
    }

  }

}
