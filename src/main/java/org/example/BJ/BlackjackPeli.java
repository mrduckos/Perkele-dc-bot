package org.example.BJ;

public class BlackjackPeli {
    private final Deck deck;
    private final Pelaaja pelaaja;
    private final Dealer dealer;

    public BlackjackPeli() {
        deck = new Deck();
        pelaaja = new Pelaaja();
        dealer = new Dealer();

        // Jaetaan alkuun kaksi korttia
        pelaaja.lisaaKortti(deck.nosta());
        pelaaja.lisaaKortti(deck.nosta());
        dealer.lisaaKortti(deck.nosta());
        dealer.lisaaKortti(deck.nosta());
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public String tarkistaTulos() {
        int pelaajanPisteet = pelaaja.laskePisteet();
        int dealerinPisteet = dealer.laskePisteet();

        if (pelaajanPisteet > 21) return "Hävisit! Ylitit 21.";
        if (dealerinPisteet > 21) return "Voitit! Jakaja ylitti 21.";
        if (pelaajanPisteet > dealerinPisteet) return "Voitit!";
        if (pelaajanPisteet < dealerinPisteet) return "Hävisit!";
        return "Tasapeli!";
    }
}