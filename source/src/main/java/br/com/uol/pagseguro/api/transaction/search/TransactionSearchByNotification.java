package br.com.uol.pagseguro.api.transaction.search;

import java.io.IOException;
import java.util.Map;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.PagSeguroCommand;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Search transaction by notification code
 *
 * @author PagSeguro Internet Ltda.
 * @see PagSeguroCommand
 * @see TransactionDetail
 */
class TransactionSearchByNotification implements PagSeguroCommand<TransactionDetail> {

  private static final Log LOGGER = LoggerFactory.getLogger(TransactionSearchByNotification.class);

  private final String notificationCode;

  /**
   * Constructor
   *
   * @param notificationCode Notification code
   */
  TransactionSearchByNotification(String notificationCode) {
    this.notificationCode = notificationCode;
  }

  /**
   * Execute Search Transaction by notification code
   *
   * @param pagseguro  Pagseguro instance
   * @param httpClient Http Client
   * @return Transaction Detail
   * @see PagSeguroCommand#execute(PagSeguro, HttpClient)
   * @see HttpClient#execute(HttpMethod, String, Map, HttpRequestBody)
   * @see TransactionDetail
   * @see PagSeguro
   */
  @Override
  public TransactionDetail execute(PagSeguro pagseguro, HttpClient httpClient) {
    LOGGER.info("Iniciando busca de transacao por codigo de notificacao");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: notificationCode:%s", notificationCode));
      response = httpClient.execute(HttpMethod.GET,
          String.format(Endpoints.TRANSACTION_SEARCH_BY_NOTIFICATION_CODE, pagseguro.getHost(),
              notificationCode), null, null);
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar busca de transacao por codigo de notificacao");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    TransactionDetail transaction = response.parseXMLContent(pagseguro, TransactionDetailXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Busca de transacao por codigo de notificacao finalizada");
    return transaction;
  }
}
