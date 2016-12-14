package br.com.uol.pagseguro.api.application.authorization;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.exception.PagSeguroBadRequestException;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.exception.ServerError;
import br.com.uol.pagseguro.api.exception.ServerErrors;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class AuthorizationsResourceTest extends Resource4Test {

  private AuthorizationsResource authorizationsResource;

  private AuthorizationRegistration authorizationRegistration;

  @Before
  public void setUp() throws Exception {
    authorizationsResource = new AuthorizationsResource(pagSeguro, httpClient);
    authorizationRegistration = new AuthorizationRegistrationBuilder()
        .withReference("reference")
        .withNotificationUrl("notificationUrl")
        .withRedirectURL("redirectUrl")
        .addPermission(PermissionCode.Code.CREATE_CHECKOUTS)
        .addPermission(PermissionCode.Code.DIRECT_PAYMENT)
        .addPermission(PermissionCode.Code.MANAGE_PAYMENT_PRE_APPROVALS)
        .build();
  }

  @Test
  public void shouldRegister() throws Exception {
    String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                              "<authorizationRequest>" +
                              "<code>D8DD848AC9C98D9EE44C5FB3A1E53913</code>" +
                              "<date>2016-11-09T00:00:00.000-03:00</date>" +
                              "</authorizationRequest>";
    HttpResponse response = new HttpResponse(200, responseAsString);
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenReturn(response);

    RegisteredAuthorization registeredAuthorization = authorizationsResource
        .register(authorizationRegistration);

    assertEquals("D8DD848AC9C98D9EE44C5FB3A1E53913", registeredAuthorization.getCode());
    assertEquals("https://sandbox.pagseguro.uol.com.br/v2/authorization/request.jhtml?code=" +
                 "D8DD848AC9C98D9EE44C5FB3A1E53913", registeredAuthorization.getRedirectURL());
  }

  @Test
  public void shouldThrowsBadRequestOnRegister() throws Exception {
    try {
      String responseAsString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                                "<errors>" +
                                "<error>" +
                                "<code>11064</code>" +
                                "<message>" +
                                "redirectURL must have the same domain as applicationURL." +
                                "</message>" +
                                "</error>" +
                                "</errors>";
      HttpResponse response = new HttpResponse(400, responseAsString);
      when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
          any(HttpRequestBody.class))).thenReturn(response);

      authorizationsResource.register(authorizationRegistration);
    } catch (PagSeguroBadRequestException e) {
      ServerErrors errors = e.getErrors();
      ServerError error = errors.getErrors().iterator().next();
      assertEquals(new Integer(11064), error.getCode());
      assertEquals("redirectURL must have the same domain as applicationURL.", error.getMessage());
    } catch (Exception e) {
    }
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLibOnRegister() throws Exception {
    when(httpClient.execute(any(HttpMethod.class), anyString(), anyMap(),
        any(HttpRequestBody.class))).thenThrow(new IOException());
    authorizationsResource.register(authorizationRegistration);
  }
}