package br.com.uol.pagseguro.api.common.domain.xml;

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by victorfreitas on 19/05/17.
 */
public class DocumentXML implements Document {

    private DocumentType type;
    private String value;

    DocumentXML (){

    }

    @XmlElement
    public void setType(DocumentType type){ this.type = type; }

    @XmlElement
    public void setValue(String value){ this.value = value; }

    @Override
    public DocumentType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "DocumentsXML{" +
               "type='" + type + '\'' +
               ",value='" + value +  '\'' +
               '}';
    }
}
