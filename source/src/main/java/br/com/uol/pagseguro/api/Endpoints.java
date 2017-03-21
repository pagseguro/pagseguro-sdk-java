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
package br.com.uol.pagseguro.api;

/**
 * Class responsible for all endpoints of api.
 *
 * @author PagSeguro Internet Ltda.
 */
public final class Endpoints {

  Endpoints() {
    new IllegalStateException();
  }

  /**
   * AUTHORIZATIONS
   */

  /**
   * Authorization registration
   */
  public static final String AUTHORIZATION_REQUEST = "%s/v2/authorizations/request";

  /**
   * Authorization redirect url after registration
   */
  public static final String AUTHORIZATION_REDIRECT_URL = "%s/v2/authorization/request.jhtml?code=%s";

  /**
   * Search authorization by code
   */
  public static final String AUTHORIZATION_SEARCH_BY_CODE = "%s/v2/authorizations/%s";

  /**
   * Search authorization by notification code
   */
  public static final String AUTHORIZATION_SEARCH_BY_NOTIFICATION_CODE = "%s/v2/authorizations/notifications/%s";

  /**
   * Search authorizations
   */
  public static final String AUTHORIZATION_SEARCH = "%s/v2/authorizations?%s";

  /**
   * CHECKOUT
   */

  /**
   * Checkout registration
   */
  public static final String CHECKOUT_REQUEST = "%s/v2/checkout";

  /**
   * Checkout redirect url after registration
   */
  public static final String CHECKOUT_REDIRECT_URL = "%s/v2/checkout/payment.html?code=%s";

  /**
   * TRANSACTIONS
   */

  /**
   * Transaction cancellation
   */
  public static final String TRANSACTION_CANCEL = "%s/v2/transactions/cancels";

  /**
   * Transaction refunds
   */
  public static final String TRANSACTION_REFUND = "%s/v2/transactions/refunds";

  /**
   * Search abandoned transactions
   */
  public static final String TRANSACTION_ABANDONED = "%s/v2/transactions/abandoned?%s";

  /**
   * Search transactions
   */
  public static final String TRANSACTION_SEARCH = "%s/v3/transactions?%s";

  /**
   * Search transaction by code
   */
  public static final String TRANSACTION_SEARCH_BY_CODE = "%s/v3/transactions/%s";

  /**
   * Search transaction by notification code
   */
  public static final String TRANSACTION_SEARCH_BY_NOTIFICATION_CODE = "%s/v2/transactions/notifications/%s";

  /**
   * PRE APPROVAL
   */

  /**
   * Pre approval registration
   */
  public static final String PRE_APPROVAL_REQUEST = "%s/v2/pre-approvals/request";

  /**
   * Pre approval redirect url after registration
   */
  public static final String PRE_APPROVAL_REDIRECT_URL = "%s/v2/pre-approvals/request.html?code=%s";

  /**
   * Pre approval cancellation
   */
  public static final String PRE_APPROVAL_CANCEL = "%s/v2/pre-approvals/cancel/%s?%s";

  /**
   * Search pre approval by notification code
   */
  public static final String PRE_APPROVAL_SEARCH_BY_NOTIFICATION = "%s/v2/pre-approvals/notifications/%s";

  /**
   * Search pre approval by code
   */
  public static final String PRE_APPROVAL_SEARCH_BY_CODE = "%s/v2/pre-approvals/%s";

  /**
   * Search pre approvals by interval
   */
  public static final String PRE_APPROVAL_SEARCH_BY_INTERVAL = "%s/v2/pre-approvals/notifications?%s";

  /**
   * Search pre approvals
   */
  public static final String PRE_APPROVAL_SEARCH = "%s/v2/pre-approvals?%s";

  /**
   * Pre approval charging
   */
  public static final String PRE_APPROVAL_CHARGE = "%s/v2/pre-approvals/payment";

  /**
   * SESSIONS
   */

  /**
   * Session creation for seller
   */
  public static final String SESSION_CREATE = "%s/v2/sessions";

  /**
   * Session creation for application
   */
  public static final String SESSION_CREATE_APPLICATION = "%s/v2/sessions?%s";

  /**
   * Session creation for split application
   */
  public static final String SESSION_SPLIT_CREATE_APPLICATION = "%s/sessions";

  /**
   * Session creation for split seller
   */
  public static final String SESSION_SPLIT_CREATE= "%s/sessions";

  /**
   * DIRECT PAYMENT
   */

  /**
   * Direct payment registration
   */
  public static final String DIRECT_PAYMENT = "%s/v2/transactions";

  /**
   * INSTALLMENTS
   */

  /**
   * List installments
   */
  public static final String INSTALLMENT_SEARCH = "%s/v2/installments?%s";

  /**
   * SPLIT PAYMENT
   */

  /**
   * Split payment registration
   */
  public static final String SPLIT_PAYMENT = "%s/transactions/";

}
