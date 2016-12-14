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

import java.text.SimpleDateFormat;

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.Holder;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Abstract converter for holder.
 * Used to convert attributes of holder in request map.
 * This class must be implemented because the keys of the attributes of the request are dynamic.
 *
 * @author PagSeguro Internet Ltda.
 */
public abstract class AbstractHolderMapConverter extends AbstractMapConverter<Holder> {

  /**
   * Convert attributes of holder in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param holder     The interface of Holder
   * @see RequestMap
   * @see Holder
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Holder holder) {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    requestMap.putString(getNameKey(), holder.getName());
    for (Document document : holder.getDocuments()) {
      if (document.getType().equals(DocumentType.CPF)) {
        requestMap.putString(getCpfKey(), document.getValue());
      } else {
        requestMap.putString(getCnpjKey(), document.getValue());
      }
    }
    requestMap.putDate(getBirthDateKey(), holder.getBirthDate(), sdf);
    requestMap.putMap(getPhoneMapConverter().convert(holder.getPhone()));
  }

  /**
   * Get name key
   *
   * @return The key to the name is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getNameKey();

  /**
   * Get cpf key
   *
   * @return The key to the cpf is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getCpfKey();

  /**
   * Get cnpj key
   *
   * @return The key to the cnpj is a key dynamic inserted in the implementation of this class.
   */
  protected abstract String getCnpjKey();

  /**
   * Get birth date key
   *
   * @return The key to the birth date is a key dynamic inserted in the implementation of this
   * class.
   */
  protected abstract String getBirthDateKey();

  /**
   * Get phone key
   *
   * @return The key to the phone is a key dynamic inserted in the implementation of this class.
   */
  protected abstract AbstractPhoneMapConverter getPhoneMapConverter();

}
