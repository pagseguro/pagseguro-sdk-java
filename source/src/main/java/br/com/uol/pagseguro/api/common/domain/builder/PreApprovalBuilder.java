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

package br.com.uol.pagseguro.api.common.domain.builder;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.Expiration;
import br.com.uol.pagseguro.api.common.domain.PreApproval;
import br.com.uol.pagseguro.api.common.domain.enums.Charge;
import br.com.uol.pagseguro.api.common.domain.enums.DayOfWeek;
import br.com.uol.pagseguro.api.common.domain.enums.Period;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Pre Approval
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PreApprovalBuilder implements Builder<PreApproval> {

  private String charge = null;
  private String name = null;
  private String details = null;
  private BigDecimal amountPerPayment = null;
  private BigDecimal maxAmountPerPayment = null;
  private BigDecimal maxTotalAmount = null;
  private BigDecimal maxAmountPerPeriod = null;
  private Integer maxPaymentsPerPeriod = null;
  private String period = null;
  private DateRange dateRange = new DateRangeBuilder().build();
  private BigDecimal membershipFee = null;
  private Integer trialPeriodDuration = null;
  private Expiration expiration = null;
  private String dayOfYear = null;
  private Integer dayOfMonth = null;
  private String dayOfWeek = null;
  private String cancelURL = null;

  /**
   * Set charge of pre approval
   *
   * @deprecated backward compatibility use {@link #withCharge(Charge)}
   * @param charge String
   * @return Builder for pre approval
   * @see PreApproval#getCharge()
   */
  public PreApprovalBuilder withCharge(String charge) {
    this.charge = charge;
    return this;
  }

  /**
   * Set charge of pre approval
   *
   * @param charge Charge
   * @return Builder for pre approval
   * @see PreApproval#getCharge()
   */
  public PreApprovalBuilder withCharge(Charge charge) {
    this.charge = charge.getValue();
    return this;
  }

  /**
   * Set name of pre approval
   *
   * @param name Name
   * @return Builder for Pre Approval
   * @see PreApproval#getName()
   */
  public PreApprovalBuilder withName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Set details of pre approval
   *
   * @param details Details
   * @return Builder for Pre Approval
   * @see PreApproval#getDetails()
   */
  public PreApprovalBuilder withDetails(String details) {
    this.details = details;
    return this;
  }

  /**
   * Set Amount per payment of pre approval
   *
   * @param amountPerPayment Amount per payment
   * @return Builder for Pre Approval
   * @see PreApproval#getAmountPerPayment()
   */
  public PreApprovalBuilder withAmountPerPayment(BigDecimal amountPerPayment) {
    this.amountPerPayment = amountPerPayment;
    return this;
  }

  /**
   * Set max amount per payment of pre approval
   *
   * @param maxAmountPerPayment Max amount per payment
   * @return Builder for Pre Approval
   * @see PreApproval#getMaxAmountPerPayment()
   */
  public PreApprovalBuilder withMaxAmountPerPayment(BigDecimal maxAmountPerPayment) {
    this.maxAmountPerPayment = maxAmountPerPayment;
    return this;
  }

  /**
   * Set max total amount of pre approval
   *
   * @param maxTotalAmount Max total amount
   * @return Builder for Pre Approval
   * @see PreApproval#getMaxTotalAmount()
   */
  public PreApprovalBuilder withMaxTotalAmount(BigDecimal maxTotalAmount) {
    this.maxTotalAmount = maxTotalAmount;
    return this;
  }

  /**
   * Set max amount per period of pre approval
   *
   * @param maxAmountPerPeriod Max amount per period
   * @return Builder for Pre Approval
   * @see PreApproval#getMaxAmountPerPeriod()
   */
  public PreApprovalBuilder withMaxAmountPerPeriod(BigDecimal maxAmountPerPeriod) {
    this.maxAmountPerPeriod = maxAmountPerPeriod;
    return this;
  }

  /**
   * Set max payments per period of pre approval
   *
   * @param maxPaymentsPerPeriod Max payment per period
   * @return Builder for Pre Approval
   * @see PreApproval#getMaxAmountPerPeriod()
   */
  public PreApprovalBuilder withMaxPaymentsPerPeriod(Integer maxPaymentsPerPeriod) {
    this.maxPaymentsPerPeriod = maxPaymentsPerPeriod;
    return this;
  }

  /**
   * Set period of pre approval
   *
   * @deprecated backward compatibility use {@link #withPeriod(Period)}
   * @param period String
   * @return Builder for Pre Approval
   * @see PreApproval#getPeriod()
   */
  public PreApprovalBuilder withPeriod(String period) {
    this.period = period;
    return this;
  }


  /**
   * Set period of pre approval
   *
   * @param period Period
   * @return Builder for Pre Approval
   * @see PreApproval#getPeriod()
   */
  public PreApprovalBuilder withPeriod(Period period) {
    this.period = period.getValue();
    return this;
  }

  /**
   * Set date range of pre approval
   *
   * @param dateRange Date range
   * @return Builder for Pre Approval
   * @see PreApproval#getDateRange()
   */
  public PreApprovalBuilder withDateRange(DateRange dateRange) {
    this.dateRange = dateRange;
    return this;
  }

  /**
   * Set date range of pre approval
   *
   * @param dateRangeBuilder Builder for Date range
   * @return Builder for Pre Approval
   * @see PreApproval#getDateRange()
   */
  public PreApprovalBuilder withDateRange(Builder<DateRange> dateRangeBuilder) {
    return withDateRange(dateRangeBuilder.build());
  }

  /**
   * Set Amount per payment of pre approval
   *
   * @param membershipFee Amount per payment
   * @return Builder for Pre Approval
   * @see PreApproval#getMembershipFee()
   */
  public PreApprovalBuilder withMembershipFee(BigDecimal membershipFee) {
    this.membershipFee = membershipFee;
    return this;
  }

  /**
   * Set trial period (in days) of pre approval plan
   *
   * @param trialPeriodDuration Amount per payment
   * @return Builder for Pre Approval
   * @see PreApproval#getTrialPeriodDuration()
   */
  public PreApprovalBuilder withTrialPeriodDuration(Integer trialPeriodDuration) {
    this.trialPeriodDuration = trialPeriodDuration;
    return this;
  }

  /**
   * Set expiration of pre approval plan registration
   *
   * @param expiration Expiration
   * @return Builder for pre approval registration
   * @see PreApproval#getExpiration()
   */
  public PreApprovalBuilder withExpiration(Expiration expiration) {
    this.expiration = expiration;
    return this;
  }

  /**
   * Set expiration of pre approval plan registration
   *
   * @param expirationBuilder Builder for Expiration
   * @return Builder for pre approval registration
   * @see PreApproval#getExpiration()
   */
  public PreApprovalBuilder withExpiration(Builder<Expiration> expirationBuilder) {
    return withExpiration(expirationBuilder.build());
  }

  /**
   * Set day of the year of pre approval when the charge will be charged
   *
   * @param dayOfYear DayOfYear
   * @return Builder for Pre Approval
   * @see PreApproval#getDayOfYear()
   */
  public PreApprovalBuilder withDayOfYear(String dayOfYear) {
    this.dayOfYear = dayOfYear;
    return this;
  }

  /**
   * Set day of the month of pre approval when the charge will be charged
   *
   * @param dayOfMonth DayOfMonth
   * @return Builder for Pre Approval
   * @see PreApproval#getDayOfMonth()
   */
  public PreApprovalBuilder withDayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
    return this;
  }

  /**
   * Set day of the week of pre approval when the charge will be charged
   *
   * @param dayOfWeek DayOfWeek
   * @return Builder for Pre Approval
   * @see PreApproval#getDayOfWeek()
   */
  public PreApprovalBuilder withDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek.getValue();
    return this;
  }

  /**
   * Set cancel URL of pre approval
   *
   * @param cancelURL CancelURL
   * @return Builder for Pre Approval
   * @see PreApproval#getCancelURl()
   */
  public PreApprovalBuilder withCancelURL(String cancelURL) {
    this.cancelURL = cancelURL;
    return this;
  }

  /**
   * Build the pre approval
   *
   * @return Interface for Pre Approval
   */
  @Override
  public PreApproval build() {
    return new SimplePreApproval(this);
  }

  /**
   * Implementation of {@code PreApproval}
   */
  private static class SimplePreApproval implements PreApproval {

    private final PreApprovalBuilder preApprovalBuilder;

    public SimplePreApproval(PreApprovalBuilder preApprovalBuilder) {
      this.preApprovalBuilder = preApprovalBuilder;
    }

    @Override
    public String getCharge() {
      return preApprovalBuilder.charge;
    }

    @Override
    public String getName() {
      return preApprovalBuilder.name;
    }

    @Override
    public String getDetails() {
      return preApprovalBuilder.details;
    }

    @Override
    public BigDecimal getAmountPerPayment() {
      return preApprovalBuilder.amountPerPayment;
    }

    @Override
    public BigDecimal getMaxAmountPerPayment() {
      return preApprovalBuilder.maxAmountPerPayment;
    }

    @Override
    public BigDecimal getMaxTotalAmount() {
      return preApprovalBuilder.maxTotalAmount;
    }

    @Override
    public BigDecimal getMaxAmountPerPeriod() {
      return preApprovalBuilder.maxAmountPerPeriod;
    }

    @Override
    public Integer getMaxPaymentsPerPeriod() {
      return preApprovalBuilder.maxPaymentsPerPeriod;
    }

    @Override
    public String getPeriod() {
      return preApprovalBuilder.period;
    }

    @Override
    public DateRange getDateRange() {
      return preApprovalBuilder.dateRange;
    }

    @Override
    public BigDecimal getMembershipFee() {
      return preApprovalBuilder.membershipFee;
    }

    @Override
    public Integer getTrialPeriodDuration() {
      return preApprovalBuilder.trialPeriodDuration;
    }

    @Override
    public Expiration getExpiration() {
      return preApprovalBuilder.expiration;
    }

    @Override
    public String getDayOfYear() {
      return preApprovalBuilder.dayOfYear;
    }

    @Override
    public Integer getDayOfMonth() {
      return preApprovalBuilder.dayOfMonth;
    }

    @Override
    public String getDayOfWeek() {
      return preApprovalBuilder.dayOfWeek;
    }

    @Override
    public String getCancelURl() {
      return preApprovalBuilder.cancelURL;
    }
  }
}
