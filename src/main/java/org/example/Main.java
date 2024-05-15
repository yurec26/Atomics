package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger leng3counter = new AtomicInteger(0);
    public static AtomicInteger leng4counter = new AtomicInteger(0);
    public static AtomicInteger leng5counter = new AtomicInteger(0);


    public static void main(String[] args) {


        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            final String currentS = texts[i];
            //
            new Thread(() -> {
                countFirstCriteria(currentS, leng3counter, leng4counter, leng5counter);
            }).start();
            //
            new Thread(() -> {
                countSecondCriteria(currentS, leng3counter, leng4counter, leng5counter);
            }).start();
            //
            new Thread(() -> {
                countThirdCriteria(currentS, leng3counter, leng4counter, leng5counter);
            }).start();
        }

        System.out.println(leng3counter);
        System.out.println(leng4counter);
        System.out.println(leng5counter);


    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void countFirstCriteria(String s, AtomicInteger len3, AtomicInteger len4, AtomicInteger len5) {
        StringBuilder stringBuilder = new StringBuilder().append(s);
        String reverseS = stringBuilder.reverse().toString();
        if (s.equals(reverseS)) {
            switch (s.length()) {
                case 3:
                    len3.getAndIncrement();
                    break;
                case 4:
                    len4.getAndIncrement();
                    break;
                case 5:
                    len5.getAndIncrement();
                    break;
            }
        }
    }

    public static void countSecondCriteria(String s, AtomicInteger len3, AtomicInteger len4, AtomicInteger len5) {
        boolean isgood = true;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                isgood = false;
                break;
            }
        }
        if (isgood) {
            switch (s.length()) {
                case 3:
                    len3.getAndIncrement();
                    break;
                case 4:
                    len4.getAndIncrement();
                    break;
                case 5:
                    len5.getAndIncrement();
                    break;
            }
        }
    }

    public static void countThirdCriteria(String s, AtomicInteger len3, AtomicInteger len4, AtomicInteger len5) {
        boolean isgood = true;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isgood = false;
                break;
            }
        }
        if (isgood) {
            switch (s.length()) {
                case 3:
                    len3.getAndIncrement();
                    break;
                case 4:
                    len4.getAndIncrement();
                    break;
                case 5:
                    len5.getAndIncrement();
                    break;
            }
        }
    }
}