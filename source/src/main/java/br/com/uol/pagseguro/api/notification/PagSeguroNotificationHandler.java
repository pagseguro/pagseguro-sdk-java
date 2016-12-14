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

package br.com.uol.pagseguro.api.notification;

import br.com.uol.pagseguro.api.application.authorization.search.AuthorizationDetail;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;

/**
 * Interface for notification handler.
 * Used to handle notifications
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PagSeguroNotificationHandler {

  /**
   * Handle Transaction Notification
   *
   * @param transaction Transaction
   */
  void handleTransactionNotification(TransactionDetail transaction);

  /**
   * Handle Authorization Notification
   *
   * @param authorization Authorization
   */
  void handleAuthorizationNotification(AuthorizationDetail authorization);

  /**
   * Handle Pre Approval Notification
   *
   * @param transaction Pre Approval
   */
  void handlePreApprovalNotification(PreApprovalDetail transaction);
}
