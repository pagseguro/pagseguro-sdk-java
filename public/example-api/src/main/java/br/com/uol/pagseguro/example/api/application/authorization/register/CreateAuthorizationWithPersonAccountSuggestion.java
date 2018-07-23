package br.com.uol.pagseguro.example.api.application.authorization.register;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistration;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistrationBuilder;
import br.com.uol.pagseguro.api.application.authorization.RegisteredAuthorization;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.common.domain.builder.*;
import br.com.uol.pagseguro.api.common.domain.enums.AccountType;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.common.domain.enums.PhoneType;
import br.com.uol.pagseguro.api.credential.Credential;

public class CreateAuthorizationWithPersonAccountSuggestion {

    public static void main(String[] args) {

        String appId = "your_app_id";
        String appKey = "your_app_key";

        try{
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(appId,
                appKey), PagSeguroEnv.SANDBOX);

            // Registra as autorizações com sugestão de cadastro de pessoa
            AuthorizationRegistration authorizationRegistration =
                new AuthorizationRegistrationBuilder()
                    .withReference("123")
                    .addPermission(PermissionCode.Code.CREATE_CHECKOUTS)
                    .addPermission(PermissionCode.Code.SEARCH_TRANSACTIONS)
                    .addPermission(PermissionCode.Code.RECEIVE_TRANSACTION_NOTIFICATIONS)
                    .withRedirectURL("http://seusite.com.br/redirect")
                    .withNotificationUrl("http://seusite.com.br/notification")
                    .withAccount(new AccountRegisterSuggestionBuilder()
                        .withEmail("usuario@seusite.com.br")
                        .withType(AccountType.SELLER)
                        .withPerson(new PersonBuilder()
                            .withName("Antonio Carlos")
                            .addDocument(new DocumentBuilder()
                                .withType(DocumentType.CPF)
                                .withValue("23606838450")
                            )
                            .withBirthDate("1982-02-05")
                            .addPhone(new PhoneAccountBuilder()
                                .withType(PhoneType.HOME)
                                .withAreaCode("11")
                                .withNumber("30302323")
                            )
                            .addPhone(new PhoneAccountBuilder()
                                .withType(PhoneType.MOBILE)
                                .withAreaCode("11")
                                .withNumber("976302323")
                            )
                            .withAddress(new AddressBuilder()
                                .withPostalCode("01452002")
                                .withStreet("Av. Brig. Faria Lima")
                                .withNumber("1384")
                                .withComplement("5o andar")
                                .withDistrict("Jardim Paulistano")
                                .withCity("Sao Paulo")
                                .withState("SP")
                                .withCountry("BRA")
                            )
                        )
                    )
                    .build();

            RegisteredAuthorization ra = pagSeguro.authorizations().registerWithSuggestion(authorizationRegistration);
            System.out.print(ra);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
