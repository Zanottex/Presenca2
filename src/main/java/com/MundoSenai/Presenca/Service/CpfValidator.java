package com.MundoSenai.Presenca.Service;

public class CpfValidator {
    public static boolean validateCPF(String CPF) {

        CPF = NumberCleaner.cleanNumber(CPF);

        if (CPF.length() != 11) {
            return false;
        }

        if (CPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (CPF.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        if (CPF.charAt(9) - '0' != firstDigit) {
            return false;
        }
        sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += (CPF.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }
        return CPF.charAt(10) - '0' == secondDigit;
    }
}