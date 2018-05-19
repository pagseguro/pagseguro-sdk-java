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

import java.math.BigDecimal;

/**
 * Interface for pre approval.
 *
 * The PagSeguro signatures model allows your company to offer its customers services with recurring
 * payments easily and securely. Be a magazine subscription, monthly fee of a course, unique content
 * or any model that requires a recurring charge, the PagSeguro signatures model provides a complete
 * way to create not face charges previously authorized by the buyer, thus maximizing loyalty.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PreApproval {

  /**
   * Get charge of pre approval
   *
   * @return Charge
   */
  String getCharge();

  /**
   * Get name of pre approval
   *
   * @return Name
   */
  String getName();

  /**
   * Get details of pre approval
   *
   * @return Details
   */
  String getDetails();

  /**
   * Get amount per payment of pre approval
   *
   * @return Amount
   */
  BigDecimal getAmountPerPayment();

  /**
   * Get max amount per payment of pre approval
   *
   * @return Max amount per payment
   */
  BigDecimal getMaxAmountPerPayment();

  /**
   * Get max total amount of pre approval
   *
   * @return Max total amount
   */
  BigDecimal getMaxTotalAmount();

  /**
   * Get max amount per period of pre approval
   *
   * @return Max amount per period
   */
  BigDecimal getMaxAmountPerPeriod();

  /**
   * Get max payments per period of pre approval
   *
   * @return Max payments per period
   */
  Integer getMaxPaymentsPerPeriod();

  /**
   * Get period of pre approval
   *
   * @return Period
   */
  String getPeriod();

  /**
   * Get date range of pre approval
   *
   * @return Date range
   * @see DateRange
   */
  DateRange getDateRange();

  /**
   * Get membership fee of pre approval
   * @return  MembershipFee
   */
  BigDecimal getMembershipFee();

  /**
   * Get trial period duration of pre approval
   * @return TrialPeriodDuration
   */
  Integer getTrialPeriodDuration();

  /**
   * Get Expiration of pre approval
   * @return Expiration
   * @see Expiration
   */
  Expiration getExpiration();

  /**
   * Get the charge day of the year of pre approval
   * @return DayOfYear
   */
  String getDayOfYear();

  /**
   * Get the charge day of the month of pre approval
   * @return DayOfMonth
   */
  Integer getDayOfMonth();

  /**
   * Get the charge day of the week of pre approval
   * @return DayOfWeek
   */
  String getDayOfWeek();

  /**
   * Get the cancel URL of pre approval
   * @return CancelURL
   */
  String getCancelURl();
}
