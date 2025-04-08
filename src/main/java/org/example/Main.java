package org.example;


import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        String token = Config.haeToken();

        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        KomentoHandler komentoHandler = new KomentoHandler(client);

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(e -> {
                    if (e.getMessage().getContent().startsWith("§")) {
                        komentoHandler.handle(e);
                    }
                });
        client.onDisconnect().block();
    }
}
