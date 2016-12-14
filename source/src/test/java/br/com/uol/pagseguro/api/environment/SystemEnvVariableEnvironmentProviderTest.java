package br.com.uol.pagseguro.api.environment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.uol.pagseguro.api.Case4Test;
import br.com.uol.pagseguro.api.PagSeguroEnv;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author PagSeguro Internet Ltda.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, SystemEnvVariableEnvironmentProvider.class})
public class SystemEnvVariableEnvironmentProviderTest extends Case4Test {

  private SystemEnvVariableEnvironmentProvider provider;

  @Before
  public void setUp() throws Exception {
    provider = new SystemEnvVariableEnvironmentProvider();
    mockStatic(System.class);
  }

  @Test
  public void shouldGetSandboxEnv() throws Exception {
    when(System.getenv("PSL_ENVIRONMENT")).thenReturn("sandbox");
    PagSeguroEnv expectedEnv = PagSeguroEnv.SANDBOX;

    PagSeguroEnv env = provider.getEnvironment();

    assertEquals(expectedEnv, env);
  }

  @Test
  public void shouldGetProductionEnv() throws Exception {
    when(System.getenv("PSL_ENVIRONMENT")).thenReturn("production");
    PagSeguroEnv expectedEnv = PagSeguroEnv.PRODUCTION;

    PagSeguroEnv env = provider.getEnvironment();

    assertEquals(expectedEnv, env);
  }
}