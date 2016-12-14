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

package br.com.uol.pagseguro.api.notification;

import javax.servlet.http.HttpServletRequest;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Factory to notifications
 *
 * @author PagSeguro Internet Ltda.
 */
public class NotificationsResource {

  private static Log LOGGER = LoggerFactory.getLogger(NotificationsResource.class);

  private final PagSeguro pagSeguro;

  private final HttpClient httpClient;

  public NotificationsResource(PagSeguro pagSeguro, HttpClient httpClient) {
    this.pagSeguro = pagSeguro;
    this.httpClient = httpClient;
  }

  /**
   * Handle notifications
   *
   * @param request Http Servlet Request
   * @param handle  Notification handle
   */
  public void handle(HttpServletRequest request, PagSeguroNotificationHandler handle) {
    LOGGER.info("Iniciando handler de notificacoes");
    if (request.getParameter("notificationCode").isEmpty()
        || request.getParameter("notificationType").isEmpty()) {
      throw new PagSeguroLibException(new IllegalArgumentException("Notification code or " +
          "notification type not exists"));
    }
    NotificationType notificationType =
        NotificationType.fromName(request.getParameter("notificationType"));
    switch (notificationType) {
      case TRANSACTION:
        LOGGER.info("Notificacao de transacao");
        handle.handleTransactionNotification(pagSeguro.transactions().search()
            .byNotificationCode(request.getParameter("notificationCode")));
        break;
      case APPLICATION_AUTHORIZATION:
        LOGGER.info("Notificacao de autorizcao");
        handle.handleAuthorizationNotification(pagSeguro.authorizations().search()
            .byNotificationCode(request.getParameter("notificationCode")));
        break;
      case PRE_APPROVAL:
        LOGGER.info("Notificacao de assinatura");
        handle.handlePreApprovalNotification(pagSeguro.preApprovals().search()
            .byNotificationCode(request.getParameter("notificationCode")));
        break;
      default:
        throw new PagSeguroLibException(new IllegalArgumentException("Notification not exists"));
    }
    LOGGER.info("Handler de notificacoes finalizado");
  }

}
