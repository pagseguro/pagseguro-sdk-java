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
package br.com.uol.pagseguro.api;

import br.com.uol.pagseguro.api.application.authorization.AuthorizationsResource;
import br.com.uol.pagseguro.api.checkout.CheckoutsResource;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.credential.DefaultCredentialProviderChain;
import br.com.uol.pagseguro.api.environment.DefaultEnvironmentProviderChain;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;
import br.com.uol.pagseguro.api.http.AuthenticatedHttpClient;
import br.com.uol.pagseguro.api.http.HttpClient;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.installment.InstallmentsListResource;
import br.com.uol.pagseguro.api.notification.NotificationsResource;
import br.com.uol.pagseguro.api.preapproval.PreApprovalsResource;
import br.com.uol.pagseguro.api.session.SessionResource;
import br.com.uol.pagseguro.api.transaction.TransactionsResource;
import br.com.uol.pagseguro.api.utils.logging.LoggerFactory;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

/**
 * Class responsible for retrieving the factories of the lib services and by defining the
 * environment and host that should be used to lib
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class PagSeguro {

  private final HttpClient httpClient;

  /**
   * Constructor
   *
   * @param httpClient Http Client
   * @param credential Credential
   */
  public PagSeguro(HttpClient httpClient, Credential credential) {
    this.httpClient = new AuthenticatedHttpClient(httpClient, credential);
  }

  /**
   * Get factory to checkout
   *
   * @return Factory to checkout
   */
  public CheckoutsResource checkouts() {
    return new CheckoutsResource(this, httpClient);
  }

  /**
   * Get factory to transactions
   *
   * @return Factory to transactions
   */
  public TransactionsResource transactions() {
    return new TransactionsResource(this, httpClient);
  }

  /**
   * Get factory to authorizations
   *
   * @return Factory to authorizations
   */
  public AuthorizationsResource authorizations() {
    return new AuthorizationsResource(this, httpClient);
  }

  /**
   * Get factory to pre approval
   *
   * @return Factory to pre approval
   */
  public PreApprovalsResource preApprovals() {
    return new PreApprovalsResource(this, httpClient);
  }

  /**
   * Get factory to pre approval
   *
   * @return Factory to pre approval
   */
  public SessionResource sessions() {
    return new SessionResource(this, httpClient);
  }

  /**
   * Get factory to installments list
   *
   * @return Factory to installments list
   */
  public InstallmentsListResource installments() {
    return new InstallmentsListResource(this, httpClient);
  }

  /**
   * Get factory to notifications
   *
   * @return Factory to notifications
   */
  public NotificationsResource notifications() {
    return new NotificationsResource(this, httpClient);
  }

  /**
   * Get production environment
   *
   * @param httpClient Http Client
   * @param credential Credential
   * @return Production environment
   */
  private static PagSeguro production(HttpClient httpClient, Credential credential) {
    return new PagSeguroProdEnv(httpClient, credential);
  }

  /**
   * Get sandbox environment
   *
   * @param httpClient Http Client
   * @param credential Credential
   * @return Sandbox environment
   */
  private static PagSeguro sandbox(HttpClient httpClient, Credential credential) {
    return new PagSeguroSandboxEnv(httpClient, credential);
  }

  /**
   * Get host of api
   *
   * @return Host
   */
  public abstract String getHost();

  /**
   * Get redirect host of api
   *
   * @return Redirect host
   */
  public abstract String getHostRedirect();

  /**
   * Construct instance of Pagseguro
   *
   * @return Pagseguro instance built
   */
  public static PagSeguro instance() {
    return instance(new SimpleLoggerFactory(), new JSEHttpClient(),
        new DefaultCredentialProviderChain().getCredential(), new DefaultEnvironmentProviderChain()
            .getEnvironment());
  }

  /**
   * Construct instance of Pagseguro
   *
   * @param httpClient Http Client
   * @return Pagseguro instance built
   */
  public static PagSeguro instance(HttpClient httpClient) {
    return instance(new SimpleLoggerFactory(), httpClient, new DefaultCredentialProviderChain()
        .getCredential(), new DefaultEnvironmentProviderChain().getEnvironment());
  }

  /**
   * Construct instance of Pagseguro
   *
   * @param credential  Credential
   * @param environment Environment
   * @return Pagseguro instance built
   */
  public static PagSeguro instance(Credential credential, PagSeguroEnv environment) {
    return instance(new SimpleLoggerFactory(), new JSEHttpClient(), credential, environment);
  }

  /**
   * Construct instance of Pagseguro
   *
   * @param loggerFactory logger Factory
   * @param httpClient    Http Client
   * @param credential    Credential
   * @param environment   Environment
   * @return Pagseguro instance built
   */
  public static PagSeguro instance(LoggerFactory loggerFactory, HttpClient httpClient,
                                   Credential credential, PagSeguroEnv environment) {
    LoggerFactory.configureLoggerFactory(loggerFactory);
    switch (environment) {
      case PRODUCTION:
        return production(httpClient, credential);
      case SANDBOX:
        return sandbox(httpClient, credential);
      default:
        throw new PagSeguroLibException(new IllegalArgumentException("Environment not exists"));
    }
  }
}
