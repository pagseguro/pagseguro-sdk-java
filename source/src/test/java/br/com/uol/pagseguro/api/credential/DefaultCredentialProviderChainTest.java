package br.com.uol.pagseguro.api.credential;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.uol.pagseguro.api.Case4Test;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DefaultCredentialProviderChain.class})
public class DefaultCredentialProviderChainTest extends Case4Test {

  private JVMEnvVariableCredentialProvider jvmEnvVariableCredentialProvider;

  private SystemPropCredentialProvider systemPropCredentialProvider;

  private SystemEnvVariableCredentialProvider systemEnvVariableCredentialProvider;

  private DefaultCredentialProviderChain providerChain;

  @Before
  public void setUp() throws Exception {
    jvmEnvVariableCredentialProvider = mock(JVMEnvVariableCredentialProvider.class);
    systemPropCredentialProvider = mock(SystemPropCredentialProvider.class);
    systemEnvVariableCredentialProvider = mock(SystemEnvVariableCredentialProvider.class);
    whenNew(JVMEnvVariableCredentialProvider.class).withAnyArguments()
        .thenReturn(jvmEnvVariableCredentialProvider);
    whenNew(SystemPropCredentialProvider.class).withAnyArguments()
        .thenReturn(systemPropCredentialProvider);
    whenNew(SystemEnvVariableCredentialProvider.class).withAnyArguments()
        .thenReturn(systemEnvVariableCredentialProvider);
    providerChain = new DefaultCredentialProviderChain();
  }

  @Test
  public void shouldGetCredentialsFromJvmEnv() throws Exception {
    Credential expectedCredential = Credential.sellerCredential("email", "token");

    when(jvmEnvVariableCredentialProvider.getCredential()).thenReturn(expectedCredential);

    Credential credential = providerChain.getCredential();

    assertEquals(expectedCredential, credential);
    InOrder inOrder = inOrder(jvmEnvVariableCredentialProvider, systemPropCredentialProvider,
        systemEnvVariableCredentialProvider);
    inOrder.verify(jvmEnvVariableCredentialProvider, times(1)).getCredential();
    inOrder.verify(systemPropCredentialProvider, times(0)).getCredential();
    inOrder.verify(systemEnvVariableCredentialProvider, times(0)).getCredential();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void shouldGetCredentialsFromSysProp() throws Exception {
    Credential expectedCredential = Credential.sellerCredential("email", "token");

    when(jvmEnvVariableCredentialProvider.getCredential())
        .thenThrow(new IllegalArgumentException());
    when(systemPropCredentialProvider.getCredential()).thenReturn(expectedCredential);

    Credential credential = providerChain.getCredential();

    assertEquals(expectedCredential, credential);
    InOrder inOrder = inOrder(jvmEnvVariableCredentialProvider, systemPropCredentialProvider,
        systemEnvVariableCredentialProvider);
    inOrder.verify(jvmEnvVariableCredentialProvider, times(1)).getCredential();
    inOrder.verify(systemPropCredentialProvider, times(1)).getCredential();
    inOrder.verify(systemEnvVariableCredentialProvider, times(0)).getCredential();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void shouldGetCredentialsFromSysEnv() throws Exception {
    Credential expectedCredential = Credential.sellerCredential("email", "token");

    when(jvmEnvVariableCredentialProvider.getCredential())
        .thenThrow(new IllegalArgumentException());
    when(systemPropCredentialProvider.getCredential()).thenThrow(new IllegalArgumentException());
    when(systemEnvVariableCredentialProvider.getCredential()).thenReturn(expectedCredential);

    Credential credential = providerChain.getCredential();

    assertEquals(expectedCredential, credential);
    InOrder inOrder = inOrder(jvmEnvVariableCredentialProvider, systemPropCredentialProvider,
        systemEnvVariableCredentialProvider);
    inOrder.verify(jvmEnvVariableCredentialProvider, times(1)).getCredential();
    inOrder.verify(systemPropCredentialProvider, times(1)).getCredential();
    inOrder.verify(systemEnvVariableCredentialProvider, times(1)).getCredential();
    inOrder.verifyNoMoreInteractions();
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowException() throws Exception {
    when(jvmEnvVariableCredentialProvider.getCredential())
        .thenThrow(new IllegalArgumentException());
    when(systemPropCredentialProvider.getCredential()).thenThrow(new IllegalArgumentException());
    when(systemEnvVariableCredentialProvider.getCredential())
        .thenThrow(new IllegalArgumentException());

    providerChain.getCredential();
  }
}