package org.example;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public interface Komento {
    String getNimi();
    Mono<Void> execute(MessageCreateEvent event);
}
