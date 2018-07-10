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
package br.com.uol.pagseguro.api.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.AcceptedPaymentMethods;
import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.PaymentMethodConfig;
import br.com.uol.pagseguro.api.common.domain.PreApproval;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for checkout registration
 * This class is responsible for building the attributes for register checkout
 *
 * @author PagSeguro Internet Ltda.
 */
public final class CheckoutRegistrationBuilder implements Builder<CheckoutRegistration> {

  private Currency currency = Currency.BRL;

  private BigDecimal extraAmount = null;

  private String reference = null;

  private String notificationUrl = null;

  private Sender sender = null;

  private Shipping shipping = null;

  private List<PaymentItem> items = new LinkedList<PaymentItem>();

  private PreApproval preApproval = null;

  private List<Parameter> parameters = new LinkedList<Parameter>();

  private AcceptedPaymentMethods acceptedPaymentMethods = null;

  private List<PaymentMethodConfig> paymentMethodConfigs = new LinkedList<PaymentMethodConfig>();

  /**
   * Set reference of checkout
   *
   * @param reference Reference of checkout
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getReference()
   */
  public CheckoutRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set notificationUrl of checkout
   *
   * @param notificationUrl NotificationURL of checkout
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getNotificationUrl()
   */
  public CheckoutRegistrationBuilder withNotificationUrl(String notificationUrl) {
    this.notificationUrl = notificationUrl;
    return this;
  }

