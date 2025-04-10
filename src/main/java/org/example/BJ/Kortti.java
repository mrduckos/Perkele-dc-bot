package org.example.BJ;

public class Kortti {
    private final String maa;
    private final String arvo;

    public Kortti(String maa, String arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }

    public String getArvo() {
        return arvo;
    }

    public int getPisteet() {
        switch (arvo) {
            case "A": return 11;
            case "K":
            case "Q":
            case "J": return 10;
            default: return Integer.parseInt(arvo);
        }
    }

    @Override
    public String toString() {
        return arvo + " " + maa;
    }
}