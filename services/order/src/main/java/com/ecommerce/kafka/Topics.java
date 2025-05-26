package com.ecommerce.kafka;

public enum Topics {
    ORDER("order-topic");

    public final String label;

    private Topics(String label) {
        this.label = label;
    }
}
