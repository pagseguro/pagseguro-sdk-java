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
package br.com.uol.pagseguro.example.api.application.authorization.search;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.application.authorization.search.AuthorizationSummary;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.credential.Credential;

/**
 * @author PagSeguro Internet Ltda.
 */
public class SearchAuthorizationsByDateRange {

  public static void main(String[] args) {
    String appId = "your_app_id";
    String appKey = "your_app_key";

    try {

      final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(appId,
          appKey), PagSeguroEnv.SANDBOX);

      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

      Date date = dateFormat.parse("28/07/2016");

      final DataList<? extends AuthorizationSummary> authorizations =
          pagSeguro.authorizations().search().byDateRange(
              new DateRangeBuilder().between(date, new Date()),
              1,
              10);

      System.out.print(authorizations);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
