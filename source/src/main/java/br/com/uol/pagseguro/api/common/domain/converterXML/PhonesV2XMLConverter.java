package br.com.uol.pagseguro.api.common.domain.converterXML;

import java.util.ArrayList;

public class PhonesV2XMLConverter {

    private ArrayList<PhoneV2XMLConverter> phone;

    public ArrayList<PhoneV2XMLConverter> getPhone() {
        if (phone == null){
            phone = new ArrayList<PhoneV2XMLConverter>();
        }
        return phone;
    }

    public void setPhone(ArrayList<PhoneV2XMLConverter> phone) {
        this.phone = phone;
    }
}
