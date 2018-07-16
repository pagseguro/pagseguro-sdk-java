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

package br.com.uol.pagseguro.api.preapproval;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PreApprovalRequest;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for pre approval registration.
 * This class is responsible for building the attributes for register pre approval
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PreApprovalRegistrationBuilder implements Builder<PreApprovalRegistration> {

  private String redirectURL = null;
  private String notificationURL = null;
  private Currency currency = Currency.BRL;
  private BigDecimal extraAmount = null;
  private String reference = null;
  private Shipping shipping = null;
  private Sender sender = null;
  private PreApprovalRequest preApprovalRequest = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set redirect url of pre approval registration
   *
   * @param redirectURL Redirect Url
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getRedirectURL()
   */
  public PreApprovalRegistrationBuilder withRedirectURL(String redirectURL) {
    this.redirectURL = redirectURL;
    return this;
  }

  /**
   * Set Notification URL of pre approval registration
   *
   * @param notificationURL Notification Url
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getNotificationURL()
   */
  public PreApprovalRegistrationBuilder withNotificationURL(String notificationURL) {
    this.notificationURL = notificationURL;
    return this;
  }

  /**
   * Set Currency of pre approval registration
   *
   * @param currency Currency
   * @return Builder for pre approval registration
   * @see Currency
   * @see PreApprovalRegistration#getCurrency()
   */
  public PreApprovalRegistrationBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Set Extra Amount of pre approval registration
   *
   * @param extraAmount Extra Amount
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getExtraAmount()
   */
  public PreApprovalRegistrationBuilder withExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
    return this;
  }

  /**
   * Set Reference of pre approval registration
   *
   * @param reference Reference
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getReference()
   */
  public PreApprovalRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set Shipping of pre approval registration
   *
   * @param shipping Shipping
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getShipping()
   */
  public PreApprovalRegistrationBuilder withShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  /**
   * Set Shipping of pre approval registration
   *
   * @param shippingBuilder Builder for Shipping
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getShipping()
   */
  public PreApprovalRegistrationBuilder withShipping(Builder<Shipping> shippingBuilder) {
    return withShipping(shippingBuilder.build());
  }

  /**
   * Set Sender of pre approval registration
   *
   * @param sender Sender
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getSender()
   */
  public PreApprovalRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set Sender of pre approval registration
   *
   * @param senderBuilder Builder for Sender
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getSender()
   */
  public PreApprovalRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Pre Approval of pre approval registration
   *
   * @param preApprovalRequest Pre Approval
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getPreApproval()
   */
  public PreApprovalRegistrationBuilder withPreApproval(PreApprovalRequest preApprovalRequest) {
    this.preApprovalRequest = preApprovalRequest;
    return this;
  }

  /**
   * Set Pre Approval of pre approval registration
   *
   * @param preApprovalBuilder Builder for Pre Approval
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getPreApproval()
   */
  public PreApprovalRegistrationBuilder withPreApproval(Builder<PreApprovalRequest> preApprovalBuilder) {
    return withPreApproval(preApprovalBuilder.build());
  }

  /**
   * Add Parameter to pre approval registration
   *
   * @param parameter Parameter
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getParameters()
   */
  public PreApprovalRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to pre approval registration
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getParameters()
   */
  public PreApprovalRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameters to pre approval registration
   *
   * @param parameters Parameters
   * @return Builder for pre approval registration
   * @see PreApprovalRegistration#getParameters()
   */
  public PreApprovalRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the Pre Approval Registration
   *
   * @return Interface for Pre Approval Registration
   * @see PreApprovalRegistration
   */
  @Override
  public PreApprovalRegistration build() {
    return new SimplePreApprovalRegistration(this);
  }

  /**
   * Implementation of {@code PreApprovalRegistration}
   */
  private static class SimplePreApprovalRegistration implements PreApprovalRegistration {

    private final PreApprovalRegistrationBuilder preApprovalRegistrationBuilder;

    public SimplePreApprovalRegistration(PreApprovalRegistrationBuilder preApprovalRegistrationBuilder) {
      this.preApprovalRegistrationBuilder = preApprovalRegistrationBuilder;
    }

    @Override
    public String getRedirectURL() {
      return preApprovalRegistrationBuilder.redirectURL;
    }

    @Override
    public String getNotificationURL() {
      return preApprovalRegistrationBuilder.notificationURL;
    }

    @Override
    public Currency getCurrency() {
      return preApprovalRegistrationBuilder.currency;
    }

    @Override
    public BigDecimal getExtraAmount() {
      return preApprovalRegistrationBuilder.extraAmount;
    }

    @Override
    public String getReference() {
      return preApprovalRegistrationBuilder.reference;
    }

    @Override
    public Shipping getShipping() {
      return preApprovalRegistrationBuilder.shipping;
    }

    @Override
    public Sender getSender() {
      return preApprovalRegistrationBuilder.sender;
    }

    @Override
    public PreApprovalRequest getPreApproval() {
      return preApprovalRegistrationBuilder.preApprovalRequest;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return preApprovalRegistrationBuilder.parameters;
    }
  }
}
