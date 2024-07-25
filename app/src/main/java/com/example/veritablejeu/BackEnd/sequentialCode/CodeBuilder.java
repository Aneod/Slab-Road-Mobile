package com.example.veritablejeu.BackEnd.sequentialCode;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;

import org.jetbrains.annotations.Contract;

public class CodeBuilder {

    @NonNull
    @Contract(pure = true)
    public static String buildKeyValue(char key, String codeValue) {
        String codeValueString = codeValue == null ? "" : codeValue;
        long codeLength = codeValueString.length();
        String doubleNumbers = quantifyToDoubleZdecimals(codeLength);
        return key + doubleNumbers + codeValueString;
    }

    @NonNull
    public static String quantifyToDoubleZdecimals(long integer) {
        String secondNumber = toBase36(integer);
        if(secondNumber.equals("0")) {
            return secondNumber;
        }
        int secondNumberLength = secondNumber.length();
        char firstNumber = ZdecimalCharacterConverter.intDecimal_to_charBase36(secondNumberLength);
        return firstNumber + secondNumber;
    }

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int BASE = 36;

    @NonNull
    public static String toBase36(long number) {
        if (number <= 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % BASE);
            result.append(ALPHABET.charAt(remainder));
            number /= BASE;
        }
        return result.reverse().toString();
    }

}
