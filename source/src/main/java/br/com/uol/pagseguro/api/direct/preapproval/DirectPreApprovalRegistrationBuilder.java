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

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PreApproval;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.Receiver;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.direct.preapproval.DirectPreApprovalRegistration;
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
public final class DirectPreApprovalRegistrationBuilder implements Builder<DirectPreApprovalRegistration> {

  private String redirectURL = null;
  private String notificationURL = null;
  private Currency currency = Currency.BRL;
  private BigDecimal extraAmount = null;
  private String reference = null;
  private Shipping shipping = null;
  private Sender sender = null;
  private PreApproval preApproval = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();
  private Receiver receiver = null;
  private String reviewURL = null;
  private Integer maxUses = null;

  /**
   * Set redirect url of pre approval registration
   *
   * @param redirectURL Redirect Url
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getRedirectURL()
   */
  public DirectPreApprovalRegistrationBuilder withRedirectURL(String redirectURL) {
    this.redirectURL = redirectURL;
    return this;
  }

  /**
   * Set Notification URL of pre approval registration
   *
   * @param notificationURL Notification Url
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getNotificationURL()
   */
  public DirectPreApprovalRegistrationBuilder withNotificationURL(String notificationURL) {
    this.notificationURL = notificationURL;
    return this;
  }

  /**
   * Set Currency of pre approval registration
   *
   * @param currency Currency
   * @return Builder for pre approval registration
   * @see Currency
   * @see DirectPreApprovalRegistration#getCurrency()
   */
  public DirectPreApprovalRegistrationBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Set Extra Amount of pre approval registration
   *
   * @param extraAmount Extra Amount
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getExtraAmount()
   */
  public DirectPreApprovalRegistrationBuilder withExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
    return this;
  }

  /**
   * Set Reference of pre approval registration
   *
   * @param reference Reference
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getReference()
   */
  public DirectPreApprovalRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set Shipping of pre approval registration
   *
   * @param shipping Shipping
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getShipping()
   */
  public DirectPreApprovalRegistrationBuilder withShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  /**
   * Set Shipping of pre approval registration
   *
   * @param shippingBuilder Builder for Shipping
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getShipping()
   */
  public DirectPreApprovalRegistrationBuilder withShipping(Builder<Shipping> shippingBuilder) {
    return withShipping(shippingBuilder.build());
  }

  /**
   * Set Sender of pre approval registration
   *
   * @param sender Sender
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getSender()
   */
  public DirectPreApprovalRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set Sender of pre approval registration
   *
   * @param senderBuilder Builder for Sender
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getSender()
   */
  public DirectPreApprovalRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Pre Approval of pre approval registration
   *
   * @param preApproval Pre Approval
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getPreApproval()
   */
  public DirectPreApprovalRegistrationBuilder withPreApproval(PreApproval preApproval) {
    this.preApproval = preApproval;
    return this;
  }

  /**
   * Set Pre Approval of pre approval registration
   *
   * @param preApprovalBuilder Builder for Pre Approval
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getPreApproval()
   */
  public DirectPreApprovalRegistrationBuilder withPreApproval(Builder<PreApproval> preApprovalBuilder) {
    return withPreApproval(preApprovalBuilder.build());
  }



  /**
   * Add Parameter to pre approval registration
   *
   * @param parameter Parameter
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getParameters()
   */
  public DirectPreApprovalRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to pre approval registration
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getParameters()
   */
  public DirectPreApprovalRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameters to pre approval registration
   *
   * @param parameters Parameters
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getParameters()
   */
  public DirectPreApprovalRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
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
   * @see DirectPreApprovalRegistration#getReceiver()
   */
  public DirectPreApprovalRegistrationBuilder withReceiver(Receiver receiver) {
    this.receiver = receiver;
    return this;
  }

  /**
   * Set receiver of pre approval registration
   *
   * @param receiverBuilder Builder for Receiver
   * @return DirectPaymentRegistrationBuilder
   * @see DirectPreApprovalRegistration#getReceiver()
   */
  public DirectPreApprovalRegistrationBuilder withReceiver(Builder<Receiver> receiverBuilder) {
    return withReceiver(receiverBuilder.build());
  }


  /**
   * Set Review URL of pre approval registration
   *
   * @param reviewURL Review Url
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getReviewURL()
   */
  public DirectPreApprovalRegistrationBuilder withReviewURL(String reviewURL) {
    this.reviewURL = reviewURL;
    return this;
  }


  /**
   * Set Max Uses the pre approval (the number of accessions that can be made)
   *
   * @param maxUses Max Uses
   * @return Builder for pre approval registration
   * @see DirectPreApprovalRegistration#getMaxUses()
   */
  public DirectPreApprovalRegistrationBuilder withMaxUses(Integer maxUses) {
    this.maxUses = maxUses;
    return this;
  }

  /**
   * Build the Pre Approval Registration
   *
   * @return Interface for Pre Approval Registration
   * @see DirectPreApprovalRegistration
   */
  @Override
  public DirectPreApprovalRegistration build() {
    return new SimplePreApprovalRegistration(this);
  }

  /**
   * Implementation of {@code PreApprovalRegistration}
   */
  private static class SimplePreApprovalRegistration implements DirectPreApprovalRegistration {

    private final DirectPreApprovalRegistrationBuilder directPreApprovalRegistrationBuilder;

    public SimplePreApprovalRegistration(DirectPreApprovalRegistrationBuilder directPreApprovalRegistrationBuilder) {
      this.directPreApprovalRegistrationBuilder = directPreApprovalRegistrationBuilder;
    }

    @Override
    public String getRedirectURL() {
      return directPreApprovalRegistrationBuilder.redirectURL;
    }

    @Override
    public String getNotificationURL() {
      return directPreApprovalRegistrationBuilder.notificationURL;
    }

    @Override
    public Currency getCurrency() {
      return directPreApprovalRegistrationBuilder.currency;
    }

    @Override
    public BigDecimal getExtraAmount() {
      return directPreApprovalRegistrationBuilder.extraAmount;
    }

    @Override
    public String getReference() {
      return directPreApprovalRegistrationBuilder.reference;
    }

    @Override
    public Shipping getShipping() {
      return directPreApprovalRegistrationBuilder.shipping;
    }

    @Override
    public Sender getSender() {
      return directPreApprovalRegistrationBuilder.sender;
    }

    @Override
    public PreApproval getPreApproval() {
      return directPreApprovalRegistrationBuilder.preApproval;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return directPreApprovalRegistrationBuilder.parameters;
    }

    @Override
    public Receiver getReceiver() {return directPreApprovalRegistrationBuilder.receiver; }

    @Override
    public String getReviewURL() {
      return directPreApprovalRegistrationBuilder.reviewURL;
    }

    @Override
    public Integer getMaxUses() {
      return directPreApprovalRegistrationBuilder.maxUses;
    }
  }
}
