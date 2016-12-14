package br.com.uol.pagseguro.api.transaction.search;

import java.io.IOException;
import java.util.Map;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpRequestBody;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.PagSeguroCommand;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

/**
 * Search transactions by date range
 *
 * @author PagSeguro Internet Ltda.
 * @see PagSeguroCommand
 * @see DataList
 * @see TransactionSummary
 */
class TransactionSearchByDateRange implements PagSeguroCommand<DataList<? extends
    TransactionSummary>> {

  private static final Log LOGGER = LoggerFactory.getLogger(TransactionSearchByDateRange.class);

  private static final TransactionSearchV2MapConverter TRANSACTION_SEARCH_MC =
      new TransactionSearchV2MapConverter();

  private final TransactionSearch transactionSearch;

  /**
   * Constructor
   *
   * @param transactionSearch Interface for Transaction Search
   */
  TransactionSearchByDateRange(TransactionSearch transactionSearch) {
    this.transactionSearch = transactionSearch;
  }

  /**
   * Execute Search Transactions by date range
   *
   * @param pagseguro  Pagseguro instance
   * @param httpClient Http Client
   * @return Transactions List
   * @see DataList
   * @see PagSeguroCommand#execute(PagSeguro, HttpClient)
   * @see HttpClient#execute(HttpMethod, String, Map, HttpRequestBody)
   */
  @Override
  public DataList<? extends TransactionSummary> execute(PagSeguro pagseguro,
                                                        HttpClient httpClient) {
    LOGGER.info("Iniciando busca de transacao por intervalo de data");
    LOGGER.info("Convertendo valores");
    final RequestMap map = TRANSACTION_SEARCH_MC.convert(transactionSearch);
    LOGGER.info("Valores convertidos");
    final HttpResponse response;
    try {
      LOGGER.debug(String.format("Parametros: %s", map));
      response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.TRANSACTION_SEARCH,
          pagseguro.getHost(), map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
      LOGGER.debug(String.format("Resposta: %s", response.toString()));
    } catch (IOException e) {
      LOGGER.error("Erro ao executar busca de transacao por intervalo de data");
      throw new PagSeguroLibException(e);
    }
    LOGGER.info("Parseando XML de resposta");
    DataList<? extends TransactionSummary> transactionsSummary = response.parseXMLContent(pagseguro,
        TransactionSearchResponseXML.class);
    LOGGER.info("Parseamento finalizado");
    LOGGER.info("Busca de transacao por intervalo de data finalizada");
    return transactionsSummary;
  }
}
