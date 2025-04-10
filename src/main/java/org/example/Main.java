package org.example;


import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.example.BJ.BlackjackButtonHandler;

public class Main {

    public static void main(String[] args) {
        String token = Config.haeToken();

        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        KomentoHandler komentoHandler = new KomentoHandler(client);

        client.on(ButtonInteractionEvent.class, BlackjackButtonHandler::handle).subscribe();

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(e -> {
                    if (e.getMessage().getContent().startsWith("§")) {
                        komentoHandler.handle(e);
                    }
                });
        client.onDisconnect().block();
    }
}
