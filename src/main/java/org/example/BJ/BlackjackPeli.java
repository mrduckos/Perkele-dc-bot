package org.example.BJ;

public class BlackjackPeli {
    private final Deck deck;
    private final Pelaaja pelaaja;
    private final Dealer dealer;

    public BlackjackPeli(Pelaaja pelaaja, Dealer dealer) {
        this.pelaaja = pelaaja;
        this.dealer = dealer;
        this.deck = new Deck();

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

        if (pelaajanPisteet > 21) return pelaaja.getNimi() + " Hävisit pelin! Ylitit 21.";
        if (dealerinPisteet > 21) return pelaaja.getNimi() + " Voitit pelin! Jakaja ylitti 21.";
        if (pelaajanPisteet > dealerinPisteet) return pelaaja.getNimi() + " Voitit pelin!";
        if (pelaajanPisteet < dealerinPisteet) return pelaaja.getNimi() + " Hävisit pelin!";
        return "Tasapeli!";
    }
}