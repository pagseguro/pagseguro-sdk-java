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

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for pre approval registration
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalRegistration {

  /**
   * Plan code that the user will accede
   *
   * @return Plan
   */
  String getPlan();

  /**
   * Code / identifier to reference the signature on your system.
   *
   * @return Reference
   */
  String getReference();

  /**
   * Buyer Data
   *
   * @return Sender
   * @see Sender
   */
  Sender getSender();

  /**
   * Pre ApprovalPayment data
   *
   * @return Pre Approval Payment Data
   * @see PreApprovalPaymentMethod
   */
  PreApprovalPaymentMethod getPaymentMethod();

  /**
   * Extra Parameters for another versions
   *
   * @return Parameters
   */
  List<? extends Parameter> getParameters();
}


