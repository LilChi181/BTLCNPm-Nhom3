package protune.controller.inapp;

import protune.model.Constant;

import java.util.Random;

public class RandomIDGenerator {
    public static String gen(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(Constant.AudioIDLength);

        for (int i = 0; i < Constant.AudioIDLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }
}
