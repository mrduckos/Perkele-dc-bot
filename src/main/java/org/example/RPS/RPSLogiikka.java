package org.example.RPS;

public class RPSLogiikka {
    private final Pelaaja pelaaja;
    private final Vastus vastus;

    public RPSLogiikka(Pelaaja pelaaja, Vastus vastus) {
        this.pelaaja = pelaaja;
        this.vastus = vastus;
    }

    public String voittoTarkastus(String valinta) {
        String vastusValinta = vastus.getValinta();
        String tulos;

        if (valinta.equals(vastusValinta)) {
            tulos = "tasapelili!";
        } else if (voittaako(valinta, vastusValinta)) {
            pelaaja.lisaaVoitto();
            tulos = pelaaja.getNimi() + " voitti! " + pelaaja.getNimi() + " valitsi: " + valinta + " vastus valitsi: " + vastusValinta;
        } else {
            vastus.lisaaVoitto();
            tulos = "Vastus voitti! " + pelaaja.getNimi() + " valitsi: " + valinta + " vastus valitsi: " + vastusValinta;
        }
        return tulos + "\n" + pelaaja.getNimi() + ": " + pelaaja.getVoitot() +
                " voittoa\n" + vastus.getNimi() + ": " + vastus.getVoitot() + " voittoa";

    }

    public boolean voittaako(String pelaajaValinta, String vastusValinta) {
        if(
                pelaajaValinta.equals("kivi") && vastusValinta.equals("sakset")
                || pelaajaValinta.equals("paperi") && vastusValinta.equals("kivi")
                || pelaajaValinta.equals("sakset") && vastusValinta.equals("paperi")) {
            return true;
        } else {
            return false;
        }
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public Vastus getVastus() {
        return vastus;
    }
}
