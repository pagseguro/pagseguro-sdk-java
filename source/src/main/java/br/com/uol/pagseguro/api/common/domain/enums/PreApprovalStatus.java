package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * PreApprovalStatus. Used to define the type of status to be executed.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum PreApprovalStatus {

    /**
     * ACTIVE status
     */
    ACTIVE("ACTIVE"),

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
    PreApprovalStatus(String value) {
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
    public static PreApprovalStatus fromValue(String value) {
        for (PreApprovalStatus status : PreApprovalStatus.values()) {
            if (status.value != null && status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return UNRECOGNIZED;
    }
}
