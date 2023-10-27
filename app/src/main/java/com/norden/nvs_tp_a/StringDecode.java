package com.norden.nvs_tp_a;

import java.util.ArrayList;
import java.util.List;

public class StringDecode {

    public static List<Integer> getIntList(String data){
        String[] conv = data.split(",");
        mLog.print("str decode str[] size="+conv.length);
        List<Integer> toret = new ArrayList<>();
        int i=0;
        for (String number : conv) {
            if(i++>=600){
                break;
            }
            try {
                toret.add(Integer.parseInt(number.trim()));
            }catch (Exception e){
                mLog.print(e.toString());
            }
        }
        return toret;
    }

}
