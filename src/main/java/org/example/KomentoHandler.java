package org.example;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.example.BJ.BlackjackKomento;
import org.example.RPS.RPSKomento;

import java.util.HashMap;
import java.util.Map;

public class KomentoHandler {
    private final Map<String, Komento> komennot = new HashMap<>();

    public KomentoHandler(GatewayDiscordClient client) {
        registerKomento(new PingPong());
        registerKomento(new Minecraft());
        registerKomento(new McInfo());
        registerKomento(new BlackjackKomento());
        registerKomento(new RPSKomento());
    }

    private void registerKomento(Komento komento) {
        komennot.put(komento.getNimi().toLowerCase(), komento);
    }

    public void handle(MessageCreateEvent event) {
        String sisalto = event.getMessage().getContent().toLowerCase();
        Komento komento = komennot.get(sisalto);
        if (komento != null) {
            komento.execute(event).subscribe();
        }

    }
}
