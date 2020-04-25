package de.midorlo.relentless.controller.exception;

public class PerkNotFoundException extends RuntimeException {

    public PerkNotFoundException(String name) {
        super("Perk not found:" + name);
    }
}
