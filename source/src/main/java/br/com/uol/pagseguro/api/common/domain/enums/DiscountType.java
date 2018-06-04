package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * Discount type to be applied
 *
 * @author PagSeguro Internet Ltda.
 */
public enum DiscountType {

    /**
     * Discount Percent
     */
    DISCOUNT_PERCENT ("DISCOUNT_PERCENT"),

    /**
     * Discount Amount
     */
    DISCOUNT_AMOUNT("DISCOUNT_AMOUNT");

    private final String value;

    DiscountType(String value) {
        this.value = value;
    }

    /**
     * @return Value of Enum
     */
    public String getValue() {
        return value;
    }
}
