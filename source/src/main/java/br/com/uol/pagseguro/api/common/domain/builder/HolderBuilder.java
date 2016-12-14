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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.Holder;
import br.com.uol.pagseguro.api.common.domain.Phone;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for holder
 *
 * @author PagSeguro Internet Ltda.
 */
public final class HolderBuilder implements Builder<Holder> {

  private String name = null;
  private List<Document> documents = new ArrayList<Document>();
  private Date birthDate = null;
  private Phone phone = null;

  /**
   * Set name of holder
   *
   * @param name Name of holder
   * @return Builder for holder
   * @see Holder#getName()
   */
  public HolderBuilder withName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Add document to holder
   *
   * @param document Document
   * @return Builder for holder
   * @see Holder#getDocuments()
   */
  public HolderBuilder addDocument(Document document) {
    documents.add(document);
    return this;
  }

  /**
   * Add document to holder
   *
   * @param documentBuilder Builder for Document
   * @return Builder for holder
   * @see Holder#getDocuments()
   */
  public HolderBuilder addDocument(Builder<Document> documentBuilder) {
    return addDocument(documentBuilder.build());
  }

  /**
   * Add document to holder
   *
   * @param documents Documents
   * @return Builder for holder
   * @see Holder#getDocuments()
   */
  public HolderBuilder addDocuments(Iterable<? extends Document> documents) {
    for (Document document : documents) {
      addDocument(document);
    }
    return this;
  }

  /**
   * Set birth date of holder
   *
   * @param birthDate Birth Date
   * @return Builder for holder
   * @see Holder#getBirthDate()
   */
  public HolderBuilder withBithDate(Date birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Set phone of holder
   *
   * @param phone Phone
   * @return Builder for holder
   * @see Holder#getPhone()
   */
  public HolderBuilder withPhone(Phone phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Set phone of holder
   *
   * @param phoneBuilder Builder for Phone
   * @return Builder for holder
   * @see Holder#getPhone()
   */
  public HolderBuilder withPhone(Builder<Phone> phoneBuilder) {
    return withPhone(phoneBuilder.build());
  }

  /**
   * Build the Holder
   *
   * @return Interface for Holder
   * @see Holder
   */
  @Override
  public Holder build() {
    return new SimpleHolderBuilder(this);
  }

  /**
   * Implementation of {@code Holder}
   */
  private static class SimpleHolderBuilder implements Holder {

    private final HolderBuilder holderBuilder;

    private SimpleHolderBuilder(HolderBuilder holderBuilder) {
      this.holderBuilder = holderBuilder;
    }


    @Override
    public String getName() {
      return holderBuilder.name;
    }

    @Override
    public List<Document> getDocuments() {
      return holderBuilder.documents;
    }

    @Override
    public Date getBirthDate() {
      return holderBuilder.birthDate;
    }

    @Override
    public Phone getPhone() {
      return holderBuilder.phone;
    }
  }
}
