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
import br.com.uol.pagseguro.api.common.domain.Receiver;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for split payment registration.
 * This class is responsible for building the attributes for register split payment
 *
 * @author PagSeguro Internet Ltda.
 */
public final class SplitPaymentRegistrationBuilder implements Builder<SplitPaymentRegistration> {

  private String paymentMode = null;
  private Currency currency = Currency.BRL;
  private List<PaymentItem> items = new ArrayList<PaymentItem>();
  private String notificationURL = null;
  private String reference = null;
  private Sender sender = null;
  private Shipping shipping = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();
  private List<Receiver> receivers = new ArrayList<Receiver>();
  private Receiver primaryReceiver = null;
  private BigDecimal extraAmount = null;

  /**
   * Set payment mode of split payment
   *
   * @param paymentMode Payment mode
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getPaymentMode()
   */
  public SplitPaymentRegistrationBuilder withPaymentMode(String paymentMode) {
    this.paymentMode = paymentMode;
    return this;
  }

  /**
   * Set Currency of split payment
   * Currency used.
   *
   * @param currency Currency
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getCurrency()
   */
  public SplitPaymentRegistrationBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Add item to split payment
   *
   * @param item Payment Item
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getItems()
   */
  public SplitPaymentRegistrationBuilder addItem(PaymentItem item) {
    items.add(item);
    return this;
  }

  /**
   * Add item to split payment
   *
   * @param itemBuilder Builder for Payment Item
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getItems()
   */
  public SplitPaymentRegistrationBuilder addItem(Builder<PaymentItem> itemBuilder) {
    return addItem(itemBuilder.build());
  }

  /**
   * Add items to split payment
   *
   * @param items Payment Items
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getItems()
   */
  public SplitPaymentRegistrationBuilder addItems(Iterable<? extends PaymentItem> items) {
    for (PaymentItem item : items) {
      addItem(item);
    }
    return this;
  }

  /**
   * Set notification URL of Split Payment
   *
   * @param notificationURL Notification URL of Split Payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getNotificationURL()
   */
  public SplitPaymentRegistrationBuilder withNotificationURL(String notificationURL) {
    this.notificationURL = notificationURL;
    return this;
  }

  /**
   * Set reference of split payment
   *
   * @param reference Reference of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getReference()
   */
  public SplitPaymentRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set sender of split payment
   *
   * @param sender Sender of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getSender()
   */
  public SplitPaymentRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set sender of split payment
   *
   * @param senderBuilder Builder for Sender of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getSender()
   */
  public SplitPaymentRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Shipping of split payment
   *
   * @param shipping Shipping of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getShipping()
   */
  public SplitPaymentRegistrationBuilder withShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  /**
   * Set Shipping of split payment
   *
   * @param shippingBuilder Builder for Shipping of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getShipping()
   */
  public SplitPaymentRegistrationBuilder withShipping(Builder<Shipping> shippingBuilder) {
    return withShipping(shippingBuilder.build());
  }

  /**
   * Add parameter to split payment registration
   *
   * @param parameter Parameter
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getParameters()
   */
  public SplitPaymentRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add parameter to split payment registration
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getParameters()
   */
  public SplitPaymentRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add parameters to split payment registration
   *
   * @param parameters Parameters
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getParameters()
   */
  public SplitPaymentRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Add receiver to split payment
   *
   * @param receiver Receiver of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getReceivers()
   */
  public SplitPaymentRegistrationBuilder addReceiver(Receiver receiver) {
    receivers.add(receiver);
    return this;
  }

  /**
   * Add receiver to split payment
   *
   * @param receiverBuilder Builder for Receiver of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getReceivers()
   */
  public SplitPaymentRegistrationBuilder addReceiver(Builder<Receiver> receiverBuilder) {
    return addReceiver(receiverBuilder.build());
  }

  /**
   * Add receivers to split payment
   *
   * @param receivers Receivers of split payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getReceivers()
   */
  public SplitPaymentRegistrationBuilder addReceivers(Iterable<? extends Receiver> receivers) {
    for (Receiver receiver : receivers) {
      addReceiver(receiver);
    }
    return this;
  }

  /**
   * Set Primary Receiver
   *
   * @param receiver Primary Receiver of Split Payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getPrimaryReceiver()
   */
  public SplitPaymentRegistrationBuilder withPrimaryReceiver(Receiver receiver) {
    this.primaryReceiver = receiver;
    return this;
  }

  /**
   * Set Primary Receiver
   *
   * @param receiverBuilder Builder for Primary Receiver of Split Payment
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getPrimaryReceiver()
   */
  public SplitPaymentRegistrationBuilder withPrimaryReceiver(Builder<Receiver> receiverBuilder) {
    withPrimaryReceiver(receiverBuilder.build());
    return this;
  }

  /**
   * Set extra amount
   *
   * @param extraAmount Extra amount
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration#getExtraAmount()
   */
  public SplitPaymentRegistrationBuilder withExtraAmount(BigDecimal extraAmount) {
    this.extraAmount = extraAmount;
    return this;
  }

  /**
   * Build the Split Payment
   *
   * @return Builder for split payment registration
   * @see SplitPaymentRegistration
   */
  @Override
  public SplitPaymentRegistration build() {
    return new SimpleSplitPaymentRegistration(this);
  }

  /**
   * Implementation of {@code SplitPaymentRegistration}
   */
  private static class SimpleSplitPaymentRegistration implements SplitPaymentRegistration {

    private final SplitPaymentRegistrationBuilder splitPaymentRegistrationBuilder;

    public SimpleSplitPaymentRegistration(
        SplitPaymentRegistrationBuilder splitPaymentRegistrationBuilder) {
      this.splitPaymentRegistrationBuilder = splitPaymentRegistrationBuilder;
    }

    @Override
    public List<? extends Receiver> getReceivers() {
      return splitPaymentRegistrationBuilder.receivers;
    }

    @Override
    public Receiver getPrimaryReceiver() {
      return splitPaymentRegistrationBuilder.primaryReceiver;
    }

    @Override
    public String getPaymentMode() {
      return splitPaymentRegistrationBuilder.paymentMode;
    }

    @Override
    public Currency getCurrency() {
      return splitPaymentRegistrationBuilder.currency;
    }

    @Override
    public List<? extends PaymentItem> getItems() {
      return splitPaymentRegistrationBuilder.items;
    }

    @Override
    public String getNotificationURL() {
      return splitPaymentRegistrationBuilder.notificationURL;
    }

    @Override
    public String getReference() {
      return splitPaymentRegistrationBuilder.reference;
    }

    @Override
    public Sender getSender() {
      return splitPaymentRegistrationBuilder.sender;
    }

    @Override
    public Shipping getShipping() {
      return splitPaymentRegistrationBuilder.shipping;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return splitPaymentRegistrationBuilder.parameters;
    }

    @Override
    public BigDecimal getExtraAmount() {
      return splitPaymentRegistrationBuilder.extraAmount;
    }
  }
}
