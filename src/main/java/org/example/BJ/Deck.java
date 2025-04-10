package org.example.BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Kortti> kortit = new ArrayList<>();
    private final Random random = new Random();

    public Deck() {
        String[] maat = {"♠", "♥", "♦", "♣"};
        String[] arvot = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String maa : maat) {
            for (String arvo : arvot) {
                kortit.add(new Kortti(maa, arvo));
            }
        }

        Collections.shuffle(kortit);
    }

    public Kortti nosta() {
        return kortit.removeFirst();
    }
}