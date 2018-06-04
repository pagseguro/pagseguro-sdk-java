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
//@TODO revert the name to DirectPreApprovalsResource, because it will be used for preapproval plan, accession, cancel, charge, etc
public class DirectPreApprovalsRequestResource {
    private static final Log LOGGER = LoggerFactory.getLogger(DirectPreApprovalsRequestResource.class.getName());
    private static final DirectPreApprovalRequestRegistrationJsonConverter DIRECT_PRE_APPROVAL_REQUEST_REGISTRATION_JC =
            new DirectPreApprovalRequestRegistrationJsonConverter();

    private static final DirectPreApprovalRegistrationJsonConverter DIRECT_PRE_APPROVAL_REGISTRATION_JC =
            new DirectPreApprovalRegistrationJsonConverter();

    private static final DirectPreApprovalRequestEditionJsonConvert DIRECT_PRE_APPROVAL_EDITION_JC =
            new DirectPreApprovalRequestEditionJsonConvert();

    private static final DirectPreApprovalRequestDiscountJsonConvert DIRECT_PRE_APPROVAL_DISCOUNT_JC =
            new DirectPreApprovalRequestDiscountJsonConvert();

    private static final DirectPreApprovalRequestChargeJsonConvert DIRECT_PRE_APPROVAL_CHARGE_JC =
            new DirectPreApprovalRequestChargeJsonConvert();

//    @TODO add here charge method
//    private static final PreApprovalChargingV2MapConverter PRE_APPROVAL_CHARGING_MC =
//            new PreApprovalChargingV2MapConverter();
//    @TODO add here cancel method
//    private static final PreApprovalCancellationV2MapConverter PRE_APPROVAL_CANCELLATION_MC =
//            new PreApprovalCancellationV2MapConverter();

    private final PagSeguro pagSeguro;
    private final HttpClient httpClient;

    public DirectPreApprovalsRequestResource(PagSeguro pagSeguro, HttpClient httpClient) {
        this.pagSeguro = pagSeguro;
        this.httpClient = httpClient;
    }

    /**
     * Pre Approval Request Registration, used to create a Pre Approval Plan
     *
     * @param directPreApprovalRequestRegistrationBuilder Builder for Direct Pre Approval Registration
     * @return Response of pre approval registration
     * @see DirectPreApprovalRequestRegistration
     * @see RegisteredDirectPreApprovalRequest
     */
    public RegisteredDirectPreApprovalRequest register(
            Builder<DirectPreApprovalRequestRegistration> directPreApprovalRequestRegistrationBuilder) {
        return register(directPreApprovalRequestRegistrationBuilder.build());
    }

