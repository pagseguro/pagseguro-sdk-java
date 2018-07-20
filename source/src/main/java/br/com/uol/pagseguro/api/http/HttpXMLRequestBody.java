package br.com.uol.pagseguro.api.http;

/**
 * Body to http request
 *
 * @author PagSeguro Internet Ltda.
 */
public class HttpXMLRequestBody {

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
    public HttpXMLRequestBody(String contentType, String content, String charset) {
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
        if (!(o instanceof HttpXMLRequestBody)) return false;

        HttpXMLRequestBody that = (HttpXMLRequestBody) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (charset != null ? !charset.equals(that.charset) : that.charset != null) return false;
        return contentType != null ? contentType.equals(that.contentType) : that.contentType == null;
    }
}