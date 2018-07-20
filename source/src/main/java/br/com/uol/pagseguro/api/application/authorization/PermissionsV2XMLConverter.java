package br.com.uol.pagseguro.api.application.authorization;

import java.util.ArrayList;

public class PermissionsV2XMLConverter {

    private ArrayList<String> code;

    public ArrayList<String> getCode() {
        if (code == null){
            code = new ArrayList<String>();
        }
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }
}
