package br.com.uol.pagseguro.api.environment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.uol.pagseguro.api.Case4Test;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.exception.PagSeguroLibException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DefaultEnvironmentProviderChain.class, JVMEnvVariableEnvironmentProvider.class,
                SystemPropEnvironmentProvider.class, SystemEnvVariableEnvironmentProvider.class})
public class DefaultEnvironmentProviderChainTest extends Case4Test {

  private JVMEnvVariableEnvironmentProvider jvmEnvVariableEnvironmentProvider;

  private SystemPropEnvironmentProvider systemPropEnvironmentProvider;

  private SystemEnvVariableEnvironmentProvider systemEnvVariableEnvironmentProvider;


  private DefaultEnvironmentProviderChain providerChain;

  @Before
  public void setUp() throws Exception {
    jvmEnvVariableEnvironmentProvider = mock(JVMEnvVariableEnvironmentProvider.class);
    systemPropEnvironmentProvider = mock(SystemPropEnvironmentProvider.class);
    systemEnvVariableEnvironmentProvider = mock(SystemEnvVariableEnvironmentProvider.class);
    whenNew(JVMEnvVariableEnvironmentProvider.class).withAnyArguments()
        .thenReturn(jvmEnvVariableEnvironmentProvider);
    whenNew(SystemPropEnvironmentProvider.class).withAnyArguments()
        .thenReturn(systemPropEnvironmentProvider);
    whenNew(SystemEnvVariableEnvironmentProvider.class).withAnyArguments()
        .thenReturn(systemEnvVariableEnvironmentProvider);
    providerChain = new DefaultEnvironmentProviderChain();
  }

  @Test
  public void shouldGetCredentialsFromJvmEnv() throws Exception {
    PagSeguroEnv expectedEnv = PagSeguroEnv.SANDBOX;

    when(jvmEnvVariableEnvironmentProvider.getEnvironment()).thenReturn(expectedEnv);

    PagSeguroEnv env = providerChain.getEnvironment();

    assertEquals(expectedEnv, env);
    InOrder inOrder = inOrder(jvmEnvVariableEnvironmentProvider, systemPropEnvironmentProvider,
        systemEnvVariableEnvironmentProvider);
    inOrder.verify(jvmEnvVariableEnvironmentProvider, times(1)).getEnvironment();
    inOrder.verify(systemPropEnvironmentProvider, times(0)).getEnvironment();
    inOrder.verify(systemEnvVariableEnvironmentProvider, times(0)).getEnvironment();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void shouldGetCredentialsFromSysProp() throws Exception {
    PagSeguroEnv expectedEnv = PagSeguroEnv.SANDBOX;

    when(jvmEnvVariableEnvironmentProvider.getEnvironment())
        .thenThrow(new IllegalArgumentException());
    when(systemPropEnvironmentProvider.getEnvironment()).thenReturn(expectedEnv);

    PagSeguroEnv env = providerChain.getEnvironment();

    assertEquals(expectedEnv, env);
    InOrder inOrder = inOrder(jvmEnvVariableEnvironmentProvider, systemPropEnvironmentProvider,
        systemEnvVariableEnvironmentProvider);
    inOrder.verify(jvmEnvVariableEnvironmentProvider, times(1)).getEnvironment();
    inOrder.verify(systemPropEnvironmentProvider, times(1)).getEnvironment();
    inOrder.verify(systemEnvVariableEnvironmentProvider, times(0)).getEnvironment();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void shouldGetCredentialsFromSysEnv() throws Exception {
    PagSeguroEnv expectedEnv = PagSeguroEnv.SANDBOX;

    when(jvmEnvVariableEnvironmentProvider.getEnvironment())
        .thenThrow(new IllegalArgumentException());
    when(systemPropEnvironmentProvider.getEnvironment()).thenThrow(new IllegalArgumentException());
    when(systemEnvVariableEnvironmentProvider.getEnvironment()).thenReturn(expectedEnv);

    PagSeguroEnv env = providerChain.getEnvironment();

    assertEquals(expectedEnv, env);
    InOrder inOrder = inOrder(jvmEnvVariableEnvironmentProvider, systemPropEnvironmentProvider,
        systemEnvVariableEnvironmentProvider);
    inOrder.verify(jvmEnvVariableEnvironmentProvider, times(1)).getEnvironment();
    inOrder.verify(systemPropEnvironmentProvider, times(1)).getEnvironment();
    inOrder.verify(systemEnvVariableEnvironmentProvider, times(1)).getEnvironment();
    inOrder.verifyNoMoreInteractions();
  }

  @Test(expected = PagSeguroLibException.class)
  public void shouldThrowException() throws Exception {
    when(jvmEnvVariableEnvironmentProvider.getEnvironment())
        .thenThrow(new IllegalArgumentException());
    when(systemPropEnvironmentProvider.getEnvironment()).thenThrow(new IllegalArgumentException());
    when(systemEnvVariableEnvironmentProvider.getEnvironment())
        .thenThrow(new IllegalArgumentException());

    providerChain.getEnvironment();
  }
}