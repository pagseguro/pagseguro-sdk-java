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
package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.utils.AbstractJsonConverter;
import br.com.uol.pagseguro.api.utils.RequestJson;

import java.util.List;

/**
 * Abstract converter for documents.
 * Used to convert attributes of documents in request json.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractDocumentJsonConverter extends AbstractJsonConverter<List<Document>> {

  /**
   * Convert attributes of documents in request json
   *
   * @param requestJson Request Json used to pass params to api
   * @param documentList      The document list
   * @see RequestJson
   * @see Document
   * @see AbstractJsonConverter#convert(Object)
   */
  protected void convert(RequestJson requestJson, List<Document> documentList) {
    for (Document document : documentList) {
      if (document.getType().equals(DocumentType.CPF)) {
          requestJson.putDocument(getTypeKey(), getValueKey(), document);
      }
    }
  }

  /**
   * Get area code key
   *
   * @return The key to the area code is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getTypeKey();

  /**
   * Get phone number key
   *
   * @return The key to the phone number is a key dynamic inserted in the implementation of this
   * class.
   */
  protected abstract String getValueKey();

}
