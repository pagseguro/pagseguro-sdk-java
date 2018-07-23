package br.com.uol.pagseguro.api.application.authorization;

import br.com.uol.pagseguro.api.common.domain.PermissionCode;

import java.util.ArrayList;
import java.util.List;

public class PermissionsV2XMLConverter {

    private ArrayList<String> code;

    public ArrayList<String> getCode() {
        if (code == null) {
            code = new ArrayList<String>();
        }
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    /**
     * Convert attributes in request XML
     *
     * @param codeList List of permission codes
     * @return PermissionsV2XMLConverter
     */
    public PermissionsV2XMLConverter convert(List<PermissionCode.Code> codeList) {
        PermissionsV2XMLConverter convertedPermissions = new PermissionsV2XMLConverter();
        convertedPermissions.setCode(parseToCodeStringList(codeList));
        return convertedPermissions;
    }

    /**
     * Parse a permission codes list to a string list of codes
     *
     * @param codeList List of permission codes
     * @return ArrayList<String> List of permission codes
     */
    private ArrayList<String> parseToCodeStringList(List<PermissionCode.Code> codeList) {
        ArrayList<String> codeStringList = new ArrayList<String>();
        for (PermissionCode.Code code : codeList) {
            codeStringList.add(code.getStringCode());
        }
        return codeStringList;
    }
}
