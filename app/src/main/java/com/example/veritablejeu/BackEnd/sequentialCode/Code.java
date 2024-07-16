package com.example.veritablejeu.BackEnd.sequentialCode;

import androidx.annotation.NonNull;

public class Code {

    public static void apply(@NonNull String code, @NonNull Object... args) {
        AssociationsIdConsumer associations = new AssociationsIdConsumer();
        associations.addMultiple(args);
        CodeProcessor.sequencialCodeReader(associations, code);
    }

}
