package org.example.BJ;

public class Dealer extends Pelaaja {

    public void pelaa(Deck deck) {
        while (laskePisteet() < 17) {
            lisaaKortti(deck.nosta());
        }
    }
}