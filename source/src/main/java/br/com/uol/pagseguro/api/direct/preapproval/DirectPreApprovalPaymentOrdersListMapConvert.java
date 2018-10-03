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

import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for Direct Pre Approval Payment Orders List
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalPaymentOrdersListMapConvert extends AbstractMapConverter<DirectPreApprovalPaymentOrdersList> {

    DirectPreApprovalPaymentOrdersListMapConvert() {
    }

    /**
     * Convert Interface for Listing Direct Pre Approval Payment Orders in Request Map
     *
     * @param requestMap Request Map used to pass params to api
     * @param directPreApprovalPaymentOrdersList Interface for Direct Pre Approval Payment Orders List
     * @see RequestMap
     * @see DirectPreApprovalPaymentOrdersList
     * @see AbstractMapConverter#convert(Object)
     */
    @Override
    protected void convert(RequestMap requestMap, DirectPreApprovalPaymentOrdersList directPreApprovalPaymentOrdersList) {
        requestMap.putInteger("status", directPreApprovalPaymentOrdersList.getStatus());
        requestMap.putInteger("page", directPreApprovalPaymentOrdersList.getPage());
        requestMap.putInteger("maxPageResults", directPreApprovalPaymentOrdersList.getMaxPageResults());
    }
}
