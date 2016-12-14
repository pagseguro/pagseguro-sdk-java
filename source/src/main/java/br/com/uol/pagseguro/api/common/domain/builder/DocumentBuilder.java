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

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for Document
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DocumentBuilder implements Builder<Document> {

  private DocumentType type = DocumentType.CPF;

  private String value = null;

  /**
   * Set document type
   *
   * @param type Document type
   * @return Builder for document
   * @see Document#getType()
   */
  public DocumentBuilder withType(DocumentType type) {
    this.type = type;
    return this;
  }

  /**
   * Set document value
   *
   * @param value Document Value
   * @return Builder for Document
   * @see Document#getValue()
   */
  public DocumentBuilder withValue(String value) {
    this.value = value;
    return this;
  }

  /**
   * Build the Document
   *
   * @return Interface for document
   * @see Document
   */
  @Override
  public Document build() {
    return new SimpleDocument(this);
  }

  /**
   * Implementation of {@code Document}
   */
  private static class SimpleDocument implements Document {

    private final DocumentBuilder documentBuilder;

    public SimpleDocument(DocumentBuilder documentBuilder) {
      this.documentBuilder = documentBuilder;
    }

    @Override
    public DocumentType getType() {
      return documentBuilder.type;
    }

    @Override
    public String getValue() {
      return documentBuilder.value;
    }
  }
}
