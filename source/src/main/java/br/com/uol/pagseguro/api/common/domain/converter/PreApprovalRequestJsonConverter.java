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
//@TODO validate if is necessary to create a domain for DirectPreApproval instead of use PreApprovalRequest default domain
import br.com.uol.pagseguro.api.common.domain.PreApprovalRequest;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;
import java.text.SimpleDateFormat;

/**
 * Converter for direct pre approval.
 * Used to convert attributes of pre approval in request json.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalRequestJsonConverter extends AbstractJsonConverter<PreApprovalRequest> {

  private static final PreApprovalExpirationJsonConverter PRE_APPROVAL_EXPIRATION_JC = new PreApprovalExpirationJsonConverter();

  /**
   * Convert attributes of direct pre approval in request json
   *
   * @param requestJson  Request Json used to pass params to api
   * @param preApprovalRequest The interface of pre approval
   * @see RequestJson
   * @see PreApprovalRequest
   * @see AbstractJsonConverter#convert(Object)
   */
  @Override
  protected void convert(RequestJson requestJson, PreApprovalRequest preApprovalRequest) {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
    requestJson.putString("charge", preApprovalRequest.getCharge());
    requestJson.putString("name", preApprovalRequest.getName());
    requestJson.putString("details", preApprovalRequest.getDetails());
    requestJson.putCurrency("amountPerPayment", preApprovalRequest.getAmountPerPayment());
    requestJson.putCurrency("maxAmountPerPayment", preApprovalRequest.getMaxAmountPerPayment());
    requestJson.putCurrency("maxTotalAmount", preApprovalRequest.getMaxTotalAmount());
    requestJson.putInteger("maxPaymentsPerPeriod", preApprovalRequest.getMaxPaymentsPerPeriod());
    requestJson.putCurrency("maxAmountPerPeriod", preApprovalRequest.getMaxAmountPerPeriod());
    requestJson.putString("period", preApprovalRequest.getPeriod());
    requestJson.putDate("initialDate", preApprovalRequest.getDateRange().getFrom(), sdf);
    requestJson.putDate("finalDate", preApprovalRequest.getDateRange().getTo(), sdf);
    requestJson.putCurrency("membershipFee", preApprovalRequest.getMembershipFee());
    requestJson.putInteger("trialPeriodDuration", preApprovalRequest.getTrialPeriodDuration());
    requestJson.putString("dayOfYear", preApprovalRequest.getDayOfYear());
    requestJson.putInteger("dayOfMonth", preApprovalRequest.getDayOfMonth());
    requestJson.putString("dayOfWeek", preApprovalRequest.getDayOfWeek());
    requestJson.putString("cancelURL", preApprovalRequest.getCancelURl());
    requestJson.putJson(PRE_APPROVAL_EXPIRATION_JC.convert(preApprovalRequest.getExpiration()), "expiration");
  }

  /**
   * Implementation of {@code AbstractExpirationJsonConverter}. Used to set key values
   *
   * @see AbstractExpirationJsonConverter
   */
  private static class PreApprovalExpirationJsonConverter extends AbstractExpirationJsonConverter {

    @Override
    protected String getValueKey() {
      return "value";
    }

    @Override
    protected String getUnitKey() {
      return "unit";
    }

  }
}
