package org.example.BJ;

public class Dealer extends Pelaaja {

    public Dealer(String nimi) {
        super(nimi);
    }

    public void pelaa(Deck deck) {
        while (laskePisteet() < 17) {
            lisaaKortti(deck.nosta());
        }
    }
}