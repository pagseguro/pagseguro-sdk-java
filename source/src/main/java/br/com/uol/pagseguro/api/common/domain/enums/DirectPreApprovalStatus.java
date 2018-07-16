package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * Direct Pre Approval Status enum, used to define the id related to each status of a direct pre approval
 *
 * @author PagSeguro Internet Ltda.
 */
public enum DirectPreApprovalStatus {

    /**
     * INITIATED status
     */
    INITIATED("INITIATED"),

    /**
     * PENDING status
     */
    PENDING("PENDING"),

    /**
     * ACTIVE status
     */
    ACTIVE("ACTIVE"),

    /**
     * CANCELLED_BY_RECEIVER status
     */
    CANCELLED_BY_RECEIVER("CANCELLED_BY_RECEIVER"),

    /**
     * CANCELLED_BY_SENDER status
     */
    CANCELLED_BY_SENDER("CANCELLED_BY_SENDER"),

    /**
     * EXPIRED status
     */
    EXPIRED("EXPIRED"),

    /**
     * SUSPENDED status
     */
    SUSPENDED("SUSPENDED"),

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
    DirectPreApprovalStatus(String value) {
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
    public static DirectPreApprovalStatus fromValue(String value) {
        for (DirectPreApprovalStatus charge : DirectPreApprovalStatus.values()) {
            if (charge.value != null && charge.value.equalsIgnoreCase(value)) {
                return charge;
            }
        }
        return UNRECOGNIZED;
    }
}
