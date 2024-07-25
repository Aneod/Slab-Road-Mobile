package com.example.veritablejeu.BackEnd.sequentialCode;

public class Code {

    public static void apply(String code, Object... args) {
        AssociationsIdConsumer associations = new AssociationsIdConsumer();
        associations.addMultiple(args);
        CodeProcessor.sequencialCodeReader(associations, code);
    }

}