    /**
     * Pre Approval Registration
     *
     * @param directPreApprovalRequestRegistration Direct Pre Approval Registration
     * @return Response of direct pre approval registration
     * @see DirectPreApprovalRequestRegistration
     * @see RegisteredDirectPreApprovalRequest
     */
    public RegisteredDirectPreApprovalRequest register(DirectPreApprovalRequestRegistration directPreApprovalRequestRegistration) {
        LOGGER.info("Iniciando registro direct pre approval");
        LOGGER.info("Convertendo valores");
        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_REQUEST_REGISTRATION_JC.convert(directPreApprovalRequestRegistration);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));
            response = httpClient.executeJson(HttpMethod.POST, String.format(Endpoints.DIRECT_PRE_APPROVAL_REQUEST,
                    pagSeguro.getHost()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar registro direct pre approval");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");
        RegisterDirectPreApprovalRequestResponseXML registeredPreApproval = response.parseXMLContent(pagSeguro,
                RegisterDirectPreApprovalRequestResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Registro direct pre approval finalizado");
        return registeredPreApproval;
        //return null;
    }

    /**
     * Pre Approval Accession Registration, used to do an accession to a Direct Pre Approval Plan
     *
     * @param directPreApprovalRegistrationBuilder Builder for Direct Pre Approval Accession Registration
     * @return Response of direct pre approval accession registration
     * @see DirectPreApprovalRegistration
     * @see RegisteredDirectPreApproval
     */
    public RegisteredDirectPreApproval accession(
            Builder<DirectPreApprovalRegistration> directPreApprovalRegistrationBuilder) {
        return accession(directPreApprovalRegistrationBuilder.build());
    }

    /**
     * Direct Pre Approval Acession Registration
     *
     * @param directPreApprovalRegistration Direct Pre Approval Registration
     * @return Response of direct pre approval registration
     * @see DirectPreApprovalRegistration
     * @see RegisteredDirectPreApproval
     */
    public RegisteredDirectPreApproval accession(DirectPreApprovalRegistration directPreApprovalRegistration) {
        LOGGER.info("Iniciando registro direct pre approval accession");
        LOGGER.info("Convertendo valores");
        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_REGISTRATION_JC.convert(directPreApprovalRegistration);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));
            response = httpClient.executeJson(HttpMethod.POST, String.format(Endpoints.DIRECT_PRE_APPROVAL,
                    pagSeguro.getHost()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar registro direct pre approval acession");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");
        RegisterDirectPreApprovalResponseXML registeredPreApproval = response.parseXMLContent(pagSeguro,
                RegisterDirectPreApprovalResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Registro direct pre approval accession finalizado");
        return registeredPreApproval;
    }

    /**
     * Direct Pre Approval Edition
     *
     * @param directPreApprovalRequestEdition Builder for Direct Pre Approval Edition
     * @see DirectPreApprovalRequestEdition
     */
    public void edit(Builder<DirectPreApprovalRequestEdition> directPreApprovalRequestEdition) {
        edit(directPreApprovalRequestEdition.build());
    }

    /**
     * Direct Pre Approval Edition
     *
     * @param directPreApprovalRequestEdition Direct Pre Approval Edition
     * @see DirectPreApprovalRequestEdition
     */
    public void edit(DirectPreApprovalRequestEdition directPreApprovalRequestEdition) {
        LOGGER.info("Iniciando edicao direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_EDITION_JC.convert(directPreApprovalRequestEdition);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));

            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_EDIT,
                    pagSeguro.getHost(), directPreApprovalRequestEdition.getCode()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));

            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar edicao de valor pre approval");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        response.parseXMLContentNoBody(pagSeguro);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Edicao valor pre approval finalizado");
    }


    /**
     * Direct Pre Approval Discount
     *
     * @param directPreApprovalRequestDiscount Builder for Direct Pre Approval Discount
     * @see DirectPreApprovalRequestDiscount
     */
    public void discount(Builder<DirectPreApprovalRequestDiscount> directPreApprovalRequestDiscount) {
        discount(directPreApprovalRequestDiscount.build());
    }

    /**
     * Direct Pre Approval Discount
     *
     * @param directPreApprovalRequestDiscount Direct Pre Approval discount
     * @see DirectPreApprovalRequestDiscount
     */
    public void discount(DirectPreApprovalRequestDiscount directPreApprovalRequestDiscount) {
        LOGGER.info("Iniciando discount direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_DISCOUNT_JC.convert(directPreApprovalRequestDiscount);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));

            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_DISCOUNT,
                    pagSeguro.getHost(), directPreApprovalRequestDiscount.getCode()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));

            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar desconto no pagamento");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        response.parseXMLContentNoBody(pagSeguro);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Desconto no pagamento finalizado");
    }





    //_______________________________________________________________________

    /**
     * Pre Approval Request Registration, used to create a Pre Approval Plan
     *
     * @param directPreApprovalRequestCharge Builder for Direct Pre Approval Registration
     * @return Response of pre approval registration
     * @see DirectPreApprovalRequestRegistration
     * @see RegisteredDirectPreApprovalRequest
     */
    public ChargedDirectPreApproval charge(Builder<DirectPreApprovalRequestCharge> directPreApprovalRequestCharge) {
        return charge(directPreApprovalRequestCharge.build());
    }

    /**
     * Direct Pre Approval Edition
     *
     * @param directPreApprovalRequestCharge Direct Pre Approval Edition
     * @see DirectPreApprovalRequestEdition
     */
    public ChargedDirectPreApproval charge(DirectPreApprovalRequestCharge directPreApprovalRequestCharge) {
        LOGGER.info("Iniciando cobranca manual direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_CHARGE_JC.convert(directPreApprovalRequestCharge);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));

            response = httpClient.executeJson(HttpMethod.POST, String.format(Endpoints.DIRECT_PRE_APPROVAL_CHARGE,
                    pagSeguro.getHost()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));

            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar cobranca");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        ChargeDirectPreApprovalResponseXML chargedPreApproval = response.parseXMLContent(pagSeguro,
                ChargeDirectPreApprovalResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Cobranca finalizada");
        return chargedPreApproval;
    }





//    @TODO add here cancel method
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
//    @TODO add here cancel method
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
//    @TODO add here search method
//    /**
//     * Pre Approval Search
//     *
//     * @return Factory to pre approval search
//     * @see PreApprovalSearchResource
//     */
//    public PreApprovalSearchResource search() {
//        return new PreApprovalSearchResource(pagSeguro, httpClient);
//    }
//    @TODO add here charge method
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
//    @TODO add here charge method
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
