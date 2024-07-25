package fr.games;

import lombok.Getter;

@Getter
public enum DuelType {

    SHIFOUMI("§aPierre, Feuille, Ciseau");
    final String name;
    DuelType(final String name) {
        this.name = name;
    }

}