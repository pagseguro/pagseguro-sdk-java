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

import br.com.uol.pagseguro.api.common.domain.converter.CreditCardJsonConverter;
import br.com.uol.pagseguro.api.common.domain.converter.PreApprovalSenderRiskDataJsonConverter;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Json Converter for direct pre approval change payment method.
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalChangingPaymentMethodJsonConvert extends AbstractJsonConverter<DirectPreApprovalChangingPaymentMethod> {
    private final static PreApprovalSenderRiskDataJsonConverter PRE_APPROVAL_SENDER_RISK_DATA_JC = new PreApprovalSenderRiskDataJsonConverter();
    private final static CreditCardJsonConverter CREDIT_CARD_JC = new CreditCardJsonConverter();

    public DirectPreApprovalChangingPaymentMethodJsonConvert() {
    }

    /**
     * Convert Interface for DirectPreApprovalChangingPaymentMethod in Request Json
     *
     * @param requestJson Request Json used to pass params to api
     * @param directPreApprovalChangingPaymentMethod Interface for Direct Pre Approval Changing Payment Method
     * @see RequestJson
     * @see DirectPreApprovalChangingPaymentMethod
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, DirectPreApprovalChangingPaymentMethod directPreApprovalChangingPaymentMethod) {
        requestJson.putString("type", directPreApprovalChangingPaymentMethod.getType());
        requestJson.putJson(PRE_APPROVAL_SENDER_RISK_DATA_JC.convert(directPreApprovalChangingPaymentMethod.getSenderRiskData()), "sender");
        requestJson.putJson(CREDIT_CARD_JC.convert(directPreApprovalChangingPaymentMethod.getCreditCard()), "creditCard");
    }
}
