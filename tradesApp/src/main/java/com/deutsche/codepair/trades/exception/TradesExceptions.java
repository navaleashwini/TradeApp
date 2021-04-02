package com.deutsche.codepair.trades.exception;

public class TradesExceptions extends RuntimeException {

    private final String id;

    public TradesExceptions(final String id) {
        super("Invalid Trade: " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
