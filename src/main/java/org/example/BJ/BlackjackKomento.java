package org.example.BJ;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import org.example.Komento;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class BlackjackKomento implements Komento {

    // Pelaajat tallennetaan ID:n mukaan
    public static final Map<String, BlackjackPeli> pelit = new HashMap<>();

    @Override
    public String getNimi() {
        return "§bj";
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        String userId = event.getMessage().getAuthor().map(u -> u.getId().asString()).orElse("tuntematon");

        BlackjackPeli peli = new BlackjackPeli();
        pelit.put(userId, peli);

        Pelaaja pelaaja = peli.getPelaaja();
        Dealer dealer = peli.getDealer();

        String pelaajaKasi = pelaaja.kasiMerkkijonona();
        String dealerKortti = dealer.getKasi().get(0).toString();

        return event.getMessage().getChannel()
                .flatMap(channel -> channel.createMessage(msg -> msg
                        .setContent("Sinun kätesi: " + pelaajaKasi + " (" + pelaaja.laskePisteet() + " pistettä)\n" +
                                    "Jakajan näkyvä kortti: " + dealerKortti)
                        .setComponents(ActionRow.of(
                                Button.primary("hit_" + userId, "🔼 Ota kortti"),
                                Button.danger("stand_" + userId, "✋ Jää")
                        ))
                )).then();
    }
}