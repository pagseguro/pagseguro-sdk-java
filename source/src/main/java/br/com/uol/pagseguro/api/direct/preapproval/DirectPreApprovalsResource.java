package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.Builder;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestJson;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirectPreApprovalsResource {
    private static final Log LOGGER = LoggerFactory.getLogger(DirectPreApprovalsResource.class.getName());

//    private static final DirectPreApprovalRegistrationV2MapConverter DIRECT_PRE_APPROVAL_REGISTRATION_MC =
//            new DirectPreApprovalRegistrationV2MapConverter();

    private static final DirectPreApprovalRegistrationJsonConverter DIRECT_PRE_APPROVAL_REGISTRATION_JC =
            new DirectPreApprovalRegistrationJsonConverter();
//    private static final PreApprovalChargingV2MapConverter PRE_APPROVAL_CHARGING_MC =
//            new PreApprovalChargingV2MapConverter();
//
//    private static final PreApprovalCancellationV2MapConverter PRE_APPROVAL_CANCELLATION_MC =
//            new PreApprovalCancellationV2MapConverter();

    private final PagSeguro pagSeguro;
    private final HttpClient httpClient;

    public DirectPreApprovalsResource(PagSeguro pagSeguro, HttpClient httpClient) {
        this.pagSeguro = pagSeguro;
        this.httpClient = httpClient;
    }

    /**
     * Pre Approval Registration
     *
     * @param directPreApprovalRegistrationBuilder Builder for Pre Approval Registration
     * @return Response of pre approval registration
     * @see DirectPreApprovalRegistration
     * @see RegisteredDirectPreApproval
     */
    public RegisteredDirectPreApproval register(
            Builder<DirectPreApprovalRegistration> directPreApprovalRegistrationBuilder) {
        return register(directPreApprovalRegistrationBuilder.build());
    }

    /**
     * Pre Approval Registration
     *
     * @param directPreApprovalRegistration Direct Pre Approval Registration
     * @return Response of direct pre approval registration
     * @see DirectPreApprovalRegistration
     * @see RegisteredDirectPreApproval
     */
    public RegisteredDirectPreApproval register(DirectPreApprovalRegistration directPreApprovalRegistration) {
        LOGGER.info("Iniciando registro pre approval");
        LOGGER.info("Convertendo valores");
        //final RequestMap map = DIRECT_PRE_APPROVAL_REGISTRATION_MC.convert(preApprovalRegistration);
        final RequestJson map = DIRECT_PRE_APPROVAL_REGISTRATION_JC.convert(directPreApprovalRegistration);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            LOGGER.debug(String.format("Parametros: %s", map.jsonBody()));
            //@TODO change to executeJson
            response = httpClient.executeJson(HttpMethod.POST, String.format(Endpoints.DIRECT_PRE_APPROVAL_REQUEST,
                    pagSeguro.getHost()), headers, map.toHttpJsonRequestBody(CharSet.ENCODING_ISO));
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar registro pre approval");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");
        RegisterDirectPreApprovalResponseXML registeredPreApproval = response.parseXMLContent(pagSeguro,
                RegisterDirectPreApprovalResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Registro pre approval finalizado");
        return registeredPreApproval;
        //return null;
    }
//
//    /**
//     * Pre Approval Cancellation
//     *
//     * @param preApprovalCancellationBuilder Builder for Pre Approval Cancellation
//     * @return Response of Pre Approval Cancellation
//     * @see PreApprovalCancellation
//     * @see CancelledPreApproval
//     */
//    public CancelledPreApproval cancel(
//            Builder<PreApprovalCancellation> preApprovalCancellationBuilder) {
//        return cancel(preApprovalCancellationBuilder.build());
//    }
//
//    /**
//     * Pre Approval Cancellation
//     *
//     * @param preApprovalCancellation Pre Approval Cancellation
//     * @return Response of Pre Approval Cancellation
//     * @see PreApprovalCancellation
//     * @see CancelledPreApproval
//     */
//    public CancelledPreApproval cancel(PreApprovalCancellation preApprovalCancellation) {
//        LOGGER.info("Iniciando cancelamento pre approval");
//        LOGGER.info("Convertendo valores");
//        final RequestMap map = PRE_APPROVAL_CANCELLATION_MC.convert(preApprovalCancellation);
//        LOGGER.info("Valores convertidos");
//        final HttpResponse response;
//        try {
//            LOGGER.debug(String.format("Parametros: preApprovalCode:%s, %s",
//                    preApprovalCancellation.getCode(), map));
//            response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.PRE_APPROVAL_CANCEL,
//                    pagSeguro.getHost(), preApprovalCancellation.getCode(),
//                    map.toUrlEncode(CharSet.ENCODING_UTF)), null, null);
//            LOGGER.info(String.format("Resposta: %s", response.toString()));
//        } catch (IOException e) {
//            LOGGER.error("Erro ao executar cancelamento pre approval");
//            throw new PagSeguroLibException(e);
//        }
//        LOGGER.info("Parseando XML de resposta");
//        CancelPreApprovalResponseXML cancelledPreApproval = response.parseXMLContent(pagSeguro,
//                CancelPreApprovalResponseXML.class);
//        LOGGER.info("Parseamento finalizado");
//        LOGGER.info("Cancelamento pre approval finalizado");
//        return cancelledPreApproval;
//    }
//
//    /**
//     * Pre Approval Search
//     *
//     * @return Factory to pre approval search
//     * @see PreApprovalSearchResource
//     */
//    public PreApprovalSearchResource search() {
//        return new PreApprovalSearchResource(pagSeguro, httpClient);
//    }
//
//    /**
//     * Pre Approval Charging
//     *
//     * @param preApprovalChargingBuilder Builder for Pre Approval Charging
//     * @return Response of Pre Approval Charging
//     * @see PreApprovalCharging
//     * @see ChargedPreApproval
//     */
//    public ChargedPreApproval charge(Builder<PreApprovalCharging> preApprovalChargingBuilder) {
//        return charge(preApprovalChargingBuilder.build());
//    }
//
//    /**
//     * Pre Approval Charging
//     *
//     * @param preApprovalCharging Pre Approval Charging
//     * @return Response of Pre Approval Charging
//     * @see PreApprovalCharging
//     * @see ChargedPreApproval
//     */
//    public ChargedPreApproval charge(PreApprovalCharging preApprovalCharging) {
//        LOGGER.info("Iniciando cobranca");
//        LOGGER.info("Convertendo valores");
//        final RequestMap map = PRE_APPROVAL_CHARGING_MC.convert(preApprovalCharging);
//        LOGGER.info("Valores convertidos");
//        final HttpResponse response;
//        try {
//            LOGGER.debug(String.format("Parametros: %s", map));
//            response = httpClient.execute(HttpMethod.POST, String.format(Endpoints.PRE_APPROVAL_CHARGE,
//                    pagSeguro.getHost()), null, map.toHttpRequestBody(CharSet.ENCODING_ISO));
//            LOGGER.debug(String.format("Resposta: %s", response.toString()));
//        } catch (IOException e) {
//            LOGGER.error("Erro ao executar cobranca");
//            throw new PagSeguroLibException(e);
//        }
//        LOGGER.info("Parseando XML de resposta");
//        ChargePreApprovalResponseXML chargedPreApproval = response.parseXMLContent(pagSeguro,
//                ChargePreApprovalResponseXML.class);
//        LOGGER.info("Parseamento finalizado");
//        LOGGER.info("Cobranca finalizada");
//        return chargedPreApproval;
//    }
}
