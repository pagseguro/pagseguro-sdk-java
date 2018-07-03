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

package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.PreApprovalSenderRiskData;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Converter for pre approval sender risk data.
 * Used to convert attributes of sender risk data in request json.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalSenderRiskDataJsonConverter extends AbstractJsonConverter<PreApprovalSenderRiskData> {
    /**
     * Convert attributes of pre approval sender risk data in request json
     *
     * @param requestJson  Request Json used to pass params to api
     * @param preApprovalSenderRiskData The interface of pre approval
     * @see RequestJson
     * @see PreApprovalSenderRiskData
     * @see AbstractJsonConverter#convert(Object)
     */
    @Override
    protected void convert(RequestJson requestJson, PreApprovalSenderRiskData preApprovalSenderRiskData) {
        requestJson.putString("ip", preApprovalSenderRiskData.getIp());
        requestJson.putString("hash", preApprovalSenderRiskData.getHash());
    }
}
