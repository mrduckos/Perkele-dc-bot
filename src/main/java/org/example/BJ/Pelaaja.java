package org.example.BJ;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {
    protected final List<Kortti> kasi = new ArrayList<>();

    public void lisaaKortti(Kortti kortti) {
        kasi.add(kortti);
    }

    public int laskePisteet() {
        int summa = 0;
        int aCount = 0;

        for (Kortti k : kasi) {
            summa += k.getPisteet();
            if (k.getArvo().equals("A")) aCount++;
        }

        while (summa > 21 && aCount > 0) {
            summa -= 10;
            aCount--;
        }

        return summa;
    }

    public List<Kortti> getKasi() {
        return kasi;
    }

    public String kasiMerkkijonona() {
        return String.join(", ", kasi.stream().map(Kortti::toString).toList());
    }
}