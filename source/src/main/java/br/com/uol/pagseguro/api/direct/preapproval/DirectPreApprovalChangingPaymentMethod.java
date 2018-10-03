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

import br.com.uol.pagseguro.api.common.PreApprovalSenderRiskData;
import br.com.uol.pagseguro.api.common.domain.PreApprovalCreditCard;

/**
 * Interface for direct pre approval change payment
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalChangingPaymentMethod {
    /**
     * Code / identifier for a created Direct Pre Approval in PagSeguro
     *
     * @return Pre Approval Code
     */
    String getPreApprovalCode();

    /**
     * Payment Method type
     *
     * @return Payment Method Type
     */
    String getType();

    /**
     * Buyer risk data
     *
     * @return Sender Risk Data
     */
    PreApprovalSenderRiskData getSenderRiskData();

    /**
     * Get pre approval credit card data
     *
     * @return PreApprovalCreditCard
     */
    PreApprovalCreditCard getCreditCard();
}
