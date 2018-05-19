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

package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.*;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for pre approval registration
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalRegistration {

  /**
   * URL where the buyer is redirected after completion of the signature stream.
   *
   * @return Redirect Url
   */
  String getRedirectURL();

  /**
   * Url for the sending notifications
   *
   * @return Notification url
   */
  String getNotificationURL();

  /**
   * Informs an extra value that was added to or subtracted from the amount paid by the buyer. This
   * value is specified by you in the payment and may represent a value that you want to charge
   * separately buyer or a discount you want to give to it.
   *
   * @return Extra Value
   */
  BigDecimal getExtraAmount();

  /**
   * Code / identifier to reference the signature on your system.
   *
   * @return Reference
   */
  String getReference();

  /**
   * Currency used.
   *
   * @return Currency
   * @see Currency
   */
  Currency getCurrency();

  /**
   * Shipping Data
   *
   * @return Shipping
   * @see Shipping
   */
  Shipping getShipping();

  /**
   * Buyer Data
   *
   * @return Sender
   * @see Sender
   */
  Sender getSender();

  /**
   * Pre Approval
   *
   * @return Pre Approval
   * @see PreApproval
   */
  PreApproval getPreApproval();

  /**
   * Extra Parameters for another versions
   *
   * @return Parameters
   */
  List<? extends Parameter> getParameters();

  /**
   * Receiver data
   * @return Receiver
   * @see Receiver
   */
  Receiver getReceiver();

  /**
   * Review Url
   * @return String
   */
  String getReviewURL();

  /**
   * Max Uses
   * @return Integer
   */
  Integer getMaxUses();
}


