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
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for direct pre approval accede registration.
 * This class is responsible for building the attributes for register direct pre approval accede
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalAccessionBuilder implements Builder<DirectPreApprovalAccession> {
  private String plan = null;
  private String reference = null;
  private Sender sender = null;
  private PreApprovalPaymentMethod preApprovalPaymentMethod = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set the plan of direct pre approval accede
   *
   * @param plan Direct Pre Approval Plan identifier
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getPlan()
   */
  public DirectPreApprovalAccessionBuilder withPlan(String plan) {
    this.plan = plan;
    return this;
  }

  /**
   * Set the accede reference of direct pre approval
   *
   * @param reference Direct Pre Approval Reference
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getReference()
   */
  public DirectPreApprovalAccessionBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set Sender of direct pre approval accede registration
   *
   * @param sender Sender
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getSender()
   */
  public DirectPreApprovalAccessionBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set Sender of direct pre approval accede registration
   *
   * @param senderBuilder Builder for Sender
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getSender()
   */
  public DirectPreApprovalAccessionBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Payment Method of direct pre approval accede registration
   *
   * @param preApprovalPaymentMethod Pre Approval Payment Method
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getPaymentMethod()
   */
  public DirectPreApprovalAccessionBuilder withPaymentMethod(PreApprovalPaymentMethod preApprovalPaymentMethod) {
    this.preApprovalPaymentMethod = preApprovalPaymentMethod;
    return this;
  }

  /**
   * Set Payment Method of direct pre approval accede registration
   *
   * @param preApprovalPaymentMethodBuilder Builder for Pre Approval Payment Method
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getPaymentMethod()
   */
  public DirectPreApprovalAccessionBuilder withPaymentMethod(Builder<PreApprovalPaymentMethod> preApprovalPaymentMethodBuilder) {
    return withPaymentMethod(preApprovalPaymentMethodBuilder.build());
  }

  /**
   * Add Parameter to direct pre approval accede registration
   *
   * @param parameter Parameter
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalAccessionBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to direct pre approval accede registration
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for direct pre approval accede registration
   * @see DirectPreApprovalAccession#getParameters()
   */
  public DirectPreApprovalAccessionBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameters to direct pre approval accede registration
   *
   * @param parameters Parameters
   * @return Builder for direct pre approval accede registrat
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalAccessionBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the Direct Pre Approval Acession Registration
   *
   * @return Interface for Direct Pre Approval Accession Registration
   * @see DirectPreApprovalAccession
   */
  @Override
  public DirectPreApprovalAccession build() {
    return new SimplePreApprovalAccession(this);
  }

  /**
   * Implementation of {@code PreApprovalRegistration}
   */
  private static class SimplePreApprovalAccession implements DirectPreApprovalAccession {

    private final DirectPreApprovalAccessionBuilder directPreApprovalAccessionBuilder;

    public SimplePreApprovalAccession(DirectPreApprovalAccessionBuilder directPreApprovalAccessionBuilder) {
      this.directPreApprovalAccessionBuilder = directPreApprovalAccessionBuilder;
    }

    @Override
    public String getPlan() {
      return directPreApprovalAccessionBuilder.plan;
    }

    @Override
    public String getReference() {
      return directPreApprovalAccessionBuilder.reference;
    }

    @Override
    public PreApprovalPaymentMethod getPaymentMethod() {
      return directPreApprovalAccessionBuilder.preApprovalPaymentMethod;
    }

    @Override
    public Sender getSender() {
      return directPreApprovalAccessionBuilder.sender;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return directPreApprovalAccessionBuilder.parameters;
    }
  }
}
