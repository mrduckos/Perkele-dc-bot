package org.example;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.time.Instant;

public class McInfo implements Komento {
    @Override
    public String getNimi() {
        return "§mcinfo";
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        LaskuriData data = LaskuriTallennus.lataa();
        return event.getMessage().getAuthor()
                .map(u -> {
                    String nimi = u.getUsername();
                    String avatarURL = u.getAvatarUrl();

                    EmbedCreateSpec embed = EmbedCreateSpec.builder()
                            .color(Color.MAGENTA)
                            .author(nimi, null, avatarURL)
                            .addField("🧟‍♂️ Zombeja nähty: ", String.valueOf(data.zombit), false)
                            .addField("🐔 Kanoja nähty: ", String.valueOf(data.kanat), false)
                            .timestamp(Instant.now())
                            .build();

                    return event.getMessage().getChannel()
                            .flatMap(c -> c.createMessage(embed))
                            .then();
                })
                .orElse(Mono.empty());
    }
}
