package org.example;

import java.io.Serializable;

public class LaskuriData implements Serializable {

    public int zombit;
    public int kanat;

    public LaskuriData(int zombit, int kanat) {
        this.zombit = zombit;
        this.kanat = kanat;
    }
}