package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.enums.DiscountType;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval discount.
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalRequestDiscountBuilder implements Builder<DirectPreApprovalRequestDiscount> {

    private String code = null;
    private String type = null;
    private String value = null;

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval discount
     * @see DirectPreApprovalRequestDiscount#getCode()
     */
    public DirectPreApprovalRequestDiscountBuilder withCode(String code){
        this.code = code;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param type Pre Approval Code
     * @return Builder for Pre Approval discount
     * @see DirectPreApprovalRequestDiscount#getType()
     */
    public DirectPreApprovalRequestDiscountBuilder withType(DiscountType type){
        this.type = type.getValue();
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param value Pre Approval Code
     * @return Builder for Pre Approval discount
     * @see DirectPreApprovalRequestDiscount#getValue()
     */
    public DirectPreApprovalRequestDiscountBuilder withValue(String value){
        this.value = value;
        return this;
    }

    /**
     * Build the Direct Pre Approval Discount
     *
     * @return Interface for Direct Pre Approval Discount
     * @see DirectPreApprovalRequestDiscount
     */
    @Override
    public DirectPreApprovalRequestDiscount build() {
        return new SimpleDirectPreApprovalRequestDiscount(this);
    }

    /**
     * Implementation of {@type DirectPreApprovalRequestDiscount and @value DirectPreApprovalRequestDiscount}
     */
    private static class SimpleDirectPreApprovalRequestDiscount implements DirectPreApprovalRequestDiscount {

        private final DirectPreApprovalRequestDiscountBuilder directPreApprovalRequestDiscountBuilder;

        public SimpleDirectPreApprovalRequestDiscount(DirectPreApprovalRequestDiscountBuilder directPreApprovalRequestDiscountBuilder) {
            this.directPreApprovalRequestDiscountBuilder = directPreApprovalRequestDiscountBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalRequestDiscountBuilder.code;
        }

        @Override
        public String getType() {
            return directPreApprovalRequestDiscountBuilder.type;
        }

        @Override
        public String getValue() {
            return directPreApprovalRequestDiscountBuilder.value;
        }
    }
}
