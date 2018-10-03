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
package br.com.uol.pagseguro.api.application.authorization;

import java.util.ArrayList;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.AccountRegisterSuggestion;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for authorization registration.
 * This class is responsible for building the attributes for register authorization
 *
 * @author PagSeguro Internet Ltda.
 */
public final class AuthorizationRegistrationBuilder implements Builder<AuthorizationRegistration> {

    private String reference;
    private List<PermissionCode.Code> permissions = new ArrayList<PermissionCode.Code>();
    private String notificationURL;
    private String redirectURL;
    private AccountRegisterSuggestion account;

    /**
     * Set reference of authorization
     *
     * @param reference Optional. Identifier used to refer to the permission of your request.
     * @return Builder for authorization registration
     * @see AuthorizationRegistration#getReference()
     */
    public AuthorizationRegistrationBuilder withReference(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * Add permission of authorization
     *
     * @param permission Required. List of permissions in this authorization.
     * @return Builder for Authorization Registration
     * @see PermissionCode.Code
     * @see AuthorizationRegistration#getPermissions()
     */
    public AuthorizationRegistrationBuilder addPermission(PermissionCode.Code permission) {
        permissions.add(permission);
        return this;
    }

    /**
     * Add Permissions of Authorization
     *
     * @param permissions Required. List of permissions in this authorization.
     * @return Builder for Authorization Registration
     * @see PermissionCode.Code
     * @see AuthorizationRegistration#getPermissions()
     */
    public AuthorizationRegistrationBuilder addPermissions(
        Iterable<? extends PermissionCode.Code> permissions) {
        for (PermissionCode.Code permission : permissions) {
            addPermission(permission);
        }
        return this;
    }

    /**
     * Set notification URL of Authorization
     *
     * @param notificationURL Notification URL of Authorization
     * @return Builder for Authorization Registration
     * @see AuthorizationRegistration#getNotificationURL()
     */
    public AuthorizationRegistrationBuilder withNotificationUrl(String notificationURL) {
        this.notificationURL = notificationURL;
        return this;
    }

    /**
     * Set redirect URL of Authorization
     *
     * @param redirectURL Redirect URL of Authorization
     * @return Builder for Authorization Registration
     * @see AuthorizationRegistration#getRedirectURL()
     */
    public AuthorizationRegistrationBuilder withRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
        return this;
    }

    /**
     * Set Account of Authorization
     *
     * @param account Account of Authorization
     * @return Builder for Authorization Registration
     * @see AuthorizationRegistration#getAccount()
     */
    public AuthorizationRegistrationBuilder withAccount(AccountRegisterSuggestion account) {
        this.account = account;
        return this;
    }

    /**
     * Set Account of Authorization
     *
     * @param accountBuilder Builder for Account of Authorization
     * @return Builder for person
     * @see AuthorizationRegistration#getAccount()
     */
    public AuthorizationRegistrationBuilder withAccount(Builder<AccountRegisterSuggestion> accountBuilder) {
        return withAccount(accountBuilder.build());
    }


    /**
     * Build the Authorization Registration
     *
     * @return Interface for Authorization Registration
     * @see AuthorizationRegistration
     */
    @Override
    public AuthorizationRegistration build() {
        return new SimpleAuthorizationRegistration(this);
    }

    /**
     * Implementation of {@code AuthorizationRegistration}
     */
    private static class SimpleAuthorizationRegistration implements AuthorizationRegistration {

        private final AuthorizationRegistrationBuilder authorizationRegistrationBuilder;

        private SimpleAuthorizationRegistration(AuthorizationRegistrationBuilder authorizationRegistrationBuilder) {
            this.authorizationRegistrationBuilder = authorizationRegistrationBuilder;
        }

        @Override
        public String getReference() {
            return authorizationRegistrationBuilder.reference;
        }

        @Override
        public List<PermissionCode.Code> getPermissions() {
            return authorizationRegistrationBuilder.permissions;
        }

        @Override
        public String getRedirectURL() {
            return authorizationRegistrationBuilder.redirectURL;
        }

        @Override
        public String getNotificationURL() {
            return authorizationRegistrationBuilder.notificationURL;
        }

        @Override
        public AccountRegisterSuggestion getAccount() { return authorizationRegistrationBuilder.account; }
    }
}
