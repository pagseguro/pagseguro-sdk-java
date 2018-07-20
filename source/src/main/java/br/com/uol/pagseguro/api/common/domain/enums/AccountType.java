package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * AccountType. Used to define the type of the account.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum AccountType {

    /**
     * SELLER type
     */
    SELLER("SELLER"),

    /**
     * COMPANY type
     */
    COMPANY("COMPANY"),

    /**
     * Unrecognized
     */
    UNRECOGNIZED(null);

    private final String value;

    /**
     * Constructor
     *
     * @param value Enum value
     */
    AccountType(String value) {
        this.value = value;
    }

    /**
     * Get value
     *
     * @return Enum value
     */
    public String getValue() {
        return value;
    }

    /**
     * Get enum by value
     *
     * @param value Enum value
     * @return Enum
     */
    public static AccountType fromValue(String value) {
        for (AccountType type : AccountType.values()) {
            if (type.value != null && type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return UNRECOGNIZED;
    }
}
