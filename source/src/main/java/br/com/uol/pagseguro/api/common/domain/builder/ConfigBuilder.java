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

package br.com.uol.pagseguro.api.common.domain.builder;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.common.domain.Config;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for configuration
 *
 * @author PagSeguro Internet Ltda.
 */
public final class ConfigBuilder implements Builder<Config> {

  private ConfigKey key = null;

  private BigDecimal value = null;

  /**
   * Set key of configuration
   *
   * @param key Key of configuration
   * @return Builder for configuration
   */
  public ConfigBuilder withKey(ConfigKey key) {
    this.key = key;
    return this;
  }

  /**
   * Set value of configuration
   *
   * @param value Value of configuration
   * @return Builder for configuration
   */
  public ConfigBuilder withValue(BigDecimal value) {
    this.value = value;
    return this;
  }

  /**
   * Build the Configuration
   *
   * @return Interface for Configuration
   * @see Config
   */
  @Override
  public Config build() {
    return new SimpleConfig(this);
  }

  /**
   * Implementation of {@code Config}
   */
  private static class SimpleConfig implements Config {

    private final ConfigBuilder configBuilder;

    public SimpleConfig(ConfigBuilder configBuilder) {
      this.configBuilder = configBuilder;
    }

    @Override
    public ConfigKey getKey() {
      return configBuilder.key;
    }

    @Override
    public BigDecimal getValue() {
      return configBuilder.value;
    }
  }
}
