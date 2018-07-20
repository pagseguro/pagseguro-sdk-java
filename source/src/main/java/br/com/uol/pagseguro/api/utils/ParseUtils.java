package br.com.uol.pagseguro.api.utils;

import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.common.domain.PhoneAccount;
import br.com.uol.pagseguro.api.common.domain.converterXML.DocumentV2XMLConverter;
import br.com.uol.pagseguro.api.common.domain.converterXML.PhoneV2XMLConverter;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    public static ArrayList<String> parseToCodeStringList(List<PermissionCode.Code> codeList) {

        ArrayList<String> codeStringList = new ArrayList<String>();

        for (PermissionCode.Code code : codeList) {
            codeStringList.add(code.getStringCode());
        }

        return codeStringList;
    }

    public static ArrayList<DocumentV2XMLConverter> parseToDocumentList(List<? extends Document> documentsList) {

        ArrayList<DocumentV2XMLConverter> documentList = new ArrayList<DocumentV2XMLConverter>();

        for (Document document : documentsList) {
            DocumentV2XMLConverter documentConverter = new DocumentV2XMLConverter(
                document.getType().toString(), document.getValue()
            );
            documentList.add(documentConverter);
        }

        return documentList;
    }

    public static ArrayList<PhoneV2XMLConverter> parseToPhoneList(List<? extends PhoneAccount> phoneAccountList) {

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
