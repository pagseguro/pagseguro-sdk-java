package br.com.uol.pagseguro.api.common.domain.converterXML;

import br.com.uol.pagseguro.api.common.domain.PhoneAccount;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Convert attributes in request XML
     *
     * @param phoneAccountList Phone Account data
     * @see PhoneAccount
     * @return PhonesV2XMLConverter
     */
    public PhonesV2XMLConverter convert(List<? extends PhoneAccount> phoneAccountList) {
        PhonesV2XMLConverter convertedPhone = new PhonesV2XMLConverter();
        convertedPhone.setPhone(parseToPhoneList(phoneAccountList));
        return convertedPhone;
    }

    /**
     * Parse a PhoneAccount list to a list of converted phone accounts
     *
     * @param phoneAccountList List of PhoneAccount
     * @see PhoneAccount
     * @return ArrayList<PhoneV2XMLConverter> List of converted phones
     */
    private ArrayList<PhoneV2XMLConverter> parseToPhoneList(List<? extends PhoneAccount> phoneAccountList) {
        ArrayList<PhoneV2XMLConverter> phoneList = new ArrayList<PhoneV2XMLConverter>();

        for (PhoneAccount phoneAccount : phoneAccountList) {
            PhoneV2XMLConverter phone = new PhoneV2XMLConverter(
                phoneAccount.getType(), phoneAccount.getAreaCode(), phoneAccount.getNumber()
            );
            phoneList.add(phone);
        }

        return phoneList;
    }
}
