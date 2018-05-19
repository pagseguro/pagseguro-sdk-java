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
//@TODO validate if is necessary to create a domain for DirectPreApproval instead of use PreApproval default domain
import br.com.uol.pagseguro.api.common.domain.PreApproval;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;
import java.text.SimpleDateFormat;

/**
 * Converter for direct pre approval.
 * Used to convert attributes of pre approval in request json.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PreApprovalJsonConverter extends AbstractJsonConverter<PreApproval> {

  private static final PreApprovalExpirationJsonConverter PRE_APPROVAL_EXPIRATION_JC = new PreApprovalExpirationJsonConverter();

  /**
   * Convert attributes of direct pre approval in request json
   *
   * @param requestJson  Request Json used to pass params to api
   * @param preApproval The interface of pre approval
   * @see RequestJson
   * @see PreApproval
   * @see AbstractJsonConverter#convert(Object)
   */
  @Override
  protected void convert(RequestJson requestJson, PreApproval preApproval) {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
    requestJson.putString("charge", preApproval.getCharge());
    requestJson.putString("name", preApproval.getName());
    requestJson.putString("details", preApproval.getDetails());
    requestJson.putCurrency("amountPerPayment", preApproval.getAmountPerPayment());
    requestJson.putCurrency("maxAmountPerPayment", preApproval.getMaxAmountPerPayment());
    requestJson.putCurrency("maxTotalAmount", preApproval.getMaxTotalAmount());
    requestJson.putInteger("maxPaymentsPerPeriod", preApproval.getMaxPaymentsPerPeriod());
    requestJson.putCurrency("maxAmountPerPeriod", preApproval.getMaxAmountPerPeriod());
    requestJson.putString("period", preApproval.getPeriod());
    requestJson.putDate("initialDate", preApproval.getDateRange().getFrom(), sdf);
    requestJson.putDate("finalDate", preApproval.getDateRange().getTo(), sdf);
    requestJson.putCurrency("membershipFee", preApproval.getMembershipFee());
    requestJson.putInteger("trialPeriodDuration", preApproval.getTrialPeriodDuration());
    requestJson.putString("dayOfYear", preApproval.getDayOfYear());
    requestJson.putInteger("dayOfMonth", preApproval.getDayOfMonth());
    requestJson.putString("dayOfWeek", preApproval.getDayOfWeek());
    requestJson.putString("cancelURL", preApproval.getCancelURl());
    requestJson.putJson(PRE_APPROVAL_EXPIRATION_JC.convert(preApproval.getExpiration()), "expiration");
  }

  /**
   * Implementation of {@code AbstractPhoneMapConverter}. Used to set key values
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
