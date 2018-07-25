package br.com.uol.pagseguro.api.common.domain.builder;

import br.com.uol.pagseguro.api.common.domain.PhoneAccount;
import br.com.uol.pagseguro.api.common.domain.enums.PhoneType;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for phone account
 *
 * @author PagSeguro Internet Ltda.
 */
public final class PhoneAccountBuilder implements Builder<PhoneAccount> {

    private String type;
    private String areaCode;
    private String number;

    /**
     * Set type of phone account
     *
     * @param type Phone Account Type
     * @return Builder for Phone Account
     * @see PhoneAccount#getType()
     */
    public PhoneAccountBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Set type of phone account
     *
     * @param type Phone Account Type
     * @return Builder for Phone Account
     * @see PhoneAccount#getType()
     */
    public PhoneAccountBuilder withType(PhoneType type) {
        this.type = type.getValue();
        return this;
    }

    /**
     * Set area code of phone account
     *
     * @param areaCode Phone Account Area Code
     * @return Builder for Phone Account
     * @see PhoneAccount#getAreaCode()
     */
    public PhoneAccountBuilder withAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    /**
     * Set Number of phone account
     *
     * @param phoneNumber Phone Account Number
     * @return Builder for Phone Account
     * @see PhoneAccount#getNumber()
     */
    public PhoneAccountBuilder withNumber(String phoneNumber) {
        this.number = phoneNumber;
        return this;
    }

    /**
     * Build the phone account
     *
     * @return Interface for Phone Account
     * @see PhoneAccount
     */
    @Override
    public PhoneAccount build() {
        return new SimplePhoneAccount(this);
    }

    /**
     * Implementation of {@code PhoneAccount}
     */
    private static class SimplePhoneAccount implements PhoneAccount {

        private PhoneAccountBuilder phoneAccountBuilder;

        private SimplePhoneAccount(PhoneAccountBuilder phoneAccountBuilder) {
            this.phoneAccountBuilder = phoneAccountBuilder;
        }

        @Override
        public String getType() {
            return phoneAccountBuilder.type;
        }

        @Override
        public String getAreaCode() {
            return phoneAccountBuilder.areaCode;
        }

        @Override
        public String getNumber() {
            return phoneAccountBuilder.number;
        }

    }
}
