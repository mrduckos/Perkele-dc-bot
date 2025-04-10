package org.example.BJ;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import reactor.core.publisher.Mono;

public class BlackjackButtonHandler {

    public static Mono<Void> handle(ButtonInteractionEvent event) {
        String customId = event.getCustomId(); // esim. "hit_123456"
        String userId = event.getInteraction().getUser().getId().asString();

        if (!customId.endsWith(userId)) {
            return event.reply("Tämä peli ei ole sinun!").withEphemeral(true);
        }

        BlackjackPeli peli = BlackjackKomento.pelit.get(userId);
        if (peli == null) {
            return event.reply("Peliä ei löytynyt. Käynnistä komennolla §bj.");
        }

        if (customId.startsWith("hit_")) {
            peli.getPelaaja().lisaaKortti(peli.getDeck().nosta());

            int pisteet = peli.getPelaaja().laskePisteet();
            if (pisteet > 21) {
                peli.getDealer().pelaa(peli.getDeck());
                String dealerKasi = peli.getDealer().kasiMerkkijonona();
                String tulos = peli.tarkistaTulos();

                return event.edit()
                        .withContent("HÄVISIT! Sinun kätesi: " + peli.getPelaaja().kasiMerkkijonona() +
                                " (" + pisteet + " pistettä)\n" +
                                "Jakajan käsi: " + dealerKasi + " (" + peli.getDealer().laskePisteet() + ")\n" +
                                tulos)
                        .withComponents();
            }

            return event.edit()
                    .withContent("Sinun kätesi: " + peli.getPelaaja().kasiMerkkijonona() +
                            " (" + pisteet + " pistettä)\n" +
                            "Jakajan näkyvä kortti: " + peli.getDealer().getKasi().get(0) + "\n" +
                            "Valitse toiminto:")
                    .withComponents(ActionRow.of(
                            Button.primary("hit_" + userId, "🔼 Ota kortti"),
                            Button.secondary("stand_" + userId, "✋ Jää")
                    ));
        }

        if (customId.startsWith("stand_")) {
            peli.getDealer().pelaa(peli.getDeck());

            String pelaajaKasi = peli.getPelaaja().kasiMerkkijonona();
            String dealerKasi = peli.getDealer().kasiMerkkijonona();
            String tulos = peli.tarkistaTulos();

            return event.edit()
                    .withContent("Sinun kätesi: " + pelaajaKasi + " (" + peli.getPelaaja().laskePisteet() + " pistettä)\n" +
                            "Jakajan käsi: " + dealerKasi + " (" + peli.getDealer().laskePisteet() + ")\n" +
                            tulos)
                    .withComponents();
        }

        return Mono.empty();
    }
}