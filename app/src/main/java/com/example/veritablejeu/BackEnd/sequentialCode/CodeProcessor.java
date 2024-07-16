package com.example.veritablejeu.BackEnd.sequentialCode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public class CodeProcessor {

    /**
     * A sequencial code have this shape : i211xxxx...xxxxj12yyk1zwww...www.
     * <br>
     * How to read this strange code ?
     * <p>
     *     This kind of code work by this logic : Take 'A13xxx'.
     *     <br>
     *     We can discompose this little code like this : 'A' '13' 'xxx'.
     *     <br>
     *     The 'A' is the key. Any character are autorized to be a key.
     *     <br>
     *     The '13' is a number. Not 13, but 3. The first character, '1', is a 36-base number.
     *     And representing how many following characters must be take for create a second number.
     *     <br>
     *     It is equals to 1 in decimal. So we must take the only next character in the code to compose a second number : '3'.
     *     <br>
     *     '3' is also a 36-base number, and is equals to 3. This second number indicates how many following characters we must put
     *     in the key associated String Consumer.
     *     <br>
     *     So, the 3 next characters are "xxx". And the key is 'A'.
     *     <br>
     *     The last step, is to find the String consumer of 'A' in the given {@link AssociationsIdConsumer}, and accept it with "xxx" String.
     * </p>
     * <p>
     *     Another example : '@16FFFFFFx2013'
     *     <br>
     *     The '@' consumer will accept "FFFFFF".
     *     And the 'x' consumer will accept "3".
     * </p>
     * @param associationsIdConsumer the key-consumer to apply to the code.
     * @param code the code to used with the given {@link AssociationsIdConsumer}.
     */
    public static void sequencialCodeReader(AssociationsIdConsumer associationsIdConsumer, String code) {
        if(associationsIdConsumer == null || code == null || code.isEmpty()) {
            return;
        }

        int index = 0;
        while (index < code.length()) {
            char id = code.charAt(index);
            index += 1;

            if(code.length() < index + 1) return;
            String superNumber = getSubString(code, index, 1);
            if(superNumber == null) return;
            int howManyNumbers = (int) base36ToLong(superNumber);
            index += 1;

            if(code.length() < index + howManyNumbers) return;
            String numbers = getSubString(code, index, howManyNumbers);
            if(numbers == null) return;
            int howManyCharacters = (int) base36ToLong(numbers);
            index += howManyNumbers;

            if(code.length() < index + howManyCharacters) return;
            String partOfCode = getSubString(code, index, howManyCharacters);
            associationsIdConsumer.execute_byId(id, partOfCode);
            index += howManyCharacters;
        }
    }

    private static long base36ToLong(String base36Number) {
        if (base36Number == null || base36Number.isEmpty()) {
            return 0;
        }
        return Long.parseLong(base36Number, 36);
    }

    @Nullable
    @Contract(pure = true)
    private static String getSubString(@NonNull String original, int firstIndex, int length) {
        int secondIndex = firstIndex + length;
        boolean firstIsOutOfBound = isOutOfBound(original, firstIndex);
        boolean secondIsOutOfBound = isOutOfBound(original, secondIndex);
        if(firstIsOutOfBound || secondIsOutOfBound) {
            return null;
        }
        return original.substring(firstIndex, secondIndex);
    }

    private static boolean isOutOfBound(@NonNull String code, int index) {
        return index < 0 || index > code.length();
    }
}
