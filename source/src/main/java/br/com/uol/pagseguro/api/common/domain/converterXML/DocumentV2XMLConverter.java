package br.com.uol.pagseguro.api.common.domain.converterXML;

public class DocumentV2XMLConverter {

    private String type;
    private String value;

    public DocumentV2XMLConverter() {
    }

    public DocumentV2XMLConverter(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
