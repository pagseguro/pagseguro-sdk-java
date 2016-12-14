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

import br.com.uol.pagseguro.api.common.domain.Bank;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Bank
 *
 * @author PagSeguro Internet Ltda.
 */
public final class BankBuilder implements Builder<Bank> {

  private BankName name;

  /**
   * Set name of bank
   *
   * @param name Name of bank
   * @return Builder for Bank
   * @see Bank#getBankName()
   */
  public BankBuilder withName(BankName.Name name) {
    this.name = new BankName(name);
    return this;
  }

  /**
   * Build the Bank
   *
   * @return Interface for bank
   * @see Bank
   */
  @Override
  public Bank build() {
    return new SimpleBank(this);
  }

  /**
   * Implementation of {@code Bank}
   */
  private static class SimpleBank implements Bank {

    private final BankBuilder bankBuilder;

    public SimpleBank(BankBuilder bankBuilder) {
      this.bankBuilder = bankBuilder;
    }

    @Override
    public BankName getBankName() {
      return bankBuilder.name;
    }
  }
}
