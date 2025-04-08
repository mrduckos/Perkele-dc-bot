package org.example;

import java.io.*;

public class LaskuriTallennus {
    private static final String TIEDOSTO_NIMI = "mc_laskurit.dat";

    public static void tallenna(LaskuriData data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TIEDOSTO_NIMI))) {
            oos.writeObject(data);
            System.out.println("Tallennettu!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LaskuriData lataa() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TIEDOSTO_NIMI))) {
            return (LaskuriData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ei löytynyt tallennettua dataa, luodaan uusi.");
            return new LaskuriData(0, 0);
        }
    }
}