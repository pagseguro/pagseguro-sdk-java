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
package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.PreApprovalSenderRiskData;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval sender risk data.
 * This class is responsible for building the attributes for changing direct pre approval payment method
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalSenderRiskDataBuilder implements Builder<PreApprovalSenderRiskData> {

    private String ip = null;
    private String hash = null;

    /**
     * Set hash of sender risk data
     *
     * @param hash Hash
     * @return Builder for sender risk data
     * @see PreApprovalSenderRiskData#getHash()
     */
    public PreApprovalSenderRiskDataBuilder withHash(String hash) {
        this.hash = hash;
        return this;
    }

    /**
     * Set ip of sender risk data
     *
     * @param ip IP
     * @return Builder for sender risk data
     * @see PreApprovalSenderRiskData#getIp()
     */
    public PreApprovalSenderRiskDataBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }
    /**
     * Build the PreApprovalSenderRiskData
     *
     * @return Class built from PreApprovalSenderRiskData Interface
     * @see PreApprovalSenderRiskData
     */
    @Override
    public PreApprovalSenderRiskData build() {
        return new SimpleSenderRiskData(this);
    }

    /**
     * Implementation of {@code PreApprovalSenderRiskData}
     */
    private static class SimpleSenderRiskData implements PreApprovalSenderRiskData {
        private final PreApprovalSenderRiskDataBuilder preApprovalSenderRiskDataBuilder;

        SimpleSenderRiskData(PreApprovalSenderRiskDataBuilder preApprovalSenderRiskDataBuilder) {
            this.preApprovalSenderRiskDataBuilder = preApprovalSenderRiskDataBuilder;
        }

        @Override
        public String getHash() {
            return preApprovalSenderRiskDataBuilder.hash;
        }

        @Override
        public String getIp() {
            return preApprovalSenderRiskDataBuilder.ip;
        }
    }
}
