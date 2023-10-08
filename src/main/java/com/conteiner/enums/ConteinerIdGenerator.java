package com.conteiner.enums;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class ConteinerIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder prefix = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            prefix.append(letters.charAt(random.nextInt(letters.length())));
        }

        int randomNumber = 1000000 + random.nextInt(9000000);

        return prefix.toString() + randomNumber;
    }
}
