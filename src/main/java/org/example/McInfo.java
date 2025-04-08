package org.example;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class McInfo implements Komento {
    @Override
    public String getNimi() {
        return "§mcinfo";
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        LaskuriData data = LaskuriTallennus.lataa();
        String viesti = "🧟‍♂️ Zombeja nähty: " + data.zombit + "\n" +
                "🐔 Kanoja nähty: " + data.kanat;

        return event.getMessage().getChannel()
                .flatMap(c -> c.createMessage(viesti))
                .then();
    }
}
