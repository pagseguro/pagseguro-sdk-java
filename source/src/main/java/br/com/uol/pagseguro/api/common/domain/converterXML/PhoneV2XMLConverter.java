package br.com.uol.pagseguro.api.common.domain.converterXML;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"type", "areaCode", "number"})
public class PhoneV2XMLConverter {

    private String type;
    private String areaCode;
    private String number;

    public PhoneV2XMLConverter() {
    }

    public PhoneV2XMLConverter(String type, String areaCode, String number) {
        this.type = type;
        this.areaCode = areaCode;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public PhoneV2XMLConverter setType(String type) {
        this.type = type;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public PhoneV2XMLConverter setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public PhoneV2XMLConverter setNumber(String number) {
        this.number = number;
        return this;
    }
}
