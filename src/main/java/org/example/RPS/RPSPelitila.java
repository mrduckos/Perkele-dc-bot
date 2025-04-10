package org.example.RPS;

import java.util.HashMap;
import java.util.Map;

public class RPSPelitila {
    private static final Map<Long, RPSLogiikka> pelitilat = new HashMap<>();

    public static RPSLogiikka haeTaiLuo(long userId, String pelaajaNimi) {
        return pelitilat.computeIfAbsent(userId, id -> new RPSLogiikka(new Pelaaja(pelaajaNimi), new Vastus("OP AI")));
    }

    public static void poistaPeli(long userId) {
        pelitilat.remove(userId);
    }
}