  /**
   * Set Extra Amount
   *
   * @param extraAmount Extra Amount of checkout
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getExtraAmount()
   */
  public CheckoutRegistrationBuilder withExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
    return this;
  }

  /**
   * Set currency
   * Indicates the currency in which payment will be made.
   *
   * @param currency Currency of checkout
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getCurrency()
   */
  public CheckoutRegistrationBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Set Shipping
   * Shipping data
   *
   * @param shipping Shipping of checkout
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getShipping()
   */
  public CheckoutRegistrationBuilder withShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  /**
   * Set Shipping
   * Shipping data
   *
   * @param shippingBuilder Builder for Shipping of checkout
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getShipping()
   */
  public CheckoutRegistrationBuilder withShipping(Builder<Shipping> shippingBuilder) {
    return withShipping(shippingBuilder.build());
  }

  /**
   * Set Sender
   * Sender Data
   *
   * @param sender Sender
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getSender()
   */
  public CheckoutRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set Sender
   * Sender Data
   * Optional
   *
   * @param senderBuilder Builder for Sender
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getSender()
   */
  public CheckoutRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Add Item to checkout
   *
   * @param item Payment item
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getItems()
   */
  public CheckoutRegistrationBuilder addItem(PaymentItem item) {
    items.add(item);
    return this;
  }

  /**
   * Add Item to checkout
   *
   * @param itemBuilder Builder for Payment item
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getItems()
   */
  public CheckoutRegistrationBuilder addItem(Builder<PaymentItem> itemBuilder) {
    return addItem(itemBuilder.build());
  }

  /**
   * Add Items to checkout
   *
   * @param items Payment items
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getItems()
   */
  public CheckoutRegistrationBuilder addItems(Iterable<? extends PaymentItem> items) {
    for (PaymentItem item : items) {
      addItem(item);
    }
    return this;
  }

  /**
   * Set Pre Approval
   *
   * @param preApproval Pre Approval
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getPreApproval()
   */
  public CheckoutRegistrationBuilder withPreApproval(PreApproval preApproval) {
    this.preApproval = preApproval;
    return this;
  }

  /**
   * Set Pre Approval
   *
   * @param preApproval Builder for Pre Approval
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getPreApproval()
   */
  public CheckoutRegistrationBuilder withPreApproval(Builder<PreApproval> preApproval) {
    return withPreApproval(preApproval.build());
  }

  /**
   * Add Parameter to checkout
   *
   * @param parameter Parameter
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getParameters()
   */
  public CheckoutRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to checkout
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getParameters()
   */
  public CheckoutRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameter to checkout
   *
   * @param parameters Parameters
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getParameters()
   */
  public CheckoutRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Set Accepted payment methods
   *
   * @param acceptedPaymentMethods Accepted Payment Methods
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getAcceptedPaymentMethods()
   */
  public CheckoutRegistrationBuilder withAcceptedPaymentMethods(
      AcceptedPaymentMethods acceptedPaymentMethods) {
    this.acceptedPaymentMethods = acceptedPaymentMethods;
    return this;
  }

  /**
   * Set Accepted payment methods
   *
   * @param acceptedPaymentMethodsBuilder Builder for Accepted Payment Methods
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getAcceptedPaymentMethods()
   */
  public CheckoutRegistrationBuilder withAcceptedPaymentMethods(
      Builder<AcceptedPaymentMethods> acceptedPaymentMethodsBuilder) {
    return withAcceptedPaymentMethods(acceptedPaymentMethodsBuilder.build());
  }

  /**
   * Add Payments Methods Config to Checkout Registration
   *
   * @param paymentMethodConfig Payments Methods Config
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getPaymentMethodConfigs()
   */
  public CheckoutRegistrationBuilder addPaymentMethodConfig(
      PaymentMethodConfig paymentMethodConfig) {
    paymentMethodConfigs.add(paymentMethodConfig);
    return this;
  }

  /**
   * Add Payments Methods Config to Checkout Registration
   *
   * @param paymentMethodConfigBuilder Builder for Payments Methods Config
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getPaymentMethodConfigs()
   */
  public CheckoutRegistrationBuilder addPaymentMethodConfig(
      Builder<PaymentMethodConfig> paymentMethodConfigBuilder) {
    return addPaymentMethodConfig(paymentMethodConfigBuilder.build());
  }

  /**
   * Add Payments Methods Config to Checkout Registration
   *
   * @param paymentMethodConfigs Payments Methods Configs
   * @return Builder for checkout registration
   * @see CheckoutRegistration#getPaymentMethodConfigs()
   */
  public CheckoutRegistrationBuilder addPaymentMethodConfigs(
      Iterable<? extends PaymentMethodConfig> paymentMethodConfigs) {
    for (PaymentMethodConfig paymentMethodConfig : paymentMethodConfigs) {
      addPaymentMethodConfig(paymentMethodConfig);
    }
    return this;
  }

  /**
   * Build the Checkout Registration
   *
   * @return Interface for Checkout Registration
   * @see CheckoutRegistration
   */
  @Override
  public CheckoutRegistration build() {
    return new SimpleCheckoutRegistration(this);
  }

  /**
   * Implementation of {@code CheckoutRegistration}
   */
  private static class SimpleCheckoutRegistration implements CheckoutRegistration {

    private final CheckoutRegistrationBuilder checkoutRegistrationBuilder;

    private SimpleCheckoutRegistration(CheckoutRegistrationBuilder checkoutRegistrationBuilder) {
      this.checkoutRegistrationBuilder = checkoutRegistrationBuilder;
    }

    @Override
    public String getReference() {
      return checkoutRegistrationBuilder.reference;
    }

    @Override
    public String getNotificationUrl() {return checkoutRegistrationBuilder.notificationUrl; }

    @Override
    public BigDecimal getExtraAmount() {
      return checkoutRegistrationBuilder.extraAmount;
    }

    @Override
    public Currency getCurrency() {
      return checkoutRegistrationBuilder.currency;
    }

    @Override
    public Shipping getShipping() {
      return checkoutRegistrationBuilder.shipping;
    }

    @Override
    public Sender getSender() {
      return checkoutRegistrationBuilder.sender;
    }

    @Override
    public List<? extends PaymentItem> getItems() {
      return checkoutRegistrationBuilder.items;
    }

    @Override
    public PreApproval getPreApproval() {
      return checkoutRegistrationBuilder.preApproval;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return checkoutRegistrationBuilder.parameters;
    }

    @Override
    public AcceptedPaymentMethods getAcceptedPaymentMethods() {
      return checkoutRegistrationBuilder.acceptedPaymentMethods;
    }

    @Override
    public List<? extends PaymentMethodConfig> getPaymentMethodConfigs() {
      return checkoutRegistrationBuilder.paymentMethodConfigs;
    }
  }

}
