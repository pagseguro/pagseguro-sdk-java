package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * PhoneType. Used to define the type of phone.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum PhoneType {

    /**
     * HOME status
     */
    HOME("HOME"),

    /**
     * MOBILE status
     */
    MOBILE("MOBILE"),

    /**
     * BUSINESS status
     */
    BUSINESS("BUSINESS"),

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
    PhoneType(String value) {
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
    public static PhoneType fromValue(String value) {
        for (PhoneType type : PhoneType.values()) {
            if (type.value != null && type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return UNRECOGNIZED;
    }
}
