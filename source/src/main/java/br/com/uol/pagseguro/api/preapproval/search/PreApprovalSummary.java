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

import br.com.uol.pagseguro.api.common.domain.PreApprovalStatus;

/**
 * Interface of the response of pre approval search.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PreApprovalSummary {

  /**
   * Name / signature description.
   *
   * @return Name / signature description.
   */
  String getName();

  /**
   * Request code created. This code should be used to direct the buyer to the approval flow.
   *
   * @return Request code
   */
  String getCode();

  /**
   * Date of the request.
   *
   * @return Date
   */
  Date getDate();

  /**
   * Public identifier code. Used to facilitate differentiation of multiple subscriptions to the
   * same name / description.
   *
   * @return Public identifier code.
   */
  String getTracker();

  /**
   * Status
   *
   * @return Status
   * @see PreApprovalStatus
   */
  PreApprovalStatus getStatus();

  /**
   * Identifier that was used to refer to signature at the time of request / charge.
   *
   * @return Reference
   */
  String getReference();

  /**
   * Date / time on which the last change in the status of the signature.
   *
   * @return The last change in the status of the signature.
   */
  Date getLastEvent();

  /**
   * Indicates whether the subscription is managed by the seller (manual) or PagSeguro (self)
   *
   * @return Charge
   */
  String getCharge();

  /**
   * Get Detail
   *
   * @return Pre Approval Detail
   */
  PreApprovalDetail getDetail();
}
