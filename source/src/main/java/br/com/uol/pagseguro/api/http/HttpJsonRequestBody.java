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
package br.com.uol.pagseguro.api.http;

/**
 * Body to http request
 *
 * @author PagSeguro Internet Ltda.
 */
public class HttpJsonRequestBody {

  private final String content;

  private final String charset;

  private final String contentType;

  /**
   * Constructor
   *
   * @param contentType Content type
   * @param content     Content
   * @param charset     Encoding
   */
  public HttpJsonRequestBody(String contentType, String content, String charset) {
    if (content == null || contentType == null || charset == null) {
      throw new IllegalArgumentException();
    }
    this.content = content;
    this.charset = charset;
    this.contentType = contentType;
  }

  /**
   * Get content
   *
   * @return Content
   */
  public String getContent() {
    return content;
  }

  /**
   * Get content type with charset
   *
   * @return Content type with charset
   */
  public String getContentTypeWithCharset() {
    return getContentType() + "; charset=" + getCharset();
  }

  /**
   * Get content type
   *
   * @return Content type
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * Get charset
   *
   * @return Charset
   */
  public String getCharset() {
    return charset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof HttpJsonRequestBody)) return false;

    HttpJsonRequestBody that = (HttpJsonRequestBody) o;

    if (content != null ? !content.equals(that.content) : that.content != null) return false;
    if (charset != null ? !charset.equals(that.charset) : that.charset != null) return false;
    return contentType != null ? contentType.equals(that.contentType) : that.contentType == null;

  }

}
