package org.example.RPS;

public class Pelaaja {
    private final String nimi;
    private String valinta;
    private int voitot;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.valinta = "";
        this.voitot = 0;
    }

    public String getNimi() {
        return nimi;
    }

    public String getValinta() {
        return valinta;
    }

    public void setValinta(String valinta) {
        this.valinta = valinta;
    }

    public int getVoitot() {
        return voitot;
    }

    public void lisaaVoitto() {
        voitot++;
    }
}
