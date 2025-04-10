package org.example.RPS;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.util.Color;
import org.example.Komento;
import reactor.core.publisher.Mono;

import java.time.Instant;

public class RPSKomento implements Komento {

    @Override
    public String getNimi() {
        return "§RPS";
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        String nimi = event.getMessage().getAuthor().get().getUsername();
        Pelaaja pelaaja = new Pelaaja(nimi);

        Vastus vastus = new Vastus("OP AI");
        RPSLogiikka peli = new RPSLogiikka(pelaaja, vastus);

        String avatarURL = event.getMessage().getAuthor().get().getAvatarUrl();

        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.DEEP_LILAC)
                .author(nimi, null, avatarURL)
                .description(nimi + ", valitse kivi, paperi tai sakset nappeja painamalla!")
                .timestamp(Instant.now())
                .build();

        MessageCreateSpec messageSpec = MessageCreateSpec.builder()
                .addEmbed(embed)
                .addComponent(ActionRow.of(
                        Button.primary("kivi_", "⛰️ Kivi"),
                        Button.primary("paperi", "📄 Paperi"),
                        Button.primary("sakset_", "✂️ Sakset")
                ))
                .build();

        return event.getMessage().getChannel()
                .flatMap(channel -> channel.createMessage(messageSpec))
                .then();
    }
}