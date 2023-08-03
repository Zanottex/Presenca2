package com.MundoSenai.Presenca.Service;

public class NumberCleaner {
    public static String cleanNumber(String num){
        return num.replaceAll("[^0-9]","");

    }
}
