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

package br.com.uol.pagseguro.api.transaction.register;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct payment registration.
 * This class is responsible for building the attributes for register direct payment
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPaymentRegistrationBuilder implements Builder<DirectPaymentRegistration> {

  private String paymentMode = null;
  private Currency currency = Currency.BRL;
  private List<PaymentItem> items = new ArrayList<PaymentItem>();
  private String notificationURL = null;
  private String reference = null;
  private Sender sender = null;
  private Shipping shipping = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();
  private BigDecimal extraAmount = null;
  private String receiverEmail = null;

  /**
   * Set payment mode of direct payment
   *
   * @param paymentMode Payment mode
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getPaymentMode()
   */
  public DirectPaymentRegistrationBuilder withPaymentMode(String paymentMode) {
    this.paymentMode = paymentMode;
    return this;
  }

  /**
   * Set Currency of direct payment
   * Currency used.
   *
   * @param currency Currency
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getCurrency()
   */
  public DirectPaymentRegistrationBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Add item to direct payment
   *
   * @param item Payment Item
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getItems()
   */
  public DirectPaymentRegistrationBuilder addItem(PaymentItem item) {
    items.add(item);
    return this;
  }

  /**
   * Add item to direct payment
   *
   * @param itemBuilder Builder for Payment Item
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getItems()
   */
  public DirectPaymentRegistrationBuilder addItem(Builder<PaymentItem> itemBuilder) {
    return addItem(itemBuilder.build());
  }

  /**
   * Add items to direct payment
   *
   * @param items Payment Items
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getItems()
   */
  public DirectPaymentRegistrationBuilder addItems(Iterable<? extends PaymentItem> items) {
    for (PaymentItem item : items) {
      addItem(item);
    }
    return this;
  }

  /**
   * Set notification URL of Direct Payment
   *
   * @param notificationURL Notification URL of Direct Payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getNotificationURL()
   */
  public DirectPaymentRegistrationBuilder withNotificationURL(String notificationURL) {
    this.notificationURL = notificationURL;
    return this;
  }

  /**
   * Set reference of direct payment
   *
   * @param reference Reference of direct payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getReference()
   */
  public DirectPaymentRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set sender of direct payment
   *
   * @param sender Sender of direct payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getSender()
   */
  public DirectPaymentRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set sender of direct payment
   *
   * @param senderBuilder Builder for Sender of direct payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getSender()
   */
  public DirectPaymentRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Shipping of direct payment
   *
   * @param shipping Shipping of direct payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getShipping()
   */
  public DirectPaymentRegistrationBuilder withShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  /**
   * Set Shipping of direct payment
   *
   * @param shippingBuilder Builder for Shipping of direct payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getShipping()
   */
  public DirectPaymentRegistrationBuilder withShipping(Builder<Shipping> shippingBuilder) {
    return withShipping(shippingBuilder.build());
  }

  /**
   * Add parameter to direct payment
   *
   * @param parameter Parameter
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getParameters()
   */
  public DirectPaymentRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add parameter to direct payment
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getParameters()
   */
  public DirectPaymentRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add parameters to direct payment
   *
   * @param parameters Parameters
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getParameters()
   */
  public DirectPaymentRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Set extra amount of direct payment
   *
   * @param extraAmount Extra amount of direct payment
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration#getExtraAmount()
   */
  public DirectPaymentRegistrationBuilder withExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
    return this;
  }

  /**
   * Set receiver email of direct payment
   *
   * @param receiverEmail Receiver email of direct payment
   * @return DirectPaymentRegistrationBuilder
   * @see DirectPaymentRegistration#getExtraAmount()
   */
  public DirectPaymentRegistrationBuilder withReceiverEmail(String receiverEmail) {
    this.receiverEmail = receiverEmail;
    return this;
  }

  /**
   * Build the Direct Payment
   *
   * @return Builder for direct payment registration
   * @see DirectPaymentRegistration
   */
  @Override
  public DirectPaymentRegistration build() {
    return new SimpleDirectPaymentRegistration(this);
  }

  /**
   * Implementation of {@code DirectPaymentRegistration}
   */
  private static class SimpleDirectPaymentRegistration implements DirectPaymentRegistration {

    private final DirectPaymentRegistrationBuilder directPaymentRegistrationBuilder;

    public SimpleDirectPaymentRegistration(
        DirectPaymentRegistrationBuilder directPaymentRegistrationBuilder) {
      this.directPaymentRegistrationBuilder = directPaymentRegistrationBuilder;
    }

    @Override
    public String getPaymentMode() {
      return directPaymentRegistrationBuilder.paymentMode;
    }

    @Override
    public Currency getCurrency() {
      return directPaymentRegistrationBuilder.currency;
    }

    @Override
    public List<? extends PaymentItem> getItems() {
      return directPaymentRegistrationBuilder.items;
    }

    @Override
    public String getNotificationURL() {
      return directPaymentRegistrationBuilder.notificationURL;
    }

    @Override
    public String getReference() {
      return directPaymentRegistrationBuilder.reference;
    }

    @Override
    public Sender getSender() {
      return directPaymentRegistrationBuilder.sender;
    }

    @Override
    public Shipping getShipping() {
      return directPaymentRegistrationBuilder.shipping;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return directPaymentRegistrationBuilder.parameters;
    }

    @Override
    public BigDecimal getExtraAmount() {
      return directPaymentRegistrationBuilder.extraAmount;
    }

    @Override
    public String getReceiverEmail() {
      return directPaymentRegistrationBuilder.receiverEmail;
    }
  }
}
