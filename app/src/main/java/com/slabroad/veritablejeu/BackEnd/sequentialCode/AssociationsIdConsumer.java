package com.slabroad.veritablejeu.BackEnd.sequentialCode;

import androidx.core.util.Consumer;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a functionnal tool to create a key-value system between char and Consumer of String.
 */
public class AssociationsIdConsumer {

    private final Map<Character, Consumer<String>> associations = new HashMap<>();

    public AssociationsIdConsumer() {}

    /**
     * Add a list of [id, consumer] in the class.
     * @param args the list of [id, consumer] to add.
     */
    public void addMultiple(Object... args) {
        if (args == null || args.length == 0) {
            return;
        }
        for (int i = 0; i < args.length; i += 2) {
            if ((i + 1) >= args.length) {
                return;
            }

            if (args[i] instanceof Character && args[i + 1] instanceof Consumer) {
                char ch = (Character) args[i];
                @SuppressWarnings("unchecked")
                Consumer<String> consumer = (Consumer<String>) args[i + 1];
                associations.put(ch, consumer);
            }
        }
    }

    /**
     * Execute the consumer linked to the given id.
     * @param id the key to select which Consumer use with the given String code.
     * @param code the String code to accept with the id's consumer.
     */
    public void execute_byId(char id, String code) {
        if(code == null) {
            return;
        }
        Consumer<String> consumer = associations.get(id);
        if (consumer != null) {
            consumer.accept(code);
        }
    }

    /**
     * Checks if the given char is one of the id of the associations.
     * @param id the char to check.
     * @return true if the given char is one of the id of the associations.
     */
    public boolean containsKey(char id) {
        return associations.containsKey(id);
    }

}
