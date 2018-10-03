package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.enums.DiscountType;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval discount.
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalDiscountBuilder implements Builder<DirectPreApprovalDiscount> {

    private String code = null;
    private String type = null;
    private String value = null;

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param code Pre Approval Code
     * @return Builder for Pre Approval discount
     * @see DirectPreApprovalDiscount#getCode()
     */
    public DirectPreApprovalDiscountBuilder withCode(String code){
        this.code = code;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param type Pre Approval Code
     * @return Builder for Pre Approval discount
     * @see DirectPreApprovalDiscount#getType()
     */
    public DirectPreApprovalDiscountBuilder withType(DiscountType type){
        this.type = type.getValue();
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param value Pre Approval Code
     * @return Builder for Pre Approval discount
     * @see DirectPreApprovalDiscount#getValue()
     */
    public DirectPreApprovalDiscountBuilder withValue(String value){
        this.value = value;
        return this;
    }

    /**
     * Build the Direct Pre Approval Discount
     *
     * @return Interface for Direct Pre Approval Discount
     * @see DirectPreApprovalDiscount
     */
    @Override
    public DirectPreApprovalDiscount build() {
        return new SimpleDirectPreApprovalDiscount(this);
    }

    /**
     * Implementation of {@type DirectPreApprovalDiscount and @value DirectPreApprovalDiscount}
     */
    private static class SimpleDirectPreApprovalDiscount implements DirectPreApprovalDiscount {

        private final DirectPreApprovalDiscountBuilder directPreApprovalDiscountBuilder;

        public SimpleDirectPreApprovalDiscount(DirectPreApprovalDiscountBuilder directPreApprovalDiscountBuilder) {
            this.directPreApprovalDiscountBuilder = directPreApprovalDiscountBuilder;
        }

        @Override
        public String getCode() {
            return directPreApprovalDiscountBuilder.code;
        }

        @Override
        public String getType() {
            return directPreApprovalDiscountBuilder.type;
        }

        @Override
        public String getValue() {
            return directPreApprovalDiscountBuilder.value;
        }
    }
}
