package br.com.uol.pagseguro.api.notification;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.Resource4Test;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationsResource;
import br.com.uol.pagseguro.api.application.authorization.search.AuthorizationDetail;
import br.com.uol.pagseguro.api.application.authorization.search.AuthorizationSearchResource;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.preapproval.PreApprovalsResource;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalDetail;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalSearchResource;
import br.com.uol.pagseguro.api.transaction.TransactionsResource;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionSearchResource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
public class NotificationsResourceTest extends Resource4Test {

  @Mock
  private HttpServletRequest httpServletRequest;

  @Mock
  private PagSeguroNotificationHandler handler;

  @Mock
  private PagSeguro pagSeguro;

  @Mock
  private TransactionDetail transaction;

  private TransactionsResource transactionsResource;

  private TransactionSearchResource transactionSearchResource;

  @Mock
  private AuthorizationDetail authorizationDetail;

  private AuthorizationsResource authorizationsResource;

  private AuthorizationSearchResource authorizationSearchResource;

  @Mock
  private PreApprovalDetail preApprovalDetail;

  private PreApprovalsResource preApprovalsResource;

  private PreApprovalSearchResource preApprovalSearchResource;

  private NotificationsResource notificationsResource;

  @Before
  public void setUp() throws Exception {
    transactionsResource = mock(TransactionsResource.class);
    transactionSearchResource = mock(TransactionSearchResource.class);

    authorizationsResource = mock(AuthorizationsResource.class);
    authorizationSearchResource = mock(AuthorizationSearchResource.class);

    preApprovalsResource = mock(PreApprovalsResource.class);
    preApprovalSearchResource = mock(PreApprovalSearchResource.class);

    notificationsResource = new NotificationsResource(pagSeguro, httpClient);

    when(pagSeguro.transactions()).thenReturn(transactionsResource);
    when(transactionsResource.search()).thenReturn(transactionSearchResource);
    when(transactionSearchResource.byNotificationCode(anyString())).thenReturn(transaction);

    when(pagSeguro.authorizations()).thenReturn(authorizationsResource);
    when(authorizationsResource.search()).thenReturn(authorizationSearchResource);
    when(authorizationSearchResource.byNotificationCode(anyString()))
        .thenReturn(authorizationDetail);

    when(pagSeguro.preApprovals()).thenReturn(preApprovalsResource);
    when(preApprovalsResource.search()).thenReturn(preApprovalSearchResource);
    when(preApprovalSearchResource.byNotificationCode(anyString())).thenReturn(preApprovalDetail);
  }

  @Test
  public void shouldHandlerTransactionNotification() throws Exception {
    when(httpServletRequest.getParameter("notificationCode")).thenReturn("code");
    when(httpServletRequest.getParameter("notificationType")).thenReturn("transaction");
    notificationsResource.handle(httpServletRequest, handler);
    InOrder inOrder = inOrder(httpServletRequest, handler);
    inOrder.verify(httpServletRequest, times(1)).getParameter("notificationCode");
    inOrder.verify(httpServletRequest, times(2)).getParameter("notificationType");
    inOrder.verify(handler, times(1)).handleTransactionNotification(any(TransactionDetail.class));
    inOrder.verify(handler, times(0)).handleAuthorizationNotification(any(AuthorizationDetail.class));
    inOrder.verify(handler, times(0)).handlePreApprovalNotification(any(PreApprovalDetail.class));
  }

  @Test
  public void shouldHandlerAuthorizationNotification() throws Exception {
    when(httpServletRequest.getParameter("notificationCode")).thenReturn("code");
    when(httpServletRequest.getParameter("notificationType")).thenReturn("applicationAuthorization");
    notificationsResource.handle(httpServletRequest, handler);
    InOrder inOrder = inOrder(httpServletRequest, handler);
    inOrder.verify(httpServletRequest, times(1)).getParameter("notificationCode");
    inOrder.verify(httpServletRequest, times(2)).getParameter("notificationType");
    inOrder.verify(handler, times(0)).handleTransactionNotification(any(TransactionDetail.class));
    inOrder.verify(handler, times(1)).handleAuthorizationNotification(any(AuthorizationDetail.class));
    inOrder.verify(handler, times(0)).handlePreApprovalNotification(any(PreApprovalDetail.class));
  }

  @Test
  public void shouldHandlerPreApprovalNotification() throws Exception {
    when(httpServletRequest.getParameter("notificationCode")).thenReturn("code");
    when(httpServletRequest.getParameter("notificationType")).thenReturn("preApproval");
    notificationsResource.handle(httpServletRequest, handler);
    InOrder inOrder = inOrder(httpServletRequest, handler);
    inOrder.verify(httpServletRequest, times(1)).getParameter("notificationCode");
    inOrder.verify(httpServletRequest, times(2)).getParameter("notificationType");
    inOrder.verify(handler, times(0)).handleTransactionNotification(any(TransactionDetail.class));
    inOrder.verify(handler, times(0)).handleAuthorizationNotification(any(AuthorizationDetail.class));
    inOrder.verify(handler, times(1)).handlePreApprovalNotification(any(PreApprovalDetail.class));
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowsErrorLib() throws Exception {
    when(httpServletRequest.getParameter("notificationCode")).thenReturn("code");
    when(httpServletRequest.getParameter("notificationType")).thenReturn("type");
    notificationsResource.handle(httpServletRequest, handler);
  }
}