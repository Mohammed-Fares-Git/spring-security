package com.mohammedfares.spring_security.comon;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABETS = "0123456789azertyuiopqsdfghjklmwxcvbn";

    public String generateUserId(int length) {
        StringBuilder userId = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            userId.append(ALPHABETS.charAt(RANDOM.nextInt(ALPHABETS.length())));
        }

        return new String(userId);
    }
}
