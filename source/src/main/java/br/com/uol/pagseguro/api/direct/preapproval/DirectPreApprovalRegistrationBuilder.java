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
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.utils.Builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder for direct pre approval accession registration.
 * This class is responsible for building the attributes for register direct pre approval accession
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalRegistrationBuilder implements Builder<DirectPreApprovalRegistration> {
  private String plan = null;
  private String reference = null;
  private Sender sender = null;
  private PreApprovalPaymentMethod preApprovalPaymentMethod = null;
  private List<Parameter> parameters = new ArrayList<Parameter>();

  /**
   * Set the plan of direct pre approval accession
   *
   * @param plan Direct Pre Approval Plan identifier
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getPlan()
   */
  public DirectPreApprovalRegistrationBuilder withPlan(String plan) {
    this.plan = plan;
    return this;
  }

  /**
   * Set the accession reference of direct pre approval
   *
   * @param reference Direct Pre Approval Reference
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getReference()
   */
  public DirectPreApprovalRegistrationBuilder withReference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Set Sender of direct pre approval accession registration
   *
   * @param sender Sender
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getSender()
   */
  public DirectPreApprovalRegistrationBuilder withSender(Sender sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Set Sender of direct pre approval accession registration
   *
   * @param senderBuilder Builder for Sender
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getSender()
   */
  public DirectPreApprovalRegistrationBuilder withSender(Builder<Sender> senderBuilder) {
    return withSender(senderBuilder.build());
  }

  /**
   * Set Payment Method of direct pre approval accession registration
   *
   * @param preApprovalPaymentMethod Pre Approval Payment Method
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getPaymentMethod()
   */
  public DirectPreApprovalRegistrationBuilder withPaymentMethod(PreApprovalPaymentMethod preApprovalPaymentMethod) {
    this.preApprovalPaymentMethod = preApprovalPaymentMethod;
    return this;
  }

  /**
   * Set Payment Method of direct pre approval accession registration
   *
   * @param preApprovalPaymentMethodBuilder Builder for Pre Approval Payment Method
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getPaymentMethod()
   */
  public DirectPreApprovalRegistrationBuilder withPaymentMethod(Builder<PreApprovalPaymentMethod> preApprovalPaymentMethodBuilder) {
    return withPaymentMethod(preApprovalPaymentMethodBuilder.build());
  }

  /**
   * Add Parameter to direct pre approval accession registration
   *
   * @param parameter Parameter
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalRegistrationBuilder addParameter(Parameter parameter) {
    parameters.add(parameter);
    return this;
  }

  /**
   * Add Parameter to direct pre approval accession registration
   *
   * @param parameterBuilder Builder for Parameter
   * @return Builder for direct pre approval accession registration
   * @see DirectPreApprovalRegistration#getParameters()
   */
  public DirectPreApprovalRegistrationBuilder addParameter(Builder<Parameter> parameterBuilder) {
    return addParameter(parameterBuilder.build());
  }

  /**
   * Add Parameters to direct pre approval accession registration
   *
   * @param parameters Parameters
   * @return Builder for direct pre approval accession registrat
   * @see DirectPreApprovalRequestRegistration#getParameters()
   */
  public DirectPreApprovalRegistrationBuilder addParameters(Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      addParameter(parameter);
    }
    return this;
  }

  /**
   * Build the Direct Pre Approval Acession Registration
   *
   * @return Interface for Direct Pre Approval Accession Registration
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
    public String getPlan() {
      return directPreApprovalRegistrationBuilder.plan;
    }

    @Override
    public String getReference() {
      return directPreApprovalRegistrationBuilder.reference;
    }

    @Override
    public PreApprovalPaymentMethod getPaymentMethod() {
      return directPreApprovalRegistrationBuilder.preApprovalPaymentMethod;
    }

    @Override
    public Sender getSender() {
      return directPreApprovalRegistrationBuilder.sender;
    }

    @Override
    public List<? extends Parameter> getParameters() {
      return directPreApprovalRegistrationBuilder.parameters;
    }
  }
}
