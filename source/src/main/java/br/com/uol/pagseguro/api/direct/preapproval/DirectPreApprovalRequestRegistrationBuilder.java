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
import br.com.uol.pagseguro.api.common.domain.PreApprovalRequest;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.Builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder for pre approval registration.
 * This class is responsible for building the attributes for register pre approval
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalRequestRegistrationBuilder implements Builder<DirectPreApprovalRequestRegistration> {

  private String redirectURL = null;
  private String notificationURL = null;
  private Currency currency = Currency.BRL;
  private BigDecimal extraAmount = null;
  private String reference = null;
  private Shipping shipping = null;
  private Sender sender = null;
  private PreApprovalRequest preApprovalRequest = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();
  private Receiver receiver = null;
  private String reviewURL = null;
  private Integer maxUses = null;

  /**
   * Set redirect url of pre approval registration
   *
   * @param redirectURL Redirect Url
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getRedirectURL()
   */
  public DirectPreApprovalRequestRegistrationBuilder withRedirectURL(String redirectURL) {
    this.redirectURL = redirectURL;
    return this;
  }

  /**
   * Set Notification URL of pre approval registration
   *
   * @param notificationURL Notification Url
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getNotificationURL()
   */
  public DirectPreApprovalRequestRegistrationBuilder withNotificationURL(String notificationURL) {
    this.notificationURL = notificationURL;
    return this;
  }

  /**
   * Set Currency of pre approval registration
   *
   * @param currency Currency
   * @return Builder for pre approval registration
   * @see Currency
   * @see DirectPreApprovalRequestRegistration#getCurrency()
   */
  public DirectPreApprovalRequestRegistrationBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Set Extra Amount of pre approval registration
   *
   * @param extraAmount Extra Amount
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getExtraAmount()
   */
  public DirectPreApprovalRequestRegistrationBuilder withExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
    return this;
  }

  /**
   * Set Reference of pre approval registration
   *
   * @param reference Reference
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getReference()
   */
  public DirectPreApprovalRequestRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set Shipping of pre approval registration
   *
   * @param shipping Shipping
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getShipping()
   */
  public DirectPreApprovalRequestRegistrationBuilder withShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  /**
   * Set Shipping of pre approval registration
   *
   * @param shippingBuilder Builder for Shipping
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getShipping()
   */
  public DirectPreApprovalRequestRegistrationBuilder withShipping(Builder<Shipping> shippingBuilder) {
    return withShipping(shippingBuilder.build());
  }

  /**
   * Set Sender of pre approval registration
   *
   * @param sender Sender
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getSender()
   */
  public DirectPreApprovalRequestRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set Sender of pre approval registration
   *
   * @param senderBuilder Builder for Sender
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getSender()
   */
  public DirectPreApprovalRequestRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Pre Approval of pre approval registration
   *
   * @param preApprovalRequest Pre Approval
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getPreApproval()
   */
  public DirectPreApprovalRequestRegistrationBuilder withPreApproval(PreApprovalRequest preApprovalRequest) {
    this.preApprovalRequest = preApprovalRequest;
    return this;
  }

  /**
   * Set Pre Approval of pre approval registration
   *
   * @param preApprovalBuilder Builder for Pre Approval
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getPreApproval()
   */
  public DirectPreApprovalRequestRegistrationBuilder withPreApproval(Builder<PreApprovalRequest> preApprovalBuilder) {
    return withPreApproval(preApprovalBuilder.build());
  }



  /**
   * Add Parameter to pre approval registration
   *
   * @param parameter Parameter
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalRequestRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to pre approval registration
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalRequestRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameters to pre approval registration
   *
   * @param parameters Parameters
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalRequestRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Set receiver of pre approval registration
   *
   * @param receiver Receiver
   * @return DirectPaymentRegistrationBuilder
   * @see DirectPreApprovalRequestRegistration#getReceiver()
   */
  public DirectPreApprovalRequestRegistrationBuilder withReceiver(Receiver receiver) {
    this.receiver = receiver;
    return this;
  }

  /**
   * Set receiver of pre approval registration
   *
   * @param receiverBuilder Builder for Receiver
   * @return DirectPaymentRegistrationBuilder
   * @see DirectPreApprovalRequestRegistration#getReceiver()
   */
  public DirectPreApprovalRequestRegistrationBuilder withReceiver(Builder<Receiver> receiverBuilder) {
    return withReceiver(receiverBuilder.build());
  }


  /**
   * Set Review URL of pre approval registration
   *
   * @param reviewURL Review Url
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getReviewURL()
   */
  public DirectPreApprovalRequestRegistrationBuilder withReviewURL(String reviewURL) {
    this.reviewURL = reviewURL;
    return this;
  }


  /**
   * Set Max Uses the pre approval (the number of accessions that can be made)
   *
   * @param maxUses Max Uses
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRequestRegistration#getMaxUses()
   */
  public DirectPreApprovalRequestRegistrationBuilder withMaxUses(Integer maxUses) {
    this.maxUses = maxUses;
    return this;
  }

  /**
   * Build the Pre Approval Registration
   *
   * @return Interface for Pre Approval Registration
   * @see DirectPreApprovalRequestRegistration
   */
  @Override
  public DirectPreApprovalRequestRegistration build() {
    return new SimplePreApprovalRequestRegistration(this);
  }

  /**
   * Implementation of {@code PreApprovalRegistration}
   */
  private static class SimplePreApprovalRequestRegistration implements DirectPreApprovalRequestRegistration {

    private final DirectPreApprovalRequestRegistrationBuilder directPreApprovalRequestRegistrationBuilder;

    public SimplePreApprovalRequestRegistration(DirectPreApprovalRequestRegistrationBuilder directPreApprovalRequestRegistrationBuilder) {
      this.directPreApprovalRequestRegistrationBuilder = directPreApprovalRequestRegistrationBuilder;
    }

    @Override
    public String getRedirectURL() {
      return directPreApprovalRequestRegistrationBuilder.redirectURL;
    }

    @Override
    public String getNotificationURL() {
      return directPreApprovalRequestRegistrationBuilder.notificationURL;
    }

    @Override
    public Currency getCurrency() {
      return directPreApprovalRequestRegistrationBuilder.currency;
    }

    @Override
    public BigDecimal getExtraAmount() {
      return directPreApprovalRequestRegistrationBuilder.extraAmount;
    }

    @Override
    public String getReference() {
      return directPreApprovalRequestRegistrationBuilder.reference;
    }

    @Override
    public Shipping getShipping() {
      return directPreApprovalRequestRegistrationBuilder.shipping;
    }

    @Override
    public Sender getSender() {
      return directPreApprovalRequestRegistrationBuilder.sender;
    }

    @Override
    public PreApprovalRequest getPreApproval() {
      return directPreApprovalRequestRegistrationBuilder.preApprovalRequest;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return directPreApprovalRequestRegistrationBuilder.parameters;
    }

    @Override
    public Receiver getReceiver() {return directPreApprovalRequestRegistrationBuilder.receiver; }

    @Override
    public String getReviewURL() {
      return directPreApprovalRequestRegistrationBuilder.reviewURL;
    }

    @Override
    public Integer getMaxUses() {
      return directPreApprovalRequestRegistrationBuilder.maxUses;
    }
  }
}
