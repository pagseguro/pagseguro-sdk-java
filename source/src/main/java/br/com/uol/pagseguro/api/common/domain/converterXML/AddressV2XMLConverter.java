package br.com.uol.pagseguro.api.common.domain.converterXML;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"postalCode", "street", "number", "complement", "district", "city", "state", "country"})
public class AddressV2XMLConverter {

    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String country;

    public AddressV2XMLConverter() {
    }

    public AddressV2XMLConverter(String postalCode, String street, String number, String complement,
                          String district, String city, String state, String country) {
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressV2XMLConverter setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressV2XMLConverter setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public AddressV2XMLConverter setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getComplement() {
        return complement;
    }

    public AddressV2XMLConverter setComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AddressV2XMLConverter setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressV2XMLConverter setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressV2XMLConverter setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressV2XMLConverter setCountry(String country) {
        this.country = country;
        return this;
    }
}
