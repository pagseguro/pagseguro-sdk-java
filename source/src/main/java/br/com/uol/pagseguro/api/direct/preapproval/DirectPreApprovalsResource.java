package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.Endpoints;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.HttpMethod;
import br.com.uol.pagseguro.api.http.HttpResponse;
import br.com.uol.pagseguro.api.utils.Builder;
import br.com.uol.pagseguro.api.utils.CharSet;
import br.com.uol.pagseguro.api.utils.RequestJson;
import br.com.uol.pagseguro.api.utils.RequestMap;
import br.com.uol.pagseguro.api.utils.logging.Log;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirectPreApprovalsResource {
    private static final Log LOGGER = LoggerFactory.getLogger(DirectPreApprovalsResource.class.getName());

    /** Used to register (create) a direct pre approval plan */
    private static final DirectPreApprovalRequestRegistrationJsonConverter DIRECT_PRE_APPROVAL_REQUEST_REGISTRATION_JC =
            new DirectPreApprovalRequestRegistrationJsonConverter();
    /** Used to accede to a direct pre approval plan */
    private static final DirectPreApprovalAccessionJsonConverter DIRECT_PRE_APPROVAL_REGISTRATION_JC =
            new DirectPreApprovalAccessionJsonConverter();
    /** Used to edit a direct pre approval plan */
    private static final DirectPreApprovalEditionJsonConvert DIRECT_PRE_APPROVAL_EDITION_JC =
            new DirectPreApprovalEditionJsonConvert();
    /** Used to give a discount in the next pre approval charge */
    private static final DirectPreApprovalDiscountJsonConvert DIRECT_PRE_APPROVAL_DISCOUNT_JC =
            new DirectPreApprovalDiscountJsonConvert();
    /** Used to charge a manual pre approval */
    private static final DirectPreApprovalChargeJsonConvert DIRECT_PRE_APPROVAL_CHARGE_JC =
            new DirectPreApprovalChargeJsonConvert();
    /** Used to edit a direct pre approval plan */
    private static final DirectPreApprovalChangingStatusJsonConvert DIRECT_PRE_APPROVAL_CHANGING_STATUS_JC =
            new DirectPreApprovalChangingStatusJsonConvert();
    /** Used to list direct pre approval payment orders */
    private static final DirectPreApprovalPaymentOrdersListMapConvert DIRECT_PRE_APPROVAL_PAYMENT_ORDERS_LIST_MC =
            new DirectPreApprovalPaymentOrdersListMapConvert();
    /** Used to change direct pre approval payment method */
    private static final DirectPreApprovalChangingPaymentMethodJsonConvert DIRECT_PRE_APPROVAL_CHANGING_PAYMENT_METHOD_JC =
            new DirectPreApprovalChangingPaymentMethodJsonConvert();
    /** Used to list direct pre approval by date interval */
    private static final DirectPreApprovalByDateIntervalListMapConvert DIRECT_PRE_APPROVAL_BY_DATE_INTERVAL_LIST_MC =
        new DirectPreApprovalByDateIntervalListMapConvert();
    /** Used to list direct pre approval by day interval */
    private static final DirectPreApprovalByDayIntervalListMapConvert DIRECT_PRE_APPROVAL_BY_DAY_INTERVAL_LIST_MC =
        new DirectPreApprovalByDayIntervalListMapConvert();

    private final PagSeguro pagSeguro;
    private final HttpClient httpClient;

    public DirectPreApprovalsResource(PagSeguro pagSeguro, HttpClient httpClient) {
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
    }

    /**
     * Pre Approval Accession, used to accede to a Direct Pre Approval Plan
     *
     * @param directPreApprovalAccessionBuilder Builder for Direct Pre Approval Accession
     * @return Response of direct pre approval accede
     * @see DirectPreApprovalAccession
     * @see AccededDirectPreApproval
     */
    public AccededDirectPreApproval accede(
            Builder<DirectPreApprovalAccession> directPreApprovalAccessionBuilder) {
        return accede(directPreApprovalAccessionBuilder.build());
    }

    /**
     * Direct Pre Approval Acession
     *
     * @param directPreApprovalAccession Direct Pre Approval
     * @return Response of direct pre approval
     * @see DirectPreApprovalAccession
     * @see AccededDirectPreApproval
     */
    public AccededDirectPreApproval accede(DirectPreApprovalAccession directPreApprovalAccession) {
        LOGGER.info("Iniciando adesao direct pre approval");
        LOGGER.info("Convertendo valores");
        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_REGISTRATION_JC.convert(directPreApprovalAccession);

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
            LOGGER.error("Erro ao executar adesao direct pre approval");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");
        AccedeDirectPreApprovalResponseXML registeredPreApproval = response.parseXMLContent(pagSeguro,
                AccedeDirectPreApprovalResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Adesao direct pre approval finalizada");
        return registeredPreApproval;
    }

    /**
     * Direct Pre Approval Edition
     *
     * @param directPreApprovalRequestEdition Builder for Direct Pre Approval Edition
     * @see DirectPreApprovalEdition
     */
    public void edit(Builder<DirectPreApprovalEdition> directPreApprovalRequestEdition) {
        edit(directPreApprovalRequestEdition.build());
    }

    /**
     * Direct Pre Approval Edition
     *
     * @param directPreApprovalEdition Direct Pre Approval Edition
     * @see DirectPreApprovalEdition
     */
    public void edit(DirectPreApprovalEdition directPreApprovalEdition) {
        LOGGER.info("Iniciando edicao direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_EDITION_JC.convert(directPreApprovalEdition);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));

            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_EDIT,
                    pagSeguro.getHost(), directPreApprovalEdition.getCode()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));

            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar edicao de valor direct pre approval");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        response.parseXMLContentNoBody(pagSeguro);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Edicao valor direct pre approval finalizado");
    }


    /**
     * Direct Pre Approval Discount
     *
     * @param directPreApprovalRequestDiscount Builder for Direct Pre Approval Discount
     * @see DirectPreApprovalDiscount
     */
    public void discount(Builder<DirectPreApprovalDiscount> directPreApprovalRequestDiscount) {
        discount(directPreApprovalRequestDiscount.build());
    }

    /**
     * Direct Pre Approval Discount
     *
     * @param directPreApprovalDiscount Direct Pre Approval discount
     * @see DirectPreApprovalDiscount
     */
    public void discount(DirectPreApprovalDiscount directPreApprovalDiscount) {
        LOGGER.info("Iniciando desconto direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_DISCOUNT_JC.convert(directPreApprovalDiscount);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));

            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_DISCOUNT,
                    pagSeguro.getHost(), directPreApprovalDiscount.getCode()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));

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

    /**
     * Direct Pre Approval Cancellation
     *
     * @param directPreApprovalRequestCancellation Builder for Direct Pre Approval Cancellation
     * @see DirectPreApprovalCancellation
     */
    public void cancel(Builder<DirectPreApprovalCancellation> directPreApprovalRequestCancellation) {
        cancel(directPreApprovalRequestCancellation.build());
    }

    /**
     * Direct Pre Approval Cancellation
     *
     * @param directPreApprovalCancellation Direct Pre Approval Cancellation
     * @see DirectPreApprovalCancellation
     */
    public void cancel(DirectPreApprovalCancellation directPreApprovalCancellation) {
        LOGGER.info("Iniciando cancelamento de adesao direct pre approval");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        final HttpResponse response;

        try {
            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_CANCEL,
                    pagSeguro.getHost(), directPreApprovalCancellation.getCode()), headers, null);

        } catch (IOException e) {
            LOGGER.error("Erro ao executar cancelamento de adesao direct pre approval");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        response.parseXMLContentNoBody(pagSeguro);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Cancelamento de adesao direct pre approval finalizado");
    }

    /**
     * Pre Approval Request Registration, used to create a Pre Approval Plan
     *
     * @param directPreApprovalRequestCharge Builder for Direct Pre Approval Registration
     * @return Response of pre approval registration
     * @see DirectPreApprovalRequestRegistration
     * @see RegisteredDirectPreApprovalRequest
     */
    public ChargedDirectPreApproval charge(Builder<DirectPreApprovalCharge> directPreApprovalRequestCharge) {
        return charge(directPreApprovalRequestCharge.build());
    }

    /**
     * Direct Pre Approval Edition
     *
     * @param directPreApprovalCharge Direct Pre Approval Edition
     * @return Response of pre approval charge
     * @see DirectPreApprovalEdition
     */
    public ChargedDirectPreApproval charge(DirectPreApprovalCharge directPreApprovalCharge) {
        LOGGER.info("Iniciando cobranca manual direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_CHARGE_JC.convert(directPreApprovalCharge);

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

    /**
     * Direct Pre Approval Changing Status
     *
     * @param directPreApprovalRequestChangingStatus Builder for Direct Pre Approval Changing Status
     * @see DirectPreApprovalChangingStatus
     */
    public void changeStatus(Builder<DirectPreApprovalChangingStatus> directPreApprovalRequestChangingStatus) {
        changeStatus(directPreApprovalRequestChangingStatus.build());
    }

    /**
     * Direct Pre Approval Changing Status
     *
     * @param directPreApprovalChangingStatus Direct Pre Approval ChangingStatus
     * @see DirectPreApprovalChangingStatus
     */
    public void changeStatus(DirectPreApprovalChangingStatus directPreApprovalChangingStatus) {
        LOGGER.info("Iniciando alteracao de status de adesao direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_CHANGING_STATUS_JC.convert(directPreApprovalChangingStatus);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));
            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_CHANGE_STATUS,
                    pagSeguro.getHost(), directPreApprovalChangingStatus.getCode()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar alteracao de status de adesao direct pre approval");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        response.parseXMLContentNoBody(pagSeguro);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Alteracao de status de adesao direct pre approval finalizado");
    }

    /**
     * Direct Pre Approval Payment Orders List, used to list Pre Approval payment orders
     *
     * @param directPreApprovalPaymentOrdersListBuilder Builder for Direct Pre Approval Payment Order Listing
     * @return Payment order list
     * @see DataList
     * @see PaymentOrder
     */
    public DataList<? extends PaymentOrder> listPaymentOrders(
            Builder<DirectPreApprovalPaymentOrdersList> directPreApprovalPaymentOrdersListBuilder) {
        return listPaymentOrders(directPreApprovalPaymentOrdersListBuilder.build());
    }

    /**
     * Direct Pre Approval Payment Orders List
     *
     * @param directPreApprovalPaymentOrdersList Direct Pre Approval Payment Order Listing
     * @return Response of Direct Pre Approval Payment Order Listing
     * @see DataList
     * @see PaymentOrder
     */
    public DataList<? extends PaymentOrder> listPaymentOrders(DirectPreApprovalPaymentOrdersList directPreApprovalPaymentOrdersList) {
        LOGGER.info("Iniciando lista de ordens de pagamento");
        LOGGER.info("Convertendo valores");
        final RequestMap map = DIRECT_PRE_APPROVAL_PAYMENT_ORDERS_LIST_MC.convert(directPreApprovalPaymentOrdersList);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            LOGGER.debug(String.format("Parametros: %s", map));
            response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.DIRECT_PRE_APPROVAL_PAYMENT_ORDERS,
                    pagSeguro.getHost(), directPreApprovalPaymentOrdersList.getCode(), map.toUrlEncode(CharSet.ENCODING_UTF)), headers, null);
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar lista de ordens de pagamento");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");

        DataList<? extends PaymentOrder> paymentOrder = response.parseXMLContent(pagSeguro, DirectPreApprovalPaymentOrdersListResponseXML.class);

        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Listagem de ordens de pagamento finalizada");

        return paymentOrder;
    }

    /**
     * Direct Pre Approval Search By Code
     *
     * @param directPreApprovalSearchByAccessionCode Builder for Direct Pre Approval Search By Accession Code
     * @see DirectPreApprovalSearchByAccessionCode
     * @return Searched Direct Pre Approval Data
     */
    public SearchedDirectPreApprovalByAccessionCode searchByAccessionCode(
            Builder<DirectPreApprovalSearchByAccessionCode> directPreApprovalSearchByAccessionCode) {
        return searchByAccessionCode(directPreApprovalSearchByAccessionCode.build());
    }

    /**
     * Direct Pre Approval Search By Accession Code
     *
     * @param directPreApprovalSearchByAccessionCode Direct Pre Approval Search By Accession Code
     * @see DirectPreApprovalSearchByAccessionCode
     * @return Searched Direct Pre Approval Data
     */
    public SearchedDirectPreApprovalByAccessionCode searchByAccessionCode(DirectPreApprovalSearchByAccessionCode directPreApprovalSearchByAccessionCode) {
        LOGGER.info("Iniciando consulta por codigo de adesao");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        final HttpResponse response;

        try {
            response = httpClient.executeJson(HttpMethod.GET, String.format(Endpoints.DIRECT_PRE_APPROVAL_SEARCH_BY_ACCESSION_CODE,
                    pagSeguro.getHost(), directPreApprovalSearchByAccessionCode.getCode()), headers, null);
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar consulta por codigo de adesao");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        SearchedDirectPreApprovalByAccessionCodeResponseXML searchedPreApproval = response.parseXMLContent(pagSeguro,
                SearchedDirectPreApprovalByAccessionCodeResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Consulta por codigo de adesao finalizada");

        return searchedPreApproval;
    }

    /**
     * Direct Pre Approval Search By Notification Code
     *
     * @param directPreApprovalSearchByNotificationCode Builder for Direct Pre Approval Search By Notification Code
     * @see DirectPreApprovalSearchByNotificationCode
     * @return Searched Direct Pre Approval Data
     */
    public SearchedDirectPreApprovalByNotificationCode searchByNotificationCode(
            Builder<DirectPreApprovalSearchByNotificationCode> directPreApprovalSearchByNotificationCode) {
        return searchByNotificationCode(directPreApprovalSearchByNotificationCode.build());
    }

    /**
     * Direct Pre Approval Search By Notification Code
     *
     * @param directPreApprovalSearchByNotificationCode Direct Pre Approval Search By Notification Code
     * @see DirectPreApprovalSearchByNotificationCode
     * @return Searched Direct Pre Approval Data
     */
    public SearchedDirectPreApprovalByNotificationCode searchByNotificationCode(DirectPreApprovalSearchByNotificationCode directPreApprovalSearchByNotificationCode) {
        LOGGER.info("Iniciando consulta por codigo de notificacao");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        final HttpResponse response;

        try {
            response = httpClient.executeJson(HttpMethod.GET, String.format(Endpoints.DIRECT_PRE_APPROVAL_SEARCH_BY_NOTIFICATION_CODE,
                    pagSeguro.getHost(), directPreApprovalSearchByNotificationCode.getCode()), headers, null);
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar consulta por codigo de notificacao");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        SearchedDirectPreApprovalByNotificationCodeResponseXML searchedPreApproval = response.parseXMLContent(pagSeguro,
                SearchedDirectPreApprovalByNotificationCodeResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Consulta por codigo de notificacao finalizada");

        return searchedPreApproval;
    }

    /**
     * Payment Retry, used to retry a payment of a direct pre approval order
     *
     * @param directPreApprovalPaymentRetryBuilder Builder for Direct Pre Approval Accession
     * @return Response of direct pre approval payment retry
     * @see DirectPreApprovalPaymentRetry
     * @see RetriedPayment
     */
    public RetriedPayment paymentRetry(
            Builder<DirectPreApprovalPaymentRetry> directPreApprovalPaymentRetryBuilder) {
        return paymentRetry(directPreApprovalPaymentRetryBuilder.build());
    }

    /**
     * Payment Retry, used to retry a payment of a direct pre approval order
     *
     * @param directPreApprovalPaymentRetry Direct Pre Approval
     * @return Response of direct pre approval payment retry
     * @see DirectPreApprovalPaymentRetry
     * @see RetriedPayment
     */
    public RetriedPayment paymentRetry(DirectPreApprovalPaymentRetry directPreApprovalPaymentRetry) {
        LOGGER.info("Iniciando retentativa de pagamento direct pre approval");
        LOGGER.info("Convertendo valores");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            response = httpClient.executeJson(HttpMethod.POST, String.format(Endpoints.DIRECT_PRE_APPROVAL_PAYMENT_ORDERS_PAYMENT_RETRY,
                    pagSeguro.getHost(),directPreApprovalPaymentRetry.getPreApprovalCode(), directPreApprovalPaymentRetry.getPaymentOrderCode()), headers, null);
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar adesao direct pre approval");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");
        RetriedPaymentResponseXML retriedPayment = response.parseXMLContent(pagSeguro,
                RetriedPaymentResponseXML.class);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Retantativa de pagamento direct pre approval finalizada");
        return retriedPayment;
    }

    /**
     * Direct Pre Approval Change Payment Method
     *
     * @param directPreApprovalChangingPaymentMethod Builder for Direct Pre Approval Change Payment Method
     * @see DirectPreApprovalChangingPaymentMethod
     */
    public void changePaymentMethod(Builder<DirectPreApprovalChangingPaymentMethod> directPreApprovalChangingPaymentMethod) {
        changePaymentMethod(directPreApprovalChangingPaymentMethod.build());
    }

    /**
     * Direct Pre Approval Change Payment Method
     *
     * @param directPreApprovalChangingPaymentMethod Direct Pre Approval Edition
     * @see DirectPreApprovalChangingPaymentMethod
     */
    public void changePaymentMethod(DirectPreApprovalChangingPaymentMethod directPreApprovalChangingPaymentMethod) {
        LOGGER.info("Iniciando mudanca de meio de pagamento direct pre approval");
        LOGGER.info("Convertendo valores");

        final RequestJson jsonBody = DIRECT_PRE_APPROVAL_CHANGING_PAYMENT_METHOD_JC.convert(directPreApprovalChangingPaymentMethod);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;

        try {
            LOGGER.debug(String.format("Parametros: %s", jsonBody));

            response = httpClient.executeJson(HttpMethod.PUT, String.format(Endpoints.DIRECT_PRE_APPROVAL_CHANGE_PAYMENT_METHOD,
                    pagSeguro.getHost(), directPreApprovalChangingPaymentMethod.getPreApprovalCode()), headers, jsonBody.toHttpJsonRequestBody(CharSet.ENCODING_ISO));

            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao executar mudanca de meio de pagamento direct pre approval");
            throw new PagSeguroLibException(e);
        }

        LOGGER.info("Parseando XML de resposta");
        response.parseXMLContentNoBody(pagSeguro);
        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Mudanca de meio de pagamento direct pre approval finalizado");
    }

    /**
     * Direct Pre Approval By Date Interval List
     *
     * @param directPreApprovalByDateIntervalListBuilder Builder for Direct Pre Approval By Date Interval Listing
     * @return Direct Pre Approval By Date Interval List
     * @see DataList
     * @see DirectPreApprovalData
     */
    public DataList<? extends DirectPreApprovalData> listDirectPreApprovalByDateInterval(
        Builder<DirectPreApprovalByDateIntervalList> directPreApprovalByDateIntervalListBuilder) {
        return listDirectPreApprovalByDateInterval(directPreApprovalByDateIntervalListBuilder.build());
    }

    /**
     * Direct Pre Approval By Date Interval List
     *
     * @param directPreApprovalByDateIntervalList Direct Pre Approval By Date Interval Listing
     * @return Response of Direct Pre Approval By Date Interval Listing
     * @see DataList
     * @see DirectPreApprovalData
     */
    public DataList<? extends DirectPreApprovalData> listDirectPreApprovalByDateInterval(DirectPreApprovalByDateIntervalList directPreApprovalByDateIntervalList) {
        LOGGER.info("Iniciando lista de recorrencias dentro do intervalo de datas");
        LOGGER.info("Convertendo valores");
        final RequestMap map = DIRECT_PRE_APPROVAL_BY_DATE_INTERVAL_LIST_MC.convert(directPreApprovalByDateIntervalList);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            LOGGER.debug(String.format("Parametros: %s", map));
            response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.DIRECT_PRE_APPROVAL_SEARCH_BY_DATE_INTERVAL,
                pagSeguro.getHost(), map.toUrlEncode(CharSet.ENCODING_UTF)), headers, null);
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao pesquisar pelas reconrrencias dentro de um intervalo de datas");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");

        DataList<? extends DirectPreApprovalData> directPreApprovalData = response.parseXMLContent(pagSeguro, DirectPreApprovalByDateIntervalListResponseXML.class);

        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Listagem de recorrencias por intervalo de datas finalizada");

        return directPreApprovalData;
    }

    /**
     * Direct Pre Approval By Day Interval List
     *
     * @param directPreApprovalByDayIntervalListBuilder Builder for Direct Pre Approval By Day Interval Listing
     * @return Direct Pre Approval By Day Interval List
     * @see DataList
     * @see DirectPreApprovalData
     */
    public DataList<? extends DirectPreApprovalData> listDirectPreApprovalByDayInterval(
        Builder<DirectPreApprovalByDayIntervalList> directPreApprovalByDayIntervalListBuilder) {
        return listDirectPreApprovalByDayInterval(directPreApprovalByDayIntervalListBuilder.build());
    }

    /**
     * Direct Pre Approval By Day Interval List
     *
     * @param directPreApprovalByDayIntervalList Direct Pre Approval By Day Interval Listing
     * @return Response of Direct Pre Approval By Day Interval Listing
     * @see DataList
     * @see DirectPreApprovalData
     */
    public DataList<? extends DirectPreApprovalData> listDirectPreApprovalByDayInterval(DirectPreApprovalByDayIntervalList directPreApprovalByDayIntervalList) {
        LOGGER.info("Iniciando lista de recorrencias dentro do intervalo de dias");
        LOGGER.info("Convertendo valores");
        final RequestMap map = DIRECT_PRE_APPROVAL_BY_DAY_INTERVAL_LIST_MC.convert(directPreApprovalByDayIntervalList);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");

        LOGGER.info("Valores convertidos");
        final HttpResponse response;
        try {
            LOGGER.debug(String.format("Parametros: %s", map));
            response = httpClient.execute(HttpMethod.GET, String.format(Endpoints.DIRECT_PRE_APPROVAL_SEARCH_BY_DAY_INTERVAL,
                pagSeguro.getHost(), map.toUrlEncode(CharSet.ENCODING_UTF)), headers, null);
            LOGGER.debug(String.format("Resposta: %s", response.toString()));
        } catch (IOException e) {
            LOGGER.error("Erro ao pesquisar pelas reconrrencias dentro de um intervalo de dias");
            throw new PagSeguroLibException(e);
        }
        LOGGER.info("Parseando XML de resposta");

        DataList<? extends DirectPreApprovalData> directPreApprovalData = response.parseXMLContent(pagSeguro, DirectPreApprovalByDayIntervalListResponseXML.class);

        LOGGER.info("Parseamento finalizado");
        LOGGER.info("Listagem de recorrencias por intervalo de dias finalizada");

        return directPreApprovalData;
    }
}
