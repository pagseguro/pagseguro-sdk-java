package br.com.uol.pagseguro.api.utils;

import br.com.uol.pagseguro.api.http.HttpXMLRequestBody;

import java.io.UnsupportedEncodingException;

/**
 * Class used to convert the data on a XML string to pass the parameters of the requests in api
 *
 * @author PagSeguro Internet Ltda.
 */
public class RequestXML {

    public String xmlBody = "";

    public RequestXML() {
    }

    public RequestXML(String xmlBody) {
        this.xmlBody = xmlBody;
    }

    public String getXmlBody() {
        return xmlBody;
    }

    public RequestXML setXmlBody(String xmlBody) {
        this.xmlBody = xmlBody;
        return this;
    }

    /**
     * Convert to http request body
     *
     * @param charset Encoding
     * @return Http Request Body
     * @see HttpXMLRequestBody
     * @throws UnsupportedEncodingException if not accepted encode is used
     */
    public HttpXMLRequestBody toHttpXMLRequestBody(String charset) throws UnsupportedEncodingException {
        return new HttpXMLRequestBody(
            String.format("application/xml; charset=%s", charset),
            encodeBodyContent(xmlBody, charset),
            charset);
    }

    /**
     * Encode the body content to PagSeguro API requirement
     *
     * @param toEncodeString String to be encoded
     * @param charset Charset used to encode
     * @return String body encoded by charset param
     * @throws UnsupportedEncodingException if not accepted encode is used
     */
    public String encodeBodyContent(String toEncodeString, String charset) throws UnsupportedEncodingException {
        byte[] ptext = toEncodeString.getBytes();
        return new String(ptext, charset);
    }

    @Override
    public String toString() {
        return "RequestXML{" +
            "xmlBody='" + xmlBody + '\'' +
            '}';
    }
}
