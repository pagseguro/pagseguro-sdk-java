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

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for  charging pre approval.
 * This class is responsible for building the attributes for charging pre approval
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PreApprovalChargingBuilder implements Builder<PreApprovalCharging> {

  private String code = null;
  private String reference = null;
  private List<PaymentItem> items = new ArrayList<PaymentItem>();
  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set code of pre approval charging
   *
   * @param code Pre Approval charging
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getCode()
   */
  public PreApprovalChargingBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  /**
   * Set reference of pre approval charging
   *
   * @param reference Reference of pre approval charging
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getReference()
   */
  public PreApprovalChargingBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Add Item to pre approval charging
   *
   * @param item Item of Pre Approval charging
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getItems()
   */
  public PreApprovalChargingBuilder addItem(PaymentItem item) {
    this.items.add(item);
    return this;
  }

  /**
   * Add Item to pre approval charging
   *
   * @param paymentItemBuilder Builder for Item of Pre Approval charging
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getItems()
   */
  public PreApprovalChargingBuilder addItem(Builder<PaymentItem> paymentItemBuilder) {
    return addItem(paymentItemBuilder.build());
  }

  /**
   * Add Items to pre approval charging
   *
   * @param items Items of Pre Approval charging
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getItems()
   */
  public PreApprovalChargingBuilder addItems(Iterable<? extends PaymentItem> items) {
    for (PaymentItem item : items) {
      addItem(item);
    }
    return this;
  }

  /**
   * Add Parameter to Pre Approval Charging
   *
   * @param parameter Parameter
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getParameters()
   */
  public PreApprovalChargingBuilder addParameter(Parameter parameter) {
    this.parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to Pre Approval Charging
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getParameters()
   */
  public PreApprovalChargingBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameter to Pre Approval Charging
   *
   * @param parameters Parameters
   * @return Builder for Pre Approval charging
   * @see PreApprovalCharging#getParameters()
   */
  public PreApprovalChargingBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the Pre Approval Charging
   *
   * @return Interface for Pre Approval Charging
   * @see PreApprovalCharging
   */
  @Override
  public PreApprovalCharging build() {
    return new SimplePreApprovalCharging(this);
  }

  /**
   * Implementation of {@code PreApprovalCharging}
   */
  private static class SimplePreApprovalCharging implements PreApprovalCharging {

    private final PreApprovalChargingBuilder preApprovalChargingBuilder;

    public SimplePreApprovalCharging(PreApprovalChargingBuilder preApprovalChargingBuilder) {
      this.preApprovalChargingBuilder = preApprovalChargingBuilder;
    }

    @Override
    public String getCode() {
      return preApprovalChargingBuilder.code;
    }

    @Override
    public String getReference() {
      return preApprovalChargingBuilder.reference;
    }

    @Override
    public List<? extends PaymentItem> getItems() {
      return preApprovalChargingBuilder.items;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return preApprovalChargingBuilder.parameters;
    }
  }
}
