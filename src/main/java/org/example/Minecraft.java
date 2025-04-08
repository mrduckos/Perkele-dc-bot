package org.example;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

import java.util.Random;

public class Minecraft implements Komento {
    private String zomb = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\n" +
            "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\n" +
            "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\n" +
            "\uD83D\uDFE9⬛⬛\uD83D\uDFE9\uD83D\uDFE9⬛⬛\uD83D\uDFE9\n" +
            "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9⬛⬛\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\n" +
            "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\n" +
            "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9";

    private String kana = "⬜⬜⬜⬜⬜⬜⬜⬜\n" +
            "⬜⬜⬛⬜⬜⬛⬜⬜\n" +
            "⬜⬜\uD83D\uDFE8\uD83D\uDFE8\uD83D\uDFE8\uD83D\uDFE8⬜⬜\n" +
            "⬜⬜\uD83D\uDFE8\uD83D\uDFE8\uD83D\uDFE8\uD83D\uDFE8⬜⬜\n" +
            "⬜⬜⬜\uD83D\uDFE5\uD83D\uDFE5⬜⬜⬜\n" +
            "⬜⬜⬜\uD83D\uDFE5\uD83D\uDFE5⬜⬜⬜\n";

    private String kumpi;

    public int zombLaskuri;
    public int kanaLaskuri;

    @Override
    public String getNimi() {
        return "§minecraft";
    }

    public void getRandom() {
        Random random = new Random();
        int randomNum = random.nextInt(2);
        if (randomNum == 1){
            kumpi = zomb;
        } else {
            kumpi = kana;
        }
    }
    public void laskuri() {
        if (kumpi.equals(zomb)) {
            zombLaskuri++;
        } else if (kumpi.equals(kana)){
            kanaLaskuri++;
        }
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event){
        getRandom();
        laskuri();
        System.out.println("zombit: " + zombLaskuri);
        System.out.println("kanat: " + kanaLaskuri);
        LaskuriTallennus.tallenna(new LaskuriData(zombLaskuri, kanaLaskuri));
        return event.getMessage()
                .getChannel()
                .flatMap(channel -> channel.createMessage(kumpi))
                .then();
    }
}
