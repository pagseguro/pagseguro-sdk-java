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

import br.com.uol.pagseguro.api.common.domain.Receiver;
import br.com.uol.pagseguro.api.common.domain.Split;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for receiver
 *
 * @author PagSeguro Internet Ltda.
 */
public final class ReceiverBuilder implements Builder<Receiver> {

  private Split split;
  private String publicKey;

  /**
   * Set split of receiver
   *
   * @param split Split
   * @return Builder for receiver
   * @see Receiver#getSplit()
   */
  public ReceiverBuilder withSplit(Split split) {
    this.split = split;
    return this;
  }

  /**
   * Set split of receiver
   *
   * @param splitBuilder Builder for Split
   * @return Builder for receiver
   * @see Receiver#getSplit()
   */
  public ReceiverBuilder withSplit(Builder<Split> splitBuilder) {
    return withSplit(splitBuilder.build());
  }

  /**
   * Set public key of receiver
   *
   * @param publicKey Public key
   * @return Builder for receiver
   * @see Receiver#getPublicKey()
   */
  public ReceiverBuilder withPublicKey(String publicKey) {
    this.publicKey = publicKey;
    return this;
  }

  /**
   * Build the receiver
   *
   * @return Interface for receiver
   * @see Receiver
   */
  @Override
  public Receiver build() {
    return new SimpleReceiver(this);
  }

  /**
   * Implementation of {@code Receiver}
   */
  private static class SimpleReceiver implements Receiver {

    private final ReceiverBuilder receiverBuilder;

    private SimpleReceiver(ReceiverBuilder receiverBuilder) {
      this.receiverBuilder = receiverBuilder;
    }

    @Override
    public String getPublicKey() {
      return receiverBuilder.publicKey;
    }

    @Override
    public Split getSplit() {
      return receiverBuilder.split;
    }
  }
}
