package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

/**
 * Converter for payment item.
 * Used to convert attributes of pre approval in request json.
 *
 * @author PagSeguro Internet Ltda.
 */
public class PaymentItemsJsonConverter extends AbstractJsonConverter<Iterable<? extends PaymentItem>> {

    @Override
    protected void convert(RequestJson requestJson, Iterable<? extends PaymentItem> paymentItems) {
        for (PaymentItem paymentItem : paymentItems) {
            requestJson.putString("id", paymentItem.getId());
            requestJson.putString("description", paymentItem.getDescription());
            requestJson.putInteger("quantity", paymentItem.getQuantity());
            requestJson.putString("amount", paymentItem.getAmount().toString());
            requestJson.putInteger("weight", paymentItem.getWeight());
            requestJson.putCurrency("shippingCost", paymentItem.getShippingCost());
        }
    }
}
