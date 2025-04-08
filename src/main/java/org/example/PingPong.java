package org.example;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class PingPong implements Komento {
    @Override
    public String getNimi(){
        return "§ping";
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event){
        long aloitusAika = System.currentTimeMillis();
        return event.getMessage()
                .getChannel()
                .flatMap(channel -> channel.createMessage("pong..."))
                .flatMap(lahetetty -> {
                    long lopetusAika = System.currentTimeMillis();
                    long latenssi = lopetusAika - aloitusAika;
                    return lahetetty.edit(msgEdit ->
                            msgEdit.setContent("Pongista! Pingi on: " + latenssi + "ms"));
                })
                .then();
    }
}
