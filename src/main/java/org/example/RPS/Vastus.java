package org.example.RPS;

import java.util.Random;

public class Vastus extends Pelaaja {
    String[] valinnat = {"Kivi", "Paperi", "Sakset"};

    public Vastus(String nimi) {
        super(nimi);
    }

    @Override
    public String getValinta() {
        Random random = new Random();
        int randomValinta = random.nextInt(valinnat.length);

        if (randomValinta == 1) {
            return valinnat[0];
        } else if (randomValinta == 2) {
            return valinnat[1];
        } else {
            return valinnat[2];
        }
    }


}
